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
    
    public void initFromFirstTimeSheet(TimeSheetData timesheet) {
        employeId = timesheet.getEmployeId();
        timesheets.set(0, timesheet);        
    }
       
    public TimeSheetData getTimeSheet(int index) {        
        if (index < 0 || index >= timesheets.size())
            throw new IndexOutOfBoundsException("Index " + index + " is out of bounds!");                                   
        return timesheets.get(index);                    
    }
    
    public List<TimeSheetData> getTimeSheets() {        
        return timesheets;        
    }
    
    public void setEmployeId(int id) {
        employeId = id;
    }
    
    public boolean isAdmin() {        
        return ((employeId >= 0) && (employeId < TimeSheet.EMPLOYE_ADMIN_ID_CEILING));        
    } 
    
    public boolean isDevelEmploye() {        
       return ((employeId >= TimeSheet.EMPLOYE_ADMIN_ID_CEILING) && (employeId < TimeSheet.EMPLOYE_DEVEL_ID_CEILING));           
    }
    
    public boolean isExplEmploye() {        
       return ((employeId >= TimeSheet.EMPLOYE_DEVEL_ID_CEILING) && (employeId <= TimeSheet.EMPLOYE_DIRECTION_ID_FLOOR));       
    }    
    
    public boolean isDirectionEmploye() {        
       return (employeId > TimeSheet.EMPLOYE_DIRECTION_ID_FLOOR);    
    }
    
    public boolean isPresident() {
        return (employeId == TimeSheet.PREDIDENT_ID);          
    }
    
    @Override
    public String toString() {        
        return "Employe{employeId: " + employeId + ", timesheets: " + getTimeSheets() + "}";        
    } 
}
