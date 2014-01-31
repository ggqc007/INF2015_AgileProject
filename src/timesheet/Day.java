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
 *
 * @author user
 */
public class Day {

    private String           name;
    private final List<Task> tasks;

    
    /**
     *
     */
    public Day() {
        
        tasks = new ArrayList();
        
    }

    
    /**
     *
     * @return
     */
    public int getTasksNum() {
        
        return tasks.size();
        
    }

    
    /**
     *
     * @return
     */
    public String getName() {
        
        return this.name;
        
    }

    
    /**
     *
     * @param name
     */
    public void setName(String name) {
        
        this.name = name;
        
    }

    
    /**
     * Ajoute une tâche à la journée
     * 
     * @param id   numéro d'identification du projet
     * @param time le temps en minutes passé sur le projet
     */
    public void addTask(int id, int time) {
        
        Task task = new Task(id, time); 
        
        tasks.add(task);
        
    }

    
    /**
     * Retourne la tâche numéro index.
     * 
     * @param index position de la tâche
     * @return la tâche (<b>Task</b>) à la position <b>index</b>
     */
    public Task getTask(int index) {
            
        // if index >= getTasksNum() throw exception
            
        return tasks.get(index);
                    
    }

    
    /**
     * Retourne la liste des tâches de la journée.
     * 
     * @return un <b>ArrayList&lt Task&gt</B> contenant les tâches de la journée
     */
    public List<Task> getTasks() {
            
        return tasks;
                    
    }
    
    
    /**
     * Est-ce que le jour est une jour de semaine ?
     * 
     * @return <b>true</b> si le jour est un jour de semaine.
     */
    public boolean isWorkingDay() {
        
        return name.substring(0, 5).equals("jour");
        
    }
    
}
