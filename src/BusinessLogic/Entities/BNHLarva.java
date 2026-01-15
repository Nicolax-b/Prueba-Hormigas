package BusinessLogic.Entities;

public class BNHLarva extends BNHormiga {

    public BNHLarva() {
        super();
        this.bnTipo = "Larva";
        this.bnSexo = "Asexual"; 
    }

    @Override
    public void comer(IngestaNativa alimento) {
        System.out.println("La Larva está comiendo " + alimento.toString());

        if (alimento.toString().equals("Carnivoro") || alimento instanceof Carnivoro) {
            System.out.println("¡La Larva ha comido CARNE! Evolucionando...");
            evolucionarASoldado(alimento);
        }

        if (!alimento.toString().equals("Carnivoro")|| alimento.toString().equals("Nectivor")){
            System.out.println("La Larva esta muriendo");
        }

    }

    private void evolucionarASoldado(IngestaNativa alimento) {
        this.bnTipo = "Soldado";
        this.bnSexo = "Macho"; 

        if (alimento instanceof Alimento) {
            Alimento comida = (Alimento) alimento;
            GenoAlimento genoma = comida.getGenoAlimento(); 
            
            if (genoma != null && genoma.toString().equals("XX")) {
                System.out.println( "¡GENOMA XX DETECTADO! -> SuperFuerza adquirida");
            }else{}
        }else{}

        this.guardar();
    }
}