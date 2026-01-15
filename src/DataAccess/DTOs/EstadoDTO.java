package DataAccess.DTOs;

public class EstadoDTO {

    private Integer IdEstado;
    private String  Nombre;
    private String  Descripcion;
    private String  Estado;
    private String  FechaCreacion;
    private String  FechaModifica;

    public EstadoDTO() {}

    public EstadoDTO(
            Integer idEstado,
            String nombre,
            String descripcion,
            String estado,
            String fechaCreacion,
            String fechaModifica
    ) {
        IdEstado = idEstado;
        Nombre = nombre;
        Descripcion = descripcion;
        Estado = estado;
        FechaCreacion = fechaCreacion;
        FechaModifica = fechaModifica;
    }

    // ===== GETTERS =====
    public Integer getIdEstado() {
        return IdEstado;
    }

    public String getNombre() {
        return Nombre;
    }

    public String getDescripcion() {
        return Descripcion;
    }

    public String getEstado() {
        return Estado;
    }

    public String getFechaCreacion() {
        return FechaCreacion;
    }

    public String getFechaModifica() {
        return FechaModifica;
    }

    // ===== SETTERS =====
    public void setIdEstado(Integer idEstado) {
        IdEstado = idEstado;
    }

    public void setNombre(String nombre) {
        Nombre = nombre;
    }

    public void setDescripcion(String descripcion) {
        Descripcion = descripcion;
    }

    public void setEstado(String estado) {
        Estado = estado;
    }

    public void setFechaCreacion(String fechaCreacion) {
        FechaCreacion = fechaCreacion;
    }

    public void setFechaModifica(String fechaModifica) {
        FechaModifica = fechaModifica;
    }
}

