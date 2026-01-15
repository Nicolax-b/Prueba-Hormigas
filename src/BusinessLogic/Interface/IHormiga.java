package BusinessLogic.Interface;

import BusinessLogic.Entities.Alimento;
import BusinessLogic.Entities.IngestaNativa;

public interface IHormiga {
    public void comer(IngestaNativa ingestaNativa);
    public void comer (Alimento comida);
}
