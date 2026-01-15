package BusinessLogic.Entities;

public class BNHLarva extends BNHormiga {

    public BNHLarva() {
        super();
        this.bnTipo = "Larva";
        this.bnSexo = "Asexual"; 
    }

    // --- ESTE ES EL MÉTODO OBLIGATORIO DE LA INTERFAZ ---
    // Si lo borras, te da error. Debes tenerlo.
    @Override
    public void comer(IngestaNativa alimento) {
        System.out.println("🐛 La Larva está comiendo " + alimento.toString());

        // Lógica de Evolución
        if (alimento instanceof Alimento && ((Alimento) alimento).toString().contains("Carnivoro")) {
            System.out.println("¡La Larva ha comido CARNE! Comienza la metamorfosis...");
            this.evolucionarASoldado(alimento);
        } else {
            System.out.println("... La Larva se alimenta, pero sigue siendo Larva.");
        }
    }

    private void evolucionarASoldado(IngestaNativa alimento) {
        // 1. Cambio de Identidad
        this.bnTipo = "Soldado";
        this.bnSexo = "Macho"; 

        // 2. Detección de Genoma
        if (alimento instanceof Alimento) {
            Alimento comida = (Alimento) alimento;
            GenoAlimento genoma = comida.getGenoAlimento(); 
            
            if (genoma != null && genoma.toString().equals("XX")) {
                System.out.println("¡GENOMA XX DETECTADO! (Macho)");
                System.out.println("La hormiga ha adquirido SUPERFUERZA");
            }
        }

        // 3. Guardado en Base de Datos (Active Record)
        if (this.guardar()) {
            System.out.println("Evolución guardada en Base de Datos: Ahora es un SOLDADO MACHO.");
        } else {
            System.out.println("Error al guardar la evolución en la BD.");
        }
    }

    @Override
    public void comer(Alimento comida) {
        throw new UnsupportedOperationException("Unimplemented method 'comer'");
    }

    @Override
    public void comer(Object comida) {
        throw new UnsupportedOperationException("Not supported yet.");
    }
}