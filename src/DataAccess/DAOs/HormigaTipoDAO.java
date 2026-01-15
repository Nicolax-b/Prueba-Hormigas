package DataAccess.DAOs;

import java.sql.*;
import DataAccess.Helpers.DataHelperSQLiteDAO;
import DataAccess.DTOs.HormigaTipoDTO;
import Infrastructure.AppException;

public class HormigaTipoDAO extends DataHelperSQLiteDAO<HormigaTipoDTO> {

    public HormigaTipoDAO() throws AppException {
        super(HormigaTipoDTO.class, "HormigaTipo", "IdHormigaTipo");
    }

    public Integer getIdByNombre(String nombre) throws AppException {
        String sql = "SELECT IdHormigaTipo FROM HormigaTipo WHERE Nombre = ? AND Estado = 'A'";
        try (PreparedStatement ps = openConnection().prepareStatement(sql)) {
            ps.setString(1, nombre);
            ResultSet rs = ps.executeQuery();
            return rs.next() ? rs.getInt("IdHormigaTipo") : null;
        } catch (SQLException e) {
            throw new AppException("Error getIdByNombre HormigaTipo", e, getClass(), "getIdByNombre");
        }
    }
}
