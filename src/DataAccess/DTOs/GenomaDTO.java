package DataAccess.DTOs;

public class GenomaDTO {
    private Integer IdGenoma       ;
    private String  Nombre       ;
    private String  Descripcion  ;
    private String  Estado       ;
    private String  FechaCreacion;
    private String  FechaModifica;

    public GenomaDTO() {
    }
    public GenomaDTO(String nombre, String descripcion) {
        IdGenoma = 0;
        Nombre = nombre;
        Descripcion = descripcion;
    }
    public GenomaDTO(Integer idGenoma, String nombre, String descripcion, String estado, String fechaCreacion,
            String fechaModifica) {
        IdGenoma = idGenoma;
        Nombre = nombre;
        Descripcion = descripcion;
        Estado = estado;
        FechaCreacion = fechaCreacion;
        FechaModifica = fechaModifica;
    }
    public Integer getIdGenoma() {
        return IdGenoma;
    }
    public void setIdGenoma(Integer idGenoma) {
        IdGenoma = idGenoma;
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
    public String toString(){
        return getClass().getName()
        + "\n IdGenoma        : "+ getIdGenoma      ()
        + "\n Nombre        : "+ getNombre      ()
        + "\n Descripcion   : "+ getDescripcion ()
        + "\n Estado        : "+ getEstado      ()
        + "\n FechaCreacion : "+ getFechaCreacion ()
        + "\n FechaModifica : "+ getFechaModifica ();
    }
}
