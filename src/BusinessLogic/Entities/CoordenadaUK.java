package BusinessLogic.Entities;

public class CoordenadaUK {
    
    private String  Geoposicion;
    private String  TipoArsenal;
    private Boolean Accion;


    public CoordenadaUK(String geoposicion, String tipoArsenal, Boolean accion) {
        Geoposicion = geoposicion;
        TipoArsenal = tipoArsenal;
        Accion = accion;
    }


    public String getGeoposicion() {
        return Geoposicion;
    }


    public void setGeoposicion(String geoposicion) {
        Geoposicion = geoposicion;
    }


    public String getTipoArsenal() {
        return TipoArsenal;
    }


    public void setTipoArsenal(String tipoArsenal) {
        TipoArsenal = tipoArsenal;
    }


    public Boolean getAccion() {
        return Accion;
    }


    public void setAccion(Boolean accion) {
        Accion = accion;
    }


    @Override
    public String toString(){
        return getClass().getName()
        + "\n Geoposicion    : "+ getGeoposicion    ()
        + "\n TipoArsenal    : "+ getTipoArsenal    ()
        + "\n Accion         : "+ getAccion         ();
    }
}
