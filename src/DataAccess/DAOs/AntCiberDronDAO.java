package DataAccess.DAOs;

import DataAccess.DTOs.AntCiberDronDTO;
import DataAccess.Helpers.DataHelperSQLiteDAO;
import Infrastructure.AppException;

public class AntCiberDronDAO  extends DataHelperSQLiteDAO<AntCiberDronDTO>{
    public AntCiberDronDAO() throws AppException {
        super(AntCiberDronDTO.class, "AntCiberDron", "IdAntCiberDron");
    }
    
}
