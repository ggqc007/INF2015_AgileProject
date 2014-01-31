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


public class Day {

    private String           name;
    private final List<Task> tasks;
        
    
    public Day() {
        
        tasks = new ArrayList();
        
    }       
        
        
    public int getTasksNum() {
        
        return tasks.size();
        
    }
        
            
    public String getName() {
        
        return this.name;
        
    }       
        
            
    public void setName(String name) {
        
        this.name = name;
        
    }   
                
        
    public void addTask(int id, int time) {
        
        Task task = new Task(id, time); 
        
        tasks.add(task);
        
    }  
                
        
    public Task getTask(int index) {
            
        // if index >= getTasksNum() throw exception
            
        return tasks.get(index);
                    
    }
        
    // public boolean isWorkingDay()             
    
}
