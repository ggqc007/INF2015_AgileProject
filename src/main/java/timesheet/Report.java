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
    private static final String RULES_ERROR_7 = "Cet employé a une journée avec plus de "
            + TimeSheet.MAXIMUM_HOURS_FOR_DAY + " heures travaillé.";
    private static final String RULES_ERROR_8 = "Cet employé a une journée qui ne respecte pas le nombre minimum"
            + " de minutes (" + TimeSheet.MINIMUM_MINUTES_AMOUNT_FOR_TASK + ") pour une tache.";
    private static final String RULES_ERROR_9 = "Cet employé a une journée avec plus de 24 heures qui ne"
            + " comporte pas de temps de journée de vacances ou de congé férié";    
    private static final String RULES_ERROR_10 = "Cet employé a au moins une journée de congé parental invalide";
    private static final String RULES_ERROR_11 = "Cet employé a plusieurs activités avec le même code de projet pour une même journée";
    private static final String RULES_ERROR_12 = "Cet employé a une journée invalide de congé de vacances.";
    private static final String RULES_ERROR_13 = "Cet employé a plus d'une journée de congé parental par semaine.";    
    private static final String RULES_ERROR_14 = "Cet employé a déclaré du temps de transport non permis."; 
    private static final String RULES_ERROR_15 = "Cet employé a déclaré plus de temps de transport par semaine que la limite permise."; 
    
    private Employe employe;
    private final List<String> report = new ArrayList<>();
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

    protected void buildReports() {
        buildInvalidTasksReports();
        buildInvalidDaysReports();
        buildInvalidWeekReports();
    }
    
    protected void buildInvalidTasksReports() {
        reportDaysHasNotMinimumMinutesForATask();
        reportInvalidDaysWithInvalidTasksAfter24Hours();
        reportInvalidWithDuplicateTasks();
    }
        
    protected void buildInvalidDaysReports() {
        reportInvalidDaysWithMinimumDailyTimeInOffice();
        reportInvalidDaysWithSickLeave();
        reportInvalidDaysWithPublicHoliday();
        reportInvalidDaysWithToMuchTime();
        reportInvalidDaysOfParentalHoliday();
        reportInvalidDaysOfHoliday();
    }
    
    protected void buildInvalidWeekReports() {
        reportHasNotMinimumWeeklyTimeInOffice();
        reportHasNotValidWeeklyTimeRemote();
        reportHasNotValidWeeklyTimeInOffice();
        reportHasOnlyOneParentalHolidayByWeek();
        reportHasNotValidWeeklyTransportTime();
    }
    
    public Employe getEmploye() {
        return employe;
    }

    public void setEmploye(Employe employe) {
        this.employe = employe;
    }

    protected Rules intitializeRulesForThisEmploye(Employe employe) {
        RulesFactory rulesFactory = new RulesFactory();
        return rulesFactory.makeRules(employe);
    }

    protected void reportDaysHasNotMinimumMinutesForATask() {
        if (rules.getInvalidDaysWithoutMinimumMinutesForTask().size() > 0) {
            for (int i = 0; i < rules.getInvalidDaysWithoutMinimumMinutesForTask().size(); i++)
                report.add(RULES_ERROR_8 + " (" 
                        + rules.getInvalidDaysWithoutMinimumMinutesForTask().get(i).getName() + ")");
        }
    }
    
    protected void reportInvalidDaysWithInvalidTasksAfter24Hours() {
        if (rules.getInvalidDaysWithInvalidTasksAfter24Hours().size() > 0) {
            for (int i = 0; i < rules.getInvalidDaysWithInvalidTasksAfter24Hours().size(); i++)
                report.add(RULES_ERROR_9 + " (" 
                        + rules.getInvalidDaysWithInvalidTasksAfter24Hours().get(i).getName() + ")");
        }
    }
    
    protected void reportInvalidWithDuplicateTasks() {
        if (rules.getInvalidDaysWithDuplicateTasks().size() > 0) {
            for (int i = 0; i < rules.getInvalidDaysWithDuplicateTasks().size(); i++)
                report.add(RULES_ERROR_11 + " (" 
                        + rules.getInvalidDaysWithDuplicateTasks().get(i).getName() + ")");
        }  
    }
    
    protected void reportInvalidDaysWithMinimumDailyTimeInOffice() {
        if (rules.getInvalidDaysWithMinimumDailyTimeInOffice().size() > 0)
            for (int i = 0; i < rules.getInvalidDaysWithMinimumDailyTimeInOffice().size(); i++)
                report.add(RULES_ERROR_2 + " (" 
                        + rules.getInvalidDaysWithMinimumDailyTimeInOffice().get(i).getName() + ")");
    }
    
    protected void reportInvalidDaysWithSickLeave() {
        String errorType;
        if (rules.getInvalidDaysWithSickLeave().size() > 0) {
            for (int i = 0; i < rules.getInvalidDaysWithSickLeave().size(); i++) {
                errorType = getErrorTypeForWorkWhileSick(rules.getInvalidDaysWithSickLeave().get(i));
                report.add(RULES_ERROR_5 + " (" 
                        + errorType + rules.getInvalidDaysWithSickLeave().get(i).getName() + ")");
            }
        }
    } 
    
    protected void reportInvalidDaysWithPublicHoliday() {
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
    
    protected void reportInvalidDaysWithToMuchTime() {
        if (rules.getInvalidDaysWithToMuchTime().size() > 0) {
            for (int i = 0; i < rules.getInvalidDaysWithToMuchTime().size(); i++)
                report.add(RULES_ERROR_7 + " (" + rules.getInvalidDaysWithToMuchTime().get(i).getName() + ")");
        }
    }
    
    protected void reportInvalidDaysOfParentalHoliday() {
        if (rules.getInvalidDaysOfParentalHoliday().size() > 0) {
            for (int i = 0; i < rules.getInvalidDaysOfParentalHoliday().size(); i++)
                report.add(RULES_ERROR_10 + " (" 
                        + rules.getInvalidDaysOfParentalHoliday().get(i).getName() + ")");
        }        
    }
    
    protected void reportInvalidDaysOfHoliday() {
        if (rules.getInvalidDaysOfHoliday().size() > 0) {
            for (int i = 0; i < rules.getInvalidDaysOfHoliday().size(); i++)
                report.add(RULES_ERROR_12 + " (" 
                        + rules.getInvalidDaysOfHoliday().get(i).getName() + ")");
        }        
    }
    
    protected void reportHasOnlyOneParentalHolidayByWeek() {
        if (!employe.getTimeSheet(0).hasOnlyOneParentalHolidayByWeek())
            report.add(RULES_ERROR_13);                
    }
    
    protected void reportHasNotMinimumWeeklyTimeInOffice() {
        if (!rules.hasMinimumWeeklyTimeInOffice())
            report.add(RULES_ERROR_1);
    }

    protected void reportHasNotValidWeeklyTimeRemote() {
        if (!rules.hasValidWeeklyTimeRemote())
            report.add(RULES_ERROR_3);
    }

    protected void reportHasNotValidWeeklyTimeInOffice() {
        if (!rules.hasValidWeeklyTimeInOffice())
            report.add(RULES_ERROR_4);
    }
    
    protected void reportHasNotValidWeeklyTransportTime() {
        if (!rules.hasValidWeeklyTransportTime())
            if (rules.canChargeTransportation())
                report.add(RULES_ERROR_15);
            else
                report.add(RULES_ERROR_14);                
    }
    
    protected String getErrorTypeForWorkWhileSick(Day day) {
        String errorType = "";
        if (day.hasOfficeTask())
            errorType = "travail au bureau - ";
        if (day.hasRemoteTask())
            errorType += "télé-travail - ";
        return errorType;
    }  
}
