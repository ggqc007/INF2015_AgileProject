package timesheet;

public class RulesProduction  extends Rules {

    public RulesProduction(Employe employe) {
        minOfficeWeekMinutes = 38*60;
        minOfficeDailyMinutes = 6*60; 
        setEmploye(employe);
    }

    @Override
    public boolean hasValidWeeklyTimeRemote(){ 
        return true;
    }
}
