package timesheet;

import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.Before;

public class TimeSheetDataTest {
    
    private static final String JOUR1_KEY = "jour1";
    private static final String JOUR2_KEY = "jour2";
    private static final String JOUR3_KEY = "jour3";
    private static final String JOUR4_KEY = "jour4";
    private static final String JOUR5_KEY = "jour5";
    private static final String WEEKEND1_KEY = "weekend1";
    private static final String WEEKEND2_KEY = "weekend2";
    private static final int VALID_EMPLOYE_ID = 1;
    private static final int INVALID_EMPLOYE_ID = -1;
    private static final String INVALID_DAY_KEY = "zwpk3z";
    
    Day day1;
    TimeSheetData timeSheetData;

    @Before
    public void initObjects() {
        day1 = new Day(JOUR1_KEY);
        timeSheetData = new TimeSheetData();
        timeSheetData.setDayByName(day1);
    }
    @Test(expected=IllegalArgumentException.class)
    public void setDayByNameFailDayAlreadyExist() {
        timeSheetData.setDayByName(new Day(JOUR1_KEY));
    }
    
    @Test(expected=IllegalArgumentException.class)
    public void setDayByNameFailInvalidDayName() {
        timeSheetData.setDayByName(new Day(INVALID_DAY_KEY));
    }
    
    @Test
    public void containsDayFound() {
        assertTrue(timeSheetData.containsDay(JOUR1_KEY));
    }
    
    @Test
    public void containsDayNotFound() {
        assertFalse(timeSheetData.containsDay(JOUR2_KEY));
    }
    
    @Test
    public void getDayByNameFound() {
        assertTrue(day1.equals(timeSheetData.getDayByName(JOUR1_KEY)));
    }
    
    @Test(expected=IllegalArgumentException.class)
    public void getDayByNameNotFound() {
        assertFalse(day1.equals(timeSheetData.getDayByName(JOUR2_KEY)));
    }
    
    @Test
    public void setEmployeIdValid() {
        timeSheetData.setEmployeId(VALID_EMPLOYE_ID);
        assertTrue(VALID_EMPLOYE_ID == timeSheetData.getEmployeId());
    }
    
    @Test(expected=IllegalArgumentException.class)
    public void setEmployeIdInvalid() {
        timeSheetData.setEmployeId(INVALID_EMPLOYE_ID);
        assertFalse(INVALID_EMPLOYE_ID == timeSheetData.getEmployeId());
    }
    
    
    @Test
    public void hasValidWeek() {
        timeSheetData.setDayByName(new Day(JOUR2_KEY));
        timeSheetData.setDayByName(new Day(JOUR3_KEY));
        timeSheetData.setDayByName(new Day(JOUR4_KEY));
        timeSheetData.setDayByName(new Day(JOUR5_KEY));
        timeSheetData.setDayByName(new Day(WEEKEND1_KEY));
        timeSheetData.setDayByName(new Day(WEEKEND2_KEY));
        assertTrue(timeSheetData.hasValidWeek());
    }
    
    @Test
    public void hasValidWeekFailMissingDay() {
        timeSheetData.setDayByName(new Day(JOUR2_KEY));
        timeSheetData.setDayByName(new Day(JOUR3_KEY));
        timeSheetData.setDayByName(new Day(JOUR4_KEY));
        timeSheetData.setDayByName(new Day(JOUR5_KEY));
        timeSheetData.setDayByName(new Day(WEEKEND2_KEY));
        assertFalse(timeSheetData.hasValidWeek());
    }    
}
