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


public class RulesEmployes extends Rules {

    public RulesEmployes() {
        this.minOfficeWeekMinutes = 38*60;
        this.minOfficeDailyMinutes = 6*60;       
    }
    
    @Override
    public boolean hasMinimumOfficeWeekMinutes(Employe employe) {
        this.setTotalWeekMinutesByEmploye(employe);
        this.setTotalHomeWeekMinutesByEmploye(employe);
        
        int officeWeekHours;
        officeWeekHours = this.totalWeekMinutes - this.totalHomeWeekMinutes;
        
        return (officeWeekHours >= this.minOfficeWeekMinutes);
    }
    
    @Override 
    public boolean hasMinimumOfficeDailyMinutes(Employe employe){
        boolean  validHours = true;
        List<Day> days = employe.getTimeSheet(0).getDays();

        for (int i = 0; i < (days.size()) && validHours == true; i++) {    
            if(days.get(i).isWorkingDay() == true) {
                int totalMinutes = this.getTotalMinutesByDay(days.get(i));
                validHours = (totalMinutes >= 6*60);
            }
        }
        
        return validHours;
    }
    
    @Override
    public boolean hasValidHomeWeekMinutes(){
        
        return true;
    }

    @Override
    public boolean hasMaximumOfficeWeekMinutes() {
       return true; 
    }

    @Override
    public boolean hasMaximumteletravailWeekMinutes() {
       return true; 
    }
}
