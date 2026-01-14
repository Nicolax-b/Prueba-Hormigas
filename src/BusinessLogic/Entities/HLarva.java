package BusinessLogic.Entities;

import DataAccess.DTOs.HormigaDTO;
import Infrastructure.AppException;
import java.util.List;

public class HLarva extends Hormiga {

    public HormigaDTO getLarva(int id) throws AppException{
        data = factory.getBy(id);
        return data;
    }
    
    public List<HormigaDTO> getLarvas() throws AppException {
        return factory.getAll();
    }
    
    public Hormiga comer(String comida) {
        if (comida.equals("carne")) {
            return new HSoldado();
        }
        return this;
    }

    @Override
    public String toString() {
        return "HLarva {}";
    }
}
