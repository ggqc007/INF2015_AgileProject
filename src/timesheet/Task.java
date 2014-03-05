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
    
    public boolean isSickLeaveTask() {      
        return (projectId == TimeSheet.SICK_LEAVE_TASK_ID); 
    }    

    @Override
    public String toString() {        
        return "Task{projectId: " + projectId + ", time: " + time +"}";        
    }    
}
