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
    private final String[]  WEEKDAYS_INDEX;

    
    /**
     * Construncteur de l'objet TimeSheetData().
     * 
     */
    public TimeSheetData() {
        
        this.WEEKDAYS_INDEX = new String[]{"weekend2", "jour1", "jour2", "jour3", "jour4", "jour5", "weekend1"};
        
        this.days = new ArrayList(7);  
        
    }   
    
    
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
     * Place une journée à une position spécifique en mémoire.
     * 
     * @param index - Position de la journée dans la liste.
     * @param day - Objet <b>day</d> à placer.
     */
    public void setDay(int index, Day day) {
        
        this.days.set(index, day);
        
    } 
    
    
    /**
     * Ajoute la journée selon son nom à sa position respective.
     * soit index 0 à 6 dans cet ordre <i>dim,lun,mar,mer,jeu,ven,sam</i>
     * 
     * @param day - Objet <b>day</d> à placer.
     */        
    public void setDayByName(Day day) {
        
        for (int i = 0; i < this.days.size(); i++) {
            
            if (this.days.get(i).getName().equals(day.getName()))
                this.days.set(i, day);
            
        }
        
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
           
        if (index <= 0 || index >= this.days.size())
            return null;
            
        return this.days.get(index);
                    
    }    

    
    /**
     * Retourne la journée ayant le nom <b>name</b>.
     * 
     * @param name - Nom de la journée.
     * @return <b>Day</b> - La journée demandée, <b>null</b> si non trouvée.
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
