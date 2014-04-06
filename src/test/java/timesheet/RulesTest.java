/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package timesheet;

import java.util.List;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author khaled
 */
public class RulesTest {
    
    public RulesTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of hasValidWeeklyTimeRemote method, of class Rules.
     */
    @Test
    public void testHasValidWeeklyTimeRemote() {
        System.out.println("hasValidWeeklyTimeRemote");
        Rules instance = new RulesImpl();
        boolean expResult = false;
        boolean result = instance.hasValidWeeklyTimeRemote();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of hasValidWeeklyTimeInOffice method, of class Rules.
     */
    @Test
    public void testHasValidWeeklyTimeInOffice() {
        System.out.println("hasValidWeeklyTimeInOffice");
        Rules instance = new RulesImpl();
        boolean expResult = false;
        boolean result = instance.hasValidWeeklyTimeInOffice();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of hasMinimumWeeklyTimeInOffice method, of class Rules.
     */
    @Test
    public void testHasMinimumWeeklyTimeInOffice() {
        System.out.println("hasMinimumWeeklyTimeInOffice");
        Rules instance = new RulesImpl();
        boolean expResult = false;
        boolean result = instance.hasMinimumWeeklyTimeInOffice();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getInvalidDaysWithMinimumDailyTimeInOffice method, of class Rules.
     */
    @Test
    public void testGetInvalidDaysWithMinimumDailyTimeInOffice() {
        System.out.println("getInvalidDaysWithMinimumDailyTimeInOffice");
        Rules instance = new RulesImpl();
        List<Day> expResult = null;
        List<Day> result = instance.getInvalidDaysWithMinimumDailyTimeInOffice();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getInvalidDaysWithSickLeave method, of class Rules.
     */
    @Test
    public void testGetInvalidDaysWithSickLeave() {
        System.out.println("getInvalidDaysWithSickLeave");
        Rules instance = new RulesImpl();
        List<Day> expResult = null;
        List<Day> result = instance.getInvalidDaysWithSickLeave();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getInvalidDaysWithPublicHoliday method, of class Rules.
     */
    @Test
    public void testGetInvalidDaysWithPublicHoliday() {
        System.out.println("getInvalidDaysWithPublicHoliday");
        Rules instance = new RulesImpl();
        List<Day> expResult = null;
        List<Day> result = instance.getInvalidDaysWithPublicHoliday();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getInvalidDaysOfParentalHoliday method, of class Rules.
     */
    @Test
    public void testGetInvalidDaysOfParentalHoliday() {
        System.out.println("getInvalidDaysOfParentalHoliday");
        Rules instance = new RulesImpl();
        List<Day> expResult = null;
        List<Day> result = instance.getInvalidDaysOfParentalHoliday();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getInvalidDaysOfHoliday method, of class Rules.
     */
    @Test
    public void testGetInvalidDaysOfHoliday() {
        System.out.println("getInvalidDaysOfHoliday");
        Rules instance = new RulesImpl();
        List<Day> expResult = null;
        List<Day> result = instance.getInvalidDaysOfHoliday();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getInvalidDaysWithToMuchTime method, of class Rules.
     */
    @Test
    public void testGetInvalidDaysWithToMuchTime() {
        System.out.println("getInvalidDaysWithToMuchTime");
        Rules instance = new RulesImpl();
        List<Day> expResult = null;
        List<Day> result = instance.getInvalidDaysWithToMuchTime();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getInvalidDaysWithInvalidTasksAfter24Hours method, of class Rules.
     */
    @Test
    public void testGetInvalidDaysWithInvalidTasksAfter24Hours() {
        System.out.println("getInvalidDaysWithInvalidTasksAfter24Hours");
        Rules instance = new RulesImpl();
        List<Day> expResult = null;
        List<Day> result = instance.getInvalidDaysWithInvalidTasksAfter24Hours();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getInvalidDaysWithoutMinimumMinutesForTask method, of class Rules.
     */
    @Test
    public void testGetInvalidDaysWithoutMinimumMinutesForTask() {
        System.out.println("getInvalidDaysWithoutMinimumMinutesForTask");
        Rules instance = new RulesImpl();
        List<Day> expResult = null;
        List<Day> result = instance.getInvalidDaysWithoutMinimumMinutesForTask();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getInvalidDaysWithDuplicateTasks method, of class Rules.
     */
    @Test
    public void testGetInvalidDaysWithDuplicateTasks() {
        System.out.println("getInvalidDaysWithDuplicateTasks");
        Rules instance = new RulesImpl();
        List<Day> expResult = null;
        List<Day> result = instance.getInvalidDaysWithDuplicateTasks();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of isDuplicateTask method, of class Rules.
     */
    @Test
    public void testIsDuplicateTask() {
        System.out.println("isDuplicateTask");
        List<Task> tasks = null;
        Rules instance = new RulesImpl();
        boolean expResult = false;
        boolean result = instance.isDuplicateTask(tasks);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of calculateTotalWeekMinutes method, of class Rules.
     */
    @Test
    public void testCalculateTotalWeekMinutes() {
        System.out.println("calculateTotalWeekMinutes");
        Rules instance = new RulesImpl();
        instance.calculateTotalWeekMinutes();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of calculateTotalWeekMinutesByTasks method, of class Rules.
     */
    @Test
    public void testCalculateTotalWeekMinutesByTasks() {
        System.out.println("calculateTotalWeekMinutesByTasks");
        List<Task> tasks = null;
        Rules instance = new RulesImpl();
        instance.calculateTotalWeekMinutesByTasks(tasks);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of sumTotalMinutes method, of class Rules.
     */
    @Test
    public void testSumTotalMinutes() {
        System.out.println("sumTotalMinutes");
        Task task = null;
        Rules instance = new RulesImpl();
        instance.sumTotalMinutes(task);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getTotalOfficeMinutesByDay method, of class Rules.
     */
    @Test
    public void testGetTotalOfficeMinutesByDay() {
        System.out.println("getTotalOfficeMinutesByDay");
        Day day = null;
        Rules instance = new RulesImpl();
        int expResult = 0;
        int result = instance.getTotalOfficeMinutesByDay(day);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getTotalRemoteMinutesByDay method, of class Rules.
     */
    @Test
    public void testGetTotalRemoteMinutesByDay() {
        System.out.println("getTotalRemoteMinutesByDay");
        Day day = null;
        Rules instance = new RulesImpl();
        int expResult = 0;
        int result = instance.getTotalRemoteMinutesByDay(day);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setEmploye method, of class Rules.
     */
    @Test
    public void testSetEmploye() {
        System.out.println("setEmploye");
        Employe employe = null;
        Rules instance = new RulesImpl();
        instance.setEmploye(employe);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getMaxOfficeWeekMinutes method, of class Rules.
     */
    @Test
    public void testGetMaxOfficeWeekMinutes() {
        System.out.println("getMaxOfficeWeekMinutes");
        Rules instance = new RulesImpl();
        int expResult = 0;
        int result = instance.getMaxOfficeWeekMinutes();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getMinOfficeWeekMinutes method, of class Rules.
     */
    @Test
    public void testGetMinOfficeWeekMinutes() {
        System.out.println("getMinOfficeWeekMinutes");
        Rules instance = new RulesImpl();
        int expResult = 0;
        int result = instance.getMinOfficeWeekMinutes();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getMaxRemoteWeekMinutes method, of class Rules.
     */
    @Test
    public void testGetMaxRemoteWeekMinutes() {
        System.out.println("getMaxRemoteWeekMinutes");
        Rules instance = new RulesImpl();
        int expResult = 0;
        int result = instance.getMaxRemoteWeekMinutes();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getMinOfficeDailyMinutes method, of class Rules.
     */
    @Test
    public void testGetMinOfficeDailyMinutes() {
        System.out.println("getMinOfficeDailyMinutes");
        Rules instance = new RulesImpl();
        int expResult = 0;
        int result = instance.getMinOfficeDailyMinutes();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getTotalWeekMinutes method, of class Rules.
     */
    @Test
    public void testGetTotalWeekMinutes() {
        System.out.println("getTotalWeekMinutes");
        Rules instance = new RulesImpl();
        int expResult = 0;
        int result = instance.getTotalWeekMinutes();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getTotalOfficeWeekMinutes method, of class Rules.
     */
    @Test
    public void testGetTotalOfficeWeekMinutes() {
        System.out.println("getTotalOfficeWeekMinutes");
        Rules instance = new RulesImpl();
        int expResult = 0;
        int result = instance.getTotalOfficeWeekMinutes();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getTotalRemoteWeekMinutes method, of class Rules.
     */
    @Test
    public void testGetTotalRemoteWeekMinutes() {
        System.out.println("getTotalRemoteWeekMinutes");
        Rules instance = new RulesImpl();
        int expResult = 0;
        int result = instance.getTotalRemoteWeekMinutes();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    public class RulesImpl extends Rules {

        public boolean hasValidWeeklyTimeRemote() {
            return false;
        }
    }
    
}
