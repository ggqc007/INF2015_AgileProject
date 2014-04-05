/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package timesheet;

import java.util.List;
import net.sf.json.JSONObject;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author GG
 */
public class ReportTest {

    private String validJSONStringAdmin;
    private String validJSONStringDevelopment;
    private String validJSONStringExploitation;
    private String validJSONStringDirection;
    private JSONObject validJSONObjectAdmin;
    private JSONObject validJSONObjectDevelopment;
    private JSONObject validJSONObjectExploitation;
    private JSONObject validJSONObjectDirection;
    private Employe employeAdmin;
    private Employe employeDevelopment;
    private Employe employeExploitation;
    private Employe employeDirection;
    private TimeSheetData validTimeSheetDataAdmin;
    private TimeSheetData validTimeSheetDataDevelopment;
    private TimeSheetData validTimeSheetDataExploitation;
    private TimeSheetData validTimeSheetDataDirection;

    @Before
    public void initObjects() throws Exception {
        employeAdmin = new Employe();
        validJSONStringAdmin = "{\n \"numero_employe\": 1,\n \"jour1\": [\n {\n \"projet\": 998,\n \"minutes\": 480\n },"
                + "\n {\n \"projet\": 911,\n \"minutes\": 36\n },\n {\n \"projet\": 910,\n \"minutes\": 8\n }\n ],\n "
                + "\"jour2\": [\n {\n \"projet\": 125,\n \"minutes\": 552\n }\n ],\n \"jour3\": [\n {\n \"projet\": 996,"
                + "\n \"minutes\": 80\n }\n ],\n \"jour4\": [\n {\n \"projet\": 996,\n \"minutes\": 80 }\n ],\n "
                + "\"jour5\": [\n  {\n \"projet\": 125,\n \"minutes\": 516 }\n ],\n \"weekend1\": [],\n \"weekend2\":"
                + " [\n {\n \"projet\": 990,\n \"minutes\": 30\n }\n ]\n}";
        validJSONObjectAdmin = JSONObject.fromObject(validJSONStringAdmin);
        validTimeSheetDataAdmin = JSONParser.toTimeSheetData(validJSONObjectAdmin);
        employeAdmin.initFromFirstTimeSheet(validTimeSheetDataAdmin);

        employeDevelopment = new Employe();
        validJSONStringDevelopment = "{\n \"numero_employe\": 1000,\n \"jour1\": [\n {\n \"projet\": 998,\n \"minutes\": 480\n },"
                + "\n {\n \"projet\": 911,\n \"minutes\": 36\n },\n {\n \"projet\": 910,\n \"minutes\": 8\n }\n ],\n "
                + "\"jour2\": [\n {\n \"projet\": 125,\n \"minutes\": 552\n }\n ],\n \"jour3\": [\n {\n \"projet\": 996,"
                + "\n \"minutes\": 80\n }\n ],\n \"jour4\": [\n {\n \"projet\": 996,\n \"minutes\": 80 }\n ],\n "
                + "\"jour5\": [\n  {\n \"projet\": 125,\n \"minutes\": 516 }\n ],\n \"weekend1\": [],\n \"weekend2\":"
                + " [\n {\n \"projet\": 990,\n \"minutes\": 30\n }\n ]\n}";
        validJSONObjectDevelopment = JSONObject.fromObject(validJSONStringDevelopment);
        validTimeSheetDataDevelopment = JSONParser.toTimeSheetData(validJSONObjectDevelopment);
        employeDevelopment.initFromFirstTimeSheet(validTimeSheetDataDevelopment);

        employeExploitation = new Employe();
        validJSONStringExploitation = "{\n \"numero_employe\": 2000,\n \"jour1\": [\n {\n \"projet\": 998,\n \"minutes\": 480\n },"
                + "\n {\n \"projet\": 911,\n \"minutes\": 36\n },\n {\n \"projet\": 910,\n \"minutes\": 8\n }\n ],\n "
                + "\"jour2\": [\n {\n \"projet\": 125,\n \"minutes\": 552\n }\n ],\n \"jour3\": [\n {\n \"projet\": 996,"
                + "\n \"minutes\": 80\n }\n ],\n \"jour4\": [\n {\n \"projet\": 996,\n \"minutes\": 80 }\n ],\n "
                + "\"jour5\": [\n  {\n \"projet\": 125,\n \"minutes\": 516 }\n ],\n \"weekend1\": [],\n \"weekend2\":"
                + " [\n {\n \"projet\": 990,\n \"minutes\": 30\n }\n ]\n}";
        validJSONObjectExploitation = JSONObject.fromObject(validJSONStringExploitation);
        validTimeSheetDataExploitation = JSONParser.toTimeSheetData(validJSONObjectExploitation);
        employeExploitation.initFromFirstTimeSheet(validTimeSheetDataExploitation);

        employeDirection = new Employe();
        validJSONStringDirection = "{\n \"numero_employe\": 5001,\n \"jour1\": [\n {\n \"projet\": 998,\n \"minutes\": 480\n },"
                + "\n {\n \"projet\": 911,\n \"minutes\": 36\n },\n {\n \"projet\": 910,\n \"minutes\": 8\n }\n ],\n "
                + "\"jour2\": [\n {\n \"projet\": 125,\n \"minutes\": 552\n }\n ],\n \"jour3\": [\n {\n \"projet\": 996,"
                + "\n \"minutes\": 80\n }\n ],\n \"jour4\": [\n {\n \"projet\": 996,\n \"minutes\": 80 }\n ],\n "
                + "\"jour5\": [\n  {\n \"projet\": 125,\n \"minutes\": 516 }\n ],\n \"weekend1\": [],\n \"weekend2\":"
                + " [\n {\n \"projet\": 990,\n \"minutes\": 30\n }\n ]\n}";
        validJSONObjectDirection = JSONObject.fromObject(validJSONStringDirection);
        validTimeSheetDataDirection = JSONParser.toTimeSheetData(validJSONObjectDirection);
        employeDirection.initFromFirstTimeSheet(validTimeSheetDataDirection);
    }

    @Test
    public void testIntitializeRulesForThisEmployeAdmin() {
        Report testReport = new Report(employeAdmin);
        Rules generatedRule = testReport.intitializeRulesForThisEmploye(employeAdmin);
        assertEquals(generatedRule.getClass().getName(), "timesheet.RulesAdmins");
    }

    @Test
    public void testIntitializeRulesForThisEmployeDevelopment() {
        Report testReport = new Report(employeDevelopment);
        Rules generatedRule = testReport.intitializeRulesForThisEmploye(employeDevelopment);
        assertEquals(generatedRule.getClass().getName(), "timesheet.RulesDevelopment");
    }

    @Test
    public void testIntitializeRulesForThisEmployeExploitation() {
        Report testReport = new Report(employeExploitation);
        Rules generatedRule = testReport.intitializeRulesForThisEmploye(employeExploitation);
        assertEquals(generatedRule.getClass().getName(), "timesheet.RulesExploitation");
    }

    @Test
    public void testIntitializeRulesForThisEmployeDirection() {
        Report testReport = new Report(employeDirection);
        Rules generatedRule = testReport.intitializeRulesForThisEmploye(employeDirection);
        assertEquals(generatedRule.getClass().getName(), "timesheet.RulesDirection");
    }

}
