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
    protected Employe employe;
    
    public Rules() {
        maxOfficeWeekMinutes = 43*60;
        maxHolidayWeekMinutes = 420;
    }
    
    abstract public boolean hasValidWeeklyTimeRemote();
    
    public boolean hasValidWeeklyTimeInOffice() {
        int officeWeekMinutes = totalWeekMinutes - totalRemoteWeekMinutes;
        return (officeWeekMinutes <= maxOfficeWeekMinutes);
    }
    
    public boolean hasMinimumWeeklyTimeInOffice() {
        int officeWeekMinutes = totalWeekMinutes - totalRemoteWeekMinutes;        
        return (officeWeekMinutes >= minOfficeWeekMinutes);
    }    
    
    public List<Day> getInvalidDaysWithMinimumDailyTimeInOffice(){
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
    
    protected void calculateTotalWeekMinutes() {
        List<Day> days = employe.getTimeSheet(0).getDays();       
        for (int i = 0; i < days.size(); i++)  
            calculateTotalWeekMinutesByTasks(days.get(i).getTasks());
    }
    
    protected void calculateTotalWeekMinutesByTasks(List<Task> tasks) {
        for (int i = 0; i < tasks.size(); i++) 
            sumTotalMinutes(tasks.get(i));
    }
    
    protected void sumTotalMinutes(Task task) {
        int minutes = (int)task.getTime();
        totalWeekMinutes += minutes;
        if (task.isRemoteTask())
            totalRemoteWeekMinutes += minutes;
        else
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
}
