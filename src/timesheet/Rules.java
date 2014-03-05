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

abstract public class Rules {
    
    protected int maxOfficeWeekMinutes;
    protected int minOfficeWeekMinutes;
    protected int maxRemoteWeekMinutes;
    protected int maxHolidayWeekMinutes;
    protected int minOfficeDailyMinutes;
    protected int totalWeekMinutes = 0;
    protected int totalOfficeWeekMinutes = 0;
    protected int totalRemoteWeekMinutes = 0;
    //protected int totalHolidayWeekMinutes = 0;
    //protected int totalSickLeaveWeekMinutes = 0;
    protected Employe employe;
    
    public Rules() {
        maxOfficeWeekMinutes = 43*60;
        maxHolidayWeekMinutes = 420;
    }
    
    abstract public boolean hasValidWeeklyTimeRemote();
    
    public boolean hasValidWeeklyTimeInOffice() {
        // Aucun employé n'a le droit de passer plus de 43 heures au bureau.
        int officeWeekMinutes = totalWeekMinutes - totalRemoteWeekMinutes;
        return (officeWeekMinutes <= maxOfficeWeekMinutes);
    }
    
    public boolean hasMinimumWeeklyTimeInOffice() {
        // Les employés normaux doivent travailler au moins 38 heures au bureau par semaine
        // (excluant le télétravail).
        int officeWeekMinutes = totalWeekMinutes - totalRemoteWeekMinutes;        
        return (officeWeekMinutes >= minOfficeWeekMinutes);
    }    
    
    public List<Day> getInvalidDaysWithMinimumDailyTimeInOffice(){
        // Les employés normaux doivent faire un minimum de 6 heures au bureau pour les jours
        // ouvrables (lundi au vendredi). 
        List<Day> invalidDays = new ArrayList<>(); 
        List<Day> days = employe.getTimeSheet(0).getDays();       
        for (int i = 0; i < days.size(); i++)    
            if (days.get(i).isWorkingDay() == true && getTotalOfficeMinutesByDay(days.get(i)) < minOfficeDailyMinutes) 
                invalidDays.add(days.get(i));
       return invalidDays;
    }    
    
    public List<Day> getInvalidDaysWithSickLeave() {
        List<Day> invalidDays = new ArrayList<>(); 
        List<Day> days = employe.getTimeSheet(0).getDays();       
        for (int i = 0; i < days.size(); i++)    
            if (!days.get(i).isValidSickLeave() && days.get(i).hasSickLeaveTask()) 
                invalidDays.add(days.get(i));
       return invalidDays;       
    }
 
    public List<Day> getInvalidDaysWithPublicHoliday() {
        List<Day> invalidDays = new ArrayList<>(); 
        List<Day> days = employe.getTimeSheet(0).getDays();       
        for (int i = 0; i < days.size(); i++)    
            if (!days.get(i).isValidPublicHoliday() && days.get(i).hasPublicHolidayTask()) 
                invalidDays.add(days.get(i));
       return invalidDays;       
    }
    
    public List<Day> getInvalidDaysWithWrongTime() {
        List<Day> invalidDays = new ArrayList<>(); 
        List<Day> days = employe.getTimeSheet(0).getDays();       
        for (int i = 0; i < days.size(); i++)    
            if (!days.get(i).hasValidHours()) 
                invalidDays.add(days.get(i));
       return invalidDays;       
    }    
    
    /*public void prepData(){
        calculateTotalWeekMinutes();
    }*/
    
    protected void calculateTotalWeekMinutes() {
        List<Day> days = employe.getTimeSheet(0).getDays();       
        for (int i = 0; i < days.size(); i++)  
            if (days.get(i).isNormalDay() || days.get(i).isValidSickLeave() || days.get(i).isValidPublicHoliday())
                calculateTotalWeekMinutesByTasks(days.get(i).getTasks());
            else if ( (days.get(i).hasSickLeaveTask() && !days.get(i).isValidSickLeave()) || (days.get(i).hasPublicHolidayTask() && !days.get(i).isValidPublicHoliday()))
                calculateTotalWeekMinutesByTasks(days.get(i).getTasks());
    }
    
    protected void calculateTotalWeekMinutesByTasks(List<Task> tasks) {
        for (int i = 0; i < tasks.size(); i++) 
            sumTotalMinutes(tasks.get(i));
    }
    
    protected void sumTotalMinutes(Task task) {
        int minutes = (int)task.getTime();
        totalWeekMinutes += minutes;
        if (task.isRemoteTask()) // Exclu les congés de maladie (999) et les congés fériés (998)
            totalRemoteWeekMinutes += minutes;
        else // Puisque les congés de maladie (999) et les congés fériés (998) NE SONT PLUS COMPTÉS COMME télétravail, par défaut on les additionne au total de présence au bureau
            totalOfficeWeekMinutes += minutes;        
    }

    protected int getTotalOfficeMinutesByDay(Day day) {
        int totalMinutes = 0;
        List<Task> tasks = day.getTasks();
        for (int i = 0; i < tasks.size(); i++) 
            if (!tasks.get(i).isRemoteTask()) 
                totalMinutes += (int)tasks.get(i).getTime();
        return totalMinutes;
    }

    protected int getTotalRemoteMinutesByDay(Day day) {
        int totalMinutes = 0;
        List<Task> tasks = day.getTasks();
        for (int i = 0; i < tasks.size(); i++) 
            if (tasks.get(i).isRemoteTask()) 
                totalMinutes += (int)tasks.get(i).getTime();        
        return totalMinutes;
    }
    
    public void setEmploye(Employe employe) {
        this.employe = employe;
    }
    
    // TODO Enlever getter au moment d'enlever méthode debug
    public int getMaxOfficeWeekMinutes() {
        return maxOfficeWeekMinutes;
    }

    public int getMinOfficeWeekMinutes() {
        return minOfficeWeekMinutes;
    }

    public int getMaxRemoteWeekMinutes() {
        return maxRemoteWeekMinutes;
    }

    public int getMinOfficeDailyMinutes() {
        return minOfficeDailyMinutes;
    }

    public int getTotalWeekMinutes() {
        return totalWeekMinutes;
    }

    public int getTotalOfficeWeekMinutes() {
        return totalOfficeWeekMinutes;
    }

    public int getTotalRemoteWeekMinutes() {
        return totalRemoteWeekMinutes;
    }
}
