package BusinessLogic.Entities;

public class X extends GenoAlimento {
    public X() { this.tipo = "X"; }

    @Override
    public boolean inyectar(BNHormiga hormiga) {
        System.out.println("\t[GENOMA] Inyectando X -> Sin cambios visibles.");
        return true;
    }
}