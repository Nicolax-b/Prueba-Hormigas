package App.ConsoleApp;

public class BNEntomologos {
    private String BNCedula;
    private String BNNombre;
    
    public BNEntomologos(String BNCedula, String BNNombre) {
        this.BNCedula = BNCedula;
        this.BNNombre = BNNombre;
    }
    public String getCedula() {
        return BNCedula;
    }
    public void setCedula(String BNCedula) {
        this.BNCedula = BNCedula;
    }
    public String getNombre() {
        return BNNombre;
    }
    public void setNombre(String BNNombre) {
        this.BNNombre = BNNombre;
    }
    
    public void show(){
        System.out.println("[+] Alumno:");
        System.out.println(getCedula() + " | " + getNombre());
    }

    @Override
    public String toString(){
        return getClass().getName()
        + "\n BNCedula    : "+ getCedula()
        + "\n BNNombre    : "+ getNombre();
    }
}
