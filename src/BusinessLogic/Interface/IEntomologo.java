package BusinessLogic.Interface;

import BusinessLogic.Entities.Alimento;
import BusinessLogic.Entities.BNHormiga;
import java.util.List;

public interface IEntomologo {
    List<BNHormiga> etlAntNest(String pathFile);
    List<Alimento>  etlAntFood(String pathFile);

    Alimento  preparar(Alimento alimento);
    BNHormiga alimentarAnt(BNHormiga hormiga, Alimento alimento);
}
