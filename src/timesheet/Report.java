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
    private static final String ERROR_ALL_1 = "Cet employe a passe plus d'heures physiquement au bureau que la quantite permise.";
    
    // Rules for admin
    private static final String ERROR_ADM_1 = "Cet administrateur n'a pas travaille le nombre d'heures minimal physiquement au bureau.";
    private static final String ERROR_ADM_2 = "Cet administrateur a fait plus d'heures de teletravail que la quantite permise";
    private static final String ERROR_ADM_3 = "Cet administrateur n'a pas fait le minimum d'heures requis du lundi au vendredi physiquement au bureau.";
    
    // Rules for employee
    private static final String ERROR_EMP_1 = "Cet employe n'a pas travaille le nombre d'heure minimal physiquement au bureau.";
    private static final String ERROR_EMP_2 = "Cet employe n'a pas fait le minimum d'heures requis du lundi au vendredi physiquement au bureau ";
    

    private Employe employe;
    
    public Report() {
    
    }
    
    public List generate(Employe employe) {
        List report = new ArrayList();
        
         // TODO: Instancier rulesEmployes ou rulesAdmins, valider les enoncés à l'aide des méthodes et générer List avec codes d'erreur et messages
        
        return report;
    }

    public Employe getEmploye() {
        return employe;
    }

    public void setEmploye(Employe employe) {
        this.employe = employe;
    }
}
