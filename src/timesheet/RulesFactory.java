package timesheet;

public class RulesFactory {   
    
    public Rules makeRules(Employe employe) {
        if (employe.isAdmin()) 
            return (Rules) new RulesAdmins(employe);
        else if(employe.isExplEmploye())
            return (Rules) new RulesExploitation(employe);
        else if(employe.isProdEmploye())
            return (Rules) new RulesProduction(employe);
        return (Rules) new Object();
    }
}
