package DataAccess.DTOs;

public class AntCiberDronDTO {
    private Integer IdAntCiberDron  ;
    private String Serie            ;
    private String Estado           ;
    private String FechaCreacion    ;
    private String FechaModifica    ;

    public AntCiberDronDTO(String serie) {
        IdAntCiberDron = 0;
        Serie = serie;
    }

    public AntCiberDronDTO() {
    }

    public AntCiberDronDTO(Integer idAntCiberDron, String serie, String estado, String fechaCreacion, String fechaModifica) {
        IdAntCiberDron = idAntCiberDron;
        Serie = serie;
        Estado = estado;
        FechaCreacion = fechaCreacion;
        FechaModifica = fechaModifica;
    }

    public Integer getIdAntCiberDron() {
        return IdAntCiberDron;
    }

    public void setIdAntCiberDron(Integer IdAntCiberDron) {
        this.IdAntCiberDron = IdAntCiberDron;
    }

    public String getSerie() {
        return Serie;
    }

    public void setSerie(String Serie) {
        this.Serie = Serie;
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
        + "\n IdAntCiberDron        : "+ getIdAntCiberDron()
        + "\n Serie                 : "+ getSerie      ()
        + "\n Estado                : "+ getEstado      ()
        + "\n FechaCreacion         : "+ getFechaCreacion ()
        + "\n FechaModifica         : "+ getFechaModifica ()
        + "\n ----------------------------------";
    }
}
