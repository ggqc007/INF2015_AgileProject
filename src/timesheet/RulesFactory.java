package timesheet;

public class RulesFactory {
    
    // TODO: Cette methode a plus de 10 lignes ############################################################################
    public Rules makeRules(Employe employe) {
        Rules rules;
        if (employe.isAdmin()) 
            rules = new RulesAdmins(employe);
        else if(employe.isExplEmploye())
            rules = new RulesExploitation(employe);
        else if(employe.isProdEmploye())
            rules = new RulesProduction(employe);
        else 
            rules = (Rules) new Object();
        return rules;
    }
}
