import App.ConsoleApp.Sistema;

public class App {

    public static void main(String[] args) {
        try {

            Sistema BN_app = new Sistema();
            BN_app.ejecutarAplicacion();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
