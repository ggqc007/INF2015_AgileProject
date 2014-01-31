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


public class Employe {

    private final List<TimeSheetData> timesheets;
    
    public Employe() {
        
        TimeSheetData timesheet = new TimeSheetData();
        
        timesheets = new ArrayList();
        
        timesheets.add(timesheet);
        
    }
    
}
