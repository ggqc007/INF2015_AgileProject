/* INF2015 – Développement de logiciels dans un environnement Agile
 *
 * Projet de session - Hiver 2014
 *
 * Equipe 8 :
 *
 *   Christian Cornejo
 *   Khaled Elsheikh
 *   Guillaume Gagnon
 *   Thomas Robert de Massy
 *
 */

package timesheet;


import java.util.ArrayList;
import java.util.List;


public class TimeSheet {

    private List<Employe> employees;

    
    public TimeSheet() {
        
        Employe employe = new Employe();
        
        employees = new ArrayList();
        
        employees.add(employe);
        
        
    }
    
        
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {

    }
    
}
