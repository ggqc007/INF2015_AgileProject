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
 * Objet TimeSheetData - Une feuille de temps hebdomadaire d'un employé
 */
public class TimeSheetData {
    
    private int             employeId;
    private final List<Day> days;

    
    /**
     * Construncteur de l'objet TimeSheetData().
     * 
     */
    public TimeSheetData() {
        
        this.days = new ArrayList();  
        
    }   
    
    
    // public TimeSheet(JSONObject json)

    
    /**
     * Ajoute une journée dans le timesheet.
     * 
     * @param name - <b>String</b> Nom de la journée à ajouter.
     * @return <b>Day</b> - Référence vers l'objet Day ajouté.
     */
    public Day addDay(String name) {
        
        Day day = new Day();
        
        day.setName(name);
        
        this.days.add(day);
        
        return day;
        
    }

    
    /**
     * Retourne le nombre de journées dans le timesheet.
     * 
     * @return <b>int</b> - Le nombre de journées.
     */
    public int getDaysNum() {
        
        return this.days.size();
        
    }    

    
    /**
     * Retourne la journée à la position <b>index</b>.
     * 
     * @param index - Position de la journée.
     * @return <b>Day</b> - La journée demandée.
     */        
    public Day getDay(int index) {
           
        // if index >= getDayNum() throw exception
            
        return this.days.get(index);
                    
    }    

    
    /**
     * Retourne la journée ayant le nom <b>name</b>.
     * 
     * @param name - Nom de la journée.
     * @return <b>Day</b> - La journée demandée, null si non trouvée.
     */        
    public Day getDayByName(String name) {

        for (Day day : days ) {
        
            if (day.getName().equals(name))
                return day;
            
        }
        
        return null;
                    
    }  
    
    
    /**
     * Retourne la liste des journées.
     * 
     * @return <b>List&lt Day&gt</b> - La journée demandée.
     */        
    public List<Day> getDays() {
      
        return days;
                    
    }    
    
    
    /**
     * Retourne le numéro d'identification de l'employé.
     * 
     * @return <b>int</b> - numéro d'identification.
     */
    public int getEmployeId() {
        
        return this.employeId;
        
    }

    
    /**
     * Modification du numéro d'identification de l'employé.
     * 
     * @param id - <b>int</b> numéro d'identification.
     */
    public void setEmployeId(int id) {
        
        this.employeId = id;
        
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
