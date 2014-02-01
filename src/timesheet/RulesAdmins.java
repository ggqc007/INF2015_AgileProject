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


public class RulesAdmins extends Rules {

    public RulesAdmins() {
        
    }
    
    @Override
    public boolean hasMinimumOfficeWeekHours() {
        // TODO: Définition de la classe par Khaled
        return true;
    }
    
    @Override
    public boolean hasMinimumOfficeDailyHours(Employe employe){
        // TODO: Définition de la classe par Khaled
        return true;
    }
    
    @Override
    public boolean hasValidHomeWeekHours(){
        // TODO: Définition de la classe par Khaled
        return true;
    }

    @Override
    public boolean hasMaximumOfficeWeekHours() {
        return true;
    }

    @Override
    public boolean hasMaximumteletravailWeekHours() {
       return true;
    }
}
