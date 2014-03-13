package timesheet;

import java.util.List;
import java.util.ArrayList;

public class Day {
    protected static final String WEEKDAY_STR_MATCH = "jour";
    
    private String name = "";
    private final List<Task> tasks;
    
    public Day() {        
        tasks = new ArrayList<>();        
    }
    
    public Day(String name) { 
        this();
        if (!isValidDayName(name))
            throw new IllegalArgumentException("Day name " + name + " is not a valid day name!");         
        this.name = name;        
    }                 

    public static boolean isValidDayName(String name) {
        for (String dayName : TimeSheetData.WEEKDAYS_NAMES)
            if (dayName.equals(name))
                return true;         
        return false;
    }
    
    public String getName() {        
        return name;        
    }
    
    public Task addTask(int id, int time) {        
        Task task = new Task(id, time);                  
        tasks.add(task);                   
        return task;        
    }         
    
    public List<Task> getTasks() {            
        return tasks;                    
    }    
    
    public boolean isWorkingDay() {
        try {
            return name.substring(0, 4).equals(WEEKDAY_STR_MATCH);        
        } catch (Exception e) {            
            return false;
        }
    }    

    public boolean isValidPublicHoliday() {
        if (!isWorkingDay() || !hasPublicHolidayTask())
            return false;         
        return (getPublicHolidayTime() == TimeSheet.PUBLIC_HOLIDAY_TIME);        
    }
        
    private int getPublicHolidayTime() {             
        int pubHolidayTime = 0;
        for (Task task : tasks)
            if (task.isPublicHolidayTask())
                pubHolidayTime += task.getTime(); 
            else if (task.isSickLeaveTask() || !task.isRemoteTask())
                return 0;                   
        return pubHolidayTime;          
    }  
    
    public boolean hasPublicHolidayTask() {        
        for (Task task : tasks)
            if (task.isPublicHolidayTask())
                return true;        
        return false;        
    } 
    
    public boolean isValidSickLeave() {       
        if (!isWorkingDay() || !hasSickLeaveTask())
            return false;         
        return (getSickLeaveTime() == TimeSheet.SICK_LEAVE_TIME);          
    }   
    
    private int getSickLeaveTime() {             
        int sickLeaveTime = 0;
        for (Task task : tasks)
            if (!task.isSickLeaveTask())
                return 0;
            else
                sickLeaveTime += task.getTime();                  
        return sickLeaveTime;          
    }     
    
    public boolean isNormalDay() {        
        return (!hasSickLeaveTask() && !hasPublicHolidayTask());
    }
    
    public boolean hasSickLeaveTask() {
        for (Task task : tasks)
            if (task.isSickLeaveTask())
                return true;                  
        return false;
    } 
    
    public boolean hasOfficeTask() {
        for (Task task : tasks)
            if (task.isOfficeTask())
                return true;       
        return false;
    }   
    
    public boolean hasRemoteTask() {
        for (Task task : tasks)
            if (task.isRemoteTask())
                return true;                  
        return false;
    }    
    
    public boolean hasValidMaximumHours() {
        int totalHours = 0;
        for (Task task : getTasks()) {            
            totalHours += task.getTime();
            if (totalHours > TimeSheet.MAXIMUM_HOURS_FOR_DAY*60)
                return false;
        }                
        return true;        
    }
    
    public boolean hasTaskWithLessThanMinimumMinutesAmount() {
        for (Task task : tasks)
            if (!task.hasMininumMinutesAmountForTask())
                return true;
        return false;
    }
}