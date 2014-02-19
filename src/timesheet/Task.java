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

public class Task {         
    private int projectId;
    private int time;    
    
    public Task(final int id, final int time) {        
        projectId = id;
        this.time = time;        
    }
    
    public int getProjectId() {        
        return projectId;        
    }
    
    public int getTime() {        
        return time;        
    }
    
    public void setProjectId(final int id) {        
        projectId = id;        
    }
    
    public void setTime(final int time) {        
        this.time = time;        
    }
    
    public boolean isRemoteTask() {        
        return (projectId > TimeSheet.REMOTE_TASK_ID_FLOOR);                
    }

    @Override
    public String toString() {        
        return "Task{projectId: " + projectId + ", time: " + time +"}";        
    }    
}
