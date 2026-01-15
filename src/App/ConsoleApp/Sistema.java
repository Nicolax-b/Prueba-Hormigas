package App.ConsoleApp;

import BusinessLogic.Services.BNEntomologo;
import Infrastructure.AppConfig;
import Infrastructure.AppException;
import java.util.Scanner;

public class Sistema {

    // Scanner global con prefijo ma (Munoz Angelo)
    private static Scanner MAScanner = new Scanner(System.in);

    /**
     * REQUERIMIENTO: METODO DE BIENVENIDA Muestra la cabecera del examen con
     * los integrantes.
     */
    public void MABienvenida() {
        System.out.println("-----------------------------------------");
        System.out.println("Prueba");
        System.out.println("Bimestre II");
        System.out.println("");
        // Datos con Cédulas solicitadas
        System.out.println("Munoz Angelo      1729053072");
        System.out.println("Bohorquez Nicolas 1753295920");
        System.out.println("-----------------------------------------");
    }

    /**
     * REQUERIMIENTO: AUTENTICACIÓN Usuario: patmic Clave: 123
     */
    public boolean MAAutenticacion() {
        int MAIntentos = 0;
        final int MA_MAX_INTENTOS = 3;

        String maUsuarioRequerido = "pat_mic";
        String maPassRequerido = "123";

        System.out.println("\n--- INICIO DE SESIÓN ---");

        while (MAIntentos < MA_MAX_INTENTOS) {
            System.out.print("-> Usuario: ");
            String maUser = MAScanner.nextLine();

            System.out.print("-> Contraseña: ");
            String maPass = MAScanner.nextLine();

            if (maUser.equals(maUsuarioRequerido) && maPass.equals(maPassRequerido)) {
                // ÉXITO
                System.out.println("\n[ ! ] Autenticación Exitosa.");
                System.out.println("Bienvenido al sistema AntDron2K25\n");
                System.out.println("|Munoz Angelo      1729053072|");
                System.out.println("|Bohorquez Nicolas 1753295920|\n");
                return true;
            } else {
                // FALLO
                MAIntentos++;
                int maRestantes = MA_MAX_INTENTOS - MAIntentos;
                System.err.println("(!) Credenciales incorrectas.");

                if (maRestantes > 0) {
                    System.out.println("Intentos restantes: " + maRestantes);
                }
            }
        }

        System.err.println("(!) Se han agotado los intentos. El sistema se cerrará.");
        return false;
    }

    public void ejecutarETL() {
        try {
            BNEntomologo ent = new BNEntomologo();

            ent.etlAntNest(AppConfig.ANTNEST_FILE);
            ent.etlAntFood(AppConfig.ANTFOOD_FILE);

            System.out.println("ETL finalizado.");
        } catch (AppException e) {
            System.out.println("Error ETL: " + e.getMessage());
        }
    }

    // ==========================================
    // MÉTODO PRINCIPAL
    // ==========================================
    public static void main(String[] args) {
        Sistema app = new Sistema();

        app.MABienvenida();
        if (app.MAAutenticacion()) {
            System.out.println("... Cargando módulos del sistema ...");
            ejecutarETL();

            // Aquí iría el resto del examen
        } else {
            System.exit(0);
        }

    }
}
