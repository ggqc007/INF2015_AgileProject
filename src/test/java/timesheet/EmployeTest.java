package timesheet;

import java.util.List;
import net.sf.json.JSONObject;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author GG
 */
public class EmployeTest {

    private Employe employe;
    private String validJSONString;
    private JSONObject validJSONObject;
    private TimeSheetData validTimeSheetData;
    private String exptectedEmployeString;
    private static final int ADMIN_EMPLOYE_MIN = 0;
    private static final int ADMIN_EMPLOYE_MAX = 999;
    private static final int DEVELOPMENT_EMPLOYE_MIN = 1000;
    private static final int DEVELOPMENT_EMPLOYE_MAX = 1999;
    private static final int EXPLOITATION_EMPLOYE_MIN = 2000;
    private static final int EXPLOITATION_EMPLOYE_MAX = 5000;
    private static final int DIRECTION_EMPLOYE_MIN = 5001;

    @Before
    public void initEmploye() throws Exception {
        employe = new Employe();
        validJSONString = "{\n \"numero_employe\": 5001,\n \"jour1\": [\n {\n \"projet\": 998,\n \"minutes\": 480\n },"
                + "\n {\n \"projet\": 911,\n \"minutes\": 36\n },\n {\n \"projet\": 910,\n \"minutes\": 8\n }\n ],\n "
                + "\"jour2\": [\n {\n \"projet\": 125,\n \"minutes\": 552\n }\n ],\n \"jour3\": [\n {\n \"projet\": 996,"
                + "\n \"minutes\": 80\n }\n ],\n \"jour4\": [\n {\n \"projet\": 996,\n \"minutes\": 80 }\n ],\n "
                + "\"jour5\": [\n  {\n \"projet\": 125,\n \"minutes\": 516 }\n ],\n \"weekend1\": [],\n \"weekend2\":"
                + " [\n {\n \"projet\": 990,\n \"minutes\": 30\n }\n ]\n}";
        exptectedEmployeString = "Employe{employeId: 5001, timesheets: [TimeSheetData{employeId: 5001, "
                + "days: [Day{name: \"weekend2\", tasks:[Task{projectId: 990, time: 30}]}, Day{name: \"jour1\", "
                + "tasks:[Task{projectId: 998, time: 480}, Task{projectId: 911, time: 36}, Task{projectId: 910, "
                + "time: 8}]}, Day{name: \"jour2\", tasks:[Task{projectId: 125, time: 552}]}, Day{name: \"jour3\", "
                + "tasks:[Task{projectId: 996, time: 80}]}, Day{name: \"jour4\", tasks:[Task{projectId: 996, time: 80}]}, "
                + "Day{name: \"jour5\", tasks:[Task{projectId: 125, time: 516}]}, Day{name: \"weekend1\", tasks:[]}]}]}";
        validJSONObject = JSONObject.fromObject(validJSONString);
        validTimeSheetData = JSONParser.toTimeSheetData(validJSONObject);
        employe.initFromFirstTimeSheet(validTimeSheetData);
    }

    @Test
    public void testInitFromFirstTimeSheetWithValidEmploye() {
        assertEquals(exptectedEmployeString, employe.toString());
    }

    @Test(expected = IllegalArgumentException.class)
    public void testInitFromFirstTimeSheetWithInvalidEmployeID() throws Exception {
        String invalidJSONString = "{\n \"numero_employe\": -1,\n \"jour1\": [\n {\n \"projet\": 998,\n \"minutes\": 480\n },"
                + "\n {\n \"projet\": 911,\n \"minutes\": 36\n },\n {\n \"projet\": 910,\n \"minutes\": 8\n }\n ],\n "
                + "\"jour2\": [\n {\n \"projet\": 125,\n \"minutes\": 552\n }\n ],\n \"jour3\": [\n {\n \"projet\": 996,"
                + "\n \"minutes\": 80\n }\n ],\n \"jour4\": [\n {\n \"projet\": 996,\n \"minutes\": 80 }\n ],\n "
                + "\"jour5\": [\n  {\n \"projet\": 125,\n \"minutes\": 516 }\n ],\n \"weekend1\": [],\n \"weekend2\":"
                + " [\n {\n \"projet\": 990,\n \"minutes\": 30\n }\n ]\n}";
        JSONObject invalidJSONObject = JSONObject.fromObject(invalidJSONString);
        TimeSheetData invalidTimeSheetData = JSONParser.toTimeSheetData(invalidJSONObject);
        employe.initFromFirstTimeSheet(invalidTimeSheetData);
        assertEquals(exptectedEmployeString, employe.toString()); // fail if no exception
    }

    @Test
    public void testGetTimeSheetValid() {
        String expectedTimeSheet = "TimeSheetData{employeId: 5001, days: [Day{name: \"weekend2\", tasks:[Task{projectId: 990,"
                + " time: 30}]}, Day{name: \"jour1\", tasks:[Task{projectId: 998, time: 480}, Task{projectId: 911,"
                + " time: 36}, Task{projectId: 910, time: 8}]}, Day{name: \"jour2\", tasks:[Task{projectId: 125,"
                + " time: 552}]}, Day{name: \"jour3\", tasks:[Task{projectId: 996, time: 80}]}, Day{name: \"jour4\","
                + " tasks:[Task{projectId: 996, time: 80}]}, Day{name: \"jour5\", tasks:[Task{projectId: 125,"
                + " time: 516}]}, Day{name: \"weekend1\", tasks:[]}]}";
        String result = employe.getTimeSheet(0).toString();
        assertEquals(expectedTimeSheet, result);
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void testGetTimeSheetInvalid() {
        employe.getTimeSheet(-1);
    }

    @Test
    public void testAdminEmployeMin() throws Exception {
        String validJSONStringForAdmin = "{\n \"numero_employe\": " + ADMIN_EMPLOYE_MIN + ",\n \"jour1\": [\n {\n \"projet\": 998,\n \"minutes\": 480\n },"
                + "\n {\n \"projet\": 911,\n \"minutes\": 36\n },\n {\n \"projet\": 910,\n \"minutes\": 8\n }\n ],\n "
                + "\"jour2\": [\n {\n \"projet\": 125,\n \"minutes\": 552\n }\n ],\n \"jour3\": [\n {\n \"projet\": 996,"
                + "\n \"minutes\": 80\n }\n ],\n \"jour4\": [\n {\n \"projet\": 996,\n \"minutes\": 80 }\n ],\n "
                + "\"jour5\": [\n  {\n \"projet\": 125,\n \"minutes\": 516 }\n ],\n \"weekend1\": [],\n \"weekend2\":"
                + " [\n {\n \"projet\": 990,\n \"minutes\": 30\n }\n ]\n}";

        JSONObject validJSONObjectForAdmin = JSONObject.fromObject(validJSONStringForAdmin);
        TimeSheetData validTimeSheetDataForAdmin = JSONParser.toTimeSheetData(validJSONObjectForAdmin);
        employe.initFromFirstTimeSheet(validTimeSheetDataForAdmin);
        assertTrue(employe.isAdmin());
    }

    @Test
    public void testAdminEmployeMax() throws Exception {
        String validJSONStringForAdmin = "{\n \"numero_employe\": " + ADMIN_EMPLOYE_MAX + ",\n \"jour1\": [\n {\n \"projet\": 998,\n \"minutes\": 480\n },"
                + "\n {\n \"projet\": 911,\n \"minutes\": 36\n },\n {\n \"projet\": 910,\n \"minutes\": 8\n }\n ],\n "
                + "\"jour2\": [\n {\n \"projet\": 125,\n \"minutes\": 552\n }\n ],\n \"jour3\": [\n {\n \"projet\": 996,"
                + "\n \"minutes\": 80\n }\n ],\n \"jour4\": [\n {\n \"projet\": 996,\n \"minutes\": 80 }\n ],\n "
                + "\"jour5\": [\n  {\n \"projet\": 125,\n \"minutes\": 516 }\n ],\n \"weekend1\": [],\n \"weekend2\":"
                + " [\n {\n \"projet\": 990,\n \"minutes\": 30\n }\n ]\n}";

        JSONObject validJSONObjectForAdmin = JSONObject.fromObject(validJSONStringForAdmin);
        TimeSheetData validTimeSheetDataForAdmin = JSONParser.toTimeSheetData(validJSONObjectForAdmin);
        employe.initFromFirstTimeSheet(validTimeSheetDataForAdmin);
        assertTrue(employe.isAdmin());
    }

    @Test
    public void testDevelopmentEmployeMin() throws Exception {
        String validJSONStringForAdmin = "{\n \"numero_employe\": " + DEVELOPMENT_EMPLOYE_MIN + ",\n \"jour1\": [\n {\n \"projet\": 998,\n \"minutes\": 480\n },"
                + "\n {\n \"projet\": 911,\n \"minutes\": 36\n },\n {\n \"projet\": 910,\n \"minutes\": 8\n }\n ],\n "
                + "\"jour2\": [\n {\n \"projet\": 125,\n \"minutes\": 552\n }\n ],\n \"jour3\": [\n {\n \"projet\": 996,"
                + "\n \"minutes\": 80\n }\n ],\n \"jour4\": [\n {\n \"projet\": 996,\n \"minutes\": 80 }\n ],\n "
                + "\"jour5\": [\n  {\n \"projet\": 125,\n \"minutes\": 516 }\n ],\n \"weekend1\": [],\n \"weekend2\":"
                + " [\n {\n \"projet\": 990,\n \"minutes\": 30\n }\n ]\n}";

        JSONObject validJSONObjectForAdmin = JSONObject.fromObject(validJSONStringForAdmin);
        TimeSheetData validTimeSheetDataForAdmin = JSONParser.toTimeSheetData(validJSONObjectForAdmin);
        employe.initFromFirstTimeSheet(validTimeSheetDataForAdmin);
        assertTrue(employe.isDevelEmploye());
    }

    @Test
    public void testDevelopmentEmployeMax() throws Exception {
        String validJSONStringForAdmin = "{\n \"numero_employe\": " + DEVELOPMENT_EMPLOYE_MAX + ",\n \"jour1\": [\n {\n \"projet\": 998,\n \"minutes\": 480\n },"
                + "\n {\n \"projet\": 911,\n \"minutes\": 36\n },\n {\n \"projet\": 910,\n \"minutes\": 8\n }\n ],\n "
                + "\"jour2\": [\n {\n \"projet\": 125,\n \"minutes\": 552\n }\n ],\n \"jour3\": [\n {\n \"projet\": 996,"
                + "\n \"minutes\": 80\n }\n ],\n \"jour4\": [\n {\n \"projet\": 996,\n \"minutes\": 80 }\n ],\n "
                + "\"jour5\": [\n  {\n \"projet\": 125,\n \"minutes\": 516 }\n ],\n \"weekend1\": [],\n \"weekend2\":"
                + " [\n {\n \"projet\": 990,\n \"minutes\": 30\n }\n ]\n}";

        JSONObject validJSONObjectForAdmin = JSONObject.fromObject(validJSONStringForAdmin);
        TimeSheetData validTimeSheetDataForAdmin = JSONParser.toTimeSheetData(validJSONObjectForAdmin);
        employe.initFromFirstTimeSheet(validTimeSheetDataForAdmin);
        assertTrue(employe.isDevelEmploye());
    }

    @Test
    public void testExploitationEmployeMin() throws Exception {
        String validJSONStringForAdmin = "{\n \"numero_employe\": " + EXPLOITATION_EMPLOYE_MIN + ",\n \"jour1\": [\n {\n \"projet\": 998,\n \"minutes\": 480\n },"
                + "\n {\n \"projet\": 911,\n \"minutes\": 36\n },\n {\n \"projet\": 910,\n \"minutes\": 8\n }\n ],\n "
                + "\"jour2\": [\n {\n \"projet\": 125,\n \"minutes\": 552\n }\n ],\n \"jour3\": [\n {\n \"projet\": 996,"
                + "\n \"minutes\": 80\n }\n ],\n \"jour4\": [\n {\n \"projet\": 996,\n \"minutes\": 80 }\n ],\n "
                + "\"jour5\": [\n  {\n \"projet\": 125,\n \"minutes\": 516 }\n ],\n \"weekend1\": [],\n \"weekend2\":"
                + " [\n {\n \"projet\": 990,\n \"minutes\": 30\n }\n ]\n}";

        JSONObject validJSONObjectForAdmin = JSONObject.fromObject(validJSONStringForAdmin);
        TimeSheetData validTimeSheetDataForAdmin = JSONParser.toTimeSheetData(validJSONObjectForAdmin);
        employe.initFromFirstTimeSheet(validTimeSheetDataForAdmin);
        assertTrue(employe.isExplEmploye());
    }

    @Test
    public void testExploitationEmployeMax() throws Exception {
        String validJSONStringForAdmin = "{\n \"numero_employe\": " + EXPLOITATION_EMPLOYE_MAX + ",\n \"jour1\": [\n {\n \"projet\": 998,\n \"minutes\": 480\n },"
                + "\n {\n \"projet\": 911,\n \"minutes\": 36\n },\n {\n \"projet\": 910,\n \"minutes\": 8\n }\n ],\n "
                + "\"jour2\": [\n {\n \"projet\": 125,\n \"minutes\": 552\n }\n ],\n \"jour3\": [\n {\n \"projet\": 996,"
                + "\n \"minutes\": 80\n }\n ],\n \"jour4\": [\n {\n \"projet\": 996,\n \"minutes\": 80 }\n ],\n "
                + "\"jour5\": [\n  {\n \"projet\": 125,\n \"minutes\": 516 }\n ],\n \"weekend1\": [],\n \"weekend2\":"
                + " [\n {\n \"projet\": 990,\n \"minutes\": 30\n }\n ]\n}";

        JSONObject validJSONObjectForAdmin = JSONObject.fromObject(validJSONStringForAdmin);
        TimeSheetData validTimeSheetDataForAdmin = JSONParser.toTimeSheetData(validJSONObjectForAdmin);
        employe.initFromFirstTimeSheet(validTimeSheetDataForAdmin);
        assertTrue(employe.isExplEmploye());
    }

    @Test
    public void testDirectionEmployeMin() throws Exception {
        String validJSONStringForAdmin = "{\n \"numero_employe\": " + DIRECTION_EMPLOYE_MIN + ",\n \"jour1\": [\n {\n \"projet\": 998,\n \"minutes\": 480\n },"
                + "\n {\n \"projet\": 911,\n \"minutes\": 36\n },\n {\n \"projet\": 910,\n \"minutes\": 8\n }\n ],\n "
                + "\"jour2\": [\n {\n \"projet\": 125,\n \"minutes\": 552\n }\n ],\n \"jour3\": [\n {\n \"projet\": 996,"
                + "\n \"minutes\": 80\n }\n ],\n \"jour4\": [\n {\n \"projet\": 996,\n \"minutes\": 80 }\n ],\n "
                + "\"jour5\": [\n  {\n \"projet\": 125,\n \"minutes\": 516 }\n ],\n \"weekend1\": [],\n \"weekend2\":"
                + " [\n {\n \"projet\": 990,\n \"minutes\": 30\n }\n ]\n}";

        JSONObject validJSONObjectForAdmin = JSONObject.fromObject(validJSONStringForAdmin);
        TimeSheetData validTimeSheetDataForAdmin = JSONParser.toTimeSheetData(validJSONObjectForAdmin);
        employe.initFromFirstTimeSheet(validTimeSheetDataForAdmin);
        assertTrue(employe.isDirectionEmploye());
    }

}
