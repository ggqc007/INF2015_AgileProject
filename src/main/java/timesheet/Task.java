package timesheet;

public class Task {         
    private int projectId;
    private int time;    
    
    public Task(int id, int time) {        
        projectId = id;
        this.time = time;        
    }
    
    public int getProjectId() {
        return projectId;
    }
    
    public void setProjectId(int projectId) {
        this.projectId = projectId;
    }
    
    public int getTime() {        
        return time;        
    }
    
    public void setTime(int time) {
        this.time = time;
    }
    
    public boolean isOfficeTask() {        
        return (projectId <= TimeSheet.REMOTE_TASK_ID_FLOOR && isWorkTask());
    }
    
    public boolean isRemoteTask() {        
        return (projectId > TimeSheet.REMOTE_TASK_ID_FLOOR && isWorkTask());                
    }
    
    public boolean isWorkTask() {        
        return (!isPublicHolidayTask() && !isSickLeaveTask() && !isHolidayTask() && !isParentalHolidayTask());                
    }    
    
    public boolean isPublicHolidayTask() {        
        return (projectId == TimeSheet.PUBLIC_HOLIDAY_TASK_ID);        
    }
    
    public boolean isHolidayTask() {
        return (projectId == TimeSheet.HOLIDAY_TASK_ID);
    }
    
    public boolean isParentalHolidayTask() {
        return (projectId == TimeSheet.PARENTAL_HOLIDAY_TASK_ID);
    }    
    
    public boolean isSickLeaveTask() {      
        return (projectId == TimeSheet.SICK_LEAVE_TASK_ID); 
    }
    
    public boolean hasMininumMinutesAmountForTask() {
        return (time > TimeSheet.MINIMUM_MINUTES_AMOUNT_FOR_TASK);
    }
    
    public boolean isTransportationTask() {
        return (projectId == TimeSheet.TRANSPORTATION_ID);
    }
    
    @Override
    public String toString() {        
        return "Task{projectId: " + projectId + ", time: " + time +"}";        
    }    
}
