/* INF2015 – Développement de logiciels dans un environnement Agile
 *
 * Projet de session - Hiver 2014
 *
 * Equipe 8 :
 *
 *   Christian Cornejo
 *   Khaled Elsheikh
 *   Guillaume Gagnon
 *   Thomas Robert de Massy
 *
 */
package timesheet;
import java.util.ArrayList;
import java.util.List;

public class Report {

    private static final String RULES_ERROR_1 = "Cet employé n'a pas fait le minimum d'heures requis du lundi au vendredi physiquement au bureau.";
    private static final String RULES_ERROR_2 = "Cet employé n'a pas travaillé le nombre d'heures minimal physiquement au bureau.";
    private static final String RULES_ERROR_3 = "Cet employé a fait plus d'heures de télétravail que la quantité permise.";
    private static final String RULES_ERROR_4 = "Cet employé a passé plus d'heures physiquement au bureau que la quantité permise.";
    private static final String RULES_ERROR_5 = "Cet employé a des journées invalides de congés de maladie.";
    private static final String RULES_ERROR_6 = "Cet employé a des journées invalides de congés fériés.";

    private Employe employe;

    public Report() {

    }

    public Report(Employe employe) {
        this();
        this.employe = employe;
    }

    public List<String> generate(Employe employe) {
        List<String> report = new ArrayList<>();
        Rules rules = intitializeRulesForThisEmploye(employe);
        reportHasNotMinimumWeeklyTimeInOffice(rules, report);
        reportInvalidDaysWithMinimumDailyTimeInOffice(rules, report);
        reportHasNotValidWeeklyTimeRemote(rules, report);
        reportHasNotValidWeeklyTimeInOffice(rules, report);
        reportInvalidDaysWithSickLeave(rules, report);
        reportInvalidDaysWithPublicHoliday(rules, report);
        return report;
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

    private void reportHasNotMinimumWeeklyTimeInOffice(Rules rules, List<String> report) {
        if (!rules.hasMinimumWeeklyTimeInOffice()) {
            report.add(RULES_ERROR_1);
        }
    }

    private void reportInvalidDaysWithMinimumDailyTimeInOffice(Rules rules, List<String> report) {
        if (rules.getInvalidDaysWithMinimumDailyTimeInOffice().size() > 0) {
            for (int i = 0; i < rules.getInvalidDaysWithMinimumDailyTimeInOffice().size(); i++) {
                report.add(RULES_ERROR_2 + " (" + rules.getInvalidDaysWithMinimumDailyTimeInOffice().get(i).getName() + ")");
            }
        }
    }

    private void reportHasNotValidWeeklyTimeRemote(Rules rules, List<String> report) {
        if (!rules.hasValidWeeklyTimeRemote()) {
            report.add(RULES_ERROR_3);
        }
    }

    private void reportHasNotValidWeeklyTimeInOffice(Rules rules, List<String> report) {
        if (!rules.hasValidWeeklyTimeInOffice()) {
            report.add(RULES_ERROR_4);
        }
    }
    
    private void reportInvalidDaysWithSickLeave(Rules rules, List<String> report) {
        String errorType = "";
        if (rules.getInvalidDaysWithSickLeave().size() > 0) {
            for (int i = 0; i < rules.getInvalidDaysWithSickLeave().size(); i++) {
                if (rules.getInvalidDaysWithSickLeave().get(i).hasOfficeTask())
                    errorType = "travail au bureau - ";
                if (rules.getInvalidDaysWithSickLeave().get(i).hasRemoteTask())
                    errorType += "télé-travail - ";                
                report.add(RULES_ERROR_5 + " (" + errorType + rules.getInvalidDaysWithSickLeave().get(i).getName() + ")");
            }
        }
    } 
    
    private void reportInvalidDaysWithPublicHoliday(Rules rules, List<String> report) {
        if (rules.getInvalidDaysWithPublicHoliday().size() > 0) {
            for (int i = 0; i < rules.getInvalidDaysWithPublicHoliday().size(); i++) {
                if (rules.getInvalidDaysWithPublicHoliday().get(i).hasOfficeTask()) 
                    report.add(RULES_ERROR_6 + " (travail au bureau-" + rules.getInvalidDaysWithPublicHoliday().get(i).getName() + ")");
                else
                    report.add(RULES_ERROR_6 + " (" + rules.getInvalidDaysWithPublicHoliday().get(i).getName() + ")");                    
            }
        }
    }    
}
