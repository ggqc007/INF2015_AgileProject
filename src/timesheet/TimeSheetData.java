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
        days = new ArrayList<Day>(DEFAULT_DAYS_NUM);          
        for (int i = 0; i < DEFAULT_DAYS_NUM; i++)
            days.add(new Day());                
    }     
    
    public TimeSheetData(final TimeSheetData timesheet) {
        if (timesheet.getEmployeId() < 0)
            throw new IllegalArgumentException("Employe id in timesheet is not valid!");  
        this.employeId = timesheet.getEmployeId();        
        this.days = timesheet.getDays();        
    }   
    
    public Day addDay(final String name) {        
        Day day = new Day();                  
        try {                    
            day.setName(name);
            days.add(day);        
        } catch (Exception e) { throw e; }                        
        return day;        
    }
    
    public Day setDay(final int index, final Day day) {        
        if (index < 0 || index >= days.size())
            throw new IndexOutOfBoundsException("Index " + index + " is out of bounds!");                     
        try {            
            days.set(index, day);        
        } catch (Exception e) { throw e; }                        
        return day;        
    }     
         
    public Day setDayByName(final Day day) {        
        if (containsDay(day.getName()))
            throw new IllegalArgumentException("Day name " + day.getName() + " is already in the timesheet!");             
        for (int i = 0; i < WEEKDAYS_NAMES.length; i++)            
            if (WEEKDAYS_NAMES[i].equals(day.getName())) {
                try {                    
                    days.set(i, day);                    
                } catch (Exception e) { throw e; }            
                return day;                
            }                              
        throw new IllegalArgumentException("Day name " + day.getName() + " is not a valid day name!");    
    }    
    
    public int getDaysNum() {        
        return days.size();        
    }    
           
    public Day getDay(final int index) {           
        if (index < 0 || index >= days.size())
            throw new IndexOutOfBoundsException("Index " + index + " is out of bounds!");                           
        return days.get(index);                    
    }    
    
    public boolean containsDay(final String name) {
        try {        
            Day day = getDayByName(name);
        } catch (IllegalArgumentException e) {
            return false;
        }        
        return true;        
    }
        
    public Day getDayByName(final String name) {
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
       
    public Task addTaskToDay(final Task task, final int dayIndex) {     
        // TODO : J'ai ajouté newTask car j'ai rajouté le final pour task
        //        à repenser à ça quand je vais mettre des throw exceptions
        Task newTask;
        if (dayIndex < 0 || dayIndex >= days.size())
            throw new IndexOutOfBoundsException("Index " + dayIndex + " is out of bounds!");         
        try {            
            newTask = days.get(dayIndex).addTask(task);            
        } catch (Exception e) { throw e; }
        return newTask;        
    }    

    public Task addTaskToDayByName(final Task task, final String dayName) {        
        Day day = getDayByName(dayName);        
        if (day != null)           
            return day.addTask(task);                            
        throw new NullPointerException("Task is null");                 
    }    

    public Task getTaskFromDay(final int taskIndex, final int dayIndex) {  
        Task task;
        if (dayIndex < 0 || dayIndex >= days.size())
            throw new IndexOutOfBoundsException("Index " + dayIndex + " is out of bounds!");                
        if (taskIndex < 0 || taskIndex >= days.get(dayIndex).getTasksNum())
            throw new IndexOutOfBoundsException("Index " + taskIndex + " is out of bounds!");                                      
        try {            
            task = days.get(dayIndex).getTask(dayIndex);            
        } catch (Exception e) { throw e; }          
        return task;                
    }    

    public Task getTaskFromDayByName(final int taskIndex, final String dayName) { 
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
    
    public boolean isAdmin() {        
        return (employeId < TimeSheet.EMPLOYE_ADMIN_ID_CEILING);        
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
