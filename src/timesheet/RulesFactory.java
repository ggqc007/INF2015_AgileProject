package timesheet;

public class RulesFactory {   
    
    public Rules makeRules(Employe employe) {
        if (employe.isAdmin()) 
            return new RulesAdmins(employe);
        else if(employe.isExplEmploye())
            return new RulesExploitation(employe);
        else if(employe.isProdEmploye())
            return new RulesProduction(employe);
        return (Rules) new Object();
    }
}

