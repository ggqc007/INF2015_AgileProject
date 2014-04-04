package timesheet;

import java.util.ArrayList;
import java.util.List;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class JSONParserTest {
    
    private String expectedTimeSheet;
    private String validJSONString;
    private JSONObject validJSONObject;
    
    @Before
    public void initObjects() {
        expectedTimeSheet = "TimeSheetData{employeId: 5001, days: [Day{name: \"weekend2\", tasks:[Task{projectId: 990,"
                + " time: 30}]}, Day{name: \"jour1\", tasks:[Task{projectId: 998, time: 480}, Task{projectId: 911,"
                + " time: 36}, Task{projectId: 910, time: 8}]}, Day{name: \"jour2\", tasks:[Task{projectId: 125,"
                + " time: 552}]}, Day{name: \"jour3\", tasks:[Task{projectId: 996, time: 80}]}, Day{name: \"jour4\","
                + " tasks:[Task{projectId: 996, time: 80}]}, Day{name: \"jour5\", tasks:[Task{projectId: 125,"
                + " time: 516}]}, Day{name: \"weekend1\", tasks:[]}]}";
        validJSONString = "{\n \"numero_employe\": 5001,\n \"jour1\": [\n {\n \"projet\": 998,\n \"minutes\": 480\n },"
                + "\n {\n \"projet\": 911,\n \"minutes\": 36\n },\n {\n \"projet\": 910,\n \"minutes\": 8\n }\n ],\n "
                + "\"jour2\": [\n {\n \"projet\": 125,\n \"minutes\": 552\n }\n ],\n \"jour3\": [\n {\n \"projet\": 996,"
                + "\n \"minutes\": 80\n }\n ],\n \"jour4\": [\n {\n \"projet\": 996,\n \"minutes\": 80 }\n ],\n "
                + "\"jour5\": [\n  {\n \"projet\": 125,\n \"minutes\": 516 }\n ],\n \"weekend1\": [],\n \"weekend2\":"
                + " [\n {\n \"projet\": 990,\n \"minutes\": 30\n }\n ]\n}";
        validJSONObject = JSONObject.fromObject(validJSONString);
    }
    
    @Test
    public void testToTimeSheetDataValidTimeSheetGeneration() throws Exception {
        String timeSheetData = JSONParser.toTimeSheetData(validJSONObject).toString();
        assertEquals(expectedTimeSheet, timeSheetData);
    }
    
    @Test(expected = Exception.class)
    public void testToTimeSheetDataMissingDayTimeSheetGeneration() throws Exception {
        String missingDayJSONString = "{\n \"numero_employe\": 5001,\n \"jour2\": [\n {\n \"projet\": 125,\n "
                + "\"minutes\": 552\n }\n ],\n \"jour3\": [\n {\n \"projet\": 996,\n \"minutes\": 80\n }\n ],\n "
                + "\"jour4\": [\n {\n \"projet\": 996,\n \"minutes\": 80 }\n ],\n \"jour5\": [\n  {\n "
                + "\"projet\": 125,\n \"minutes\": 516 }\n ],\n \"weekend1\": [],\n \"weekend2\": [\n {\n "
                + "\"projet\": 990,\n \"minutes\": 30\n }\n ]\n}";
        JSONObject missingDayJSONObject = JSONObject.fromObject(missingDayJSONString);
        String result = JSONParser.toTimeSheetData(missingDayJSONObject).toString();
        assertEquals(expectedTimeSheet, result); // Fail if Exception not thrown.
    }
    
    @Test
    public void testReportToJSONArray() {
        List<String> errorReport = new ArrayList<String>();
        JSONArray expArray = new JSONArray();
        for (int i = 1; i < 5; i++) {
            errorReport.add("Error"+i);
            expArray.add("Error"+i);
        }
        assertEquals(expArray.toString(), JSONParser.reportToJSONArray(errorReport).toString());
    } 
}
