package timesheet;

import org.junit.Test;
import static org.junit.Assert.*;

public class RulesTest {
    
    private static final int VALID_ADMIN_EMPLOYE_ID = 900;
    private static final int VALID_DEVELOPMENT_EMPLOYE_ID = 1500; 
    private static final int VALID_EXPLOITATION_EMPLOYE_ID = 2500;
    private static final int VALID_DIRECTION_EMPLOYE_ID = 5100;
    private static final int TRANSPORTATION_ID = 777;
    private static final int VALID_OFFICE_TASK_ID = 800;
    private static final int VALID_REMOTE_TASK_ID = 901;
    private static final int MAX_TRANSPORT_TIME_DIRECTION = 300;
    private static final int MAX_TRANSPORT_TIME_ADMIN = 300;      
    private static final int PRESIDENT_ID = 6000;
            
    private static final String JOUR1_KEY = "jour1";
    private static final String JOUR2_KEY = "jour2";
    private static final String JOUR3_KEY = "jour3";
    private static final String JOUR4_KEY = "jour4";
    private static final String JOUR5_KEY = "jour5";
    private static final String WEEKEND1_KEY = "weekend1";
    private static final String WEEKEND2_KEY = "weekend2";
    
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
        timeSheetData.getDayByName(JOUR1_KEY).addTask(TRANSPORTATION_ID, MAX_TRANSPORT_TIME_DIRECTION);
        rules.calculateTotalWeekTransportTime();
        assertTrue(rules.hasValidWeeklyTransportTime());
    }    
    
    @Test
    public void testHasValidWeeklyTransportTimeDirectionFail() {   
        Employe employe = makeEmployeFactory(VALID_DIRECTION_EMPLOYE_ID);
        Rules rules = new RulesDirection(employe);
        TimeSheetData timeSheetData = employe.getTimeSheet(0);
        timeSheetData.getDayByName(JOUR1_KEY).addTask(TRANSPORTATION_ID, MAX_TRANSPORT_TIME_DIRECTION + 1);
        rules.calculateTotalWeekTransportTime();
        assertFalse(rules.hasValidWeeklyTransportTime());
    }   
    
    @Test
    public void testHasValidWeeklyTransportTimeAdminPass() {   
        Employe employe = makeEmployeFactory(VALID_ADMIN_EMPLOYE_ID);
        Rules rules = new RulesAdmins(employe);
        TimeSheetData timeSheetData = employe.getTimeSheet(0);
        timeSheetData.getDayByName(JOUR1_KEY).addTask(TRANSPORTATION_ID, MAX_TRANSPORT_TIME_ADMIN);
        rules.calculateTotalWeekTransportTime();
        assertTrue(rules.hasValidWeeklyTransportTime());
    }    
    
    @Test
    public void testHasValidWeeklyTransportTimeAdminFail() {   
        Employe employe = makeEmployeFactory(VALID_ADMIN_EMPLOYE_ID);
        Rules rules = new RulesAdmins(employe);
        TimeSheetData timeSheetData = employe.getTimeSheet(0);
        timeSheetData.getDayByName(JOUR1_KEY).addTask(TRANSPORTATION_ID, MAX_TRANSPORT_TIME_ADMIN + 1);
        rules.calculateTotalWeekTransportTime();
        assertFalse(rules.hasValidWeeklyTransportTime());
    }    
    
    @Test
    public void testHasValidWeeklyTransportTimePresidentPass() {   
        Employe employe = makeEmployeFactory(PRESIDENT_ID);
        Rules rules = new RulesAdmins(employe);
        TimeSheetData timeSheetData = employe.getTimeSheet(0);
        timeSheetData.getDayByName(JOUR1_KEY).addTask(TRANSPORTATION_ID, MAX_TRANSPORT_TIME_DIRECTION);
        rules.calculateTotalWeekTransportTime();
        assertTrue(rules.hasValidWeeklyTransportTime());
    }    
    
    @Test
    public void testHasValidWeeklyTransportTimePresidentPassNoLimits() {   
        Employe employe = makeEmployeFactory(PRESIDENT_ID);
        Rules rules = new RulesPresident(employe);
        TimeSheetData timeSheetData = employe.getTimeSheet(0);
        timeSheetData.getDayByName(JOUR1_KEY).addTask(TRANSPORTATION_ID, MAX_TRANSPORT_TIME_DIRECTION + 1);
        rules.calculateTotalWeekTransportTime();
        assertTrue(rules.hasValidWeeklyTransportTime());
    }   
 
    @Test
    public void testHasValidWeeklyTransportTimeDevelopmentPass() {   
        Employe employe = makeEmployeFactory(VALID_DEVELOPMENT_EMPLOYE_ID);
        Rules rules = new RulesDevelopment(employe);
        TimeSheetData timeSheetData = employe.getTimeSheet(0);
        rules.calculateTotalWeekTransportTime();
        assertTrue(rules.hasValidWeeklyTransportTime());
    }    
    
    @Test
    public void testHasValidWeeklyTransportTimeExploitationPass() {   
        Employe employe = makeEmployeFactory(VALID_EXPLOITATION_EMPLOYE_ID);
        Rules rules = new RulesExploitation(employe);
        TimeSheetData timeSheetData = employe.getTimeSheet(0);
        rules.calculateTotalWeekTransportTime();
        assertTrue(rules.hasValidWeeklyTransportTime());
    }     
    
    @Test
    public void testHasValidWeeklyTransportTimeDevelopmentFail() {   
        Employe employe = makeEmployeFactory(VALID_DEVELOPMENT_EMPLOYE_ID);
        Rules rules = new RulesDevelopment(employe);
        TimeSheetData timeSheetData = employe.getTimeSheet(0);        
        timeSheetData.getDayByName(JOUR1_KEY).addTask(TRANSPORTATION_ID, 100);
        rules.calculateTotalWeekTransportTime();
        assertFalse(rules.hasValidWeeklyTransportTime());
    }    
    
    @Test
    public void testHasValidWeeklyTransportTimeExploitationFail() {   
        Employe employe = makeEmployeFactory(VALID_EXPLOITATION_EMPLOYE_ID);
        TimeSheetData timeSheetData = employe.getTimeSheet(0);
        timeSheetData.getDayByName(JOUR1_KEY).addTask(TRANSPORTATION_ID, 100);
        Rules rules = new RulesExploitation(employe);        
        assertFalse(rules.hasValidWeeklyTransportTime());
    }   
    
    @Test
    public void testCalculateTotalWeekTransportTime() {
        Employe employe = makeEmployeFactory(VALID_EXPLOITATION_EMPLOYE_ID);
        TimeSheetData timeSheetData = employe.getTimeSheet(0);
        timeSheetData.getDayByName(JOUR1_KEY).addTask(TRANSPORTATION_ID, 10); 
        timeSheetData.getDayByName(JOUR1_KEY).addTask(TRANSPORTATION_ID, 100); 
        timeSheetData.getDayByName(JOUR3_KEY).addTask(TRANSPORTATION_ID, 99); 
        timeSheetData.getDayByName(JOUR4_KEY).addTask(TRANSPORTATION_ID, 10);  
        Rules rules = new RulesExploitation(employe);        
        assertEquals(219,rules.totalTransportWeekMinutes);
    }
    
    @Test
    public void testGetTotalTransportTime() {
        Employe employe = makeEmployeFactory(VALID_EXPLOITATION_EMPLOYE_ID);
        TimeSheetData timeSheetData = employe.getTimeSheet(0);
        timeSheetData.getDayByName(JOUR1_KEY).addTask(TRANSPORTATION_ID, 10); 
        timeSheetData.getDayByName(JOUR1_KEY).addTask(TRANSPORTATION_ID, 100); 
        timeSheetData.getDayByName(JOUR3_KEY).addTask(TRANSPORTATION_ID, 99); 
        timeSheetData.getDayByName(JOUR4_KEY).addTask(TRANSPORTATION_ID, 10);  
        Rules rules = new RulesExploitation(employe);        
        assertEquals(219,rules.getTotalTransportTime());                        
    }
    
    @Test    
    public void testGetTotalTransportMinutesByDay() {
        Employe employe = makeEmployeFactory(VALID_EXPLOITATION_EMPLOYE_ID);
        Rules rules = new RulesExploitation(employe);
        TimeSheetData timeSheetData = employe.getTimeSheet(0);
        timeSheetData.getDayByName(JOUR1_KEY).addTask(TRANSPORTATION_ID, 10); 
        timeSheetData.getDayByName(JOUR1_KEY).addTask(TRANSPORTATION_ID, 100); 
        timeSheetData.getDayByName(JOUR3_KEY).addTask(TRANSPORTATION_ID, 99); 
        timeSheetData.getDayByName(JOUR4_KEY).addTask(TRANSPORTATION_ID, 10);  
        assertEquals(110,rules.getTotalTransportMinutesByDay(timeSheetData.getDayByName("jour1")));               
    }
    
    @Test 
    public void testTransportationAsOfficeForAdministration() {
        Employe employe = makeEmployeFactory(VALID_ADMIN_EMPLOYE_ID);
        TimeSheetData timeSheetData = employe.getTimeSheet(0);
        timeSheetData.getDayByName(JOUR1_KEY).addTask(TRANSPORTATION_ID, 10); 
        timeSheetData.getDayByName(JOUR1_KEY).addTask(TRANSPORTATION_ID, 100); 
        timeSheetData.getDayByName(JOUR3_KEY).addTask(VALID_OFFICE_TASK_ID, 30); 
        timeSheetData.getDayByName(JOUR4_KEY).addTask(VALID_OFFICE_TASK_ID, 10);
        Rules rules = new RulesAdmins(employe);        
        assertEquals(150, rules.getTotalOfficeWeekMinutes());
    }
 
    @Test 
    public void testTransportationAsOfficeForAdministrationFail() {
        Employe employe = makeEmployeFactory(VALID_ADMIN_EMPLOYE_ID);
        TimeSheetData timeSheetData = employe.getTimeSheet(0);
        timeSheetData.getDayByName(JOUR1_KEY).addTask(TRANSPORTATION_ID, 10); 
        timeSheetData.getDayByName(JOUR1_KEY).addTask(TRANSPORTATION_ID, 100); 
        timeSheetData.getDayByName(JOUR3_KEY).addTask(VALID_OFFICE_TASK_ID, 30); 
        timeSheetData.getDayByName(JOUR4_KEY).addTask(VALID_OFFICE_TASK_ID, 10);
        Rules rules = new RulesAdmins(employe);        
        assertFalse(rules.getTotalOfficeWeekMinutes() == 40);
    }
    
    @Test 
    public void testTransportationAsRemoteForDirection() {
        Employe employe = makeEmployeFactory(VALID_DIRECTION_EMPLOYE_ID);
        TimeSheetData timeSheetData = employe.getTimeSheet(0);
        timeSheetData.getDayByName(JOUR1_KEY).addTask(TRANSPORTATION_ID, 10); 
        timeSheetData.getDayByName(JOUR1_KEY).addTask(TRANSPORTATION_ID, 100); 
        timeSheetData.getDayByName(JOUR3_KEY).addTask(VALID_REMOTE_TASK_ID, 30); 
        timeSheetData.getDayByName(JOUR4_KEY).addTask(VALID_OFFICE_TASK_ID, 10);
        Rules rules = new RulesDirection(employe);        
        assertEquals(140, rules.getTotalRemoteWeekMinutes());
    }
 
    @Test
    public void testTransportationAsOfficeForDirectionFail() {
        Employe employe = makeEmployeFactory(VALID_DIRECTION_EMPLOYE_ID);
        TimeSheetData timeSheetData = employe.getTimeSheet(0);
        timeSheetData.getDayByName(JOUR1_KEY).addTask(TRANSPORTATION_ID, 10); 
        timeSheetData.getDayByName(JOUR1_KEY).addTask(TRANSPORTATION_ID, 100); 
        timeSheetData.getDayByName(JOUR3_KEY).addTask(VALID_REMOTE_TASK_ID, 30); 
        timeSheetData.getDayByName(JOUR4_KEY).addTask(VALID_OFFICE_TASK_ID, 10);
        Rules rules = new RulesDirection(employe);        
        assertFalse(rules.getTotalRemoteWeekMinutes() == 30);
    }   
            
    private Employe makeEmployeFactory(int employeId) {
        TimeSheetData timeSheetData = new TimeSheetData();
        timeSheetData.setDayByName(new Day(JOUR1_KEY));
        timeSheetData.setDayByName(new Day(JOUR2_KEY));
        timeSheetData.setDayByName(new Day(JOUR3_KEY));
        timeSheetData.setDayByName(new Day(JOUR4_KEY));
        timeSheetData.setDayByName(new Day(JOUR5_KEY));
        timeSheetData.setDayByName(new Day(WEEKEND1_KEY));
        timeSheetData.setDayByName(new Day(WEEKEND2_KEY));         
        timeSheetData.setEmployeId(employeId);
        Employe employe= new Employe();
        employe.initFromFirstTimeSheet(timeSheetData);
        return employe;
    } 
}
