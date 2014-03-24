package timesheet;

public class RulesDirection extends Rules {

    public RulesDirection(Employe employe) {
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
