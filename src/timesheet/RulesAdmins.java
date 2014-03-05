package timesheet;

public class RulesAdmins extends Rules {

    public RulesAdmins(Employe employe) {
        minOfficeWeekMinutes = 36*60;
        minOfficeDailyMinutes = 4*60; 
        maxRemoteWeekMinutes = 10*60;
        setEmploye(employe);
        calculateTotalWeekMinutes();
    }
    
    @Override
    public boolean hasValidWeeklyTimeRemote(){
        // Les employés de l'administration ne doivent pas faire plus de 10 heures de télétravail par
        // semaine.
        return (totalRemoteWeekMinutes <= maxRemoteWeekMinutes);
    }
}
