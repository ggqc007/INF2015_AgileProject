package timesheet;

import java.util.ArrayList;
import java.util.List;
import net.sf.json.JSONObject;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class ReportTest {

    private static final int ADMIN_EMPLOYE_ID = 1;
    private static final int DEVELOPMENT_EMPLOYE_ID = 1000;
    private static final int EXPLOITATION_EMPLOYE_ID = 2000;
    private static final int DIRECTION_EMPLOYE_ID = 5001;
    private static final int PRESIDENT_ID = 6000;

    private static final int TRANSPORTATION_ID = 777;
    private static final int SICK_LEAVE_TASK_ID = 999;
    private static final int PUBLIC_HOLIDAY_TASK_ID = 998;
    private static final int HOLIDAY_TASK_ID = 997;
    private static final int PARENTAL_HOLIDAY_TASK_ID = 996;

    private static final String ERROR_NOT_ENOUGH_PHYSICAL_TIME_FOR_DAY = "Cet employé n'a pas travaillé le nombre "
            + "d'heures minimal physiquement au bureau.";
    private static final String ERROR_NOT_ENOUGH_PHYSICAL_TIME_FOR_WEEK = "Cet employé n'a pas fait le minimum "
            + "d'heures requis du lundi au vendredi physiquement au bureau.";
    private static final String ERROR_INVALID_PARENTAL_HOLIDAY = "Cet employé a au moins une journée de congé parental "
            + "invalide";
    private static final String ERROR_TOO_MANY_PARENTAL_HOLIDAY = "Cet employé a plus d'une journée de congé parental "
            + "par semaine.";
    private static final String ERROR_INVALID_TASK_AFTER_24_HOURS = "Cet employé a une journée avec plus de 24 heures "
            + "qui ne comporte pas de temps de journée de vacances ou de congé férié";
    private static final String ERROR_INVALID_MAXIMUM_MINUTES_FOR_DAY = "Cet employé a une journée avec plus de 32 "
            + "heures travaillé.";
    private static final String ERROR_INVALID_MINIMUM_MINUTES_FOR_TASK = "Cet employé a une journée qui ne respecte "
            + "pas le nombre minimum de minutes (0) pour une tache.";
    private static final String ERROR_WORK_SAME_PROJECT_FOR_DAY = "Cet employé a plusieurs activités avec le même "
            + "code de projet pour une même journée";
    private static final String ERROR_INVALID_SICK_HOLIDAY = "Cet employé a une journée invalide de congé de maladie.";
    private static final String ERROR_INVALID_PUBLIC_HOLIDAY = "Cet employé a une journée invalide de congé férié.";
    private static final String ERROR_INVALID_HOLIDAY = "Cet employé a une journée invalide de congé de vacances.";
    private static final String ERROR_TOO_MANY_MINUTES_OF_REMOTE_WORK = "Cet employé a fait plus d'heures de "
            + "télétravail que la quantité permise.";
    private static final String ERROR_TOO_MANY_MINUTES_OF_OFFICE_WORK = "Cet employé a passé plus d'heures physiquement "
            + "au bureau que la quantité permise.";
    private static final String ERROR_INVALID_TRANSPORT_TIME = "Cet employé a déclaré du temps de transport non permis.";
    private static final String ERROR_TOO_MUCH_TRANSPORT_TIME = "Cet employé a déclaré plus de temps de transport par "
            + "semaine que la limite permise.";

    private static final String VALID_JSON_40HRS_WEEK = "{\n \"numero_employe\": 4,\n \"jour1\": [\n {\n \"projet\": 90,"
            + "\n \"minutes\": 480\n },\n {\n \"projet\": 904,\n \"minutes\": 480\n },\n {\n \"projet\": 905,\n "
            + "\"minutes\": 10\n }\n ],\n \"jour2\": [\n {\n \"projet\": 125,\n \"minutes\": 480\n }\n ],\n "
            + "\"jour3\": [\n {\n \"projet\": 96,\n \"minutes\": 480\n }\n ],\n \"jour4\": [\n {\n \"projet\": 99,"
            + "\n \"minutes\": 480 }\n ],\n \"jour5\": [\n  {\n \"projet\": 125,\n \"minutes\": 480 }\n ],\n "
            + "\"weekend1\": [],\n \"weekend2\": [\n {\n \"projet\": 990,\n \"minutes\": 30\n }\n ]\n}";

    private static final String VALID_JSON_43HRS_WEEK = "{\n \"numero_employe\": 5001,\n \"jour1\": [\n {\n \"projet\": "
            + "90,\n \"minutes\": 480\n },\n {\n \"projet\": 904,\n \"minutes\": 480\n },\n {\n \"projet\": 905,\n "
            + "\"minutes\": 10\n }\n ],\n \"jour2\": [\n {\n \"projet\": 125,\n \"minutes\": 660\n }\n ],\n "
            + "\"jour3\": [\n {\n \"projet\": 96,\n \"minutes\": 480\n }\n ],\n \"jour4\": [\n {\n \"projet\": 99,"
            + "\n \"minutes\": 480 }\n ],\n \"jour5\": [\n  {\n \"projet\": 125,\n \"minutes\": 480 }\n ],\n "
            + "\"weekend1\": [],\n \"weekend2\": [\n {\n \"projet\": 990,\n \"minutes\": 30\n }\n ]\n}";

    private static final String RULES_ADMINS_CLASS_PATH_AND_NAME = "timesheet.RulesAdmins";
    private static final String RULES_DEVELOPMENT_CLASS_PATH_AND_NAME = "timesheet.RulesDevelopment";
    private static final String RULES_EXPLOITATION_CLASS_PATH_AND_NAME = "timesheet.RulesExploitation";
    private static final String RULES_DIRECTION_CLASS_PATH_AND_NAME = "timesheet.RulesDirection";
    private static final String RULES_PRESIDENT_CLASS_PATH_AND_NAME = "timesheet.RulesPresident";

    private static final String JOUR1_KEY = "jour1";
    private static final String JOUR2_KEY = "jour2";
    private static final String JOUR3_KEY = "jour3";
    private static final String WEEKEND2_KEY = "weekend2";

    private JSONObject validJSONObjectAdmin;
    private JSONObject validJSONObjectDevelopment;
    private JSONObject validJSONObjectExploitation;
    private JSONObject validJSONObjectDirection;
    private JSONObject validJSONObjectPresident;
    private Employe validEmployeAdmin;
    private Employe validEmployeDevelopment;
    private Employe validEmployeExploitation;
    private Employe validEmployeDirection;
    private Employe validEmployePresident;
    private TimeSheetData validTimeSheetDataAdmin;
    private TimeSheetData validTimeSheetDataDevelopment;
    private TimeSheetData validTimeSheetDataExploitation;
    private TimeSheetData validTimeSheetDataDirection;
    private TimeSheetData validTimeSheetDataPresident;

    @Before
    public void initObjects() throws Exception {
        generateValidEmployeAdministration();
        generateValidEmployeExploitation();
        generateValidEmployeDirection();
        generateValidEmployePresident();
        generateValidEmployeDevelopment();
    }

    private void generateValidEmployeAdministration() throws Exception {
        validEmployeAdmin = new Employe();
        validJSONObjectAdmin = JSONObject.fromObject(VALID_JSON_40HRS_WEEK);
        validTimeSheetDataAdmin = JSONParser.toTimeSheetData(validJSONObjectAdmin);
        validEmployeAdmin.initFromFirstTimeSheet(validTimeSheetDataAdmin);
        validEmployeAdmin.setEmployeId(ADMIN_EMPLOYE_ID);
    }

    private void generateValidEmployeDevelopment() throws Exception {
        validEmployeDevelopment = new Employe();
        validJSONObjectDevelopment = JSONObject.fromObject(VALID_JSON_40HRS_WEEK);
        validTimeSheetDataDevelopment = JSONParser.toTimeSheetData(validJSONObjectDevelopment);
        validEmployeDevelopment.initFromFirstTimeSheet(validTimeSheetDataDevelopment);
        validEmployeDevelopment.setEmployeId(DEVELOPMENT_EMPLOYE_ID);
    }

    private void generateValidEmployeExploitation() throws Exception {
        validEmployeExploitation = new Employe();
        validJSONObjectExploitation = JSONObject.fromObject(VALID_JSON_40HRS_WEEK);
        validTimeSheetDataExploitation = JSONParser.toTimeSheetData(validJSONObjectExploitation);
        validEmployeExploitation.initFromFirstTimeSheet(validTimeSheetDataExploitation);
        validEmployeExploitation.setEmployeId(EXPLOITATION_EMPLOYE_ID);
    }

    private void generateValidEmployeDirection() throws Exception {
        validEmployeDirection = new Employe();
        validJSONObjectDirection = JSONObject.fromObject(VALID_JSON_43HRS_WEEK);
        validTimeSheetDataDirection = JSONParser.toTimeSheetData(validJSONObjectDirection);
        validEmployeDirection.initFromFirstTimeSheet(validTimeSheetDataDirection);
        validEmployeDirection.setEmployeId(DIRECTION_EMPLOYE_ID);
    }

    private void generateValidEmployePresident() throws Exception {
        validEmployePresident = new Employe();
        validJSONObjectPresident = JSONObject.fromObject(VALID_JSON_43HRS_WEEK);
        validTimeSheetDataPresident = JSONParser.toTimeSheetData(validJSONObjectPresident);
        validEmployePresident.initFromFirstTimeSheet(validTimeSheetDataPresident);
        validEmployePresident.setEmployeId(PRESIDENT_ID);
    }

    @Test
    public void testIntitializeRulesForThisEmployeAdmin() {
        Report testReport = new Report(validEmployeAdmin);
        Rules generatedRule = testReport.intitializeRulesForThisEmploye(validEmployeAdmin);
        assertEquals(generatedRule.getClass().getName(), RULES_ADMINS_CLASS_PATH_AND_NAME);
    }

    @Test
    public void testIntitializeRulesForThisEmployeDevelopment() {
        Report testReport = new Report(validEmployeDevelopment);
        Rules generatedRule = testReport.intitializeRulesForThisEmploye(validEmployeDevelopment);
        assertEquals(generatedRule.getClass().getName(), RULES_DEVELOPMENT_CLASS_PATH_AND_NAME);
    }

    @Test
    public void testIntitializeRulesForThisEmployeExploitation() {
        Report testReport = new Report(validEmployeExploitation);
        Rules generatedRule = testReport.intitializeRulesForThisEmploye(validEmployeExploitation);
        assertEquals(generatedRule.getClass().getName(), RULES_EXPLOITATION_CLASS_PATH_AND_NAME);
    }

    @Test
    public void testIntitializeRulesForThisEmployeDirection() {
        Report testReport = new Report(validEmployeDirection);
        Rules generatedRule = testReport.intitializeRulesForThisEmploye(validEmployeDirection);
        assertEquals(generatedRule.getClass().getName(), RULES_DIRECTION_CLASS_PATH_AND_NAME);
    }

    @Test
    public void testIntitializeRulesForThisPresident() {
        Report testReport = new Report(validEmployePresident);
        Rules generatedRule = testReport.intitializeRulesForThisEmploye(validEmployePresident);
        assertEquals(generatedRule.getClass().getName(), RULES_PRESIDENT_CLASS_PATH_AND_NAME);
    }

    @Test
    public void testgenerateReportAdmin() {
        List<String> expectedReport = new ArrayList<>();
        Report testReport = new Report(validEmployeAdmin);
        List<String> generatedReport = testReport.generateReport(validEmployeAdmin);
        assertEquals(expectedReport.toString(), generatedReport.toString());
    }

    @Test
    public void testgenerateReportDevelopment() {
        List<String> expectedReport = new ArrayList<>();
        Report testReport = new Report(validEmployeDevelopment);
        List<String> generatedReport = testReport.generateReport(validEmployeDevelopment);
        assertEquals(expectedReport.toString(), generatedReport.toString());
    }

    @Test
    public void testgenerateReportExploitation() {
        List<String> expectedReport = new ArrayList<>();
        Report testReport = new Report(validEmployeExploitation);
        List<String> generatedReport = testReport.generateReport(validEmployeExploitation);
        assertEquals(expectedReport.toString(), generatedReport.toString());
    }

    @Test
    public void testgenerateReportDirection() {
        List<String> expectedReport = new ArrayList<>();
        Report testReport = new Report(validEmployeDirection);
        List<String> generatedReport = testReport.generateReport(validEmployeDirection);
        assertEquals(expectedReport.toString(), generatedReport.toString());
    }

    @Test
    public void testgenerateReportPresident() {
        List<String> expectedReport = new ArrayList<>();
        Report testReport = new Report(validEmployePresident);
        List<String> generatedReport = testReport.generateReport(validEmployePresident);
        assertEquals(expectedReport.toString(), generatedReport.toString());
    }

    @Test
    public void testgenerateReportDirectionInvalidWorkAfter24hrsNoHoliday() throws Exception {
        validEmployeDirection.getTimeSheet(0).getDayByName(JOUR1_KEY).addTask(80, 480);
        List<String> expectedReport = new ArrayList<>();
        expectedReport.add(ERROR_INVALID_TASK_AFTER_24_HOURS + " (" + JOUR1_KEY + ")");
        Report testReport = new Report(validEmployeDirection);
        List<String> generatedReport = testReport.generateReport(validEmployeDirection);
        assertEquals(expectedReport.toString(), generatedReport.toString());
    }

    @Test
    public void testgenerateReportDirectionInvalid0MinuteTask() throws Exception {
        validEmployeDirection.getTimeSheet(0).getDayByName(JOUR1_KEY).addTask(80, 0);
        List<String> expectedReport = new ArrayList<>();
        expectedReport.add(ERROR_INVALID_MINIMUM_MINUTES_FOR_TASK + " (" + JOUR1_KEY + ")");
        Report testReport = new Report(validEmployeDirection);
        List<String> generatedReport = testReport.generateReport(validEmployeDirection);
        assertEquals(expectedReport.toString(), generatedReport.toString());
    }

    @Test
    public void testgenerateReportDirectionInvalidDuplicateTasksForADay() throws Exception {
        validEmployeDirection.getTimeSheet(0).getDayByName(JOUR1_KEY).addTask(80, 10);
        validEmployeDirection.getTimeSheet(0).getDayByName(JOUR1_KEY).addTask(80, 10);
        List<String> expectedReport = new ArrayList<>();
        expectedReport.add(ERROR_WORK_SAME_PROJECT_FOR_DAY + " (" + JOUR1_KEY + ")");
        Report testReport = new Report(validEmployeDirection);
        List<String> generatedReport = testReport.generateReport(validEmployeDirection);
        assertEquals(expectedReport.toString(), generatedReport.toString());
    }

    @Test
    public void testReportInvalidDaysWithSickLeaveOtherTasksWhileSickRemoteWork() throws Exception {
        List<Task> tasksForDay = validEmployeDirection.getTimeSheet(0).getDayByName(JOUR1_KEY).getTasks();
        tasksForDay.get(0).setProjectId(SICK_LEAVE_TASK_ID);
        tasksForDay.get(0).setTime(480);
        List<String> expectedReport = new ArrayList<>();
        expectedReport.add(ERROR_INVALID_SICK_HOLIDAY + " (télé-travail - " + JOUR1_KEY + ")");
        Report testReport = new Report(validEmployeDirection);
        List<String> generatedReport = testReport.generateReport(validEmployeDirection);
        assertEquals(expectedReport.toString(), generatedReport.toString());
    }

    @Test
    public void testReportInvalidDaysWithSickLeaveOtherTasksWhileSickOfficeWork() throws Exception {
        List<Task> tasksForDay = validEmployeDirection.getTimeSheet(0).getDayByName(JOUR1_KEY).getTasks();
        tasksForDay.get(0).setProjectId(SICK_LEAVE_TASK_ID);
        tasksForDay.get(0).setTime(480);
        tasksForDay.get(1).setProjectId(80);
        tasksForDay.get(2).setProjectId(81);
        List<String> expectedReport = new ArrayList<>();
        expectedReport.add(ERROR_INVALID_SICK_HOLIDAY + " (travail au bureau - " + JOUR1_KEY + ")");
        Report testReport = new Report(validEmployeDirection);
        List<String> generatedReport = testReport.generateReport(validEmployeDirection);
        assertEquals(expectedReport.toString(), generatedReport.toString());
    }
    
    @Test
    public void testReportInvalidDaysWithPublicHolidayFailWhenUsedOnWeekEnd() throws Exception {
        List<Task> tasksForDay = validEmployeDirection.getTimeSheet(0).getDayByName(WEEKEND2_KEY).getTasks();
        tasksForDay.get(0).setProjectId(PUBLIC_HOLIDAY_TASK_ID);
        tasksForDay.get(0).setTime(480);
        List<String> expectedReport = new ArrayList<>();
        expectedReport.add(ERROR_INVALID_PUBLIC_HOLIDAY + " (" + WEEKEND2_KEY + ")");
        Report testReport = new Report(validEmployeDirection);
        List<String> generatedReport = testReport.generateReport(validEmployeDirection);
        assertEquals(expectedReport.toString(), generatedReport.toString());
    }

    @Test
    public void testReportInvalidDaysWithToMuchTime() throws Exception {
        List<Task> tasksForDay = validEmployeDirection.getTimeSheet(0).getDayByName(JOUR1_KEY).getTasks();
        tasksForDay.get(1).setProjectId(914);
        tasksForDay.get(1).setTime(1920);
        List<String> expectedReport = new ArrayList<>();
        expectedReport.add(ERROR_INVALID_MAXIMUM_MINUTES_FOR_DAY + " (" + JOUR1_KEY + ")");
        Report testReport = new Report(validEmployeDirection);
        List<String> generatedReport = testReport.generateReport(validEmployeDirection);
        assertEquals(expectedReport.toString(), generatedReport.toString());
    }

    @Test
    public void testReportInvalidDaysOfHolidayCausedByWrongTime() throws Exception {
        List<Task> tasksForDay = validEmployeDirection.getTimeSheet(0).getDayByName(JOUR1_KEY).getTasks();
        tasksForDay.get(0).setProjectId(HOLIDAY_TASK_ID);
        tasksForDay.get(0).setTime(500);
        List<String> expectedReport = new ArrayList<>();
        expectedReport.add(ERROR_INVALID_HOLIDAY + " (" + JOUR1_KEY + ")");
        Report testReport = new Report(validEmployeDirection);
        List<String> generatedReport = testReport.generateReport(validEmployeDirection);
        assertEquals(expectedReport.toString(), generatedReport.toString());
    }

    @Test
    public void testReportHasNotValidWeeklyTimeRemoteTooMuchTime() throws Exception {
        List<Task> tasksForDay = validEmployeAdmin.getTimeSheet(0).getDayByName(JOUR1_KEY).getTasks();
        tasksForDay.get(1).setTime(561);
        List<String> expectedReport = new ArrayList<>();
        expectedReport.add(ERROR_TOO_MANY_MINUTES_OF_REMOTE_WORK);
        Report testReport = new Report(validEmployeAdmin);
        List<String> generatedReport = testReport.generateReport(validEmployeAdmin);
        assertEquals(expectedReport.toString(), generatedReport.toString());
    }
    
    @Test
    public void testReportHasNotValidWeeklyTimeAtOfficeTooMuchTime() throws Exception {
        List<Task> tasksForDay = validEmployeAdmin.getTimeSheet(0).getDayByName(JOUR1_KEY).getTasks();
        tasksForDay.get(0).setTime(571);
        List<String> expectedReport = new ArrayList<>();
        expectedReport.add(ERROR_TOO_MANY_MINUTES_OF_OFFICE_WORK);
        Report testReport = new Report(validEmployeAdmin);
        List<String> generatedReport = testReport.generateReport(validEmployeAdmin);
        assertEquals(expectedReport.toString(), generatedReport.toString());
    }

    @Test
    public void testReportEmployeAdminMaxOfficeHoursWeekly() throws Exception {
        List<Task> tasksForDay = validEmployeAdmin.getTimeSheet(0).getDayByName(JOUR1_KEY).getTasks();
        tasksForDay.get(0).setTime(330);
        List<String> expectedReport = new ArrayList<>();
        Report testReport = new Report(validEmployeAdmin);
        List<String> generatedReport = testReport.generateReport(validEmployeAdmin);
        assertEquals(expectedReport.toString(), generatedReport.toString());
    }

    @Test
    public void testReportEmployeAdminNoMaxOfficeHoursWeekly() throws Exception {
        List<Task> tasksForDay = validEmployeAdmin.getTimeSheet(0).getDayByName(JOUR1_KEY).getTasks();
        tasksForDay.get(0).setTime(329);
        List<String> expectedReport = new ArrayList<>();
        expectedReport.add(ERROR_NOT_ENOUGH_PHYSICAL_TIME_FOR_WEEK);
        Report testReport = new Report(validEmployeAdmin);
        List<String> generatedReport = testReport.generateReport(validEmployeAdmin);
        assertEquals(expectedReport.toString(), generatedReport.toString());
    }

    @Test
    public void testReportEmployeDirectionNoMaxHoursWeekly50_01h() throws Exception {
        List<Task> tasksForDay = validEmployeDirection.getTimeSheet(0).getDayByName(JOUR1_KEY).getTasks();
        tasksForDay.get(0).setTime(901);
        List<String> expectedReport = new ArrayList<>();
        Report testReport = new Report(validEmployeDirection);
        List<String> generatedReport = testReport.generateReport(validEmployeDirection);
        assertEquals(expectedReport.toString(), generatedReport.toString());
    }

    @Test
    public void testReportPresidentNoMaxHoursWeekly50_01h() throws Exception {
        List<Task> tasksForDay = validEmployePresident.getTimeSheet(0).getDayByName(JOUR1_KEY).getTasks();
        tasksForDay.get(0).setTime(901);
        List<String> expectedReport = new ArrayList<>();
        Report testReport = new Report(validEmployePresident);
        List<String> generatedReport = testReport.generateReport(validEmployePresident);
        assertEquals(expectedReport.toString(), generatedReport.toString());
    }

    @Test
    public void testReportEmployeDirectionMinOfficeTime8hFail() throws Exception {
        List<Task> tasksForDay1 = validEmployeDirection.getTimeSheet(0).getDayByName(JOUR1_KEY).getTasks();
        tasksForDay1.get(0).setTime(481);
        List<Task> tasksForDay = validEmployeDirection.getTimeSheet(0).getDayByName(JOUR3_KEY).getTasks();
        tasksForDay.get(0).setTime(479);
        List<String> expectedReport = new ArrayList<>();
        expectedReport.add(ERROR_NOT_ENOUGH_PHYSICAL_TIME_FOR_DAY + " (" + JOUR3_KEY + ")");
        Report testReport = new Report(validEmployeDirection);
        List<String> generatedReport = testReport.generateReport(validEmployeDirection);
        assertEquals(expectedReport.toString(), generatedReport.toString());
    }

    @Test
    public void testReportEmployeDirectionMinOfficeTime8hPass() throws Exception {
        List<String> expectedReport = new ArrayList<>();
        Report testReport = new Report(validEmployeDirection);
        List<String> generatedReport = testReport.generateReport(validEmployeDirection);
        assertEquals(expectedReport.toString(), generatedReport.toString());
    }

    @Test
    public void testReportPresidentMinOfficeTime8hFail() throws Exception {
        List<Task> tasksForDay1 = validEmployePresident.getTimeSheet(0).getDayByName(JOUR1_KEY).getTasks();
        tasksForDay1.get(0).setTime(481);
        List<Task> tasksForDay = validEmployePresident.getTimeSheet(0).getDayByName(JOUR3_KEY).getTasks();
        tasksForDay.get(0).setTime(479);
        List<String> expectedReport = new ArrayList<>();
        expectedReport.add(ERROR_NOT_ENOUGH_PHYSICAL_TIME_FOR_DAY + " (" + JOUR3_KEY + ")");
        Report testReport = new Report(validEmployePresident);
        List<String> generatedReport = testReport.generateReport(validEmployePresident);
        assertEquals(expectedReport.toString(), generatedReport.toString());
    }

    @Test
    public void testReportEmployePresidentMinOfficeTime8hPass() throws Exception {
        List<String> expectedReport = new ArrayList<>();
        Report testReport = new Report(validEmployePresident);
        List<String> generatedReport = testReport.generateReport(validEmployePresident);
        assertEquals(expectedReport.toString(), generatedReport.toString());
    }

    @Test
    public void testReportPresidentValidTransportTime301m() throws Exception {
        List<Task> tasksForDay = validEmployePresident.getTimeSheet(0).getDayByName(JOUR1_KEY).getTasks();
        tasksForDay.get(2).setProjectId(TRANSPORTATION_ID);
        tasksForDay.get(2).setTime(301);
        List<String> expectedReport = new ArrayList<>();
        Report testReport = new Report(validEmployePresident);
        List<String> generatedReport = testReport.generateReport(validEmployePresident);
        assertEquals(expectedReport.toString(), generatedReport.toString());
    }

    @Test
    public void testReportDirectionInvalidTransportTime301m() throws Exception {
        List<Task> tasksForDay = validEmployeDirection.getTimeSheet(0).getDayByName(JOUR1_KEY).getTasks();
        tasksForDay.get(2).setProjectId(TRANSPORTATION_ID);
        tasksForDay.get(2).setTime(301);
        List<String> expectedReport = new ArrayList<>();
        expectedReport.add(ERROR_TOO_MUCH_TRANSPORT_TIME);
        Report testReport = new Report(validEmployeDirection);
        List<String> generatedReport = testReport.generateReport(validEmployeDirection);
        assertEquals(expectedReport.toString(), generatedReport.toString());
    }

    @Test
    public void testReportDirectionValidTransportTime300m() throws Exception {
        List<Task> tasksForDay = validEmployeDirection.getTimeSheet(0).getDayByName(JOUR1_KEY).getTasks();
        tasksForDay.get(2).setProjectId(TRANSPORTATION_ID);
        tasksForDay.get(2).setTime(300);
        List<String> expectedReport = new ArrayList<>();
        Report testReport = new Report(validEmployeDirection);
        List<String> generatedReport = testReport.generateReport(validEmployeDirection);
        assertEquals(expectedReport.toString(), generatedReport.toString());
    }

    @Test
    public void testReportAdminInvalidTransportTime301m() throws Exception {
        List<Task> tasksForDay = validEmployeAdmin.getTimeSheet(0).getDayByName(JOUR1_KEY).getTasks();
        tasksForDay.get(0).setTime(89);
        tasksForDay.get(2).setProjectId(TRANSPORTATION_ID);
        tasksForDay.get(2).setTime(301);
        List<String> expectedReport = new ArrayList<>();
        expectedReport.add(ERROR_TOO_MUCH_TRANSPORT_TIME);
        Report testReport = new Report(validEmployeAdmin);
        List<String> generatedReport = testReport.generateReport(validEmployeAdmin);
        assertEquals(expectedReport.toString(), generatedReport.toString());
    }

    @Test
    public void testReportAdminValidTransportTime300m() throws Exception {
        List<Task> tasksForDay = validEmployeAdmin.getTimeSheet(0).getDayByName(JOUR1_KEY).getTasks();
        tasksForDay.get(0).setTime(89);
        tasksForDay.get(2).setProjectId(TRANSPORTATION_ID);
        tasksForDay.get(2).setTime(300);
        List<String> expectedReport = new ArrayList<>();
        Report testReport = new Report(validEmployeAdmin);
        List<String> generatedReport = testReport.generateReport(validEmployeAdmin);
        assertEquals(expectedReport.toString(), generatedReport.toString());
    }

    @Test
    public void testReportDevelopmentInvalidTransportTime300m() throws Exception {
        List<Task> tasksForDay = validEmployeDevelopment.getTimeSheet(0).getDayByName(JOUR1_KEY).getTasks();
        tasksForDay.get(0).setTime(100);
        tasksForDay.get(2).setProjectId(TRANSPORTATION_ID);
        tasksForDay.get(2).setTime(301);
        List<String> expectedReport = new ArrayList<>();
        expectedReport.add(ERROR_INVALID_TRANSPORT_TIME);
        Report testReport = new Report(validEmployeDevelopment);
        List<String> generatedReport = testReport.generateReport(validEmployeDevelopment);
        assertEquals(expectedReport.toString(), generatedReport.toString());
    }

    @Test
    public void testReportExploitationInvalidTransportTime300m() throws Exception {
        List<Task> tasksForDay = validEmployeExploitation.getTimeSheet(0).getDayByName(JOUR1_KEY).getTasks();
        tasksForDay.get(0).setTime(200);
        tasksForDay.get(2).setProjectId(TRANSPORTATION_ID);
        tasksForDay.get(2).setTime(300);
        List<String> expectedReport = new ArrayList<>();
        expectedReport.add(ERROR_INVALID_TRANSPORT_TIME);
        Report testReport = new Report(validEmployeExploitation);
        List<String> generatedReport = testReport.generateReport(validEmployeExploitation);
        assertEquals(expectedReport.toString(), generatedReport.toString());
    }
    
    @Test
    public void testInvalidDaysOfParentalHolidayFailOnWeekend() throws Exception {
        List<Task> tasksForDay = validEmployePresident.getTimeSheet(0).getDayByName(WEEKEND2_KEY).getTasks();
        tasksForDay.get(0).setProjectId(PARENTAL_HOLIDAY_TASK_ID);
        //tasksForDay.get(1).setProjectId(SICK_LEAVE_TASK_ID);
        tasksForDay.get(0).setTime(480);
        List<String> expectedReport = new ArrayList<>();
        expectedReport.add(ERROR_INVALID_PARENTAL_HOLIDAY + " (" + WEEKEND2_KEY + ")");
        Report testReport = new Report(validEmployePresident);
        List<String> generatedReport = testReport.generateReport(validEmployePresident);
        assertEquals(expectedReport.toString(), generatedReport.toString());
    }
    
    @Test
    public void testInvalidDaysOfParentalHoliday2InAWeek() throws Exception {
        List<Task> tasksForDay2 = validEmployePresident.getTimeSheet(0).getDayByName(JOUR2_KEY).getTasks();
        tasksForDay2.get(0).setProjectId(PARENTAL_HOLIDAY_TASK_ID);
        List<Task> tasksForDay3 = validEmployePresident.getTimeSheet(0).getDayByName(JOUR3_KEY).getTasks();
        tasksForDay3.get(0).setProjectId(PARENTAL_HOLIDAY_TASK_ID);
        List<String> expectedReport = new ArrayList<>();
        expectedReport.add(ERROR_INVALID_PARENTAL_HOLIDAY + " (" + JOUR2_KEY + ")");
        expectedReport.add(ERROR_INVALID_PARENTAL_HOLIDAY + " (" + JOUR3_KEY + ")");
        expectedReport.add(ERROR_TOO_MANY_PARENTAL_HOLIDAY);
        Report testReport = new Report(validEmployePresident);
        List<String> generatedReport = testReport.generateReport(validEmployePresident);
        assertEquals(expectedReport.toString(), generatedReport.toString());
    }
}
