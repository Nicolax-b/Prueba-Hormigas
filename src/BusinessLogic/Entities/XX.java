package BusinessLogic.Entities;

public class XX extends GenoAlimento {

    public XX() {
        this.tipo = "XX";
    }

    @Override
    public boolean inyectar(BNHormiga hormiga) {
        System.out.println("\t(GenoAlimento) Inyectando Genoma XX -> Sexo: MACHO");
        hormiga.setSexo("Macho");

        if (hormiga instanceof BNHSoldado) {
            BNHSoldado soldado = (BNHSoldado) hormiga;
            soldado.setSuperHabilidad("SuperSalto");
            System.out.println("\t¡Mutación Genética! Habilidad adquirida: " + soldado.getSuperHabilidad());
        }
        
        return true; 
    }
}
