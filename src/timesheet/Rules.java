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

abstract public class Rules {
    
    protected int maxOfficeWeekHours;
    protected int minOfficeWeekHours;
    protected int maxHomeWeekHours;
    protected int minOfficeDailyHours;
    protected int totalWeekHours;
    protected int totalOfficeWeekHours;
    protected int totalHomeWeekHours;
    protected int MaximumteletravailWeekHours;
    public Rules() 
    {
        this.maxOfficeWeekHours = 43;
        this.MaximumteletravailWeekHours = 10;
        //System.out.println( "Rules!" );
    }
    
    abstract public boolean hasMinimumOfficeWeekHours();
    
    abstract public boolean hasMinimumOfficeDailyHours(Employe employe);
    
    abstract public boolean hasValidHomeWeekHours();
    
    abstract public boolean hasMaximumOfficeWeekHours();
    abstract public boolean hasMaximumteletravailWeekHours();
    
    public int getMaxOfficeWeekHours() {
        return maxOfficeWeekHours;
    }

    public void setMaxOfficeWeekHours(int maxOfficeWeekHours) {
        this.maxOfficeWeekHours = maxOfficeWeekHours;
    }

    public int getMinOfficeWeekHours() {
        return minOfficeWeekHours;
    }

    public void setMinOfficeWeekHours(int minOfficeWeekHours) {
        this.minOfficeWeekHours = minOfficeWeekHours;
    }

    public int getMaxHomeWeekHours() {
        return maxHomeWeekHours;
    }

    public void setMaxHomeWeekHours(int maxHomeWeekHours) {
        this.maxHomeWeekHours = maxHomeWeekHours;
    }

    public int getMinOfficeDailyHours() {
        return minOfficeDailyHours;
    }

    public void setMinOfficeDailyHours(int minOfficeDailyHours) {
        this.minOfficeDailyHours = minOfficeDailyHours;
    }

    public int getTotalWeekHours() {
        return totalWeekHours;
    }

    public void setTotalWeekHours(int totalWeekHours) {
        this.totalWeekHours = totalWeekHours;
    }

    public int getTotalOfficeWeekHours() {
        return totalOfficeWeekHours;
    }

    public void setTotalOfficeWeekHours(int totalOfficeWeekHours) {
        this.totalOfficeWeekHours = totalOfficeWeekHours;
    }

    public int getTotalHomeWeekHours() {
        return totalHomeWeekHours;
    }

    public void setTotalHomeWeekHours(int totalHomeWeekHours) {
        this.totalHomeWeekHours = totalHomeWeekHours;
    }  
    
    public int getMaximumteletravailWeekHours(){
        return MaximumteletravailWeekHours;
    }
    public void setMaximumteletravailWeekHours(int MaximumteletravailWeekHours){
        this.MaximumteletravailWeekHours = MaximumteletravailWeekHours;
    }
}
