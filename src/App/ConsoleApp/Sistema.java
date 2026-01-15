package App.ConsoleApp;

import BusinessLogic.Entities.Alimento;
import BusinessLogic.Entities.BNHormiga;
import BusinessLogic.Services.BNEntomologoBL;
import Infrastructure.AppConfig;
import Infrastructure.AppException;
import java.util.List;
import java.util.Scanner;

public class Sistema {

    private static Scanner MAScanner = new Scanner(System.in);

    /**
     * Muestra la cabecera del examen con los integrantes.
     */
    public void MABienvenida() {
        System.out.println("-----------------------------------------");
        System.out.println("                Prueba");
        System.out.println("              Bimestre II");
        System.out.println("");
        System.out.println("      |Munoz Angelo      1729053072|");
        System.out.println("      |Bohorquez Nicolas 1753295920|");
        System.out.println("-----------------------------------------");
    }

    /**
     * REQUERIMIENTO: AUTENTICACIÓN
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
                System.out.println("\n[ ! ] Autenticación Exitosa.");
                System.out.println("Bienvenido al sistema AntDron2K25\n");
                return true;
            } else {
                MAIntentos++;
                System.err.println("(!) Credenciales incorrectas. Intentos restantes: " + (MA_MAX_INTENTOS - MAIntentos));
            }
        }
        return false;
    }

    /**
     * Ejecuta el proceso de carga masiva (ETL)
     */
    public void ejecutarETL() {
        try {
            BNEntomologoBL ent = new BNEntomologoBL();
            ent.etlAntNest(AppConfig.ANTNEST_FILE);
            ent.etlAntFood(AppConfig.ANTFOOD_FILE);
            System.out.println("ETL finalizado.");
        } catch (AppException e) {
            System.out.println("Error ETL: " + e.getMessage());
        }
    }

    /**
     * REQUERIMIENTO: Alimentar a toda la colonia
     */
    public void ejecutarAplicacion() {
        MABienvenida();

        if (!MAAutenticacion()) {
            System.exit(0);
        }

        System.out.println("... Cargando módulos del sistema ...");
        ejecutarETL();

        try {
            BNEntomologoBL BN_ent = new BNEntomologoBL();

            List<BNHormiga> BN_hormigas = BN_ent.etlAntNest(AppConfig.ANTNEST_FILE);
            List<Alimento> BN_alimentos = BN_ent.etlAntFood(AppConfig.ANTFOOD_FILE);

            System.out.println("\n--- PROCESO DE ALIMENTACIÓN DE LA COLONIA ---");

            for (int i = 0; i < BN_hormigas.size(); i++) {
                if (i < BN_alimentos.size()) {
                    BNHormiga hormigaActual = BN_hormigas.get(i);
                    Alimento alimentoActual = BN_alimentos.get(i);

                    BNHormiga resultado = BN_ent.alimentarAnt(hormigaActual, BN_ent.preparar(alimentoActual));

                    if (resultado != null && !resultado.getClass().equals(hormigaActual.getClass())) {
                        System.out.println("-> Registro actualizado: La colonia ahora tiene un nuevo " + resultado.getClass().getSimpleName());
                    }
                } else {
                    break;
                }
            }
        } catch (AppException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }   
    


    public static void main(String[] args) {
        Sistema app = new Sistema();
        app.ejecutarAplicacion();
    }
}
