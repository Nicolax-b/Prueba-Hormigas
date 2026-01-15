package DataAccess.DAOs;

import DataAccess.DTOs.SuperHabilidadDTO;
import DataAccess.Helpers.DataHelperSQLiteDAO;
import Infrastructure.AppException;
import java.sql.*;

public class SuperHabilidadDAO extends DataHelperSQLiteDAO<SuperHabilidadDTO> {

    public SuperHabilidadDAO() throws AppException {
        super(SuperHabilidadDTO.class, "SuperHabilidad", "IdSuperHabilidad");
    }

    public Integer getIdByNombre(String nombre) throws AppException {
        String sql = "SELECT IdSuperHabilidad FROM SuperHabilidad WHERE Nombre = ? AND Estado = 'A'";
        try (PreparedStatement ps = openConnection().prepareStatement(sql)) {
            ps.setString(1, nombre);
            ResultSet rs = ps.executeQuery();
            return rs.next() ? rs.getInt("IdSuperHabilidad") : null;
        } catch (SQLException e) {
            throw new AppException("Error getIdByNombre SuperHabilidad", e, getClass(), "getIdByNombre");
        }
    }
}

