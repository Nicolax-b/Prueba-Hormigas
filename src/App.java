//  © 2K26 ❱──💀──❰ pat_mic ? code is life : life is code

import BusinessLogic.Entities.HLarva;
import BusinessLogic.Entities.Hormiga;

public class App {
    public static void main(String[] args)   {
               
        //Probando el BL
         try {
            HLarva L = new HLarva();
            System.out.println(L.getLarva(1).toString());

            Hormiga h = L.comer("hierba");
            System.out.println(h.toString());
            
            /* FactoryBL<AntCiberDronDTO> bl = new FactoryBL<>(AntCiberDronDAO.class);
            
            for (int i = 1; i <= bl.getAll().size(); i++){
            AntCiberDronDTO oDTO = bl.getBy(i);    
            oDTO.setSerie("Serie-BL"+ i);
            bl.upd(oDTO); 
           } */

           
           /* for(var dto: bl.getAll()){
                //System.out.println(dto.toString());
            } */
        } catch (Exception e) {
        } 


        //Probando el DAO
        /* try {
           AntCiberDronDAO dao = new AntCiberDronDAO();
           
           for (int i = 1; i <= dao.readAll().size(); i++){
            AntCiberDronDTO oDTO = dao.readBy(i);    
            oDTO.setSerie("Serie-00"+ i);
            dao.update(oDTO); 
           }

           
           for(var dto: dao.readAll()){
                System.out.println(dto.toString());
            }
        } catch (Exception e) {
        } */
        
        /* try {

            HLarva l = new HLarva();
            System.out.println( l.getLava(1).getNombre() );

            for ( var larva : l.getLarvas() ) {
                System.out.println( larva.toString() );
            }
        } catch (Exception e) {
        }
 */

        // try {

        //     HormigaDAO dao = new HormigaDAO();
        //     for (VWHormigaDTO h : dao.readAllvwHormiga()) {
        //         System.out.println( h.toString() );
        //     }

        // } catch (Exception e) {
        // }

        // try {

        //     AlimentoTipoDAO dao = new AlimentoTipoDAO();
        //     AlimentoTipoDTO oDTO = dao.readBy(1);
        //     oDTO.setNombre("CARnivoro");
        //     oDTO.setDescripcion("Salado");
        //     dao.update(oDTO);

        //     for (AlimentoTipoDTO dto : dao.readAll())
        //         System.out.println( dto.toString() );

        // } catch (AppException _) {}

        
         
         

        // int a =10;
        // int b =0;
        // try {
        //     a= a / b;
        // } catch (Exception e) {
        //     AppException er = new AppException("Error al dividir por cero", e, null, "main(...)" );
        //     AppMSG.showError(er.getMessage());
        // }
        // System.out.println("Final Feliz...");


        // SistemaRuso sistemaRuso = new SistemaRuso();
        // try {   
        //     sistemaRuso.readCoord("datafile\\pat_mic.csv");

        // } catch (Exception e) {
        //     e.printStackTrace();
        // }

        // Automata automata = new Automata();
        // Scanner tipoArsenal = new Scanner("a ,ab ,abc ,abcd ").useDelimiter(",");

        // while (tipoArsenal.hasNext()) 
        //     System.out.println( automata.checkTipoArsenal(tipoArsenal.next()) ?  "OK" : "Error" ) ;
        // tipoArsenal.close();
    }
}