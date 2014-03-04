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

public class TimeSheetData {    
    protected static final String[] WEEKDAYS_NAMES = {"weekend2", "jour1", "jour2", "jour3", "jour4", "jour5", "weekend1"};     
    
    private static final int DEFAULT_DAYS_NUM = 7;       
    private int employeId;
    private final List<Day> days;    
        
    public TimeSheetData() {        
        days = new ArrayList<>(DEFAULT_DAYS_NUM);          
        for (int i = 0; i < DEFAULT_DAYS_NUM; i++)
            days.add(new Day());                
    }     
    
    public Day addDay(String name) {        
        Day day = new Day();                                      
        day.setName(name);
        days.add(day);                               
        return day;        
    }
    
    public Day setDay(int index, Day day) {        
        if (index < 0 || index >= days.size())
            throw new IndexOutOfBoundsException("Index " + index + " is out of bounds!");                     
        days.set(index, day);                               
        return day;        
    }     
         
    public Day setDayByName(Day day) {        
        if (containsDay(day.getName()))
            throw new IllegalArgumentException("Day name " + day.getName() + " is already in the timesheet!");             
        for (int i = 0; i < WEEKDAYS_NAMES.length; i++)            
            if (WEEKDAYS_NAMES[i].equals(day.getName())) {                                    
                days.set(i, day);                                
                return day;                
            }                              
        throw new IllegalArgumentException("Day name " + day.getName() + " is not a valid day name!");    
    }    
    
    public int getDaysNum() {        
        return days.size();        
    }    
           
    public Day getDay(int index) {           
        if (index < 0 || index >= days.size())
            throw new IndexOutOfBoundsException("Index " + index + " is out of bounds!");                           
        return days.get(index);                    
    }    
    
    public boolean containsDay(String name) {
        try {        
            Day day = getDayByName(name);
        } catch (IllegalArgumentException e) {
            return false;
        }        
        return true;        
    }
        
    public Day getDayByName(String name) {
        String dayName;             
        for (Day day : days) {            
            dayName = day.getName();                        
            if (dayName != null && dayName.equals(name))
                return day;                        
        }  
        throw new IllegalArgumentException("Day name " + name + " is not found!");                    
    }      
           
    public List<Day> getDays() {      
        return days;                    
    }       
       
    public Task addTaskToDay(Task task, int dayIndex) {     
        if (dayIndex < 0 || dayIndex >= days.size())
            throw new IndexOutOfBoundsException("Index " + dayIndex + " is out of bounds!");                                        
        return days.get(dayIndex).addTask(task);        
    }    

    public Task addTaskToDayByName(Task task, String dayName) {        
        Day day = getDayByName(dayName);        
        if (day != null)           
            return day.addTask(task);                            
        throw new NullPointerException("Task is null");                 
    }    

    public Task getTaskFromDay(int taskIndex, int dayIndex) {  
        Task task;
        if (dayIndex < 0 || dayIndex >= days.size())
            throw new IndexOutOfBoundsException("Index " + dayIndex + " is out of bounds!");                
        if (taskIndex < 0 || taskIndex >= days.get(dayIndex).getTasksNum())
            throw new IndexOutOfBoundsException("Index " + taskIndex + " is out of bounds!");                                                          
        task = days.get(dayIndex).getTask(dayIndex);                     
        return task;                
    }    

    public Task getTaskFromDayByName(int taskIndex, String dayName) { 
        if (taskIndex < 0)
            return null;                
        Day day = getDayByName(dayName);        
        if (day != null) {            
            if (taskIndex >= day.getTasksNum())
                throw new IndexOutOfBoundsException("Index " + taskIndex + " is out of bounds!");                       
            return day.getTask(taskIndex);            
        }        
        throw new IllegalArgumentException("Day name " + dayName + " is not found!");         
    }  
    
    public int getEmployeId() {        
        return employeId;        
    }
    
    public void setEmployeId(final int id) { 
        if (id < 0)
            throw new IllegalArgumentException("Employe id " + id + " is not valid!");
        employeId = id;        
    }
    
    public boolean hasValidWeek() {        
        for (int i = 0; i < days.size(); i++) {
            String name = days.get(i).getName();
            if (name == null || !name.equals(WEEKDAYS_NAMES[i]))
                return false;  
        }
        return true;        
    }
        
    @Override
    public String toString() {        
        return "TimeSheetData{employeId: " + employeId + ", days: " + getDays() + "}";        
    }    
}
