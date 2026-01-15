package BusinessLogic.Entities;

public class XY extends GenoAlimento {
    public XY() { this.tipo = "XY"; }

    @Override
    public boolean inyectar(BNHormiga hormiga) {
        System.out.println("\t[GENOMA] Inyectando XY -> Sin cambios.");
        return true;
    }
}
