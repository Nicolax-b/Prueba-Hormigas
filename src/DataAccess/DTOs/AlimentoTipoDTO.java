package DataAccess.DTOs;

public class AlimentoTipoDTO {
    private Integer IdAlimentoTipo;
    private String  Nombre        ;
    private String  Descripcion   ;
    private String  Estado        ;
    private String  FechaCreacion ;
    private String  FechaModifica ;
    
    public AlimentoTipoDTO() {}
    public AlimentoTipoDTO(String nombre, String descripcion) {
        IdAlimentoTipo  = 0;
        Nombre          = nombre;
        Descripcion     = descripcion;
    }
    public AlimentoTipoDTO(Integer idAlimentoTipo, String nombre, String descripcion, String estado, String fechaCreacion,
            String fechaModifica) {
        IdAlimentoTipo  = idAlimentoTipo;
        Nombre          = nombre;
        Descripcion     = descripcion;
        Estado          = estado;
        FechaCreacion   = fechaCreacion;
        FechaModifica   = fechaModifica;
    }
    public Integer getIdAlimentoTipo() {
        return IdAlimentoTipo;
    }
    public void setIdAlimentoTipo(Integer idAlimentoTipo) {
        IdAlimentoTipo = idAlimentoTipo;
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
        + "\n IdAlimentoTipo: "+ getIdAlimentoTipo  ()
        + "\n Nombre        : "+ getNombre          ()
        + "\n Descripcion   : "+ getDescripcion     ()
        + "\n Estado        : "+ getEstado          ()
        + "\n FechaCreacion : "+ getFechaCreacion   ()
        + "\n FechaModifica : "+ getFechaModifica   ()
        + "\n --------------------------- " ;
    }
}
