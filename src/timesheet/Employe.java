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


import java.util.ArrayList;
import java.util.List;


/**
 * Objet Employe
 * 
 */
public class Employe {

    private int                       employeId;
    private final List<TimeSheetData> timesheets;
    
    
    /**
     * Constructeur de Employe.
     */
    public Employe() {
        
        TimeSheetData timesheet = new TimeSheetData();
        
        timesheets = new ArrayList();
        
        timesheets.add(timesheet);
        
    }

    
    /**
     * Constructeur avec paramètres de Employe.
     * 
     * @param id Numéro d'identification de l'employé.
     */
    public Employe(int id) {
        
        this();
        
        this.employeId = id;
        
        timesheets.get(0).setEmployeId(id);
        
    }

    
    /**
     * Retourne le numéro d'identification de l'employé.
     * 
     * @return <b>int</b> - Id de l'employé.
     */
    public int getId() {
        
        return this.employeId;
        
    }

    
    /**
     * Modification du numéro d'identification de l'employé.
     * 
     * @param id - Numéro d'identification de l'employé.
     */
    public void setId(int id) {
        
        this.employeId = id;
        
        for(TimeSheetData timesheet : timesheets) {
            
            timesheet.setEmployeId(id);
            
        }
        
    }

    
    /**
     * Retourne le timesheet à la position <b>index</b> de l'employé.
     * 
     * @param index Position de l'employé dans l'index.
     * @return <b>TimeSheetData</b> - Le timesheet demandé de l'employé.
     */
    public TimeSheetData getTimeSheet(int index) {
        
        // TODO: throw exception si out of bound!
        
        return timesheets.get(index);
        
    }
    
    
    // TODO: List<TimeSheetData> getTimeSheets()
    
    // TODO: addTimeSheet(TimeSheetData timesheet)
    
    
    /**
     * Retourne le nombre de timesheet de l'employé.
     * 
     * @return <b>int</b> - Nombre de timesheets.
     */
    public int getTimeSheetNum() {
        
        return timesheets.size();
        
    }  
    
    
    /**
    * Est-ce que l'employé est un administrateur?
    *
    * @return <b>boolean</b> - <b>true</b> si administrateur.
    */
    public boolean isAdmin() {
        
        return (employeId < 1000);
        
    } 
    
}
