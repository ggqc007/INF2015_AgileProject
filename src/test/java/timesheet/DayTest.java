package timesheet;

import java.util.List;
import net.sf.json.JSONObject;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class DayTest {
    
    private static final int NORMAL_TASK_ID = 900;
    private static final int SICK_LEAVE_TASK_ID = 999;
    private static final int SICK_LEAVE_TIME = 480;
    private static final int PUBLIC_HOLIDAY_TASK_ID = 998;    
    private static final int PUBLIC_HOLIDAY_TIME = 480;  
    private static final int HOLIDAY_TASK_ID = 997;    
    private static final int HOLIDAY_TIME = 480;
    private static final int PARENTAL_HOLIDAY_TASK_ID = 996;    
    private static final int PARENTAL_HOLIDAY_TIME = 480;  

    @Before
    public void initObjects() {
    }

    @Test(expected = IllegalArgumentException.class)
    public void testIsValidDayNameFail() throws Exception {
        Employe employeAdmin = new Employe();
        String validJSONStringAdmin = "{\n \"numero_employe\": 1,\n \"jour1BAD\": [\n {\n \"projet\": 998,"
                + "\n \"minutes\": 480\n },\n {\n \"projet\": 911,\n \"minutes\": 36\n },\n {\n \"projet\": 910,\n "
                + "\"minutes\": 8\n }\n ],\n \"jour2\": [\n {\n \"projet\": 125,\n \"minutes\": 552\n }\n ],\n "
                + "\"jour3\": [\n {\n \"projet\": 996,\n \"minutes\": 80\n }\n ],\n \"jour4\": [\n {\n \"projet\": 996,"
                + "\n \"minutes\": 80 }\n ],\n \"jour5\": [\n  {\n \"projet\": 125,\n \"minutes\": 516 }\n ],\n "
                + "\"weekend1\": [],\n \"weekend2\": [\n {\n \"projet\": 990,\n \"minutes\": 30\n }\n ]\n}";
        JSONObject validJSONObjectAdmin = JSONObject.fromObject(validJSONStringAdmin);
        TimeSheetData validTimeSheetDataAdmin = JSONParser.toTimeSheetData(validJSONObjectAdmin);
        employeAdmin.initFromFirstTimeSheet(validTimeSheetDataAdmin);
    }

    @Test(expected = Exception.class)
    public void testIsWorkingDayFail() throws Exception {
        Employe employeAdmin = new Employe();
        String validJSONStringAdmin = "{\n \"numero_employe\": 1,\n \"jo\": [\n {\n \"projet\": 998,"
                + "\n \"minutes\": 480\n },\n {\n \"projet\": 911,\n \"minutes\": 36\n },\n {\n \"projet\": 910,\n "
                + "\"minutes\": 8\n }\n ],\n \"jour2\": [\n {\n \"projet\": 125,\n \"minutes\": 552\n }\n ],\n "
                + "\"jour3\": [\n {\n \"projet\": 996,\n \"minutes\": 80\n }\n ],\n \"jour4\": [\n {\n \"projet\": 996,"
                + "\n \"minutes\": 80 }\n ],\n \"jour5\": [\n  {\n \"projet\": 125,\n \"minutes\": 516 }\n ],\n "
                + "\"weekend1\": [],\n \"weekend2\": [\n {\n \"projet\": 990,\n \"minutes\": 30\n }\n ]\n}";
        JSONObject validJSONObjectAdmin = JSONObject.fromObject(validJSONStringAdmin);
        TimeSheetData validTimeSheetDataAdmin = JSONParser.toTimeSheetData(validJSONObjectAdmin);
        employeAdmin.initFromFirstTimeSheet(validTimeSheetDataAdmin);
    }
    
    @Test
    public void testIsValidPublicHolidayWeekend() {
        Day day = new Day("weekend1");
        day.addTask(PUBLIC_HOLIDAY_TASK_ID, PUBLIC_HOLIDAY_TIME);
        assertFalse(day.isValidPublicHoliday());
    }

    @Test
    public void testIsValidPublicHolidayWeek() {
        Day day = new Day("jour1");
        day.addTask(PUBLIC_HOLIDAY_TASK_ID, PUBLIC_HOLIDAY_TIME);
        assertTrue(day.isValidPublicHoliday());
    }  
    
    @Test
    public void testIsValidPublicHolidayWrongTime() {
        Day day = new Day("jour1");
        day.addTask(PUBLIC_HOLIDAY_TASK_ID, PUBLIC_HOLIDAY_TIME + 1);
        assertFalse(day.isValidPublicHoliday());
    } 
    
    @Test
    public void testIsValidPublicHolidayIDSickLeave() {
        Day day = new Day("jour1");
        day.addTask(SICK_LEAVE_TASK_ID, PUBLIC_HOLIDAY_TIME);
        assertFalse(day.isValidPublicHoliday());
    }    
    
    @Test
    public void testIsValidPublicHolidayIDHoliday() {
        Day day = new Day("jour1");
        day.addTask(HOLIDAY_TASK_ID, PUBLIC_HOLIDAY_TIME);
        assertFalse(day.isValidPublicHoliday());
    }    
    
    @Test
    public void testIsValidPublicHolidayIDParentalHoliday() {
        Day day = new Day("jour1");
        day.addTask(PARENTAL_HOLIDAY_TASK_ID, PUBLIC_HOLIDAY_TIME);
        assertFalse(day.isValidPublicHoliday());
    }   
    
    @Test
    public void testIsValidPublicHolidayIDNormal() {
        Day day = new Day("jour1");
        day.addTask(NORMAL_TASK_ID, PUBLIC_HOLIDAY_TIME);
        assertFalse(day.isValidPublicHoliday());
    }    
    
    @Test
    public void testIsValidPublicHolidayMoreThanOneHolidayCode() {
        Day day = new Day("jour1");
        day.addTask(PUBLIC_HOLIDAY_TASK_ID, PUBLIC_HOLIDAY_TIME);
        day.addTask(HOLIDAY_TASK_ID, PUBLIC_HOLIDAY_TIME);
        assertFalse(day.isValidPublicHoliday());
    }             
    
    @Test
    public void testIsValidSickLeaveWeekend() {
        Day day = new Day("weekend1");
        day.addTask(SICK_LEAVE_TASK_ID, SICK_LEAVE_TIME);
        assertFalse(day.isValidSickLeave());
    }

    @Test
    public void testIsValidSickLeaveWeek() {
        Day day = new Day("jour1");
        day.addTask(SICK_LEAVE_TASK_ID, SICK_LEAVE_TIME);
        assertTrue(day.isValidSickLeave());
    }  
    
    @Test
    public void testIsValidSickLeaveWrongTime() {
        Day day = new Day("jour1");
        day.addTask(SICK_LEAVE_TASK_ID, SICK_LEAVE_TIME + 1);
        assertFalse(day.isValidSickLeave());
    } 
    
    @Test
    public void testIsValidSickLeaveIDPublicHoliday() {
        Day day = new Day("jour1");
        day.addTask(PUBLIC_HOLIDAY_TASK_ID, SICK_LEAVE_TIME);
        assertFalse(day.isValidSickLeave());
    }    
    
    @Test
    public void testIsValidSickLeaveIDHoliday() {
        Day day = new Day("jour1");
        day.addTask(HOLIDAY_TASK_ID, SICK_LEAVE_TIME);
        assertFalse(day.isValidSickLeave());
    }    
    
    @Test
    public void testIsValidSickLeaveIDParentalHoliday() {
        Day day = new Day("jour1");
        day.addTask(PARENTAL_HOLIDAY_TASK_ID, SICK_LEAVE_TIME);
        assertFalse(day.isValidSickLeave());
    }   
    
    @Test
    public void testIsValidSickLeaveIDNormal() {
        Day day = new Day("jour1");
        day.addTask(NORMAL_TASK_ID, SICK_LEAVE_TIME);
        assertFalse(day.isValidSickLeave());
    }    
    
    @Test
    public void testIsValidSickLeaveMoreThanOneHolidayCode() {
        Day day = new Day("jour1");
        day.addTask(SICK_LEAVE_TASK_ID, SICK_LEAVE_TIME);
        day.addTask(HOLIDAY_TASK_ID, SICK_LEAVE_TIME);
        assertFalse(day.isValidSickLeave());
    }         
    
    @Test
    public void testIsValidHolidayWeekend() {
        Day day = new Day("weekend1");
        day.addTask(HOLIDAY_TASK_ID, HOLIDAY_TIME);
        assertFalse(day.isValidHoliday());
    }

    @Test
    public void testIsValidHolidayWeek() {
        Day day = new Day("jour1");
        day.addTask(HOLIDAY_TASK_ID, HOLIDAY_TIME);
        assertTrue(day.isValidHoliday());
    }  
    
    @Test
    public void testIsValidHolidayWrongTime() {
        Day day = new Day("jour1");
        day.addTask(HOLIDAY_TASK_ID, HOLIDAY_TIME + 1);
        assertFalse(day.isValidHoliday());
    } 
    
    @Test
    public void testIsValidHolidayIDPublicHoliday() {
        Day day = new Day("jour1");
        day.addTask(PUBLIC_HOLIDAY_TASK_ID, HOLIDAY_TIME);
        assertFalse(day.isValidHoliday());
    }    
    
    @Test
    public void testIsValidHolidayIDSickLeave() {
        Day day = new Day("jour1");
        day.addTask(SICK_LEAVE_TASK_ID, HOLIDAY_TIME);
        assertFalse(day.isValidHoliday());
    }    
    
    @Test
    public void testIsValidHolidayIDParentalHoliday() {
        Day day = new Day("jour1");
        day.addTask(PARENTAL_HOLIDAY_TASK_ID, HOLIDAY_TIME);
        assertFalse(day.isValidHoliday());
    }   
    
    @Test
    public void testIsValidHolidayIDNormal() {
        Day day = new Day("jour1");
        day.addTask(NORMAL_TASK_ID, HOLIDAY_TIME);
        assertFalse(day.isValidHoliday());
    }    
    
    @Test
    public void testIsValidHolidayMoreThanOneHolidayCode() {
        Day day = new Day("jour1");
        day.addTask(HOLIDAY_TASK_ID, HOLIDAY_TIME);
        day.addTask(PUBLIC_HOLIDAY_TASK_ID, HOLIDAY_TIME);
        assertFalse(day.isValidHoliday());
    }            
    
    @Test
    public void testIsValidParentalHolidayWeekend() {
        Day day = new Day("weekend1");
        day.addTask(PARENTAL_HOLIDAY_TASK_ID, PARENTAL_HOLIDAY_TIME);
        assertFalse(day.isValidParentalHoliday());
    }

    @Test
    public void testIsValidParentalHolidayWeek() {
        Day day = new Day("jour1");
        day.addTask(PARENTAL_HOLIDAY_TASK_ID, PARENTAL_HOLIDAY_TIME);
        assertTrue(day.isValidParentalHoliday());
    }  
    
    @Test
    public void testIsValidParentalHolidayWrongTime() {
        Day day = new Day("jour1");
        day.addTask(PARENTAL_HOLIDAY_TASK_ID, PARENTAL_HOLIDAY_TIME + 1);
        assertFalse(day.isValidParentalHoliday());
    } 
    
    @Test
    public void testIsValidParentalHolidayIDPublicHoliday() {
        Day day = new Day("jour1");
        day.addTask(PUBLIC_HOLIDAY_TASK_ID, PARENTAL_HOLIDAY_TIME);
        assertFalse(day.isValidParentalHoliday());
    }    
    
    @Test
    public void testIsValidParentalHolidayIDSickLeave() {
        Day day = new Day("jour1");
        day.addTask(SICK_LEAVE_TASK_ID, PARENTAL_HOLIDAY_TIME);
        assertFalse(day.isValidParentalHoliday());
    }    
    
    @Test
    public void testIsValidParentalHolidayIDHoliday() {
        Day day = new Day("jour1");
        day.addTask(HOLIDAY_TASK_ID, PARENTAL_HOLIDAY_TIME);
        assertFalse(day.isValidParentalHoliday());
    }   
    
    @Test
    public void testIsValidParentalHolidayIDNormal() {
        Day day = new Day("jour1");
        day.addTask(NORMAL_TASK_ID, PARENTAL_HOLIDAY_TIME);
        assertFalse(day.isValidParentalHoliday());
    }    
    
    @Test
    public void testIsValidParentalHolidayMoreThanOneHolidayCode() {
        Day day = new Day("jour1");
        day.addTask(PARENTAL_HOLIDAY_TASK_ID, PARENTAL_HOLIDAY_TIME);
        day.addTask(PUBLIC_HOLIDAY_TASK_ID, PARENTAL_HOLIDAY_TIME);
        assertFalse(day.isValidParentalHoliday());
    } 
}
