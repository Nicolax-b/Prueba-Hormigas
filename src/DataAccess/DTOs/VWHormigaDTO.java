package DataAccess.DTOs;

public class VWHormigaDTO {
    private String IdHormiga    ;
    private String Tipo ;
    private String Sexo ;
    private String EstadoHormiga    ;
    private String Nombre   ;
    private String Descripcion  ;
    private String Estado   ;
    private String FechaCreacion    ;
    private String FechaModifica    ;
    public VWHormigaDTO(String idHormiga, String tipo, String sexo, String estadoHormiga, String nombre,
            String descripcion, String estado, String fechaCreacion, String fechaModifica) {
        IdHormiga = idHormiga;
        Tipo = tipo;
        Sexo = sexo;
        EstadoHormiga = estadoHormiga;
        Nombre = nombre;
        Descripcion = descripcion;
        Estado = estado;
        FechaCreacion = fechaCreacion;
        FechaModifica = fechaModifica;
    }
    public String getIdHormiga() {
        return IdHormiga;
    }
    public void setIdHormiga(String idHormiga) {
        IdHormiga = idHormiga;
    }
    public String getTipo() {
        return Tipo;
    }
    public void setTipo(String tipo) {
        Tipo = tipo;
    }
    public String getSexo() {
        return Sexo;
    }
    public void setSexo(String sexo) {
        Sexo = sexo;
    }
    public String getEstadoHormiga() {
        return EstadoHormiga;
    }
    public void setEstadoHormiga(String estadoHormiga) {
        EstadoHormiga = estadoHormiga;
    }
    public String getNombre() {
        return Nombre;
    }
    public void setNombre(String nombre) {
        Nombre = nombre;
    }
    public String getDescripcion() {
        return Descripcion;
    }
    public void setDescripcion(String descripcion) {
        Descripcion = descripcion;
    }
    public String getEstado() {
        return Estado;
    }
    public void setEstado(String estado) {
        Estado = estado;
    }
    public String getFechaCreacion() {
        return FechaCreacion;
    }
    public void setFechaCreacion(String fechaCreacion) {
        FechaCreacion = fechaCreacion;
    }
    public String getFechaModifica() {
        return FechaModifica;
    }
    public void setFechaModifica(String fechaModifica) {
        FechaModifica = fechaModifica;
    } 

    @Override
    public String toString() {
        return getClass().getName()
        + "\n  IdHormiga        : "+ getIdHormiga    ()
        + "\n  Tipo            : "+ getTipo          ()
        + "\n  Sexo            : "+ getSexo          ()
        + "\n  EstadoHormiga   : "+ getEstadoHormiga ()
        + "\n  Nombre          : "+ getNombre        ()
        + "\n  Descripcion     : "+ getDescripcion   ()
        + "\n  Estado          : "+ getEstado        ()
        + "\n  FechaCreacion   : "+ getFechaCreacion ()
        + "\n  FechaModifica   : "+ getFechaModifica ()
        + "\n-----------------------------------";
    }
}
