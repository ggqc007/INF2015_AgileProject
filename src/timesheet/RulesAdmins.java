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

public class RulesAdmins extends Rules {

    public RulesAdmins(Employe employe) {
        minOfficeWeekMinutes = 36*60;
        minOfficeDailyMinutes = 4*60; 
        maxRemoteWeekMinutes = 10*60;
        setEmploye(employe);
    }
    
    /*@Override
    public boolean hasMinimumWeeklyTimeInOffice() {
        // Les employés de l'administration doivent travailler au moins 36 heures au bureau par
        // semaine (excluant le télétravail).
        int officeWeekMinutes = totalWeekMinutes - totalRemoteWeekMinutes;        
        return (officeWeekMinutes >= minOfficeWeekMinutes);
    }
    */
    @Override
    public boolean hasValidWeeklyTimeRemote(){
        // Les employés de l'administration ne doivent pas faire plus de 10 heures de télétravail par
        // semaine.
        return (totalRemoteWeekMinutes <= maxRemoteWeekMinutes);
    }

    /*@Override
    public boolean hasValidWeeklyTimeInOffice() {
        // Aucun employé n'a le droit de passer plus de 43 heures au bureau.
        int officeWeekMinutes = totalWeekMinutes - totalRemoteWeekMinutes;
        return (officeWeekMinutes <= maxOfficeWeekMinutes);
    }*/
}
