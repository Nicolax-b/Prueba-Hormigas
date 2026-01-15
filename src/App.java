import BusinessLogic.Entities.BNHLarva;
import BusinessLogic.Entities.BNHSoldado;
import BusinessLogic.Entities.Carnivoro;
import BusinessLogic.Entities.Alimento;
import BusinessLogic.Entities.XX;

public class App {
    public static void main(String[] args) {
        try {
            System.out.println("=== INICIO SIMULACIÓN CASO A ===");

            // 1. Nace la Larva
            BNHLarva miLarva = new BNHLarva();
            System.out.println("Nace: " + miLarva.toString());
            miLarva.guardar(); // Se guarda como Larva en BD

            // 2. Preparamos Comida con Genoma XX (Macho/Poder)
            Carnivoro carne = new Carnivoro();
            carne.setGenoAlimento(new XX()); // Inyectamos el genoma XX

            // 3. La larva come y evoluciona
            miLarva.comer(carne);

            // Al finalizar, verifica tu base de datos SQLite.
            // Deberías ver que la hormiga cambió de Tipo Larva (1) a Soldado (2)
            // y adquirió el Genoma XX (2).

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}