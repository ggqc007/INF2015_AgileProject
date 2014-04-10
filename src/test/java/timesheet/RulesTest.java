package timesheet;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class RulesTest {
    
    private static final int VALID_ADMIN_EMPLOYE_ID = 900;
    private static final int VALID_DEVELOPMENT_EMPLOYE_ID = 1500; 
    private static final int VALID_EXPLOITATION_EMPLOYE_ID = 2500;
    private static final int VALID_DIRECTION_EMPLOYE_ID = 5100;
    private static final int TRANSPORTATION_ID = 777;
    private static final int MAX_TRANSPORT_TIME_DIRECTION = 300;
    private static final int MAX_TRANSPORT_TIME_ADMIN = 300;      
    private static final int PRESIDENT_ID = 6000;
            
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
   
    @Test
    public void testHasValidWeeklyTransportTimeDirectionPass() {   
        Employe employe = makeEmployeFactory(VALID_DIRECTION_EMPLOYE_ID);
        Rules rules = new RulesDirection(employe);
        TimeSheetData timeSheetData = employe.getTimeSheet(0);
        timeSheetData.getDayByName("jour1").addTask(TRANSPORTATION_ID, MAX_TRANSPORT_TIME_DIRECTION);
        assertTrue(rules.hasValidWeeklyTransportTime());
    }    
    
    @Test
    public void testHasValidWeeklyTransportTimeDirectionFail() {   
        Employe employe = makeEmployeFactory(VALID_DIRECTION_EMPLOYE_ID);
        Rules rules = new RulesDirection(employe);
        TimeSheetData timeSheetData = employe.getTimeSheet(0);
        timeSheetData.getDayByName("jour1").addTask(TRANSPORTATION_ID, MAX_TRANSPORT_TIME_DIRECTION + 1);
        assertFalse(rules.hasValidWeeklyTransportTime());
    }   
    
    @Test
    public void testHasValidWeeklyTransportTimeAdminPass() {   
        Employe employe = makeEmployeFactory(VALID_ADMIN_EMPLOYE_ID);
        Rules rules = new RulesAdmins(employe);
        TimeSheetData timeSheetData = employe.getTimeSheet(0);
        timeSheetData.getDayByName("jour1").addTask(TRANSPORTATION_ID, MAX_TRANSPORT_TIME_ADMIN);
        assertTrue(rules.hasValidWeeklyTransportTime());
    }    
    
    @Test
    public void testHasValidWeeklyTransportTimeAdminFail() {   
        Employe employe = makeEmployeFactory(VALID_ADMIN_EMPLOYE_ID);
        Rules rules = new RulesAdmins(employe);
        TimeSheetData timeSheetData = employe.getTimeSheet(0);
        timeSheetData.getDayByName("jour1").addTask(TRANSPORTATION_ID, MAX_TRANSPORT_TIME_ADMIN + 1);
        assertFalse(rules.hasValidWeeklyTransportTime());
    }    
    
    @Test
    public void testHasValidWeeklyTransportTimePresidentPass() {   
        Employe employe = makeEmployeFactory(PRESIDENT_ID);
        Rules rules = new RulesAdmins(employe);
        TimeSheetData timeSheetData = employe.getTimeSheet(0);
        timeSheetData.getDayByName("jour1").addTask(TRANSPORTATION_ID, MAX_TRANSPORT_TIME_DIRECTION);
        assertTrue(rules.hasValidWeeklyTransportTime());
    }    
    
    @Test
    public void testHasValidWeeklyTransportTimePresidentPassNoLimits() {   
        Employe employe = makeEmployeFactory(PRESIDENT_ID);
        Rules rules = new RulesPresident(employe);
        TimeSheetData timeSheetData = employe.getTimeSheet(0);
        timeSheetData.getDayByName("jour1").addTask(TRANSPORTATION_ID, MAX_TRANSPORT_TIME_DIRECTION + 1);
        assertTrue(rules.hasValidWeeklyTransportTime());
    }   
    
   @Test
    public void testHasValidWeeklyTransportTimeDevelopmentAlwaysValid() {   
        Employe employe = makeEmployeFactory(VALID_DEVELOPMENT_EMPLOYE_ID);
        Rules rules = new RulesDevelopment(employe);
        TimeSheetData timeSheetData = employe.getTimeSheet(0);        
        timeSheetData.getDayByName("jour1").addTask(TRANSPORTATION_ID, 1000000);
        assertTrue(rules.hasValidWeeklyTransportTime());
    }    
    
   @Test
    public void testHasValidWeeklyTransportTimeExploitationAlwaysValid() {   
        Employe employe = makeEmployeFactory(VALID_EXPLOITATION_EMPLOYE_ID);
        Rules rules = new RulesExploitation(employe);
        TimeSheetData timeSheetData = employe.getTimeSheet(0);
        timeSheetData.getDayByName("jour1").addTask(TRANSPORTATION_ID, 1000000);
        assertTrue(rules.hasValidWeeklyTransportTime());
    }    
    
    private Employe makeEmployeFactory(int employeId) {
        TimeSheetData timeSheetData = new TimeSheetData();
        timeSheetData.setDayByName(new Day("jour1"));
        timeSheetData.setDayByName(new Day("jour2"));
        timeSheetData.setDayByName(new Day("jour3"));
        timeSheetData.setDayByName(new Day("jour4"));
        timeSheetData.setDayByName(new Day("jour5"));
        timeSheetData.setDayByName(new Day("weekend1"));
        timeSheetData.setDayByName(new Day("weekend2"));         
        timeSheetData.setEmployeId(employeId);
        Employe employe= new Employe();
        employe.initFromFirstTimeSheet(timeSheetData);
        return employe;
    } 
    
}
