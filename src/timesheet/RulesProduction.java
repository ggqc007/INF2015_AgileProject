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

    @Override
    public boolean hasMinimumWeeklyTimeInOffice() {
        return true;
    }
    
    @Override
    public List<Day> getInvalidDaysWithMinimumDailyTimeInOffice(){
       // Les employés normaux doivent faire un minimum de 6 heures au bureau pour les jours
        // ouvrables (lundi au vendredi). 
        List<Day> invalidDays = new ArrayList(); 
        List<Day> days = employe.getTimeSheet(0).getDays();       
        for (int i = 0; i < days.size(); i++) {    
            if (days.get(i).isWorkingDay() == true) {
                if (getTotalOfficeMinutesByDay(days.get(i)) < minOfficeDailyMinutes) {
                    invalidDays.add(days.get(i));
                }
            }
        }
        return invalidDays;
    }
    
    @Override
    public boolean hasValidWeeklyTimeRemote(){ 
        return true;
    }
    
    @Override
    public boolean hasValidWeeklyTimeInOffice() { 
         // Aucun employé n'a le droit de passer plus de 43 heures au bureau.
        int officeWeekMinutes = totalWeekMinutes - totalRemoteWeekMinutes;
        return (officeWeekMinutes <= maxOfficeWeekMinutes);
    }
}
