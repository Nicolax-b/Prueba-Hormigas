package DataAccess.DAOs;

import DataAccess.DTOs.HormigaSuperHabilidadDTO;
import DataAccess.Helpers.DataHelperSQLiteDAO;
import Infrastructure.AppException;

public class HormigaSuperHabilidadDAO extends DataHelperSQLiteDAO<HormigaSuperHabilidadDTO> {
    public HormigaSuperHabilidadDAO() throws AppException {
        super(HormigaSuperHabilidadDTO.class, "HormigaSuperHabilidad", "IdHormigaSuperHabilidad");
    }
}

