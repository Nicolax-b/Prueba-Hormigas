package DataAccess.DTOs;

public class HormigaAlimentoDTO {
    private Integer IdHormigaAlimento;
    private Integer IdHormiga;
    private Integer IdAlimento;
    private String  Descripcion;
    private String  Estado;
    private String  FechaCreacion;
    private String  FechaModifica;

    public HormigaAlimentoDTO() {}

    public HormigaAlimentoDTO(Integer idHormiga, Integer idAlimento, String descripcion) {
        IdHormigaAlimento = 0;
        IdHormiga = idHormiga;
        IdAlimento = idAlimento;
        Descripcion = descripcion;
    }

    public Integer getIdHormigaAlimento() {
        return IdHormigaAlimento;
    }

    public void setIdHormigaAlimento(Integer IdHormigaAlimento) {
        this.IdHormigaAlimento = IdHormigaAlimento;
    }

    public Integer getIdHormiga() {
        return IdHormiga;
    }

    public void setIdHormiga(Integer IdHormiga) {
        this.IdHormiga = IdHormiga;
    }

    public Integer getIdAlimento() {
        return IdAlimento;
    }

    public void setIdAlimento(Integer IdAlimento) {
        this.IdAlimento = IdAlimento;
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

