package timesheet;

public class RulesAdmins extends Rules {

    public RulesAdmins(Employe employe) {
        minOfficeWeekMinutes = 37.5*60;
        minOfficeDailyMinutes = 4*60; 
        maxRemoteWeekMinutes = 10*60;
        maxOfficeWeekMinutes = 41.5*60;
        setEmploye(employe);
        calculateTotalWeekMinutes();
    }
    
    @Override
    public boolean hasValidWeeklyTimeRemote(){
        return (totalRemoteWeekMinutes <= maxRemoteWeekMinutes);
    }
}
