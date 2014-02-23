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

public class RulesFactory {
    
    public Rules makeRules(Employe employe) {
        Rules rules;
        if (employe.isAdmin()) 
            rules = new RulesAdmins();
        else if(employe.isExplEmploye())
            rules = new RulesExploitation();
        else if(employe.isProdEmploye())
            rules = new RulesProduction();
        else 
            rules = (Rules) new Object();
        rules.setEmploye(employe);
        rules.prepData();
        return rules;
    }
}
