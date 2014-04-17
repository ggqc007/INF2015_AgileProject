package timesheet;

public class RulesPresident extends Rules {

    public RulesPresident(Employe employe) {
        minOfficeWeekMinutes = 2580;
        minOfficeDailyMinutes = 480;
        maxOfficeWeekMinutes = 0;        
        setEmploye(employe);
        calculateTotalWeekMinutes();
        calculateTotalWeekTransportTime();        
    }

    @Override
    public boolean hasValidWeeklyTimeRemote() {
        return true;
    }
    
    @Override
    public boolean hasValidWeeklyTimeInOffice () {
        return true;
    }
    
    @Override
    public boolean hasValidWeeklyTransportTime() {                
        return true;
    }    
}
