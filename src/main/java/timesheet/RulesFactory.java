package timesheet;

public class RulesFactory {   
    
    // Cette méthode fait plus de 10 lignes!
    public Rules makeRules(Employe employe) {
        if (employe.isAdmin()) 
            return new RulesAdmins(employe);
        else if(employe.isExplEmploye())
            return new RulesExploitation(employe);
        else if(employe.isDevelEmploye())
            return new RulesDevelopment(employe);
        else if(employe.isDirectionEmploye())
            return new RulesDirection(employe);  
        else if(employe.isPresident())
            return new RulesPresident(employe);         
        return (Rules) new Object();
    }
}