package DataAccess.DAOs;

import java.sql.*;
import DataAccess.DTOs.AlimentoDTO;
import DataAccess.Helpers.DataHelperSQLiteDAO;
import Infrastructure.AppException;

public class AlimentoDAO extends DataHelperSQLiteDAO<AlimentoDTO> {
    public AlimentoDAO() throws AppException {
        super(AlimentoDTO.class, "Alimento", "IdAlimento");
    }

    public Integer getIdByNombre(String nombre) throws AppException {
        String sql = "SELECT IdAlimento FROM Alimento WHERE Nombre = ? AND Estado='A'";
        try (PreparedStatement ps = openConnection().prepareStatement(sql)) {
            ps.setString(1, nombre);
            try (ResultSet rs = ps.executeQuery()) {
                return rs.next() ? rs.getInt(1) : null;
            }
        } catch (SQLException e) {
            throw new AppException("Error getIdByNombre Alimento", e, getClass(), "getIdByNombre");
        }
    }
}
