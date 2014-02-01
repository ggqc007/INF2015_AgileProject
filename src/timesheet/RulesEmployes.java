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


public class RulesEmployes extends Rules {

    public RulesEmployes() {
        this.minOfficeWeekHours = 38;
        this.minOfficeDailyHours = 6;       
    }
    
    @Override
    public boolean hasMinimumOfficeWeekHours() {
        int officeWeekHours;
        officeWeekHours = this.totalWeekHours - this.totalHomeWeekHours;
        
        return (officeWeekHours >= this.minOfficeWeekHours);
    }
    
    @Override 
    public boolean hasMinimumOfficeDailyHours(Employe employe){
        boolean  validHours = true;
        List<Day> days = employe.getTimeSheet(0).getDays();

        for (int i = 0; i < (days.size()) && validHours == true; i++) {    
            if(days.get(i).isWorkingDay() == true) {
                int totalMinutes = this.getTotalHoursByDay(days.get(i));
                validHours = (totalMinutes >= 360);
            }
        }
        
        return validHours;
    }
    
    @Override
    public boolean hasValidHomeWeekHours(){
        // TODO: Définition de la classe par Christian
        return true;
    }
    
    private int getTotalHoursByDay(Day day) {
        int totalMinutes = 0;
        List<Task> tasks = day.getTasks();
        
        for (int j=0; j<tasks.size(); j++) {
            totalMinutes += (int)tasks.get(j).getTime();
        }
           
        return totalMinutes;
    }
    
}
