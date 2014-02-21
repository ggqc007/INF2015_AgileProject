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

public class Employe {   
    private int employeId;
    private final List<TimeSheetData> timesheets;    
        
    public Employe() {        
        TimeSheetData timesheet = new TimeSheetData();        
        timesheets = new ArrayList<>();        
        timesheets.add(timesheet);        
    }
    
    public Employe(final int id) {        
        this();    
        if (id < 0)
            throw new IllegalArgumentException("Employe id " + id + " is not valid!");
        employeId = id;        
        timesheets.get(0).setEmployeId(id);        
    }
    
    public void initFromFirstTimeSheet(final TimeSheetData timesheet) {
        if (timesheet.getEmployeId() < 0)
            throw new IllegalArgumentException("Employe id in timesheet is not valid!");
        employeId = timesheet.getEmployeId();
        timesheets.set(0, timesheet);        
    }
    
    public int getId() {        
        return employeId;        
    }
    
    public void setId(final int id) {  
        if (id < 0)
            throw new IllegalArgumentException("Employe id " + id + " is not valid!");        
        employeId = id;                
        for(TimeSheetData timesheet : timesheets)            
            timesheet.setEmployeId(id);                            
    }
    
    public TimeSheetData getTimeSheet(final int index) {        
        if (index < 0 || index >= timesheets.size())
            throw new IndexOutOfBoundsException("Index " + index + " is out of bounds!");                
        try {                    
            return timesheets.get(index);            
        } catch (Exception e) { throw e; }        
    }
    
    public TimeSheetData setTimeSheet(final int index, final TimeSheetData timesheet) {   
        // TODO: PEUT-ETRE CLONER LE TIMESHEET ET MODIFIER LE ID COMME addTimeSheet
        if (timesheet.getEmployeId() < 0)
            throw new IllegalArgumentException("Employe id in timesheet is not valid!");        
        if (index < 0 || index >= timesheets.size())
            throw new IndexOutOfBoundsException("Index " + index + " is out of bounds!");        
        try {                    
            timesheets.set(index,timesheet);            
        } catch (Exception e) { throw e; } 
        return timesheet;        
    }
    
    public List<TimeSheetData> getTimeSheets() {        
        return timesheets;        
    }
    
    public TimeSheetData addTimeSheet(final TimeSheetData timesheet) { 
        if (timesheet.getEmployeId() < 0)
            throw new IllegalArgumentException("Employe id in timesheet is not valid!");        
        TimeSheetData newTimesheet = new TimeSheetData(timesheet);        
        newTimesheet.setEmployeId(employeId);        
        try {            
            timesheets.add(newTimesheet);            
        } catch (Exception e) { throw e; }        
        return newTimesheet;        
    }
    
    public int getTimeSheetsNum() {        
        return timesheets.size();        
    }      
    
    public boolean isAdmin() {        
        return ((employeId >= 0) && (employeId < TimeSheet.EMPLOYE_ADMIN_ID_CEILING));        
    } 
    
    public boolean isProdEmploye() {        
       return ((employeId >= TimeSheet.EMPLOYE_ADMIN_ID_CEILING) && (employeId < TimeSheet.EMPLOYE_PROD_ID_CEILING));           
    }
    
    public boolean isExplEmploye() {        
       return ( employeId >= TimeSheet.EMPLOYE_PROD_ID_CEILING);           
    }    
       
    @Override
    public String toString() {        
        return "Employe{employeId: " + employeId + ", timesheets: " + getTimeSheets() + "}";        
    }    
}
