package DataAccess.DTOs;

public class HormigaTipoDTO {

    private Integer IdHormigaTipo;
    private String  Nombre;
    private String  Descripcion;
    private String  Estado;
    private String  FechaCreacion;
    private String  FechaModifica;

    public HormigaTipoDTO() {}

    public HormigaTipoDTO(
            Integer idHormigaTipo,
            String nombre,
            String descripcion,
            String estado,
            String fechaCreacion,
            String fechaModifica
    ) {
        IdHormigaTipo = idHormigaTipo;
        Nombre = nombre;
        Descripcion = descripcion;
        Estado = estado;
        FechaCreacion = fechaCreacion;
        FechaModifica = fechaModifica;
    }

    // ===== GETTERS =====
    public Integer getIdHormigaTipo() {
        return IdHormigaTipo;
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
    public void setIdHormigaTipo(Integer idHormigaTipo) {
        IdHormigaTipo = idHormigaTipo;
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

