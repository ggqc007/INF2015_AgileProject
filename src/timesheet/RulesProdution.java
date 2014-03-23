package timesheet;

public class RulesProdution extends Rules {

    public RulesProdution(Employe employe) {
        minOfficeWeekMinutes = 38*60;
        minOfficeDailyMinutes = 6*60; 
        setEmploye(employe);
        calculateTotalWeekMinutes();
    }

    @Override
    public boolean hasValidWeeklyTimeRemote(){ 
        return true;
    }
}
