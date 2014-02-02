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

abstract public class Rules {
    
    protected int maxOfficeWeekMinutes;
    protected int minOfficeWeekMinutes;
    protected int maxRemoteWeekMinutes;
    protected int minOfficeDailyMinutes;
    protected int totalWeekMinutes;
    protected int totalOfficeWeekMinutes;
    protected int totalRemoteWeekMinutes;
    protected Employe employe;
    
    public Rules() {
        this.maxOfficeWeekMinutes = 43*60;
        
    }
    
    abstract public boolean hasMinimumOfficeWeekMinutes();
    
    abstract public boolean hasMinimumOfficeDailyMinutes();
    
    abstract public boolean hasValidRemoteWeekMinutes();
    
    abstract public boolean hasMaximumOfficeWeekMinutes();
    
    protected int getTotalMinutesByDay(Day day) {
        int totalMinutes = 0;
        List<Task> tasks = day.getTasks();
        
        for (int j=0; j<tasks.size(); j++) {
            totalMinutes += (int)tasks.get(j).getTime();
        }
           
        return totalMinutes;
    }

    public void setTotalWeekMinutesByEmploye() {
        List<Day> days = employe.getTimeSheet(0).getDays();
        
        int totalMinutes = 0;
        for (int i = 0; i < days.size() == true; i++) {    
                totalMinutes += this.getTotalMinutesByDay(days.get(i));  
        }   
        
        this.totalWeekMinutes = totalMinutes;
    }
    
    public void setTotalRemoteWeekMinutesByEmploye() {
        int totalMinutes = 0;
        // TODO : Définir méthode calcul du total d'heures de télétravail
        this.totalRemoteWeekMinutes = totalMinutes;
    }    
    

    public int getMaxOfficeWeekMinutes() {
        return maxOfficeWeekMinutes;
    }

    public void setMaxOfficeWeekMinutes(int maxOfficeWeekMinutes) {
        this.maxOfficeWeekMinutes = maxOfficeWeekMinutes;
    }

    public int getMinOfficeWeekMinutes() {
        return minOfficeWeekMinutes;
    }

    public void setMinOfficeWeekMinutes(int minOfficeWeekMinutes) {
        this.minOfficeWeekMinutes = minOfficeWeekMinutes;
    }

    public int getMaxRemoteWeekMinutes() {
        return maxRemoteWeekMinutes;
    }

    public void setMaxRemoteWeekMinutes(int maxRemoteWeekMinutes) {
        this.maxRemoteWeekMinutes = maxRemoteWeekMinutes;
    }

    public int getMinOfficeDailyMinutes() {
        return minOfficeDailyMinutes;
    }

    public void setMinOfficeDailyMinutes(int minOfficeDailyMinutes) {
        this.minOfficeDailyMinutes = minOfficeDailyMinutes;
    }

    public int getTotalWeekMinutes() {
        return totalWeekMinutes;
    }

    public void setTotalWeekMinutes(int totalWeekMinutes) {
        this.totalWeekMinutes = totalWeekMinutes;
    }

    public int getTotalOfficeWeekMinutes() {
        return totalOfficeWeekMinutes;
    }

    public void setTotalOfficeWeekMinutes(int totalOfficeWeekMinutes) {
        this.totalOfficeWeekMinutes = totalOfficeWeekMinutes;
    }

    public int getTotalRemoteWeekMinutes() {
        return totalRemoteWeekMinutes;
    }

    public void setTotalRemoteWeekMinutes(int totalRemoteWeekMinutes) {
        this.totalRemoteWeekMinutes = totalRemoteWeekMinutes;
    }

   
}
