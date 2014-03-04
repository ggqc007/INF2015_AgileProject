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

public class RulesEmployes extends Rules {

    public RulesEmployes(Employe employe) {
        minOfficeWeekMinutes = 38*60;
        minOfficeDailyMinutes = 6*60;
        setEmploye(employe);
        calculateTotalWeekMinutes();
    }
    
    /*@Override
    public boolean hasMinimumWeeklyTimeInOffice() {
        // Les employés normaux doivent travailler au moins 38 heures au bureau par semaine
        // (excluant le télétravail).
        int officeWeekMinutes = totalWeekMinutes - totalRemoteWeekMinutes;        
        return (officeWeekMinutes >= minOfficeWeekMinutes);
    }
    */
    @Override
    public boolean hasValidWeeklyTimeRemote(){
        // Les employés normaux peuvent faire autant de télétravail qu'ils le souhaitent.
        return true;
    }

    /*@Override
    public boolean hasValidWeeklyTimeInOffice() {
        // Aucun employé n'a le droit de passer plus de 43 heures au bureau.
        int officeWeekMinutes = totalWeekMinutes - totalRemoteWeekMinutes;
        return (officeWeekMinutes <= maxOfficeWeekMinutes);
    }*/
}
