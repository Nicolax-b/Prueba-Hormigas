package DataAccess.DTOs;

public class SuperHabilidadDTO {
    private Integer IdSuperHabilidad  ;
    private String Habilidad        ;
    private String Estado           ;
    private String FechaCreacion    ;
    private String FechaModifica    ;

    public SuperHabilidadDTO(String habilidad) {
        IdSuperHabilidad = 0;
        Habilidad = habilidad;
    }

    public SuperHabilidadDTO() {
    }

    public SuperHabilidadDTO(Integer idsuperhabilidad, String habilidad, String estado, String fechaCreacion, String fechaModifica) {
        IdSuperHabilidad = idsuperhabilidad;
        Habilidad = habilidad;
        Estado = estado;
        FechaCreacion = fechaCreacion;
        FechaModifica = fechaModifica;
    }

    public Integer getIdSuperHabilidad() {
        return IdSuperHabilidad;
    }

    public void setIdSuperHabilidad(Integer IdSuperHabilidad) {
        this.IdSuperHabilidad = IdSuperHabilidad;
    }

    public String getHabilidad() {
        return Habilidad;
    }

    public void setHabilidad(String Habilidad) {
        this.Habilidad = Habilidad;
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

    @Override
    public String toString(){
        return getClass().getName()
        + "\n IdSuperHabilidad        : "+ getIdSuperHabilidad()
        + "\n Habilidad             : "+ getHabilidad      ()
        + "\n Estado                : "+ getEstado      ()
        + "\n FechaCreacion         : "+ getFechaCreacion ()
        + "\n FechaModifica         : "+ getFechaModifica ()
        + "\n ----------------------------------";
    }
}
