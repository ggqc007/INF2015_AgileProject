package timesheet;

public class RulesAdmins extends Rules {

    public RulesAdmins(Employe employe) {
        minOfficeWeekMinutes = 2250;
        minOfficeDailyMinutes = 240; 
        maxRemoteWeekMinutes = 600;
        maxOfficeWeekMinutes = 2490;
        setEmploye(employe);
        calculateTotalWeekMinutes();
    }
    
    @Override
    public boolean hasValidWeeklyTimeRemote(){
        return (totalRemoteWeekMinutes <= maxRemoteWeekMinutes);
    }
    
    @Override
    public boolean hasValidWeeklyTransportTime() {        
        return (this.getTotalTransportTime() <= TimeSheet.MAX_TRANSPORTATION_TIME_ADMIN);
    }    
}
