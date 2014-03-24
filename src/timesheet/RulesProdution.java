package timesheet;

public class RulesProdution extends Rules {

    public RulesProdution(Employe employe) {
        minOfficeWeekMinutes = 2280;
        minOfficeDailyMinutes = 360; 
        setEmploye(employe);
        calculateTotalWeekMinutes();
    }

    @Override
    public boolean hasValidWeeklyTimeRemote(){ 
        return true;
    }
}
