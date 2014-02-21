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
        tasks = new ArrayList<Task>();        
    }
    
    public Day(final String name) { 
        this();
        if (!isValidDayName(name))
            throw new IllegalArgumentException("Day name " + name + " is not a valid day name!");         
        this.name = name;        
    }                 
    
    public void setName(final String name) {
        if (!isValidDayName(name))
            throw new IllegalArgumentException("Day name " + name + " is not a valid day name!");         
        this.name = name;        
    }

    public static boolean isValidDayName(final String name) {
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
    
    public Task addTask(final int id, final int time) {        
        Task task = new Task(id, time);         
        try {            
            tasks.add(task);            
        } catch (Exception e) { throw e; }        
        return task;        
    }    
    
    public Task addTask(final Task task) {         
        try {           
            tasks.add(task);            
        } catch (Exception e) { throw e; }        
        return task;        
    }       
    
    public Task getTask(final int index) {            
        Task task;
        if (index < 0 || index >= tasks.size())
            throw new IndexOutOfBoundsException("Index " + index + " is out of bounds!");                                
        try {            
            task = tasks.get(index);            
        } catch (Exception e) { throw e; }             
        return task;                    
    }
    
    public List<Task> getTasks() {            
        return tasks;                    
    }    
    
    // DEVRAIT FAIRE AUTRE CHOSE A LA PLACE DU TRY CATCH ?
    public boolean isWorkingDay() {
        try {
            return name.substring(0, 4).equals(WEEKDAY_STR_MATCH);        
        } catch (Exception e) {            
            return false;
        }
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
