package timesheet;

import java.util.List;
import java.util.ArrayList;

public class TimeSheetData {    
    protected static final String[] WEEKDAYS_NAMES = {"weekend2", "jour1", "jour2", "jour3", "jour4",
        "jour5", "weekend1"};    
    
    private static final int DEFAULT_DAYS_NUM = 7;       
    private int employeId;
    private final List<Day> days;    
        
    public TimeSheetData() {        
        days = new ArrayList<Day>(DEFAULT_DAYS_NUM);          
        for (int i = 0; i < DEFAULT_DAYS_NUM; i++)
            days.add(new Day());                
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
    
    // ATTENTION IL FAUT APPELER CETTE METHODE DANS RULES QUAND ON FAIT LES TESTS
    // DE isValidParentalHoliday() POUR SAVOIR SI IL Y A JUSTE UN ParentalHoliday
    // PAR SEMAINE, SINON ILS SONT TOUS INVALIDES
    public boolean hasOnlyOneParentalHolidayByWeek() {
        boolean foundParentalHoliday = false;
        for (Day day : days)
            if (day.hasParentalHolidayTask() && foundParentalHoliday)
                return false;   
            else if (day.hasParentalHolidayTask())
                foundParentalHoliday = true;
        return true;
    } 
    
    // TODO: REMOVE - Utilisée seulement dans DEBUG
    public int getDaysNum() {        
        return days.size();        
    }
    
    // TODO: REMOVE - Utilisée seulement dans DEBUG
    public Day getDay(int index) {           
        if (index < 0 || index >= days.size())
            throw new IndexOutOfBoundsException("Index " + index + " is out of bounds!");                           
        return days.get(index);                    
    }
    
    @Override
    public String toString() {        
        return "TimeSheetData{employeId: " + employeId + ", days: " + getDays() + "}";        
    }    
}
