package DataAccess.DTOs;

public class HormigaDTO {

    private Integer IdHormiga     ; 
    private Integer IdHormigaTipo ; 
    private Integer IdGenoma      ; 
    private Integer IdEstado      ; 
    private String  Nombre        ; 
    private String  Descripcion   ; 
    private String  Estado        ; 
    private String  FechaCreacion ; 
    private String  FechaModifica ;

    public HormigaDTO() {}
    public HormigaDTO(Integer idHormiga, Integer idHormigaTipo, Integer idGenoma, Integer idEstado, String nombre,
            String descripcion) {
        IdHormiga = idHormiga;
        IdHormigaTipo = idHormigaTipo;
        IdGenoma = idGenoma;
        IdEstado = idEstado;
        Nombre = nombre;
        Descripcion = descripcion;
    }
    public HormigaDTO(Integer idHormiga, Integer idHormigaTipo, Integer idGenoma, Integer idEstado, String nombre,
            String descripcion, String estado, String fechaCreacion, String fechaModifica) {
        IdHormiga = idHormiga;
        IdHormigaTipo = idHormigaTipo;
        IdGenoma = idGenoma;
        IdEstado = idEstado;
        Nombre = nombre;
        Descripcion = descripcion;
        Estado = estado;
        FechaCreacion = fechaCreacion;
        FechaModifica = fechaModifica;
    }
    public Integer getIdHormiga() {
        return IdHormiga;
    }
    public void setIdHormiga(Integer idHormiga) {
        IdHormiga = idHormiga;
    }
    public Integer getIdHormigaTipo() {
        return IdHormigaTipo;
    }
    public void setIdHormigaTipo(Integer idHormigaTipo) {
        IdHormigaTipo = idHormigaTipo;
    }
    public Integer getIdGenoma()() {
        return IdGenoma;
    }
    public void setIdSexo(Integer idGenoma) {
        IdGenoma = idGenoma;
    }
    public Integer getIdEstado() {
        return IdEstado;
    }
    public void setIdEstado(Integer idEstado) {
        IdEstado = idEstado;
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
        + "\n IdHormiga     : "+ getIdHormiga       ()
        + "\n IdHormigaTipo : "+ getIdHormigaTipo   ()
        + "\n IdGenoma      : "+ getIdGenoma        ()       
        + "\n IdEstado      : "+ getIdEstado        ()  
        + "\n Nombre        : "+ getNombre          ()
        + "\n Descripcion   : "+ getDescripcion     ()
        + "\n Estado        : "+ getEstado          ()
        + "\n FechaCreacion : "+ getFechaCreacion   ()
        + "\n FechaModifica : "+ getFechaModifica   ();
    }
}
