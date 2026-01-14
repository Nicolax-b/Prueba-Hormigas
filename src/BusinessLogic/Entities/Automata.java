package BusinessLogic.Entities;

import java.util.Scanner;

public class Automata {
    static final Integer er = -10;
    static final Integer ok = 100;
    static final String  alfabeto ="a,b,c,d,t, "; 
    static final Integer[][] mt={//  a    b     c     d     t    esp
                                    {1   ,er   ,er   ,er   ,er   ,er}, // q0
                                    {er  ,2    ,er   ,er   ,er   ,ok}, // q1 *
                                    {er  ,2    ,3    ,er   ,er   ,ok}, // q2 *
                                    {er  ,er   ,er   ,4    ,er   ,er}, // q3
                                    {er  ,er   ,er   ,er   ,5    ,er}, // q4
                                    {er  ,er   ,er   ,er   ,5    ,ok}  // q5 *
                                };

    @SuppressWarnings("resource")
    private int getIndexAlfabeto(String caracter){
        Scanner scAlfa = new Scanner(alfabeto).useDelimiter(",");
        for (int i = 0; scAlfa.hasNext(); i++) 
            if( caracter.equals(scAlfa.next()))
                return i;
        return er;
    }

    public boolean checkTipoArsenal(String arsenal) {
        int q = 0;
        //System.out.println("Evaluando: " + arsenal);
        for (int i = 0; i < arsenal.length(); i++) {
            int indexAlfa = getIndexAlfabeto(arsenal.charAt(i) + "");
            if (indexAlfa == er || mt[q][indexAlfa].equals(er)) 
                return false;
            q = mt[q][indexAlfa];
        }
        return (q == ok);
    }
}