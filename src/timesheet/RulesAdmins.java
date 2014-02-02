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


public class RulesAdmins extends Rules {

    public RulesAdmins() {
        this.minOfficeWeekMinutes = 36;
        this.minOfficeDailyMinutes = 4; 
        this.MaximumteletravailWeekMinutes = 10;
    }
    
    @Override
    public boolean hasMinimumOfficeWeekMinutes(Employe employe) {
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
                validHours = (totalMinutes >= 240);
            }
        }
        
        return validHours; 
    }
    
    @Override
    public boolean hasValidHomeWeekMinutes(){
        boolean ValidHomeWeekHours;
        Employe employe = new Employe();
        ValidHomeWeekHours =hasMinimumOfficeDailyMinutes(employe);
        if (ValidHomeWeekHours) {
            return ValidHomeWeekHours;
        }
        return false;
    }

    @Override
    public boolean hasMaximumOfficeWeekMinutes() {
        int officeWeekHours;
        officeWeekHours = this.totalWeekMinutes - this.totalHomeWeekMinutes;
        
        return (officeWeekHours >= this.maxOfficeWeekMinutes);
    }

    @Override
    public boolean hasMaximumteletravailWeekMinutes() {
      int officeWeekHours;
        officeWeekHours = this.totalWeekMinutes - this.totalHomeWeekMinutes;
        
        return (officeWeekHours >= this.MaximumteletravailWeekMinutes);
    }
}
