package timesheet;

import net.sf.json.JSONObject;
import org.junit.Test;
import static org.junit.Assert.*;

public class EmployeTest {

    private Employe employe;
    private JSONObject validJSONObject;
    private TimeSheetData validTimeSheetData;
    private static final int ADMIN_EMPLOYE_MIN = 0;
    private static final int ADMIN_EMPLOYE_MAX = 999;
    private static final int DEVELOPMENT_EMPLOYE_MIN = 1000;
    private static final int DEVELOPMENT_EMPLOYE_MAX = 1999;
    private static final int EXPLOITATION_EMPLOYE_MIN = 2000;
    private static final int EXPLOITATION_EMPLOYE_MAX = 5000;
    private static final int DIRECTION_EMPLOYE_MIN = 5001;
    private static final int PREDIDENT_ID = 6000;

    private static final String INVALID_JSON_STRING = "{\n \"numero_employe\": -1,\n \"jour1\": [\n {\n \"projet\": 998,"
            + "\n \"minutes\": 480\n },\n {\n \"projet\": 911,\n \"minutes\": 36\n },\n {\n \"projet\": 910,\n "
            + "\"minutes\": 8\n }\n ],\n \"jour2\": [\n {\n \"projet\": 125,\n \"minutes\": 552\n }\n ],\n \"jour3\":"
            + " [\n {\n \"projet\": 996,\n \"minutes\": 80\n }\n ],\n \"jour4\": [\n {\n \"projet\": 996,\n \"minutes\":"
            + " 80 }\n ],\n \"jour5\": [\n  {\n \"projet\": 125,\n \"minutes\": 516 }\n ],\n \"weekend1\": [],\n "
            + "\"weekend2\": [\n {\n \"projet\": 990,\n \"minutes\": 30\n }\n ]\n}";

    private static final String VALID_JSON_STRING = "{\n \"numero_employe\": 5001,\n \"jour1\": [\n {\n \"projet\": 998,\n"
            + " \"minutes\": 480\n },\n {\n \"projet\": 911,\n \"minutes\": 36\n },\n {\n \"projet\": 910,\n \"minutes\":"
            + " 8\n }\n ],\n \"jour2\": [\n {\n \"projet\": 125,\n \"minutes\": 552\n }\n ],\n \"jour3\": [\n {\n "
            + "\"projet\": 996,\n \"minutes\": 80\n }\n ],\n \"jour4\": [\n {\n \"projet\": 996,\n \"minutes\": 80 }"
            + "\n ],\n \"jour5\": [\n  {\n \"projet\": 125,\n \"minutes\": 516 }\n ],\n \"weekend1\": [],\n \"weekend2\":"
            + " [\n {\n \"projet\": 990,\n \"minutes\": 30\n }\n ]\n}";

    private static final String EXPECTED_EMPLOYE_STRING = "Employe{employeId: 5001, timesheets: [TimeSheetData{employeId: "
            + "5001, days: [Day{name: \"weekend2\", tasks:[Task{projectId: 990, time: 30}]}, Day{name: \"jour1\", "
            + "tasks:[Task{projectId: 998, time: 480}, Task{projectId: 911, time: 36}, Task{projectId: 910, "
            + "time: 8}]}, Day{name: \"jour2\", tasks:[Task{projectId: 125, time: 552}]}, Day{name: \"jour3\", "
            + "tasks:[Task{projectId: 996, time: 80}]}, Day{name: \"jour4\", tasks:[Task{projectId: 996, time: 80}]}, "
            + "Day{name: \"jour5\", tasks:[Task{projectId: 125, time: 516}]}, Day{name: \"weekend1\", tasks:[]}]}]}";

    private static final String EXPECTED_TIMESHEETDATA_STRING = "TimeSheetData{employeId: 5001, days: [Day{name: "
            + "\"weekend2\", tasks:[Task{projectId: 990, time: 30}]}, Day{name: \"jour1\", tasks:[Task{projectId: "
            + "998, time: 480}, Task{projectId: 911, time: 36}, Task{projectId: 910, time: 8}]}, Day{name: \"jour2\","
            + " tasks:[Task{projectId: 125, time: 552}]}, Day{name: \"jour3\", tasks:[Task{projectId: 996, time: 80}]},"
            + " Day{name: \"jour4\", tasks:[Task{projectId: 996, time: 80}]}, Day{name: \"jour5\", tasks:[Task{projectId:"
            + " 125, time: 516}]}, Day{name: \"weekend1\", tasks:[]}]}";

    @Test
    public void testInitFromFirstTimeSheetWithValidEmploye() throws Exception {
        employe = new Employe();
        validJSONObject = JSONObject.fromObject(VALID_JSON_STRING);
        validTimeSheetData = JSONParser.toTimeSheetData(validJSONObject);
        employe.initFromFirstTimeSheet(validTimeSheetData);
        assertEquals(EXPECTED_EMPLOYE_STRING, employe.toString());
    }

    @Test(expected = IllegalArgumentException.class)
    public void testInitFromFirstTimeSheetWithInvalidEmployeID() throws Exception {
        JSONObject invalidJSONObject = JSONObject.fromObject(INVALID_JSON_STRING);
        TimeSheetData invalidTimeSheetData = JSONParser.toTimeSheetData(invalidJSONObject);
        employe.initFromFirstTimeSheet(invalidTimeSheetData);
    }

    @Test
    public void testGetTimeSheetValid() throws Exception {
        employe = new Employe();
        validJSONObject = JSONObject.fromObject(VALID_JSON_STRING);
        validTimeSheetData = JSONParser.toTimeSheetData(validJSONObject);
        employe.initFromFirstTimeSheet(validTimeSheetData);
        String result = employe.getTimeSheet(0).toString();
        assertEquals(EXPECTED_TIMESHEETDATA_STRING, result);
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void testGetTimeSheetInvalid() {
        employe = new Employe();
        employe.getTimeSheet(-1);
    }

    @Test
    public void testAdminEmployeBelowMin() throws Exception {
        Employe testEmploye = new Employe();
        testEmploye.setEmployeId(-1);
        assertFalse(testEmploye.isAdmin());
    }

    @Test
    public void testAdminEmployeMin() throws Exception {
        Employe testEmploye = new Employe();
        testEmploye.setEmployeId(ADMIN_EMPLOYE_MIN);
        assertTrue(testEmploye.isAdmin());
    }

    @Test
    public void testAdminEmployeMax() throws Exception {
        Employe testEmploye = new Employe();
        testEmploye.setEmployeId(ADMIN_EMPLOYE_MAX);
        assertTrue(testEmploye.isAdmin());
    }

    @Test
    public void testAdminEmployeOverMax() throws Exception {
        Employe testEmploye = new Employe();
        testEmploye.setEmployeId(DEVELOPMENT_EMPLOYE_MIN);
        assertFalse(testEmploye.isAdmin());
    }

    @Test
    public void testDevelopmentEmployeBelowMin() throws Exception {
        Employe testEmploye = new Employe();
        testEmploye.setEmployeId(ADMIN_EMPLOYE_MAX);
        assertFalse(testEmploye.isDevelEmploye());
    }

    @Test
    public void testDevelopmentEmployeMin() throws Exception {
        Employe testEmploye = new Employe();
        testEmploye.setEmployeId(DEVELOPMENT_EMPLOYE_MIN);
        assertTrue(testEmploye.isDevelEmploye());
    }

    @Test
    public void testDevelopmentEmployeMax() throws Exception {
        Employe testEmploye = new Employe();
        testEmploye.setEmployeId(DEVELOPMENT_EMPLOYE_MAX);
        assertTrue(testEmploye.isDevelEmploye());
    }

    @Test
    public void testDevelopmentEmployeOverMax() throws Exception {
        Employe testEmploye = new Employe();
        testEmploye.setEmployeId(EXPLOITATION_EMPLOYE_MIN);
        assertFalse(testEmploye.isDevelEmploye());
    }

    @Test
    public void testExploitationEmployeBelowMin() throws Exception {
        Employe testEmploye = new Employe();
        testEmploye.setEmployeId(DEVELOPMENT_EMPLOYE_MAX);
        assertFalse(testEmploye.isExplEmploye());
    }

    @Test
    public void testExploitationEmployeMin() throws Exception {
        Employe testEmploye = new Employe();
        testEmploye.setEmployeId(EXPLOITATION_EMPLOYE_MIN);
        assertTrue(testEmploye.isExplEmploye());
    }

    @Test
    public void testExploitationEmployeMax() throws Exception {
        Employe testEmploye = new Employe();
        testEmploye.setEmployeId(EXPLOITATION_EMPLOYE_MAX);
        assertTrue(testEmploye.isExplEmploye());
    }

    @Test
    public void testExploitationEmployeOverMax() throws Exception {
        Employe testEmploye = new Employe();
        testEmploye.setEmployeId(DIRECTION_EMPLOYE_MIN);
        assertFalse(testEmploye.isExplEmploye());
    }

    @Test
    public void testDirectionEmployeBelowMin() throws Exception {
        Employe testEmploye = new Employe();
        testEmploye.setEmployeId(EXPLOITATION_EMPLOYE_MAX);
        assertFalse(testEmploye.isDirectionEmploye());
    }

    @Test
    public void testDirectionEmployeMin() throws Exception {
        Employe testEmploye = new Employe();
        testEmploye.setEmployeId(DIRECTION_EMPLOYE_MIN);
        assertTrue(testEmploye.isDirectionEmploye());
    }

    @Test
    public void testDirectionEmployePresidentID() throws Exception {
        Employe testEmploye = new Employe();
        testEmploye.setEmployeId(PREDIDENT_ID);
        assertTrue(testEmploye.isDirectionEmploye());
    }

    @Test
    public void testIsPresidentTrue() throws Exception {
        Employe testEmploye = new Employe();
        testEmploye.setEmployeId(PREDIDENT_ID);
        assertTrue(testEmploye.isPresident());
    }

    @Test
    public void testIsPresidentFalse() throws Exception {
        Employe testEmploye = new Employe();
        testEmploye.setEmployeId(DEVELOPMENT_EMPLOYE_MIN);
        assertFalse(testEmploye.isPresident());
    }
}
