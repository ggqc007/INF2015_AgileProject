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
    
    public void setName(String name) {
        if (!isValidDayName(name))
            throw new IllegalArgumentException("Day name " + name + " is not a valid day name!");         
        this.name = name;        
    }

    public static boolean isValidDayName(String name) {
        for (String dayName : TimeSheetData.WEEKDAYS_NAMES) {
            if (dayName.equals(name))
                return true;         
        }
        return false;
    }
    
    public String getName() {        
        return name;        
    } 
    
    public int getTasksNum() {        
        return tasks.size();        
    }      
    
    public Task addTask(int id, int time) {        
        Task task = new Task(id, time);                  
        tasks.add(task);                   
        return task;        
    }    
    
    public Task addTask(Task task) {                   
        tasks.add(task);                  
        return task;        
    }       
    
    public Task getTask(int index) {            
        Task task;
        if (index < 0 || index >= tasks.size())
            throw new IndexOutOfBoundsException("Index " + index + " is out of bounds!");                                         
        task = tasks.get(index);                        
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
    
    // PEUT-ETRE A REFACTORER
    // TODO Thomas, est-ce que tu peux valider que seulement télétravail est valide comme code supplémentaire lors d'un congé férié
    // Lors d'un congé férié, il est permis de faire du télétravail en plus durant la journée.
    public boolean isValidPublicHoliday() {
        if (!isWorkingDay() || !hasPublicHolidayTask())
            return false; 
        int pubHolidayTime = 0;
        for (Task task : tasks) {
            if (task.isPublicHolidayTask())
                pubHolidayTime += task.getTime(); 
            else if (task.isSickLeaveTask() || !task.isRemoteTask())
                return false;
        }           
        return (pubHolidayTime == TimeSheet.PUBLIC_HOLIDAY_TIME);        
    }
    
    public boolean hasPublicHolidayTask() {        
        for (Task task : tasks) {
            if (task.isPublicHolidayTask())
                return true;
        }
        return false;        
    }
    
    // TODO Thomas, est-ce que tu peux valider si cet énoncé est respécté - Christian
    // Il n'est pas permis d'avoir d'autres activités professionnelles lors d'un congé de maladie (pas d'autre temps sur d'autres codes). 
    public boolean isValidSickLeave() {       
        if (!isWorkingDay() || !hasSickLeaveTask())
            return false; 
        int sickLeaveTime = 0;
        for (Task task : tasks) {
            if (!task.isSickLeaveTask())
                return false;
            else
                sickLeaveTime += task.getTime(); 
        }           
        return (sickLeaveTime == TimeSheet.SICK_LEAVE_TIME);          
    }    
    
    public boolean isNormalDay() {        
        return (!hasSickLeaveTask() && !hasPublicHolidayTask());
    }
    
    public boolean hasSickLeaveTask() {
        for (Task task : tasks) {
            if (task.isSickLeaveTask())
                return true;
        }          
        return false;
    } 
    
    public boolean hasOfficeTask() {
        for (Task task : tasks) {
            if (task.isOfficeTask())
                return true;
        }          
        return false;
    }   
    
    public boolean hasRemoteTask() {
        for (Task task : tasks) {
            if (task.isRemoteTask())
                return true;
        }          
        return false;
    }    
    
    public boolean hasValidHours() {
        int totalHours = 0;
        for (Task task : getTasks()) {            
            totalHours += task.getTime();
            if (totalHours > 24 * 60)
                return false;            
        }                
        return true;        
    }
           
    @Override
    public String toString() {        
        return "Day{name: \"" + name + "\", tasks:" + getTasks() + "}";        
    }    
}