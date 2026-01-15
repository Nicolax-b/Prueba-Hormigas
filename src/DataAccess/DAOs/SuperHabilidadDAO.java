package DataAccess.DAOs;

import DataAccess.DTOs.SuperHabilidadDTO;
import DataAccess.Helpers.DataHelperSQLiteDAO;
import Infrastructure.AppException;

public class SuperHabilidadDAO  extends DataHelperSQLiteDAO<SuperHabilidadDTO>{
    public SuperHabilidadDAO() throws AppException {
        super(SuperHabilidadDTO.class, "SuperHabilidad", "IdSuperHabilidad");
    }
    
}
