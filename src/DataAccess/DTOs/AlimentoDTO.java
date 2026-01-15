package DataAccess.DTOs;

public class AlimentoDTO {
    private Integer IdAlimento;
    private Integer IdAlimentoTipo;
    private String  Nombre;
    private String  Descripcion;
    private String  Estado;
    private String  FechaCreacion;
    private String  FechaModifica;

    public AlimentoDTO() {}

    public AlimentoDTO(Integer idAlimentoTipo, String nombre, String descripcion) {
        IdAlimento = 0;
        IdAlimentoTipo = idAlimentoTipo;
        Nombre = nombre;
        Descripcion = descripcion;
    }

    public Integer getIdAlimento() {
        return IdAlimento;
    }

    public void setIdAlimento(Integer IdAlimento) {
        this.IdAlimento = IdAlimento;
    }

    public Integer getIdAlimentoTipo() {
        return IdAlimentoTipo;
    }

    public void setIdAlimentoTipo(Integer IdAlimentoTipo) {
        this.IdAlimentoTipo = IdAlimentoTipo;
    }

    public String getNombre() {
        return Nombre;
    }

    public void setNombre(String Nombre) {
        this.Nombre = Nombre;
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

