package App.ConsoleApp;

public class KGD {
    private String cedula;
    private String nombre;
    
    public KGD(String cedula, String nombre) {
        this.cedula = cedula;
        this.nombre = nombre;
    }
    public String getCedula() {
        return cedula;
    }
    public void setCedula(String cedula) {
        this.cedula = cedula;
    }
    public String getNombre() {
        return nombre;
    }
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    
    public void show(){
        System.out.println("[+] Alumno:");
        System.out.println(getCedula() + " | " + getNombre());
    }

    @Override
    public String toString(){
        return getClass().getName()
        + "\n cedula    : "+ getCedula()
        + "\n nombre    : "+ getNombre();
    }
}
