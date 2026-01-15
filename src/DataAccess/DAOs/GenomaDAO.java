package DataAccess.DAOs;

import DataAccess.DTOs.GenomaDTO;
import DataAccess.Helpers.DataHelperSQLiteDAO;
import Infrastructure.AppException;

public class GenomaDAO extends DataHelperSQLiteDAO<GenomaDTO>{
    public GenomaDAO() throws AppException {
        super(GenomaDTO.class, "Genoma", "IdGenoma");
    }
    public Integer getIdByNombre(String nombre) throws AppException { ... } // igual patrón

}
