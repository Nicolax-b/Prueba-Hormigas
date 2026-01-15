package BusinessLogic.Entities;

import BusinessLogic.FactoryBL;
import BusinessLogic.Interface.IHormiga;
import DataAccess.DAOs.HormigaDAO;
import DataAccess.DTOs.HormigaDTO;
import Infrastructure.AppException;
import java.util.List;

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
        // Nombre único basado en el tiempo para evitar duplicados en BD
        this.bnNombre = "Ant_" + System.currentTimeMillis(); 
    }

    public boolean guardar() {
        try {
            bnData.setNombre(this.bnNombre);
            bnData.setDescripcion("Simulacion Examen");
            
            // Mapeos
            bnData.setEstado("A");
            bnData.setIdHormigaTipo(getIdTipo(this.bnTipo));
            bnData.setIdEstado(this.bnEstado.equals("VIVA") ? 1 : 2);
            bnData.setIdGenoma(getIdGenomaPorSexo(this.bnSexo)); 

            // Lógica Active Record (Insert o Update)
            boolean exito = false;
            
            // Si ya tenemos ID, actualizamos
            if (bnData.getIdHormiga() != null && bnData.getIdHormiga() > 0) {
                exito = bnFactory.upd(bnData);
            } else {
                // Si no, creamos
                exito = bnFactory.add(bnData);
                
                // TRUCO: Recuperar el ID recién creado para poder actualizarlo después
                if(exito) {
                    recuperarIdDeBaseDatos();
                }
            }
            return exito;

        } catch (AppException e) {
            System.out.println("Error al guardar/actualizar: " + e.getMessage());
            return false;
        }
    }
    private void recuperarIdDeBaseDatos() {
        try {
            // Buscamos todas y filtramos por el nombre único que acabamos de poner
            List<HormigaDTO> lista = bnFactory.getAll();
            for (HormigaDTO dto : lista) {
                if (dto.getNombre().equals(this.bnNombre)) {
                    this.bnData.setIdHormiga(dto.getIdHormiga());
                    break;
                }
            }
        } catch (Exception e) {
            System.out.println("No se pudo sincronizar ID");
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

    private int getIdGenomaPorSexo(String sexo) {
        if (sexo == null) return 1; 
        switch (sexo) {
            case "Asexual": return 1; // X
            case "Macho":   return 2; // XX
            case "Hembra":  return 3; // XY
            default:        return 1;
        }
    } 

    // Getters y Setters
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