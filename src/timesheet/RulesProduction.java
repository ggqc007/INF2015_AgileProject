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
    }

    @Override
    public boolean hasMinimumWeeklyTimeInOffice() {
        return true;
    }
    
    @Override
    public List<Day> getInvalidDaysWithMinimumDailyTimeInOffice(){
        List<Day> days = employe.getTimeSheet(0).getDays();
        return days; 
    }
    
    @Override
    public boolean hasValidWeeklyTimeRemote(){ 
        return true;
    }
    
    @Override
    public boolean hasValidWeeklyTimeInOffice() { 
         return true;
    }
}
