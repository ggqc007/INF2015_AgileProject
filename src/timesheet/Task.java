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
    
    public Task() {
        projectId = 0;
        time = 0;                
    }
    
    public Task(int id, int time) {        
        projectId = id;
        this.time = time;        
    }
    
    public int getProjectId() {        
        return projectId;        
    }
    
    public int getTime() {        
        return time;        
    }
    
    public void setProjectId(int id) {        
        projectId = id;        
    }
    
    public void setTime(int time) {        
        this.time = time;        
    }
    
    public boolean isRemoteTask() {        
        return (projectId > TimeSheet.REMOTE_TASK_ID_FLOOR && !isPublicHolidayTask() && !isSickLeaveTask());                
    }
    
    public boolean isPublicHolidayTask() {        
        return (projectId == TimeSheet.PUBLIC_HOLIDAY_TASK_ID);        
    }
    
    public boolean isSickLeaveTask() {      
        return (projectId == TimeSheet.SICK_LEAVE_TASK_ID); 
    }    

    @Override
    public String toString() {        
        return "Task{projectId: " + projectId + ", time: " + time +"}";        
    }    
}
