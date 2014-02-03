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


/**
 * Objet Task - Une tâche exécutée sur un projet.
 * 
 */
public class Task {
    
    private int projectId;
    private int time;
    
    private static final int REMOTE_TASK_IDS = 900;

    
    /**
     * Constructeur avec paramêtres de Task().
     * 
     * @param id - Numéro d'identification du projet.
     * @param time - Temps en minutes de la tâche.
     */
    public Task(int id, int time) {
        
        projectId = id;
        this.time = time;
        
    }

    
    /**
     * Retourne le numéro de projet sur lequel la tâche est exécutée.
     * 
     * @return <b>int</b> - nombre du minutes passées sur la tâche.
     */
    public int getProjectId() {
        
        return projectId;
        
    }

    
    /**
     * Retourne la durée de la tâche en minutes.
     * 
     * @return <b>int</b> - durée de la tâche en minutes.
     */
    public int getTime() {
        
        return time;
        
    }

    
    /**
     * Modification du numéro de projet sur lequel la tâche est exécutée.
     * 
     * @param id - <b>int</b> nombre de minutes.
     */
    public void setProjectId(int id) {
        
        projectId = id;
        
    }

    
    /**
     * Modification de la durée de la tâche en minutes.
     * 
     * @param time - <b>int</b> - nombre de minutes passées sur le projet.
     */
    public void setTime(int time) {
        
        this.time = time;
        
    }

    
    /**
     * Est-ce que la tâche est une tâche sur un projet télétravail?
     * 
     * @return - <b>boolean</b> - <b>true</b> si télétravail.
     */
    public boolean isRemoteTask() {
        
        return (projectId > REMOTE_TASK_IDS);
                
    }
    
    
    @Override
    public String toString() {
        
        return "{[projectID: " + projectId + ", time: " + time +"}";
        
    }
    
}
