package timesheet;

import java.util.ArrayList;
import java.util.List;
import net.sf.json.JSONObject;
import org.junit.Ignore; // À retirer plus tard
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class ReportTest {

    private static final int ADMIN_EMPLOYE_ID = 1;
    private static final int DEVELOPMENT_EMPLOYE_ID = 1000;
    private static final int EXPLOITATION_EMPLOYE_ID = 2000;
    private static final int DIRECTION_EMPLOYE_ID = 5001;
    private static final int PRESIDENT_ID = 6000;
    
    private String validJSONStringAdmin;
    private String validJSONStringDevelopment;
    private String validJSONStringExploitation;
    private String validJSONStringDirection;
    private String validJSONStringPresident;    
    private JSONObject validJSONObjectAdmin;
    private JSONObject validJSONObjectDevelopment;
    private JSONObject validJSONObjectExploitation;
    private JSONObject validJSONObjectDirection;
    private JSONObject validJSONObjectPresident;    
    private Employe employeAdmin;
    private Employe employeDevelopment;
    private Employe employeExploitation;
    private Employe employeDirection;
    private Employe employePresident;    
    private TimeSheetData validTimeSheetDataAdmin;
    private TimeSheetData validTimeSheetDataDevelopment;
    private TimeSheetData validTimeSheetDataExploitation;
    private TimeSheetData validTimeSheetDataDirection;
    private TimeSheetData validTimeSheetDataPresident;
    
    @Before
    public void initObjects() throws Exception {
        employeAdmin = new Employe();
        validJSONStringAdmin = "{\n \"numero_employe\": " + ADMIN_EMPLOYE_ID + ",\n \"jour1\": [\n {\n \"projet\": 998,"
                + "\n \"minutes\": 480\n },\n {\n \"projet\": 911,\n \"minutes\": 36\n },\n {\n \"projet\": 910,\n "
                + "\"minutes\": 8\n }\n ],\n \"jour2\": [\n {\n \"projet\": 125,\n \"minutes\": 552\n }\n ],\n "
                + "\"jour3\": [\n {\n \"projet\": 996,\n \"minutes\": 80\n }\n ],\n \"jour4\": [\n {\n \"projet\": 996,"
                + "\n \"minutes\": 80 }\n ],\n \"jour5\": [\n  {\n \"projet\": 125,\n \"minutes\": 516 }\n ],\n "
                + "\"weekend1\": [],\n \"weekend2\": [\n {\n \"projet\": 990,\n \"minutes\": 30\n }\n ]\n}";
        validJSONObjectAdmin = JSONObject.fromObject(validJSONStringAdmin);
        validTimeSheetDataAdmin = JSONParser.toTimeSheetData(validJSONObjectAdmin);
        employeAdmin.initFromFirstTimeSheet(validTimeSheetDataAdmin);

        employeDevelopment = new Employe();
        validJSONStringDevelopment = "{\n \"numero_employe\": " + DEVELOPMENT_EMPLOYE_ID + ",\n \"jour1\": [\n {\n "
                + "\"projet\": 998,\n \"minutes\": 480\n },\n {\n \"projet\": 911,\n \"minutes\": 36\n },\n {\n "
                + "\"projet\": 910,\n \"minutes\": 8\n }\n ],\n \"jour2\": [\n {\n \"projet\": 125,\n \"minutes\": "
                + "552\n }\n ],\n \"jour3\": [\n {\n \"projet\": 996,\n \"minutes\": 80\n }\n ],\n \"jour4\": [\n "
                + "{\n \"projet\": 996,\n \"minutes\": 80 }\n ],\n \"jour5\": [\n  {\n \"projet\": 125,\n \"minutes\": "
                + "516 }\n ],\n \"weekend1\": [],\n \"weekend2\": [\n {\n \"projet\": 990,\n \"minutes\": 30\n }\n ]\n}";
        validJSONObjectDevelopment = JSONObject.fromObject(validJSONStringDevelopment);
        validTimeSheetDataDevelopment = JSONParser.toTimeSheetData(validJSONObjectDevelopment);
        employeDevelopment.initFromFirstTimeSheet(validTimeSheetDataDevelopment);

        employeExploitation = new Employe();
        validJSONStringExploitation = "{\n \"numero_employe\": " + EXPLOITATION_EMPLOYE_ID + ",\n \"jour1\": [\n {\n "
                + "\"projet\": 998,\n \"minutes\": 480\n },\n {\n \"projet\": 911,\n \"minutes\": 36\n },\n {\n "
                + "\"projet\": 910,\n \"minutes\": 8\n }\n ],\n \"jour2\": [\n {\n \"projet\": 125,\n \"minutes\": "
                + "552\n }\n ],\n \"jour3\": [\n {\n \"projet\": 996,\n \"minutes\": 80\n }\n ],\n \"jour4\": "
                + "[\n {\n \"projet\": 996,\n \"minutes\": 80 }\n ],\n \"jour5\": [\n  {\n \"projet\": 125,\n "
                + "\"minutes\": 516 }\n ],\n \"weekend1\": [],\n \"weekend2\": [\n {\n \"projet\": 990,\n \"minutes\": "
                + "30\n }\n ]\n}";
        validJSONObjectExploitation = JSONObject.fromObject(validJSONStringExploitation);
        validTimeSheetDataExploitation = JSONParser.toTimeSheetData(validJSONObjectExploitation);
        employeExploitation.initFromFirstTimeSheet(validTimeSheetDataExploitation);

        employeDirection = new Employe();
        validJSONStringDirection = "{\n \"numero_employe\": " + DIRECTION_EMPLOYE_ID + ",\n \"jour1\": [\n {\n \"projet\": "
                + "998,\n \"minutes\": 480\n },\n {\n \"projet\": 911,\n \"minutes\": 36\n },\n {\n \"projet\": 910,\n "
                + "\"minutes\": 8\n }\n ],\n \"jour2\": [\n {\n \"projet\": 125,\n \"minutes\": 552\n }\n ],\n \"jour3\": "
                + "[\n {\n \"projet\": 996,\n \"minutes\": 80\n }\n ],\n \"jour4\": [\n {\n \"projet\": 996,\n \"minutes\": "
                + "80 }\n ],\n \"jour5\": [\n  {\n \"projet\": 125,\n \"minutes\": 516 }\n ],\n \"weekend1\": [],\n "
                + "\"weekend2\": [\n {\n \"projet\": 990,\n \"minutes\": 30\n }\n ]\n}";
        validJSONObjectDirection = JSONObject.fromObject(validJSONStringDirection);
        validTimeSheetDataDirection = JSONParser.toTimeSheetData(validJSONObjectDirection);
        employeDirection.initFromFirstTimeSheet(validTimeSheetDataDirection);
        
        employePresident = new Employe();
        validJSONStringPresident = "{\n \"numero_employe\": " + PRESIDENT_ID + ",\n \"jour1\": [\n {\n \"projet\": "
                + "998,\n \"minutes\": 480\n },\n {\n \"projet\": 911,\n \"minutes\": 36\n },\n {\n \"projet\": 910,\n "
                + "\"minutes\": 8\n }\n ],\n \"jour2\": [\n {\n \"projet\": 125,\n \"minutes\": 552\n }\n ],\n \"jour3\": "
                + "[\n {\n \"projet\": 996,\n \"minutes\": 80\n }\n ],\n \"jour4\": [\n {\n \"projet\": 996,\n \"minutes\": "
                + "80 }\n ],\n \"jour5\": [\n  {\n \"projet\": 125,\n \"minutes\": 516 }\n ],\n \"weekend1\": [],\n "
                + "\"weekend2\": [\n {\n \"projet\": 990,\n \"minutes\": 30\n }\n ]\n}";
        validJSONObjectPresident = JSONObject.fromObject(validJSONStringPresident);
        validTimeSheetDataPresident = JSONParser.toTimeSheetData(validJSONObjectPresident);
        employePresident.initFromFirstTimeSheet(validTimeSheetDataPresident);        
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
    
    @Test
    public void testIntitializeRulesForThisPresident() {
        Report testReport = new Report(employePresident);
        Rules generatedRule = testReport.intitializeRulesForThisEmploye(employePresident);
        assertEquals(generatedRule.getClass().getName(), "timesheet.RulesPresident");
    }    

    @Test
    public void testgenerateReportAdmin() {
        List<String> expectedReport = new ArrayList<String>();
        expectedReport.add("Cet employé n'a pas travaillé le nombre d'heures minimal physiquement au bureau. (jour3)");
        expectedReport.add("Cet employé n'a pas travaillé le nombre d'heures minimal physiquement au bureau. (jour4)");
        expectedReport.add("Cet employé a au moins une journée de congé parental invalide (jour3)");
        expectedReport.add("Cet employé a au moins une journée de congé parental invalide (jour4)");
        expectedReport.add("Cet employé n'a pas fait le minimum d'heures requis du lundi au vendredi physiquement au bureau.");
        expectedReport.add("Cet employé a plus d'une journée de congé parental par semaine.");

        Report testReport = new Report(employeAdmin);
        List<String> generatedReport = testReport.generateReport(employeAdmin);
        assertEquals(expectedReport.toString(), generatedReport.toString());
    }

    @Test
    public void testgenerateReportDevelopment() {
        List<String> expectedReport = new ArrayList<String>();
        expectedReport.add("Cet employé n'a pas travaillé le nombre d'heures minimal physiquement au bureau. (jour3)");
        expectedReport.add("Cet employé n'a pas travaillé le nombre d'heures minimal physiquement au bureau. (jour4)");
        expectedReport.add("Cet employé a au moins une journée de congé parental invalide (jour3)");
        expectedReport.add("Cet employé a au moins une journée de congé parental invalide (jour4)");
        expectedReport.add("Cet employé n'a pas fait le minimum d'heures requis du lundi au vendredi physiquement au bureau.");
        expectedReport.add("Cet employé a plus d'une journée de congé parental par semaine.");

        Report testReport = new Report(employeDevelopment);
        List<String> generatedReport = testReport.generateReport(employeDevelopment);
        assertEquals(expectedReport.toString(), generatedReport.toString());
    }

    @Test
    public void testgenerateReportExploitation() {
        List<String> expectedReport = new ArrayList<String>();
        expectedReport.add("Cet employé n'a pas travaillé le nombre d'heures minimal physiquement au bureau. (jour3)");
        expectedReport.add("Cet employé n'a pas travaillé le nombre d'heures minimal physiquement au bureau. (jour4)");
        expectedReport.add("Cet employé a au moins une journée de congé parental invalide (jour3)");
        expectedReport.add("Cet employé a au moins une journée de congé parental invalide (jour4)");
        expectedReport.add("Cet employé n'a pas fait le minimum d'heures requis du lundi au vendredi physiquement au bureau.");
        expectedReport.add("Cet employé a plus d'une journée de congé parental par semaine.");

        Report testReport = new Report(employeExploitation);
        List<String> generatedReport = testReport.generateReport(employeExploitation);
        assertEquals(expectedReport.toString(), generatedReport.toString());
    }

    @Test
    public void testgenerateReportDirection() {
        List<String> expectedReport = new ArrayList<String>();
        expectedReport.add("Cet employé n'a pas travaillé le nombre d'heures minimal physiquement au bureau. (jour3)");
        expectedReport.add("Cet employé n'a pas travaillé le nombre d'heures minimal physiquement au bureau. (jour4)");
        expectedReport.add("Cet employé a au moins une journée de congé parental invalide (jour3)");
        expectedReport.add("Cet employé a au moins une journée de congé parental invalide (jour4)");
        expectedReport.add("Cet employé n'a pas fait le minimum d'heures requis du lundi au vendredi physiquement au bureau.");
        expectedReport.add("Cet employé a plus d'une journée de congé parental par semaine.");

        Report testReport = new Report(employeDirection);
        List<String> generatedReport = testReport.generateReport(employeDirection);
        assertEquals(expectedReport.toString(), generatedReport.toString());
    }
    
    // Je dois attendre l'implémentation de temps de transport pour compléter ce test
    @Ignore ("Je dois attendre l'implémentation de temps de transport pour compléter ce test")
    @Test
    public void testgenerateReportPresident() {
        List<String> expectedReport = new ArrayList<String>();
        expectedReport.add("Cet employé n'a pas travaillé le nombre d'heures minimal physiquement au bureau. (jour3)");
        expectedReport.add("Cet employé n'a pas travaillé le nombre d'heures minimal physiquement au bureau. (jour4)");
        expectedReport.add("Cet employé a au moins une journée de congé parental invalide (jour3)");
        expectedReport.add("Cet employé a au moins une journée de congé parental invalide (jour4)");
        expectedReport.add("Cet employé n'a pas fait le minimum d'heures requis du lundi au vendredi physiquement au bureau.");
        expectedReport.add("Cet employé a plus d'une journée de congé parental par semaine.");

        Report testReport = new Report(employePresident);
        List<String> generatedReport = testReport.generateReport(employePresident);
        assertEquals(expectedReport.toString(), generatedReport.toString());
    }    

    @Test
    public void testgenerateReportDirectionInvalidWorkAfter24hrsNoHoliday() throws Exception {
        Employe employeDirection = new Employe();
        validJSONStringDirection = "{\n \"numero_employe\": " + DIRECTION_EMPLOYE_ID + ",\n \"jour1\": [\n {\n \"projet\": "
                + "901,\n \"minutes\": 1440\n },\n {\n \"projet\": 911,\n \"minutes\": 36\n },\n {\n \"projet\": 910,\n "
                + "\"minutes\": 8\n }\n ],\n \"jour2\": [\n {\n \"projet\": 125,\n \"minutes\": 552\n }\n ],\n \"jour3\": "
                + "[\n {\n \"projet\": 96,\n \"minutes\": 480\n }\n ],\n \"jour4\": [\n {\n \"projet\": 99,\n \"minutes\": "
                + "480 }\n ],\n \"jour5\": [\n  {\n \"projet\": 125,\n \"minutes\": 516 }\n ],\n \"weekend1\": [],\n "
                + "\"weekend2\": [\n {\n \"projet\": 990,\n \"minutes\": 30\n }\n ]\n}";
        validJSONObjectDirection = JSONObject.fromObject(validJSONStringDirection);
        validTimeSheetDataDirection = JSONParser.toTimeSheetData(validJSONObjectDirection);
        employeDirection.initFromFirstTimeSheet(validTimeSheetDataDirection);

        List<String> expectedReport = new ArrayList<String>();
        expectedReport.add("Cet employé a une journée avec plus de 24 heures qui ne comporte pas de temps de journée de vacances ou de congé férié (jour1)");
        expectedReport.add("Cet employé n'a pas travaillé le nombre d'heures minimal physiquement au bureau. (jour1)");
        expectedReport.add("Cet employé n'a pas fait le minimum d'heures requis du lundi au vendredi physiquement au bureau.");

        Report testReport = new Report(employeDirection);
        List<String> generatedReport = testReport.generateReport(employeDirection);
        assertEquals(expectedReport.toString(), generatedReport.toString());
    }

    @Test
    public void testgenerateReportDirectionInvalid0MinuteTask() throws Exception {
        Employe employeDirection = new Employe();
        validJSONStringDirection = "{\n \"numero_employe\": " + DIRECTION_EMPLOYE_ID + ",\n \"jour1\": [\n {\n \"projet\": "
                + "901,\n \"minutes\": 1440\n },\n {\n \"projet\": 911,\n \"minutes\": 36\n },\n {\n \"projet\": 910,\n "
                + "\"minutes\": 0\n }\n ],\n \"jour2\": [\n {\n \"projet\": 125,\n \"minutes\": 552\n }\n ],\n \"jour3\": "
                + "[\n {\n \"projet\": 96,\n \"minutes\": 480\n }\n ],\n \"jour4\": [\n {\n \"projet\": 99,\n \"minutes\": "
                + "480 }\n ],\n \"jour5\": [\n  {\n \"projet\": 125,\n \"minutes\": 516 }\n ],\n \"weekend1\": [],\n "
                + "\"weekend2\": [\n {\n \"projet\": 990,\n \"minutes\": 30\n }\n ]\n}";
        validJSONObjectDirection = JSONObject.fromObject(validJSONStringDirection);
        validTimeSheetDataDirection = JSONParser.toTimeSheetData(validJSONObjectDirection);
        employeDirection.initFromFirstTimeSheet(validTimeSheetDataDirection);

        List<String> expectedReport = new ArrayList<String>();
        expectedReport.add("Cet employé a une journée qui ne respecte pas le nombre minimum de minutes (0) pour une tache. (jour1)");
        expectedReport.add("Cet employé a une journée avec plus de 24 heures qui ne comporte pas de temps de journée de vacances ou de congé férié (jour1)");
        expectedReport.add("Cet employé n'a pas travaillé le nombre d'heures minimal physiquement au bureau. (jour1)");
        expectedReport.add("Cet employé n'a pas fait le minimum d'heures requis du lundi au vendredi physiquement au bureau.");;

        Report testReport = new Report(employeDirection);
        List<String> generatedReport = testReport.generateReport(employeDirection);
        assertEquals(expectedReport.toString(), generatedReport.toString());
    }

    @Test
    public void testgenerateReportDirectionInvalidDuplicateTasksForADay() throws Exception {
        Employe employeDirection = new Employe();
        validJSONStringDirection = "{\n \"numero_employe\": " + DIRECTION_EMPLOYE_ID + ",\n \"jour1\": [\n {\n \"projet\": "
                + "901,\n \"minutes\": 1440\n },\n {\n \"projet\": 910,\n \"minutes\": 36\n },\n {\n \"projet\": 910,\n "
                + "\"minutes\": 10\n }\n ],\n \"jour2\": [\n {\n \"projet\": 125,\n \"minutes\": 552\n }\n ],\n \"jour3\": "
                + "[\n {\n \"projet\": 96,\n \"minutes\": 480\n }\n ],\n \"jour4\": [\n {\n \"projet\": 99,\n \"minutes\": "
                + "480 }\n ],\n \"jour5\": [\n  {\n \"projet\": 125,\n \"minutes\": 516 }\n ],\n \"weekend1\": [],\n "
                + "\"weekend2\": [\n {\n \"projet\": 990,\n \"minutes\": 30\n }\n ]\n}";
        validJSONObjectDirection = JSONObject.fromObject(validJSONStringDirection);
        validTimeSheetDataDirection = JSONParser.toTimeSheetData(validJSONObjectDirection);
        employeDirection.initFromFirstTimeSheet(validTimeSheetDataDirection);

        List<String> expectedReport = new ArrayList<String>();
        expectedReport.add("Cet employé a une journée avec plus de 24 heures qui ne comporte pas de temps de journée de vacances ou de congé férié (jour1)");
        expectedReport.add("Cet employé a plusieurs activités avec le même code de projet pour une même journée (jour1)");
        expectedReport.add("Cet employé n'a pas travaillé le nombre d'heures minimal physiquement au bureau. (jour1)");
        expectedReport.add("Cet employé n'a pas fait le minimum d'heures requis du lundi au vendredi physiquement au bureau.");

        Report testReport = new Report(employeDirection);
        List<String> generatedReport = testReport.generateReport(employeDirection);
        assertEquals(expectedReport.toString(), generatedReport.toString());
    }

    @Test
    public void testReportInvalidDaysWithSickLeaveOtherTasksWhileSickRemoteWork() throws Exception {
        Employe employeDirection = new Employe();
        validJSONStringDirection = "{\n \"numero_employe\": " + DIRECTION_EMPLOYE_ID + ",\n \"jour1\": [\n {\n \"projet\": "
                + "901,\n \"minutes\": 440\n },\n {\n \"projet\": 999,\n \"minutes\": 480\n },\n {\n \"projet\": 910,\n "
                + "\"minutes\": 10\n }\n ],\n \"jour2\": [\n {\n \"projet\": 125,\n \"minutes\": 552\n }\n ],\n \"jour3\": "
                + "[\n {\n \"projet\": 96,\n \"minutes\": 480\n }\n ],\n \"jour4\": [\n {\n \"projet\": 99,\n \"minutes\": "
                + "480 }\n ],\n \"jour5\": [\n  {\n \"projet\": 125,\n \"minutes\": 516 }\n ],\n \"weekend1\": [],\n "
                + "\"weekend2\": [\n {\n \"projet\": 990,\n \"minutes\": 30\n }\n ]\n}";
        validJSONObjectDirection = JSONObject.fromObject(validJSONStringDirection);
        validTimeSheetDataDirection = JSONParser.toTimeSheetData(validJSONObjectDirection);
        employeDirection.initFromFirstTimeSheet(validTimeSheetDataDirection);

        List<String> expectedReport = new ArrayList<String>();
        expectedReport.add("Cet employé a une journée invalide de congé de maladie. (télé-travail - jour1)");
        expectedReport.add("Cet employé n'a pas fait le minimum d'heures requis du lundi au vendredi physiquement au bureau.");

        Report testReport = new Report(employeDirection);
        List<String> generatedReport = testReport.generateReport(employeDirection);
        assertEquals(expectedReport.toString(), generatedReport.toString());
    }

    @Test
    public void testReportInvalidDaysWithSickLeaveOtherTasksWhileSickOfficeWork() throws Exception {
        Employe employeDirection = new Employe();
        validJSONStringDirection = "{\n \"numero_employe\": " + DIRECTION_EMPLOYE_ID + ",\n \"jour1\": [\n {\n \"projet\": "
                + "10,\n \"minutes\": 2\n },\n {\n \"projet\": 999,\n \"minutes\": 480\n },\n {\n \"projet\": 91,\n "
                + "\"minutes\": 10\n }\n ],\n \"jour2\": [\n {\n \"projet\": 125,\n \"minutes\": 552\n }\n ],\n \"jour3\": "
                + "[\n {\n \"projet\": 96,\n \"minutes\": 480\n }\n ],\n \"jour4\": [\n {\n \"projet\": 99,\n \"minutes\": "
                + "480 }\n ],\n \"jour5\": [\n  {\n \"projet\": 125,\n \"minutes\": 516 }\n ],\n \"weekend1\": [],\n "
                + "\"weekend2\": [\n {\n \"projet\": 990,\n \"minutes\": 30\n }\n ]\n}";
        validJSONObjectDirection = JSONObject.fromObject(validJSONStringDirection);
        validTimeSheetDataDirection = JSONParser.toTimeSheetData(validJSONObjectDirection);
        employeDirection.initFromFirstTimeSheet(validTimeSheetDataDirection);

        List<String> expectedReport = new ArrayList<String>();
        expectedReport.add("Cet employé a une journée invalide de congé de maladie. (travail au bureau - jour1)");
        expectedReport.add("Cet employé n'a pas fait le minimum d'heures requis du lundi au vendredi physiquement au bureau.");

        Report testReport = new Report(employeDirection);
        List<String> generatedReport = testReport.generateReport(employeDirection);
        assertEquals(expectedReport.toString(), generatedReport.toString());
    }

    @Test
    public void testReportInvalidDaysWithPublicHolidayOfficeTaskOnPublicHoliday() throws Exception {
        Employe employeDirection = new Employe();
        validJSONStringDirection = "{\n \"numero_employe\": " + DIRECTION_EMPLOYE_ID + ",\n \"jour1\": [\n {\n \"projet\": "
                + "99,\n \"minutes\": 480\n },\n {\n \"projet\": 10,\n \"minutes\": 4\n },\n {\n \"projet\": 910,\n "
                + "\"minutes\": 10\n }\n ],\n \"jour2\": [\n {\n \"projet\": 125,\n \"minutes\": 552\n }\n ],\n \"jour3\": "
                + "[\n {\n \"projet\": 96,\n \"minutes\": 480\n }\n ],\n \"jour4\": [\n {\n \"projet\": 99,\n \"minutes\": "
                + "480 }\n ],\n \"jour5\": [\n  {\n \"projet\": 125,\n \"minutes\": 516 }\n ],\n \"weekend1\": [],\n "
                + "\"weekend2\": [\n {\n \"projet\": 998,\n \"minutes\": 480\n }\n ]\n}";
        validJSONObjectDirection = JSONObject.fromObject(validJSONStringDirection);
        validTimeSheetDataDirection = JSONParser.toTimeSheetData(validJSONObjectDirection);
        employeDirection.initFromFirstTimeSheet(validTimeSheetDataDirection);

        List<String> expectedReport = new ArrayList<String>();
        expectedReport.add("Cet employé a une journée invalide de congé férié. (weekend2)");
        expectedReport.add("Cet employé a passé plus d'heures physiquement au bureau que la quantité permise.");

        Report testReport = new Report(employeDirection);
        List<String> generatedReport = testReport.generateReport(employeDirection);
        assertEquals(expectedReport.toString(), generatedReport.toString());
    }

    @Test
    public void testReportInvalidDaysWithToMuchTime() throws Exception {
        Employe employeDirection = new Employe();
        validJSONStringDirection = "{\n \"numero_employe\": " + DEVELOPMENT_EMPLOYE_ID + ",\n \"jour1\": [\n {\n \"projet\": "
                + "994,\n \"minutes\": 1920\n },\n {\n \"projet\": 10,\n \"minutes\": 480\n },\n {\n \"projet\": 910,\n "
                + "\"minutes\": 10\n }\n ],\n \"jour2\": [\n {\n \"projet\": 125,\n \"minutes\": 552\n }\n ],\n \"jour3\": "
                + "[\n {\n \"projet\": 96,\n \"minutes\": 480\n }\n ],\n \"jour4\": [\n {\n \"projet\": 99,\n \"minutes\": "
                + "480 }\n ],\n \"jour5\": [\n  {\n \"projet\": 125,\n \"minutes\": 516 }\n ],\n \"weekend1\": [],\n "
                + "\"weekend2\": [\n {\n \"projet\": 910,\n \"minutes\": 480\n }\n ]\n}";
        validJSONObjectDirection = JSONObject.fromObject(validJSONStringDirection);
        validTimeSheetDataDirection = JSONParser.toTimeSheetData(validJSONObjectDirection);
        employeDirection.initFromFirstTimeSheet(validTimeSheetDataDirection);

        List<String> expectedReport = new ArrayList<String>();
        expectedReport.add("Cet employé a une journée avec plus de 32 heures travaillé. (jour1)");

        Report testReport = new Report(employeDirection);
        List<String> generatedReport = testReport.generateReport(employeDirection);
        assertEquals(expectedReport.toString(), generatedReport.toString());
    }

    @Test
    public void testReportInvalidDaysOfHolidayCausedByWrongTime() throws Exception {
        Employe employeDirection = new Employe();
        validJSONStringDirection = "{\n \"numero_employe\": " + DEVELOPMENT_EMPLOYE_ID + ",\n \"jour1\": [\n {\n \"projet\": "
                + "997,\n \"minutes\": 500\n },\n {\n \"projet\": 10,\n \"minutes\": 4\n },\n {\n \"projet\": 910,\n "
                + "\"minutes\": 10\n }\n ],\n \"jour2\": [\n {\n \"projet\": 125,\n \"minutes\": 552\n }\n ],\n \"jour3\": "
                + "[\n {\n \"projet\": 96,\n \"minutes\": 480\n }\n ],\n \"jour4\": [\n {\n \"projet\": 99,\n \"minutes\": "
                + "480 }\n ],\n \"jour5\": [\n  {\n \"projet\": 125,\n \"minutes\": 516 }\n ],\n \"weekend1\": [],\n "
                + "\"weekend2\": [\n {\n \"projet\": 910,\n \"minutes\": 480\n }\n ]\n}";
        validJSONObjectDirection = JSONObject.fromObject(validJSONStringDirection);
        validTimeSheetDataDirection = JSONParser.toTimeSheetData(validJSONObjectDirection);
        employeDirection.initFromFirstTimeSheet(validTimeSheetDataDirection);

        List<String> expectedReport = new ArrayList<String>();
        expectedReport.add("Cet employé a une journée invalide de congé de vacances. (jour1)");

        Report testReport = new Report(employeDirection);
        List<String> generatedReport = testReport.generateReport(employeDirection);
        assertEquals(expectedReport.toString(), generatedReport.toString());
    }

    @Test
    public void testReportHasNotValidWeeklyTimeRemoteTooMuchTime() throws Exception {
        Employe employeDirection = new Employe();
        validJSONStringDirection = "{\n \"numero_employe\": " + ADMIN_EMPLOYE_ID + ",\n \"jour1\": [\n {\n \"projet\": "
                + "907,\n \"minutes\": 601\n },\n {\n \"projet\": 10,\n \"minutes\": 240\n },\n {\n \"projet\": 910,\n "
                + "\"minutes\": 10\n }\n ],\n \"jour2\": [\n {\n \"projet\": 125,\n \"minutes\": 552\n }\n ],\n \"jour3\": "
                + "[\n {\n \"projet\": 96,\n \"minutes\": 480\n }\n ],\n \"jour4\": [\n {\n \"projet\": 99,\n \"minutes\": "
                + "480 }\n ],\n \"jour5\": [\n  {\n \"projet\": 125,\n \"minutes\": 516 }\n ],\n \"weekend1\": [],\n "
                + "\"weekend2\": [\n {\n \"projet\": 910,\n \"minutes\": 480\n }\n ]\n}";
        validJSONObjectDirection = JSONObject.fromObject(validJSONStringDirection);
        validTimeSheetDataDirection = JSONParser.toTimeSheetData(validJSONObjectDirection);
        employeDirection.initFromFirstTimeSheet(validTimeSheetDataDirection);

        List<String> expectedReport = new ArrayList<String>();
        expectedReport.add("Cet employé a fait plus d'heures de télétravail que la quantité permise.");

        Report testReport = new Report(employeDirection);
        List<String> generatedReport = testReport.generateReport(employeDirection);
        assertEquals(expectedReport.toString(), generatedReport.toString());
    }
}
