package BusinessLogic.Entities;

import BusinessLogic.FactoryBL;
import DataAccess.DAOs.HormigaDAO;
import DataAccess.DTOs.HormigaDTO;

public abstract class Hormiga {
    protected FactoryBL<HormigaDTO> factory = new FactoryBL<>(HormigaDAO.class); //Para hacerle pasar los datos a la base
    public HormigaDTO data = new HormigaDTO(); //Para pasarle los datos 


    // protected HormigaDAO hormigaDAO;
    // protected Hormiga() throws AppException  {
    //     this.hormigaDAO = new HormigaDAO();
    // }

    // public FactoryBL<HormigaDTO> factory = new FactoryBL<>(() -> {
    //     try {
    //         return new HormigaDAO();
    //     } catch (Exception e) {
    //         new RuntimeExceptionp();
    //     }
    // });
}
