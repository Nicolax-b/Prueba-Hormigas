package BusinessLogic.Entities;

public class HSoldado extends Hormiga {
   private String MASuperHabilidad;
   
   public HSoldado(){
      super();
      this.bnTipo = "Soldado";
      this.bnSexo = "Macho";
      this.MASuperHabilidad = "Ninguna";
   }

   @Override
    public void comer(IngestaNativa alimento) {
        System.out.println("El Soldado come " + alimento.toString());
    }
    
    @Override
    public String toString() {
        return super.toString() + " [Poder: " + bnSuperHabilidad + "]";
    }

    public String getMASuperHabilidad() {
        return MASuperHabilidad;
    }

    public void setMASuperHabilidad(String MASuperHabilidad) {
        this.MASuperHabilidad = MASuperHabilidad;
    }
}
