package DataAccess.DAOs;

import DataAccess.DTOs.HormigaAlimentoDTO;
import DataAccess.Helpers.DataHelperSQLiteDAO;
import Infrastructure.AppException;

public class HormigaAlimentoDAO extends DataHelperSQLiteDAO<HormigaAlimentoDTO> {
    public HormigaAlimentoDAO() throws AppException {
        super(HormigaAlimentoDTO.class, "HormigaAlimento", "IdHormigaAlimento");
    }
}

