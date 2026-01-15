//  © 2K26 ❱──💀──❰ pat_mic ? code is life : life is code
package DataAccess.Helpers;

import DataAccess.Interfaces.IDAO;
import Infrastructure.AppConfig;
import Infrastructure.AppException;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.sql.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class DataHelperSQLiteDAO <T> implements IDAO<T> { 
    protected final Class<T>DTOClass;
    protected final String  tableName;
    protected final String  tablePK;    

    private static final String DBPath = AppConfig.DATABASE; 
    private static Connection conn = null;

    protected static synchronized Connection openConnection() throws SQLException {
        if (conn == null || conn.isClosed()) {
            conn = DriverManager.getConnection(DBPath);
        }
        return conn;
    }

    protected static void closeConnection() throws SQLException {
        if (conn != null && !conn.isClosed()) {
            conn.close();
        }
    }

    protected String getDataTimeNow() {
        return LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
    }

    public DataHelperSQLiteDAO(Class<T> dtoClass, String tableName, String tablePK) throws AppException {
        try {
            openConnection();
        } catch (SQLException e) {
            throw new AppException(null, e, getClass(), "DataHelperSQLiteDAO");
        }
        this.DTOClass  = dtoClass;
        this.tableName = tableName;
        this.tablePK   = tablePK;
    }

    @Override
    public boolean create(T entity) throws AppException {
        Field[] fields = DTOClass.getDeclaredFields();
        StringBuilder columns = new StringBuilder();
        StringBuilder placeholders = new StringBuilder();

        // 1. Construir columnas dinámicamente
        for (Field field : fields) {
            field.setAccessible(true);
            String name = field.getName();
            // Excluimos PK y campos de auditoría automáticos de la reflexión
            if (!name.equalsIgnoreCase(tablePK)
                && !name.equalsIgnoreCase("Estado")
                && !name.equalsIgnoreCase("FechaCreacion")
                && !name.equalsIgnoreCase("FechaModifica")) {
                columns.append(name).append(",");
                placeholders.append("?,");
            }
        }

        // 2. Agregar manualmente FechaCreacion para evitar error NOT NULL
        columns.append("FechaCreacion");
        placeholders.append("?");

        String sql = String.format("INSERT INTO %s (%s) VALUES (%s)", tableName, columns, placeholders);

        try (PreparedStatement stmt = openConnection().prepareStatement(sql)) {
            int index = 1;
            // Setear valores de los campos de la clase
            for (Field field : fields) {
                String name = field.getName();
                if (!name.equalsIgnoreCase(tablePK)
                    && !name.equalsIgnoreCase("Estado")
                    && !name.equalsIgnoreCase("FechaCreacion")
                    && !name.equalsIgnoreCase("FechaModifica")) 
                        stmt.setObject(index++, field.get(entity));
            }
            
            // Setear la fecha actual
            stmt.setString(index++, getDataTimeNow());

            return (stmt.executeUpdate() > 0);
        } catch (SQLException | IllegalAccessException e) {
            throw new AppException(null, e, getClass(), "create");
        }
    }

    @Override
    public boolean update(T entity) throws AppException {
        try {
            Field[] fields = DTOClass.getDeclaredFields();
            StringBuilder updates = new StringBuilder();
            Object pkValue = null;

            for (Field field : fields) {
                String name = field.getName();

                if (!name.equalsIgnoreCase(tablePK)) {
                    updates.append(name).append(" = ?, ");
                } else {
                    if (!field.canAccess(entity)) {
                        field.setAccessible(true);
                    }
                    pkValue = field.get(entity);
                }
            }

            updates.append("FechaModifica = ?"); 

            String sql = String.format("UPDATE %s SET %s WHERE %s = ?", tableName, updates, tablePK);

            try (PreparedStatement stmt = openConnection().prepareStatement(sql)) {
                int index = 1;
                for (Field field : fields) {
                    String name = field.getName();
                    if (!name.equalsIgnoreCase(tablePK)) {
                        if (!field.canAccess(entity)) {
                            field.setAccessible(true);
                        }
                        stmt.setObject(index++, field.get(entity));
                    }
                }

                stmt.setString(index++, getDataTimeNow()); 
                stmt.setObject(index, pkValue); 

                return stmt.executeUpdate() > 0;
            }

        }   catch (SQLException | IllegalAccessException e) {
            throw new AppException(null, e, getClass(), "update");
        }
    }

    @Override
    public boolean delete(Integer id) throws AppException {
        String sql = String.format("UPDATE %s SET Estado = ?, FechaModifica = ? WHERE %s = ?", tableName, tablePK);
        try (PreparedStatement stmt = openConnection().prepareStatement(sql)) {
            stmt.setString(1, "X");
            stmt.setString(2, getDataTimeNow());
            stmt.setInt   (3, id);
            return stmt.executeUpdate() > 0;
        }catch (SQLException e) {
            throw new AppException(null, e, getClass(), "delete");
        }
    }

    @Override
    public T readBy(Integer id) throws AppException {
        String sql = String.format("SELECT * FROM %s WHERE %s = ? AND Estado = 'A'", tableName, tablePK);
        try (PreparedStatement stmt = openConnection().prepareStatement(sql)) {
            stmt.setInt(1, id);
            try (ResultSet rs = stmt.executeQuery()) {
                return rs.next() ? mapResultSetToEntity(rs) : null;
            }
        }catch (SQLException e) {
            throw new AppException(null, e, getClass(), "readBy");
        }
    }

    @Override
    public List<T> readAll() throws AppException {
        List<T> list = new ArrayList<>();
        String sql = String.format("SELECT * FROM %s WHERE Estado = 'A'", tableName);
        try (PreparedStatement stmt = openConnection().prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                list.add(mapResultSetToEntity(rs));
            }
        } catch (SQLException e) {
            throw new AppException(null, e, getClass(), "readAll");
        }
        return list;
    }

    @Override
    public Integer getMaxReg() throws AppException {
        String sql = String.format("SELECT COUNT(*) FROM %s WHERE Estado = 'A'", tableName);
        try (PreparedStatement stmt = openConnection().prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {
            return rs.next() ? rs.getInt(1) : 0;
        } catch (SQLException e) {
            throw new AppException(null, e, getClass(), "getMaxReg");
        }
    }

    protected T mapResultSetToEntity(ResultSet rs) throws AppException {
        try {
            T instance = DTOClass.getDeclaredConstructor().newInstance();
            ResultSetMetaData meta = rs.getMetaData();

            for (int i = 1; i <= meta.getColumnCount(); i++) {
                String col = meta.getColumnLabel(i); 
                Object val = rs.getObject(i);

                Field field = DTOClass.getDeclaredField(col);
                if (!field.canAccess(instance)) {
                    field.setAccessible(true);
                }
                field.set(instance, val);
            }
            return instance;
        } catch (SQLException | NoSuchMethodException | InvocationTargetException | InstantiationException | IllegalAccessException | NoSuchFieldException e) {
            throw new AppException(null, e, getClass(), "mapResultSetToEntity");
        }
    }
}