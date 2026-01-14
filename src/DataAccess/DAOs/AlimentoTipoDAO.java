package DataAccess.DAOs;

import DataAccess.DTOs.AlimentoTipoDTO;
import DataAccess.Helpers.DataHelperSQLiteDAO;
import Infrastructure.AppException;

public class AlimentoTipoDAO extends DataHelperSQLiteDAO<AlimentoTipoDTO>{
    public AlimentoTipoDAO() throws AppException {
        super(AlimentoTipoDTO.class, "AlimentoTipo", "IdAlimentoTipo");
    }
}
