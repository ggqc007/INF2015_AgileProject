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
        if (!isWorkingDay() || !hasPublicHolidayTask() || hasHolidayTask() || hasSickLeaveTask() || hasParentalHolidayTask())
            return false;         
        return (getPublicHolidayTime() == TimeSheet.PUBLIC_HOLIDAY_TIME);        
    }
        
    protected int getPublicHolidayTime() {             
        int pubHolidayTime = 0;
        for (Task task : tasks)
            if (task.isPublicHolidayTask())
                pubHolidayTime += task.getTime(); 
            else if (!task.isWorkTask())
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
        if (!isWorkingDay() || !hasSickLeaveTask() || hasHolidayTask() || hasPublicHolidayTask() || hasParentalHolidayTask())
            return false;         
        return (getSickLeaveTime() == TimeSheet.SICK_LEAVE_TIME);          
    }   
    
    protected int getSickLeaveTime() {             
        int sickLeaveTime = 0;
        for (Task task : tasks)
            if (!task.isSickLeaveTask())
                return 0;
            else
                sickLeaveTime += task.getTime();                  
        return sickLeaveTime;          
    }     
    
    public boolean isNormalDay() {        
        return (!hasSickLeaveTask() && !hasPublicHolidayTask() && !hasHolidayTask() && !hasParentalHolidayTask());
    }
    
    public boolean hasSickLeaveTask() {
        for (Task task : tasks)
            if (task.isSickLeaveTask())
                return true;                  
        return false;
    } 
    
    public boolean isValidHoliday() { 
        if (!isWorkingDay() || !hasHolidayTask() || hasPublicHolidayTask() || hasSickLeaveTask() || hasParentalHolidayTask())
            return false;         
        return (getHolidayTime() == TimeSheet.HOLIDAY_TIME);          
    }   
    
    protected int getHolidayTime() {             
        int holidayTime = 0;
        for (Task task : tasks)
            if (!task.isHolidayTask() && !(task.isOfficeTask() || task.isRemoteTask()))
                return 0;
            else if (task.isHolidayTask())
                holidayTime += task.getTime();                  
        return holidayTime;          
    }      
    
    public boolean hasHolidayTask() {
        for (Task task : tasks)
            if (task.isHolidayTask())
                return true;                  
        return false;
    }    
    
    public boolean isValidParentalHoliday() {    
        if (!isWorkingDay() || !hasParentalHolidayTask() || hasPublicHolidayTask() || hasSickLeaveTask() || hasHolidayTask())
            return false;      
        return (getParentalHolidayTime() == TimeSheet.PARENTAL_HOLIDAY_TIME);          
    } 
    
    protected int getParentalHolidayTime() {             
        int parentalHolidayTime = 0;
        for (Task task : tasks)
            if (!task.isParentalHolidayTask())
                return 0;
            else
                parentalHolidayTime += task.getTime();                  
        return parentalHolidayTime;          
    }    

    public boolean hasParentalHolidayTask() {
        for (Task task : tasks)
            if (task.isParentalHolidayTask())
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
        return getTotalMinutesWorkedThisDay() <= TimeSheet.MAXIMUM_HOURS_FOR_DAY*60;        
    }
    
    public boolean hasValidTasksAfter24Hours() {
        for (Task task : tasks)
                if (task.isPublicHolidayTask() || task.isHolidayTask())
                    return true;
        return false;
    }
    
    public boolean hasTaskWithLessThanMinimumMinutesAmount() {
        for (Task task : tasks)
            if (!task.hasMininumMinutesAmountForTask())
                return true;
        return false;
    }
    
    public int getTotalMinutesWorkedThisDay() {
        int totalMinutes = 0;
        for (Task task : getTasks())          
            totalMinutes += task.getTime();
        return totalMinutes;
    }
    
    @Override
    public String toString() {        
        return "Day{name: \"" + name + "\", tasks:" + getTasks() + "}";        
    }     
}