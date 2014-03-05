package timesheet;

import java.util.List;
import java.util.ArrayList;

public class Employe {   
    private int employeId;
    private final List<TimeSheetData> timesheets;    
        
    public Employe() {        
        TimeSheetData timesheet = new TimeSheetData();        
        timesheets = new ArrayList<>();        
        timesheets.add(timesheet);        
    }
    
    public Employe(int id) {        
        this();    
        if (id < 0)
            throw new IllegalArgumentException("Employe id " + id + " is not valid!");
        employeId = id;        
        timesheets.get(0).setEmployeId(id);        
    }
    
    public void initFromFirstTimeSheet(TimeSheetData timesheet) {
        if (timesheet.getEmployeId() < 0)
            throw new IllegalArgumentException("Employe id in timesheet is not valid!");
        employeId = timesheet.getEmployeId();
        timesheets.set(0, timesheet);        
    }
    
    public int getId() {        
        return employeId;        
    }
    
    public void setId(int id) {  
        if (id < 0)
            throw new IllegalArgumentException("Employe id " + id + " is not valid!");        
        employeId = id;                
        for(TimeSheetData timesheet : timesheets)            
            timesheet.setEmployeId(id);                            
    }
       
    public TimeSheetData getTimeSheet(int index) {        
        if (index < 0 || index >= timesheets.size())
            throw new IndexOutOfBoundsException("Index " + index + " is out of bounds!");                                   
        return timesheets.get(index);                    
    }
    
    public List<TimeSheetData> getTimeSheets() {        
        return timesheets;        
    }     
    
    public boolean isAdmin() {        
        return ((employeId >= 0) && (employeId < TimeSheet.EMPLOYE_ADMIN_ID_CEILING));        
    } 
    
    public boolean isProdEmploye() {        
       return ((employeId >= TimeSheet.EMPLOYE_ADMIN_ID_CEILING) && (employeId < TimeSheet.EMPLOYE_PROD_ID_CEILING));           
    }
    
    public boolean isExplEmploye() {        
       return (employeId >= TimeSheet.EMPLOYE_PROD_ID_CEILING);           
    }    
       
    @Override
    public String toString() {        
        return "Employe{employeId: " + employeId + ", timesheets: " + getTimeSheets() + "}";        
    }    
}
