package timesheet;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class RulesTest {
    
    private static final int VALID_ADMIN_EMPLOYE_ID = 900;
    private static final int VALID_DEVELOPMENT_EMPLOYE_ID = 1500; 
    private static final int VALID_EXPLOITATION_EMPLOYE_ID = 2500;
    private static final int VALID_DIRECTION_EMPLOYE_ID = 5100;
    
    @Test
    public void testCanChargeTransportationAdmin() {
        Employe employe = makeEmployeFactory(VALID_ADMIN_EMPLOYE_ID);
        Rules rules = new RulesAdmins(employe);
        assertTrue(rules.canChargeTransportation());
    }
    
    @Test
    public void testCanChargeTransportationDevelopment() {
        Employe employe = makeEmployeFactory(VALID_DEVELOPMENT_EMPLOYE_ID);
        Rules rules = new RulesDevelopment(employe);
        assertFalse(rules.canChargeTransportation());
    }   
    
    @Test
    public void testCanChargeTransportationExploitation() {
        Employe employe = makeEmployeFactory(VALID_EXPLOITATION_EMPLOYE_ID);
        Rules rules = new RulesExploitation(employe);
        assertFalse(rules.canChargeTransportation());
    }     
    
    @Test
    public void testCanChargeTransportationDirection() {
        Employe employe = makeEmployeFactory(VALID_DIRECTION_EMPLOYE_ID);
        Rules rules = new RulesDirection(employe);
        assertTrue(rules.canChargeTransportation());
    }   
    
    private Employe makeEmployeFactory(int employeId) {
        TimeSheetData timeSheetData = new TimeSheetData();
        timeSheetData.setEmployeId(employeId);
        Employe employe= new Employe();
        employe.initFromFirstTimeSheet(timeSheetData);
        return employe;
    }
}
