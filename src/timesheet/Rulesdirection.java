package timesheet;

public class Rulesdirection extends Rules {

    public Rulesdirection(Employe employe) {
        minOfficeWeekMinutes = 43*60;
        minOfficeDailyMinutes = 6*60; 
        setEmploye(employe);
        calculateTotalWeekMinutes();
    }

    @Override
    public boolean hasValidWeeklyTimeRemote(){ 
        return true;
    }
}
