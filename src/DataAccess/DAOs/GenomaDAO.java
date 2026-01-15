package DataAccess.DAOs;

import DataAccess.DTOs.GenomaDTO;
import DataAccess.Helpers.DataHelperSQLiteDAO;
import Infrastructure.AppException;
import java.sql.*;

public class GenomaDAO extends DataHelperSQLiteDAO<GenomaDTO> {

    public GenomaDAO() throws AppException {
        super(GenomaDTO.class, "Genoma", "IdGenoma");
    }

    public Integer getIdByNombre(String nombre) throws AppException {
        String sql = "SELECT IdGenoma FROM Genoma WHERE Nombre = ? AND Estado = 'A'";
        try (PreparedStatement ps = openConnection().prepareStatement(sql)) {
            ps.setString(1, nombre);
            ResultSet rs = ps.executeQuery();
            return rs.next() ? rs.getInt("IdGenoma") : null;
        } catch (SQLException e) {
            throw new AppException("Error getIdByNombre Genoma", e, getClass(), "getIdByNombre");
        }
    }
}
