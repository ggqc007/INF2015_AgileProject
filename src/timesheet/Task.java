package timesheet;

public class Task {         
    private int projectId;
    private int time;    
    
    public Task(int id, int time) {        
        projectId = id;
        this.time = time;        
    }
    
    public int getTime() {        
        return time;        
    }
    
    public boolean isOfficeTask() {        
        return (projectId <= TimeSheet.REMOTE_TASK_ID_FLOOR);
    }
    
    public boolean isRemoteTask() {        
        return (projectId > TimeSheet.REMOTE_TASK_ID_FLOOR && !isPublicHolidayTask() && !isSickLeaveTask());                
    }
    
    public boolean isPublicHolidayTask() {        
        return (projectId == TimeSheet.PUBLIC_HOLIDAY_TASK_ID);        
    }
    
    public boolean isVacancyDayTask() {
        return (projectId == TimeSheet.VACANCY_DAY_TASK_ID);
    }
    
    public boolean isSickLeaveTask() {      
        return (projectId == TimeSheet.SICK_LEAVE_TASK_ID); 
    }
    
    public boolean hasMininumMinutesAmountForTask() {
        return (time > TimeSheet.MINIMUM_MINUTES_AMOUNT_FOR_TASK);
    }
}
