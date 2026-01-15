package BusinessLogic.Entities;

public class Alimento extends IngestaNativa {
    protected GenoAlimento genoAlimento;

    public Alimento(String tipo) {
        this.tipo = tipo;
    }
    
    public Alimento(){ }

    public void setGenoAlimento(GenoAlimento genoma) {
        this.genoAlimento = genoma;
    }
    
    public GenoAlimento getGenoAlimento() {
        return genoAlimento;
    }
    
    public String getTipoAlimento() {
        return this.tipo;
    }
    
    @Override
    public boolean inyectar(BNHormiga hormiga) {
        if (genoAlimento != null) {
            return genoAlimento.inyectar(hormiga);
        }
        return false; 
    }
}
