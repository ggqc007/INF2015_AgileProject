package timesheet;

public class RulesDirection extends Rules {

    public RulesDirection(Employe employe) {
        minOfficeWeekMinutes = 2580;
        minOfficeDailyMinutes = 360;
        setEmploye(employe);
        calculateTotalWeekMinutes();
    }

    @Override
    public boolean hasValidWeeklyTimeRemote() {
        return true;
    }
}
