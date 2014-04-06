package timesheet;

public class RulesPresident extends Rules {

    // Pour l'instant President = Direction, je dois attendre la mise en 
    // place du temps de transport pour compléter cet classe
    public RulesPresident(Employe employe) {
        minOfficeWeekMinutes = 2580;
        minOfficeDailyMinutes = 360;
        maxOfficeWeekMinutes = 0;        
        setEmploye(employe);
        calculateTotalWeekMinutes();
    }

    @Override
    public boolean hasValidWeeklyTimeRemote() {
        return true;
    }
    
    @Override
    public boolean hasValidWeeklyTimeInOffice () {
        return true;
    }    
}
