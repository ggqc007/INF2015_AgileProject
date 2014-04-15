package timesheet;

public class RulesDevelopment extends Rules {

    public RulesDevelopment(Employe employe) {
        minOfficeWeekMinutes = 2280;
        minOfficeDailyMinutes = 360; 
        setEmploye(employe);
        calculateTotalWeekMinutes();
        calculateTotalWeekTransportTime();        
    }

    @Override
    public boolean hasValidWeeklyTimeRemote(){ 
        return true;
    }
}
