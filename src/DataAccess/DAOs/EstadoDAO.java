package DataAccess.DAOs;

import java.sql.*;
import DataAccess.Helpers.DataHelperSQLiteDAO;
import DataAccess.DTOs.EstadoDTO;
import Infrastructure.AppException;

public class EstadoDAO extends DataHelperSQLiteDAO<EstadoDTO> {

    public EstadoDAO() throws AppException {
        super(EstadoDTO.class, "Estado", "IdEstado");
    }

    public Integer getIdByNombre(String nombre) throws AppException {
        String sql = "SELECT IdEstado FROM Estado WHERE Nombre = ? AND Estado = 'A'";
        try (PreparedStatement ps = openConnection().prepareStatement(sql)) {
            ps.setString(1, nombre);
            ResultSet rs = ps.executeQuery();
            return rs.next() ? rs.getInt("IdEstado") : null;
        } catch (SQLException e) {
            throw new AppException("Error getIdByNombre Estado", e, getClass(), "getIdByNombre");
        }
    }
}

