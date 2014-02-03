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
