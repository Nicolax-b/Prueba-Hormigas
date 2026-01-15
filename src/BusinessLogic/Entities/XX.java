package BusinessLogic.Entities;

public class XX extends GenoAlimento {

    public XX() {
        this.tipo = "XX";
    }

    @Override
    public boolean inyectar(Hormiga hormiga){
        System.out.println("\t(GenoAlimento): Inyectar Genoma XX");
        hormiga.setSexo("Macho");
        hormiga.setTipo("Soldado");

        return true;
    }
    
}
