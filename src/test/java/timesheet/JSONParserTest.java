package timesheet;

import java.util.List;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class JSONParserTest {
    
    public JSONParserTest() {
    }

    @Before
    public void setUp() {
        // create JSONObject
    }
    
    @After
    public void tearDown() {
        // detruit JSONObject
        String jsonString = "{\n \"numero_employe\": 5001,\n \"jour1\": [\n {\n \"projet\": 998,\n \"minutes\": 480\n },\n {\n \"projet\": 911,\n \"minutes\": 36\n },\n {\n \"projet\": 910,\n \"minutes\": 8\n }\n ],\n \"jour2\": [\n {\n \"projet\": 125,\n \"minutes\": 552\n }\n ],\n \"jour3\": [\n {\n \"projet\": 996,\n \"minutes\": 80\n }\n ],\n \"jour4\": [\n {\n \"projet\": 996,\n \"minutes\": 80 }\n ],\n \"jour5\": [\n  {\n \"projet\": 125,\n \"minutes\": 516 }\n ],\n \"weekend1\": [],\n \"weekend2\": [\n {\n \"projet\": 990,\n \"minutes\": 30\n }\n ]\n}";
        JSONObject jsonObject = JSONObject.fromObject(jsonString);
        System.out.println(jsonObject);
    }

    /**
     * Test of toTimeSheetData method, of class JSONParser.
     */
    @Test
    public void testToTimeSheetData() throws Exception {
        System.out.println("toTimeSheetData");
        JSONObject jsonObjectFromFile = null;
        TimeSheetData expResult = null;
        TimeSheetData result = JSONParser.toTimeSheetData(jsonObjectFromFile);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of reportToJSONArray method, of class JSONParser.
     */
    @Test
    public void testReportToJSONArray() {
        System.out.println("reportToJSONArray");
        List errorReport = null;
        JSONArray expResult = null;
        JSONArray result = JSONParser.reportToJSONArray(errorReport);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of employeIdToTimeSheetData method, of class JSONParser.
     */
    @Test
    public void testEmployeIdToTimeSheetData() {
        System.out.println("employeIdToTimeSheetData");
        TimeSheetData timeSheetData = null;
        JSONObject jsonObjectFromFile = null;
        JSONParser.employeIdToTimeSheetData(timeSheetData, jsonObjectFromFile);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of createDaysWithTasksInTimeSheetData method, of class JSONParser.
     */
    @Test
    public void testCreateDaysWithTasksInTimeSheetData() {
        System.out.println("createDaysWithTasksInTimeSheetData");
        TimeSheetData timeSheetData = null;
        JSONObject jsonObjectFromFile = null;
        JSONParser.createDaysWithTasksInTimeSheetData(timeSheetData, jsonObjectFromFile);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of tasksToADayIfAny method, of class JSONParser.
     */
    @Test
    public void testTasksToADayIfAny() {
        System.out.println("tasksToADayIfAny");
        Day aDay = null;
        JSONArray taskForADay = null;
        JSONParser.tasksToADayIfAny(aDay, taskForADay);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
}
