package BusinessLogic.Entities;

public abstract class Alimento {
    private GenoAlimento genoAlimento; 
    public Alimento(String tipo) {
        this.tipo = tipo;
    }

    public void setGenoAlimento(GenoAlimento genoma) {
        this.genoAlimento = genoma;
    }
    
    public GenoAlimento getGenoAlimento() {
        return genoAlimento;
    }
    
    @Override
    public boolean inyectar(Hormiga hormiga) {
        if (genoAlimento != null) {
            return genoAlimento.inyectar(hormiga);
        }
        return false;
    }
}
