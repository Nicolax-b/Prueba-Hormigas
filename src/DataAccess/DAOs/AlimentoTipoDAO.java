package DataAccess.DAOs;

import DataAccess.DTOs.AlimentoTipoDTO;
import DataAccess.Helpers.DataHelperSQLiteDAO;
import Infrastructure.AppException;
import java.sql.*;

public class AlimentoTipoDAO extends DataHelperSQLiteDAO<AlimentoTipoDTO> {

    public AlimentoTipoDAO() throws AppException {
        super(AlimentoTipoDTO.class, "AlimentoTipo", "IdAlimentoTipo");
    }

    public Integer getIdByNombre(String nombre) throws AppException {
        String sql = "SELECT IdAlimentoTipo FROM AlimentoTipo WHERE Nombre = ? AND Estado = 'A'";
        try (PreparedStatement ps = openConnection().prepareStatement(sql)) {
            ps.setString(1, nombre);
            ResultSet rs = ps.executeQuery();
            return rs.next() ? rs.getInt("IdAlimentoTipo") : null;
        } catch (SQLException e) {
            throw new AppException("Error getIdByNombre AlimentoTipo", e, getClass(), "getIdByNombre");
        }
    }
}

