package timesheet;

import java.util.ArrayList;
import java.util.List;

public class Report {

    private static final String RULES_ERROR_1 = "Cet employé n'a pas fait le minimum d'heures requis du"
            + " lundi au vendredi physiquement au bureau.";
    private static final String RULES_ERROR_2 = "Cet employé n'a pas travaillé le nombre d'heures minimal"
            + " physiquement au bureau.";
    private static final String RULES_ERROR_3 = "Cet employé a fait plus d'heures de télétravail que la"
            + " quantité permise.";
    private static final String RULES_ERROR_4 = "Cet employé a passé plus d'heures physiquement au bureau"
            + " que la quantité permise.";
    private static final String RULES_ERROR_5 = "Cet employé a une journée invalide de congé de maladie.";
    private static final String RULES_ERROR_6 = "Cet employé a une journée invalide de congé férié.";
    private static final String RULES_ERROR_7 = "Cet employé a une journée avec plus de 24 heures travaillé.";
    private static final String RULES_ERROR_8 = "Cet employé a une journée qui ne respecte pas le nombre minimum"
            + " de minutes (" + TimeSheet.MINIMUM_MINUTES_AMOUNT_FOR_TASK + ") pour une tache.";

    private Employe employe;
    private List<String> report = new ArrayList<>();
    private Rules rules;

    public Report() {

    }

    public Report(Employe employe) {
        this();
        this.employe = employe;
    }

    public List<String> generateReport(Employe employe) {
        rules = intitializeRulesForThisEmploye(employe);
        buildReports();
        return report;
    }

    private void buildReports() {
        reportHasNotMinimumWeeklyTimeInOffice();
        reportInvalidDaysWithMinimumDailyTimeInOffice();
        reportHasNotValidWeeklyTimeRemote();
        reportHasNotValidWeeklyTimeInOffice();
        reportInvalidDaysWithSickLeave();
        reportInvalidDaysWithPublicHoliday();
        reportInvalidDaysWithToMuchTime(); // TODO GG
        reportDaysHasNotMinimumMinutesForATask();
    }
    
    public Employe getEmploye() {
        return employe;
    }

    public void setEmploye(Employe employe) {
        this.employe = employe;
    }

    private Rules intitializeRulesForThisEmploye(Employe employe) {
        RulesFactory rulesFactory = new RulesFactory();
        return rulesFactory.makeRules(employe);
    }

    private void reportHasNotMinimumWeeklyTimeInOffice() {
        if (!rules.hasMinimumWeeklyTimeInOffice())
            report.add(RULES_ERROR_1);
    }

    private void reportInvalidDaysWithMinimumDailyTimeInOffice() {
        if (rules.getInvalidDaysWithMinimumDailyTimeInOffice().size() > 0)
            for (int i = 0; i < rules.getInvalidDaysWithMinimumDailyTimeInOffice().size(); i++)
                report.add(RULES_ERROR_2 + " (" 
                        + rules.getInvalidDaysWithMinimumDailyTimeInOffice().get(i).getName() + ")");
    }

    private void reportHasNotValidWeeklyTimeRemote() {
        if (!rules.hasValidWeeklyTimeRemote())
            report.add(RULES_ERROR_3);
    }

    private void reportHasNotValidWeeklyTimeInOffice() {
        if (!rules.hasValidWeeklyTimeInOffice())
            report.add(RULES_ERROR_4);
    }
    
    private void reportInvalidDaysWithSickLeave() {
        String errorType;
        if (rules.getInvalidDaysWithSickLeave().size() > 0) {
            for (int i = 0; i < rules.getInvalidDaysWithSickLeave().size(); i++) {
                errorType = getErrorTypeForWorkWhileSick(rules.getInvalidDaysWithSickLeave().get(i));
                report.add(RULES_ERROR_5 + " (" 
                        + errorType + rules.getInvalidDaysWithSickLeave().get(i).getName() + ")");
            }
        }
    } 
    
    private String getErrorTypeForWorkWhileSick(Day day) {
        String errorType = "";
        if (day.hasOfficeTask())
            errorType = "travail au bureau - ";
        if (day.hasRemoteTask())
            errorType += "télé-travail - ";
        return errorType;
    }
    
    private void reportInvalidDaysWithPublicHoliday() {
        if (rules.getInvalidDaysWithPublicHoliday().size() > 0) {
            for (int i = 0; i < rules.getInvalidDaysWithPublicHoliday().size(); i++) {
                if (rules.getInvalidDaysWithPublicHoliday().get(i).hasOfficeTask()) 
                    report.add(RULES_ERROR_6 + " (travail au bureau - " 
                            + rules.getInvalidDaysWithPublicHoliday().get(i).getName() + ")");
                else
                    report.add(RULES_ERROR_6 + " (" + rules.getInvalidDaysWithPublicHoliday().get(i).getName() + ")");                                    
            }
        }
    }
    
    private void reportInvalidDaysWithToMuchTime() {
        if (rules.getInvalidDaysWithWrongTime().size() > 0) {
            for (int i = 0; i < rules.getInvalidDaysWithWrongTime().size(); i++)
                report.add(RULES_ERROR_7 + " (" + rules.getInvalidDaysWithWrongTime().get(i).getName() + ")");
        }
    }
    
    private void reportDaysHasNotMinimumMinutesForATask() {
        if (rules.getInvalidDaysWithoutMinimumMinutesForTask().size() > 0) {
            for (int i = 0; i < rules.getInvalidDaysWithoutMinimumMinutesForTask().size(); i++)
                report.add(RULES_ERROR_8 + " (" 
                        + rules.getInvalidDaysWithoutMinimumMinutesForTask().get(i).getName() + ")");
        }
    }
}
