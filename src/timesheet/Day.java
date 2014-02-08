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

/**
 * Objet Day - Une journée.
 * 
 */
public class Day {        
    private static final String WEEKDAY_STR_MATCH = "jour";    
    private String name = "";
    private final List<Task> tasks;
    
    /**
     * Constructeur de Day().
     * 
     */
    public Day() {        
        tasks = new ArrayList();        
    }
    
    /**
     * Constructeur avec paramètre de Day().
     * 
     * @param name - <b>String</b> Nom de la journée
     */
    public Day(String name) {        
        this();        
        this.name = name;        
    }    
        
    /**
     * Retourne le nombre de tâches dans la journée.
     * 
     * @return <b>int</b> - nombre de tâches.
     */
    public int getTasksNum() {        
        return tasks.size();        
    }
    
    /**
     * Retourne le nom de la journée.
     * 
     * @return <b>String</b> - le nom de la journée.
     */
    public String getName() {        
        return name;        
    }
    
    /**
     * Modifie le nom de la journée.
     * 
     * @param name nom de la journée.
     */
    public void setName(String name) {        
        this.name = name;        
    }
    
    /**
     * Ajoute une tâche à la journée.
     * 
     * @param id   numéro d'identification du projet.
     * @param time le temps en minutes passé sur le projet.
     * @return <b>Task</b> - <b>null</b> si erreur, sinon la tâche ajoutée
     */
    public Task addTask(int id, int time) {        
        Task task = new Task(id, time);         
        try {            
            tasks.add(task);            
        } catch (Exception e) {            
            return null;            
        }        
        return task;        
    }    
    
    /**
     * Ajoute une tâche à la journée.
     * 
     * @param task la tâche à ajoutée
     * @return <b>Task</b> - <b>null</b> si erreur, sinon la tâche ajoutée
     */
    public Task addTask(Task task) {         
        try {           
            tasks.add(task);            
        } catch (Exception e) {            
            return null;            
        }        
        return task;        
    }       
    
    /**
     * Retourne la tâche numéro index.
     * 
     * @param index position de la tâche.
     * @return <b>Task</b> - la tâche à la position <b>index</b>. <b>null</b> si non trouvée.
     */
    public Task getTask(int index) {            
        Task task;
        if (index < 0 || index >= tasks.size())
            return null;                                
        try {            
            task = tasks.get(index);            
        } catch (Exception e) {            
            return null;            
        }             
        return task;                    
    }
    
    /**
     * Retourne la liste des tâches de la journée.
     * 
     * @return <b>ArrayList&lt Task&gt</B> - liste des tâches de la journée.
     */
    public List<Task> getTasks() {            
        return tasks;                    
    }    
    
    /**
     * Est-ce que le jour est une jour de semaine?
     * 
     * @return <b>boolean</b> - <b>true</b> si le jour est un jour de semaine.
     */
    public boolean isWorkingDay() {        
        return name.substring(0, 4).equals(WEEKDAY_STR_MATCH);        
    }
        
    /**
     * Override de la méthode toString() par défaut
     * 
     * @return "Day{name: \"" + name + "\", tasks:" + getTasks() + "}"
     */     
    @Override
    public String toString() {        
        return "Day{name: \"" + name + "\", tasks:" + getTasks() + "}";        
    }    
}
