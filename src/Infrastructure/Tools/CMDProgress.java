package Infrastructure.Tools;

public abstract class CMDProgress {

    private CMDProgress() {
    }

    /**
     * avance de tareas con spinner
     *
     * @throws InterruptedException
     */
    public static void showSpinner() throws InterruptedException {
        String c = "|/-\\";
        for (int x = 0; x <= 100; x++) {
            String srt = "\r " + c.charAt(x % c.length()) + " " + x + " % ";
            System.out.print(srt);
            Thread.sleep(30);
        }
    }

    /**
     * Muestra una barra de progreso en la consola.
     *
     * @param c
     * @throws InterruptedException
     */
    public static void showProgressBar(int current, int total, String msg) {
        int sizeBar = 30; // Tamaño visual de la barra
        if (total == 0) {
            total = 1; // Evitar división por cero
        }
        int percentage = (current * 100) / total;
        int nroChar = (current * sizeBar) / total;

        StringBuilder bar = new StringBuilder("\r"); // \r regresa el cursor al inicio
        bar.append(msg).append(" [");

        for (int i = 0; i < sizeBar; i++) {
            if (i < nroChar) {
                bar.append("=");      // Caracter lleno
             }else if (i == nroChar) {
                bar.append(">"); // Puntero
             }else {
                bar.append(" ");                  // Espacio vacío

                    }}

        bar.append("] ").append(percentage).append("%  ");

        System.out.print(bar.toString());

        // Si llegamos al 100%, imprimimos salto de línea
        if (current >= total) {
            System.out.println();
        }
    }
}
