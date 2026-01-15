package BusinessLogic.Entities;

public class BNHSoldado extends BNHormiga {
   
   
   private String MASuperHabilidad;
   
   public BNHSoldado(){
      super();
      this.bnTipo = "Soldado";
      this.bnSexo = "Macho";
      this.MASuperHabilidad = "Ninguna";
   }

   @Override
    public void comer(IngestaNativa alimento) {
        System.out.println("El Soldado come " + alimento.toString());
        // Lógica extra si fuera necesario
    }
    
    @Override
    public String toString() {
        // 3. Corregido para usar la variable correcta
        return super.toString() + " [Poder: " + MASuperHabilidad + "]";
    }

    public String getSuperHabilidad() {
        return MASuperHabilidad;
    }

    public void setSuperHabilidad(String habilidad) {
        this.MASuperHabilidad = habilidad;
    }
}
