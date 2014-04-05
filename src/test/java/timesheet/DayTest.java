package timesheet;

import java.util.List;
import net.sf.json.JSONObject;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class DayTest {

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

}
