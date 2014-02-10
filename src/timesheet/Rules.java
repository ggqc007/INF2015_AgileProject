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
        maxOfficeWeekMinutes = 43*60;
    }
    
    abstract public boolean hasMinimumWeeklyTimeInOffice();
    
    abstract public  List<Day> getInvalidDaysWithMinimumDailyTimeInOffice();
    
    abstract public boolean hasValidWeeklyTimeRemote();
    
    abstract public boolean hasValidWeeklyTimeInOffice();
    
    public void prepData(){
        setTotalOfficeWeekMinutes();
        setTotalRemoteWeekMinutes();
        setTotalWeekMinutes(totalOfficeWeekMinutes + totalRemoteWeekMinutes);
    }
    
    public void setTotalOfficeWeekMinutes() {
        int totalMinutes = 0;
        List<Day> days = employe.getTimeSheet(0).getDays();       
        for (int i = 0; i < days.size(); i++) {    
            totalMinutes += getTotalOfficeMinutesByDay(days.get(i));  
        }   
        totalOfficeWeekMinutes = totalMinutes;
    }
 
    protected int getTotalOfficeMinutesByDay(Day day) {
        int totalMinutes = 0;
        List<Task> tasks = day.getTasks();
        for (int i = 0; i < tasks.size(); i++) {
            if (!tasks.get(i).isRemoteTask()) {
                totalMinutes += (int)tasks.get(i).getTime();
            }
        }
        return totalMinutes;
    }
    
    public void setTotalRemoteWeekMinutes() {
        int totalMinutes = 0;
        List<Day> days = employe.getTimeSheet(0).getDays();       
        for (int i = 0; i < days.size(); i++) {  
            totalMinutes += getTotalRemoteMinutesByDay(days.get(i));
        }   
        totalRemoteWeekMinutes = totalMinutes;
    }    
    
    protected int getTotalRemoteMinutesByDay(Day day) {
        int totalMinutes = 0;
        List<Task> tasks = day.getTasks();
        for (int i = 0; i < tasks.size(); i++) {
            if (tasks.get(i).isRemoteTask()) {
                totalMinutes += (int)tasks.get(i).getTime();
            }
        }
        return totalMinutes;
    }
    
    public Employe getEmploye() {
        return employe;
    }

    public void setEmploye(Employe employe) {
        this.employe = employe;
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
