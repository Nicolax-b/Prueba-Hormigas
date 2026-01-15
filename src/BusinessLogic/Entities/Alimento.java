package BusinessLogic.Entities;

public class Alimento extends IngestaNativa {
    protected GenoAlimento genoAlimento;

    public Alimento(String tipo) {
        this.tipo = tipo;
    }
    
    // Constructor vacío necesario
    public Alimento(){ }

    // --- ESTOS SON LOS MÉTODOS QUE FALTABAN EN APP.JAVA ---
    public void setGenoAlimento(GenoAlimento genoma) {
        this.genoAlimento = genoma;
    }
    
    public GenoAlimento getGenoAlimento() {
        return genoAlimento;
    }
    
    @Override
    public boolean inyectar(BNHormiga hormiga) {
        if (genoAlimento != null) {
            return genoAlimento.inyectar(hormiga);
        }
        return false; 
    }
}
