
import App.ConsoleApp.Sistema;

public class App {

    public static void main(String[] args) {
        try {

            Sistema BN_app = new Sistema();
            BN_app.ejecutarAplicacion();

            // Al finalizar, verifica tu base de datos SQLite.
            // Deberías ver que la hormiga cambió de Tipo Larva (1) a Soldado (2)
            // y adquirió el Genoma XX (2).
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
