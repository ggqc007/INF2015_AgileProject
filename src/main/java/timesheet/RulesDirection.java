package timesheet;

import java.util.List;

public class RulesDirection extends Rules {

    public RulesDirection(Employe employe) {
        minOfficeWeekMinutes = 2580;
        minOfficeDailyMinutes = 480;
        maxOfficeWeekMinutes = 0;
        setEmploye(employe);
        calculateTotalWeekMinutes();
        calculateTotalWeekTransportTime();        
    }
    
    @Override
    protected int getTotalOfficeMinutesByDay(Day day) {
        int totalMinutes = 0;
        List<Task> tasks = day.getTasks();
        for (int i = 0; i < tasks.size(); i++) 
            if (!tasks.get(i).isRemoteTask() && !tasks.get(i).isTransportationTask()) 
                totalMinutes += (int)tasks.get(i).getTime();
        return totalMinutes;
    }
    
    @Override
    protected int getTotalRemoteMinutesByDay(Day day) {
        int totalMinutes = 0;
        List<Task> tasks = day.getTasks();
        for (int i = 0; i < tasks.size(); i++) 
            if (tasks.get(i).isRemoteTask() || tasks.get(i).isTransportationTask()) 
                totalMinutes += (int)tasks.get(i).getTime();        
        return totalMinutes;
    }

    @Override
    public boolean hasValidWeeklyTimeRemote() {
        return true;
    }
    
    @Override
    public boolean hasValidWeeklyTimeInOffice () {
        return true;
    }
    
    @Override
    public boolean hasValidWeeklyTransportTime() {                
        return (this.getTotalTransportTime() <= TimeSheet.MAX_TRANSPORTATION_TIME_DIRECTION);
    }
}