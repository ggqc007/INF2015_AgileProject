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
       // Note le prof à dit que le président est un employé de direction donc ici on ne test pas si il 
       // est président, il faut qu'on en discute pour être sûr. J'ai enlevé le test de président car 
       // dans la méthode test canChargeTransport... de Christian il ne test pas le président.
       return (employeId > TimeSheet.EMPLOYE_DIRECTION_ID_FLOOR); 
       
       // return ((employeId > TimeSheet.EMPLOYE_DIRECTION_ID_FLOOR) && (employeId != TimeSheet.PREDIDENT_ID));      
    }
    
    public boolean isPresident() {
        return (employeId == TimeSheet.PREDIDENT_ID);          
    }
    
    // TODO: REMOVE - Utilisée seulement dans DEBUG
    public int getId() {        
        return employeId;        
    }
    
    @Override
    public String toString() {        
        return "Employe{employeId: " + employeId + ", timesheets: " + getTimeSheets() + "}";        
    } 
}
