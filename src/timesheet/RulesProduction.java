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
import java.util.List;
import java.util.ArrayList;

public class RulesProduction  extends Rules {

    public RulesProduction() {
        minOfficeWeekMinutes = 38*60;
        minOfficeDailyMinutes = 6*60; 
        
    }

    /* @Override
    public boolean hasMinimumWeeklyTimeInOffice() {
        int officeWeekMinutes = totalWeekMinutes - totalRemoteWeekMinutes;        
        return (officeWeekMinutes >= minOfficeWeekMinutes);
    }*/
    
    
    @Override
    public boolean hasValidWeeklyTimeRemote(){ 
     return true;
    }
}
