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

    // Rules for all
    private static final String ERROR_1 = "Cet employe n'a pas fait le minimum d'heures requis du lundi au vendredi physiquement au bureau ";
    private static final String ERROR_2 = "Cet employe n'a pas travaille le nombre d'heures minimal physiquement au bureau.";
    private static final String ERROR_3 = "Cet employe a fait plus d'heures de teletravail que la quantite permise";
    private static final String ERROR_4 = "Cet employe a passe plus d'heures physiquement au bureau que la quantite permise.";
 

    private Employe employe;

    public Report() {

    }
    
    // AJOUTE PAR THOMAS
    // Pour éviter d'avoir à faire :
    //
    //   Report report = new Report();
    //   report.setEmploye(employe);      
    //
    // Et pouvoir faire le tout en une ligne :
    //
    //   Report report = new Report(employe);
    //
    public Report(Employe employe) {
        
        this();
        
        this.employe = employe;

    }    

    public List generate(Employe employe) {
        List report = new ArrayList();

        // TODO: Instancier rulesEmployes ou rulesAdmins, valider les enoncés à l'aide des méthodes et générer List avec codes d'erreur et messages
        Rules rules = getInstanceRules(employe);
        rules.setEmploye(employe);
        rules.prepData();
        
        if (!rules.hasMinimumWeeklyTimeInOffice()) {
            report.add(ERROR_1);
        }
        
        if (rules.getInvalidDaysWithMinimumDailyTimeInOffice().size() > 0) {
            for (int i = 0; i < rules.getInvalidDaysWithMinimumDailyTimeInOffice().size(); i++) {
                report.add(ERROR_2 + " (" + rules.getInvalidDaysWithMinimumDailyTimeInOffice().get(i).getName() + ")");
            }
        }
        
        if (!rules.hasValidWeeklyTimeRemote()) {
            report.add(ERROR_3);
        }
        
        if (!rules.hasValidWeeklyTimeInOffice()) {
            report.add(ERROR_4);
        }
        return report;
    }

    public Employe getEmploye() {
        return employe;
    }

    public void setEmploye(Employe employe) {
        this.employe = employe;
    }

    private Rules getInstanceRules(Employe employe) {
        Rules rules;
        if (employe.isAdmin()) {
            rules = new RulesAdmins();
        } else {
            rules = new RulesEmployes();
        }
        return rules;
    }
}
