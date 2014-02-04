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
    
    private static final String[] WEEKDAYS_INDEX   = {"weekend2", "jour1", "jour2", "jour3", "jour4", "jour5", "weekend1"};
    private static final int      ADMIN_IDS        = 1000;
    private static final int      DEFAULT_DAYS_NUM = 7;   
    
    private int             employeId;
    private final List<Day> days;    
    
    /**
     * Constructeur de l'objet TimeSheetData().
     * 
     */
    public TimeSheetData() {
        
        days = new ArrayList(DEFAULT_DAYS_NUM);  
        
        for (int i = 0; i < DEFAULT_DAYS_NUM; i++)
            days.add(new Day());
        
    }   
    
    
    /**
     * Ajoute une journée dans le timesheet.
     * 
     * @param name <b>String</b> Nom de la journée à ajouter.
     * @return <b>Day</b> - Référence vers l'objet Day ajouté, <b>null</b> si non ajouté.
     */
    public Day addDay(String name) {
        
        Day day = new Day();
        
        day.setName(name);
        
        try {
                    
            days.add(day);
        
        } catch (Exception e) {
                    
            return null;
                    
        }          
        
        return day;
        
    }

    
    /**
     * Place une journée à une position spécifique en mémoire.
     * 
     * @param index Position de la journée dans la liste.
     * @param day Objet <b>Day</b> à placer.
     * @return <b>Day</b> - <b>null</b> si non ajouté, <b>day</b> si ajouté.
     */
    public Day setDay(int index, Day day) {
        
        if (index < 0 || index >= days.size())
            return null;
        
        try {
            
            days.set(index, day);
        
        } catch (Exception e) {
                    
            return null;
                    
        }        
        
        return day;
        
    } 
    
    
    /**
     * Ajoute la journée selon son nom à sa position respective.
     * soit index 0 à 6 dans cet ordre <i>dim,lun,mar,mer,jeu,ven,sam</i>
     * 
     * @param day Objet <b>Day</b> à placer.
     * @return <b>Day</b> - <b>null</b> si non ajouté, <b>day</b> si ajouté.
     */        
    public Day setDayByName(Day day) {
        
        for (int i = 0; i < WEEKDAYS_INDEX.length; i++) {
            
            if (WEEKDAYS_INDEX[i].equals(day.getName())) {
                
                try {
                    
                    days.set(i, day);
                    
                } catch (Exception e) {
                    
                    return null;
                    
                }
            
                return day;
                
            }
            
        }
        
        return null;
        
    }    

    
    /**
     * Retourne le nombre de journées dans le timesheet.
     * 
     * @return <b>int</b> - Le nombre de journées.
     */
    public int getDaysNum() {
        
        return days.size();
        
    }    

    
    /**
     * Retourne la journée à la position <b>index</b>.
     * 
     * @param index Position de la journée.
     * @return <b>Day</b> - La journée demandée. <b>null</b> si non trouvée.
     */        
    public Day getDay(int index) {
           
        if (index < 0 || index >= days.size())
            return null;
            
        return days.get(index);
                    
    }    

    
    /**
     * Retourne la journée ayant le nom <b>name</b>.
     * 
     * @param name Nom de la journée.
     * @return <b>Day</b> - La journée demandée, <b>null</b> si non trouvée.
     */        
    public Day getDayByName(String name) {

        String dayName;
        
        for (Day day : days) {
            
            dayName = day.getName();
            
            if (dayName != null && dayName.equals(name))
                return day;
            
        }
        
        return null;
                    
    }      
    
    
    /**
     * Retourne la liste des journées.
     * 
     * @return <b>List&lt Day&gt</b> - Liste des journées dans le timesheet.
     */        
    public List<Day> getDays() {
      
        return days;
                    
    }    
    
  
    /**
     * Ajoute une tâche à la journée à la position <b>index</b>.
     * 
     * @param task La tâche.
     * @param dayIndex Positions du jour dans la liste.
     * @return <b>Task</b> - La tâche ajoutée. <b>null</b> si non ajoutée.
     */        
    public Task addTaskToDay(Task task, int dayIndex) {
        
        if (dayIndex < 0 || dayIndex >= days.size())
            return null;
        
        try {
            
            task = days.get(dayIndex).addTask(task);
            
        } catch (Exception e) {
            
            return null;
            
        }

        return task;
        
    }
    

    /**
     * Ajoute une tâche à la journée nommée  <b>dayName</b>.
     * 
     * @param task La tâche.
     * @param dayName Nom de la journée.
     * @return <b>Task</b> - La tâche ajoutée. <b>null</b> si non ajoutée.
     */
    public Task addTaskToDayByName(Task task, String dayName) {
        
        Day day = getDayByName(dayName);
        
        if (day != null) {            

            return day.addTask(task);
            
        }
        
        return null;
        
    }
    

    /**
     * Retourne la no <b>taskIndex</b> de la journée à la position <b>index</b>.
     *
     * @param taskIndex Numéro de la tâche (position dans la liste).
     * @param dayIndex Positions du jour dans la liste.
     * @return <b>Task</b> - La tâche trouvée, <b>null</b> si non trouvée. 
     */
    public Task getTaskFromDay(int taskIndex, int dayIndex) {
  
        if (dayIndex < 0 || dayIndex >= days.size())
            return null;
        
        if (taskIndex < 0 || taskIndex >= days.get(dayIndex).getTasksNum())
            return null;              
        
        Task task;
        
        try {
            
            task = days.get(dayIndex).getTask(dayIndex);
            
        } catch (Exception e) {
            
            return null;
            
        }  
        
        return task;
                
    }
    

    /**
     * Retourne la no <b>taskIndex</b> de la journée nommée <b>dayName</b>.
     *
     * @param taskIndex Numéro de la tâche (position dans la liste).
     * @param dayName Nom de la journée.
     * @return <b>Task</b> - La tâche trouvée, <b>null</b> si non trouvée. 
     */
    public Task getTaskFromDayByName(int taskIndex, String dayName) {
 
        if (taskIndex < 0)
            return null;
        
        Day day = getDayByName(dayName);
        
        if (day != null) {
            
            if (taskIndex >= day.getTasksNum())
                return null;
            
            return day.getTask(taskIndex);
            
        }
        
        return null;
        
    }  

    
    /**
     * Retourne le numéro d'identification de l'employé.
     * 
     * @return <b>int</b> - numéro d'identification.
     */
    public int getEmployeId() {
        
        return employeId;
        
    }

    
    /**
     * Modification du numéro d'identification de l'employé.
     * 
     * @param id <b>int</b> numéro d'identification.
     */
    public void setEmployeId(int id) {
        
        employeId = id;
        
    }

    
    /**
     * Est-ce que l'employé est un administrateur?
     * 
     * @return <b>boolean</b> - <b>true</b> si administrateur.
     */
    public boolean isAdmin() {
        
        return (employeId < ADMIN_IDS);
        
    } 
    
    
    /**
     * Override de la méthode toString() par défaut
     * 
     * @return "TimeSheetData{employeId: " + employeId + ", days: " + getDays() + "}"
     */ 
    @Override
    public String toString() {
        
        return "TimeSheetData{employeId: " + employeId + ", days: " + getDays() + "}";
        
    }
    
}
