package BusinessLogic.Entities;

import BusinessLogic.FactoryBL;
import BusinessLogic.Interface.IHormiga;
import DataAccess.DAOs.HormigaDAO;
import DataAccess.DTOs.HormigaDTO;
import Infrastructure.AppException;

public abstract class BNHormiga implements IHormiga {

    protected FactoryBL<HormigaDTO> bnFactory = new FactoryBL<>(HormigaDAO.class); 
    public HormigaDTO bnData = new HormigaDTO(); 

    protected String bnNombre;
    protected String bnTipo;    
    protected String bnSexo;    
    protected String bnEstado;  
    
    public BNHormiga() {
        this.bnEstado = "VIVA";
        this.bnSexo = "Asexual"; 
        this.bnNombre = "Ant_" + System.currentTimeMillis(); 
    }

    public boolean guardar() {
        try {
            bnData.setNombre(this.bnNombre);
            bnData.setDescripcion("Hormiga del Examen");
            bnData.setEstado("A"); // A = Activo en BD
            bnData.setIdHormigaTipo(getIdTipo(this.bnTipo));
            bnData.setIdSexo(getIdSexo(this.bnSexo));
            bnData.setIdEstado(this.bnEstado.equals("VIVA") ? 1 : 2);
            bnData.setIdGenoma(1); // Por defecto o define lógica para esto

            return bnFactory.add(bnData);

        } catch (AppException e) {
            System.out.println("Error al guardar: " + e.getMessage());
            return false;
        }
    }

    private int getIdTipo(String tipo) {
        if (tipo == null) return 1;
        switch (tipo) {
            case "Larva": return 1;
            case "Soldado": return 2;
            case "Zángano": return 3;
            case "Rastreadora": return 4;
            case "Reina": return 5;
            case "Obrera": return 6;
            default: return 1; 
        }
    }

    private int getIdSexo(String sexo) {
        if (sexo == null) return 1;
        switch (sexo) {
            case "Asexual": return 1; 
            case "Macho": return 2;
            case "Hembra": return 3;
            default: return 1;
        }
    }

    // Getters y Setters de Lógica
    public String getTipo() { return bnTipo; }
    public void setTipo(String tipo) { this.bnTipo = tipo; }
    public String getSexo() { return bnSexo; }
    public void setSexo(String sexo) { this.bnSexo = sexo; }
    public String getEstado() { return bnEstado; }
    public void setEstado(String estado) { this.bnEstado = estado; }
    
    @Override
    public String toString() {
        return "BNHormiga: " + bnTipo + " (" + bnSexo + ")";
    }
}