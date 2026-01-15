package DataAccess.DTOs;

public class HormigaSuperHabilidadDTO {
    private Integer IdHormigaSuperHabilidad;
    private Integer IdHormiga;
    private Integer IdGenoma;
    private Integer IdSuperHabilidad;
    private String  Descripcion;
    private String  Estado;
    private String  FechaCreacion;
    private String  FechaModifica;

    public HormigaSuperHabilidadDTO() {}

    public HormigaSuperHabilidadDTO(Integer idHormiga, Integer idGenoma, Integer idSuperHabilidad, String descripcion) {
        IdHormigaSuperHabilidad = 0;
        IdHormiga = idHormiga;
        IdGenoma = idGenoma;
        IdSuperHabilidad = idSuperHabilidad;
        Descripcion = descripcion;
    }

    public Integer getIdHormigaSuperHabilidad() {
        return IdHormigaSuperHabilidad;
    }

    public void setIdHormigaSuperHabilidad(Integer IdHormigaSuperHabilidad) {
        this.IdHormigaSuperHabilidad = IdHormigaSuperHabilidad;
    }

    public Integer getIdHormiga() {
        return IdHormiga;
    }

    public void setIdHormiga(Integer IdHormiga) {
        this.IdHormiga = IdHormiga;
    }

    public Integer getIdGenoma() {
        return IdGenoma;
    }

    public void setIdGenoma(Integer IdGenoma) {
        this.IdGenoma = IdGenoma;
    }

    public Integer getIdSuperHabilidad() {
        return IdSuperHabilidad;
    }

    public void setIdSuperHabilidad(Integer IdSuperHabilidad) {
        this.IdSuperHabilidad = IdSuperHabilidad;
    }

    public String getDescripcion() {
        return Descripcion;
    }

    public void setDescripcion(String Descripcion) {
        this.Descripcion = Descripcion;
    }

    public String getEstado() {
        return Estado;
    }

    public void setEstado(String Estado) {
        this.Estado = Estado;
    }

    public String getFechaCreacion() {
        return FechaCreacion;
    }

    public void setFechaCreacion(String FechaCreacion) {
        this.FechaCreacion = FechaCreacion;
    }

    public String getFechaModifica() {
        return FechaModifica;
    }

    public void setFechaModifica(String FechaModifica) {
        this.FechaModifica = FechaModifica;
    }

}

