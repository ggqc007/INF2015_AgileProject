/* INF2015 – Développement de logiciels dans un environnement Agile
 *
 * Projet de session - Hiver 2014
 *
 * Equipe 8 :
 *
 *   Christian Cornejo
 *   Khaled Elsheikh
 *   Guillaume Gagnon
 *   Thomas Robert de Massy
 *
 */

package timesheet;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

public class TimeSheet {    
    protected static final int REMOTE_TASK_ID_FLOOR = 900;
    protected static final int SICK_LEAVE_TASK_ID = 999;
    protected static final int SICK_LEAVE_TIME = 420;    
    protected static final int PUBLIC_HOLIDAY_TASK_ID = 998;    
    protected static final int PUBLIC_HOLIDAY_TIME = 420;    
    protected static final int EMPLOYE_ADMIN_ID_CEILING = 1000;
    protected static final int EMPLOYE_PROD_ID_CEILING = 2000;

    private static String inputFileName;
    private static String outputFileName;
    
    public static void verifyCmdArgs(final String[] args) {
        if (args.length != 2) {
            System.out.println("Utilisation: TimeSheet.jar input.json output.json\n");
            System.exit(1);
        }
        inputFileName = args[0];
        outputFileName = args[1];        
    }
    
    private static TimeSheetData tryJSONParserToTimeSheetData(JSONObject objectFromFile) {
        TimeSheetData newTimeSheetData = null;
        try {
            newTimeSheetData = JSONParser.toTimeSheetData(objectFromFile);
        } catch (Exception e) {
            System.out.println("Erreur dans le traitement du fichier JSON, Fin du programme. : ("+ e +")");
            exitWithEmptyJSONArrayFile();
        }
        return newTimeSheetData;
    }
    
    private static void exitWithEmptyJSONArrayFile() {
        JSONArray fileOutput = new JSONArray();
        FileWriter.writeJSONFile(fileOutput, outputFileName);
        System.exit(1);
    }

    public static void main(String[] args) {                
        verifyCmdArgs(args);                
        Employe employe = new Employe();
        JSONObject objectFromFile = JSONObject.fromObject(FileReader.readJSONFile(inputFileName));
        employe.initFromFirstTimeSheet(tryJSONParserToTimeSheetData(objectFromFile));        
        Report report = new Report(employe);             
        JSONArray outputJSON = JSONParser.reportToJSONArray(report.generate(employe));        
        FileWriter.writeJSONFile(outputJSON, outputFileName);           
        debug(employe, objectFromFile, outputJSON);   
    }
    
    private static void debug (final Employe employe, final JSONObject objectFromFile, final JSONArray outputJSON) { 
        System.out.println("\nDEBUG JSON Input filename : " + inputFileName);
        System.out.println("\nDEBUG JSON Input data : " + objectFromFile.toString(2));        
        System.out.println("\nDEBUG Parsed TimeSheetData : " + employe.getTimeSheet(0));

        System.out.print("\nDEBUG Employe ID " + employe.getId());
        if (employe.isAdmin())
            System.out.println(" is an ADMIN employe");
        else {
            if ((employe.isExplEmploye()))
                System.out.println(" is an EXPLOITATION employe");
            else {
                if ((employe.isProdEmploye()))
                    System.out.println(" is an PRODUCTION employe");
                else
                    System.out.println(" is an UNKNOWN employe"); 
            }
        }
        
        Day day;        
        Rules rules;   
        int hours, minutes;
        if (employe.isAdmin()) {
            rules = new RulesAdmins(employe);
        } else {
            rules = new RulesEmployes(employe);
        }        
        //rules.setEmploye(employe);
        //rules.prepData();    
        
        System.out.println("\nDEBUG JSON Validation :");
        System.out.print("\n      Weekdays valid : ");
        if (employe.getTimeSheet(0).hasValidWeek())
            System.out.println("YES (all 7 days with valid names)");
        else
            System.out.println("NO (one or more invalid name(s) or not 7 days)");
        
        System.out.println("\nDEBUG Working hours stats :");
        System.out.printf("\n      Valid days           : ");
        for(int i = 0; i < employe.getTimeSheet(0).getDaysNum(); i++) {
            day = employe.getTimeSheet(0).getDay(i);
            if (day.hasValidHours())
                System.out.printf(day.getName() + " ");
        }
        System.out.printf("\n      Invalid days   (>24h): ");
        for(int i = 0; i < employe.getTimeSheet(0).getDaysNum(); i++) {
            day = employe.getTimeSheet(0).getDay(i);
            if (!day.hasValidHours())
                System.out.printf(day.getName() + " ");
        }  
        System.out.printf("\n      Valid pub holiday    : ");
        for(int i = 0; i < employe.getTimeSheet(0).getDaysNum(); i++) {
            day = employe.getTimeSheet(0).getDay(i);
            if (day.hasPublicHolidayTask() && day.isValidPublicHoliday())
                System.out.printf(day.getName() + " ");
        }          
        System.out.printf("\n      Invalid pub holiday  : ");
        for(int i = 0; i < employe.getTimeSheet(0).getDaysNum(); i++) {
            day = employe.getTimeSheet(0).getDay(i);
            if (day.hasPublicHolidayTask() && !day.isValidPublicHoliday())
                System.out.printf(day.getName() + " ");
        }  
        System.out.printf("\n      Valid sick leave     : ");
        for(int i = 0; i < employe.getTimeSheet(0).getDaysNum(); i++) {
            day = employe.getTimeSheet(0).getDay(i);
            if (day.hasSickLeaveTask() && day.isValidSickLeave())
                System.out.printf(day.getName() + " ");
        }  
        System.out.printf("\n      Invalid sick leave   : ");
        for(int i = 0; i < employe.getTimeSheet(0).getDaysNum(); i++) {
            day = employe.getTimeSheet(0).getDay(i);
            if (day.hasSickLeaveTask() && !day.isValidSickLeave())
                System.out.printf(day.getName() + " ");
        } 
        
        System.out.printf("\n\n      Office day min       : " + rules.getMinOfficeDailyMinutes() + "m(%.0fh)\n", rules.getMinOfficeDailyMinutes()/60.0);

        System.out.printf("      Office week min/max  : " + rules.getMinOfficeWeekMinutes() + "m(%.0fh)/", rules.getMinOfficeWeekMinutes()/60.0);
        System.out.printf(rules.getMaxOfficeWeekMinutes() + "m(%.0fh)\n", rules.getMaxOfficeWeekMinutes()/60.0);

        if (rules.getMaxRemoteWeekMinutes() != 0)
            System.out.printf("      Remote week max      : " + rules.getMaxRemoteWeekMinutes() + "m(%.0fh)\n", rules.getMaxRemoteWeekMinutes()/60.0);
        else        
            System.out.println("      Remote week max      : N/A");               

        hours = rules.getTotalWeekMinutes() / 60;
        minutes = rules.getTotalWeekMinutes() % 60;
        System.out.printf("\n      Total by week        : " + rules.getTotalWeekMinutes() + "m(%d:%02dh)\n", hours, minutes);
        hours = rules.getTotalOfficeWeekMinutes() / 60;
        minutes = rules.getTotalOfficeWeekMinutes() % 60;        
        System.out.printf("      Total office by week : " + rules.getTotalOfficeWeekMinutes() + "m(%d:%02dh)\n", hours, minutes);        
        hours = rules.getTotalRemoteWeekMinutes() / 60;
        minutes = rules.getTotalRemoteWeekMinutes() % 60;        
        System.out.printf("      Total remote by week : " + rules.getTotalRemoteWeekMinutes() +"m(%d:%02dh)\n", hours, minutes);        

        System.out.printf("\n      Total by day         : ");               
        for(int i = 0; i < employe.getTimeSheet(0).getDaysNum()-1; i++) {
            day = employe.getTimeSheet(0).getDay(i);
            hours = (rules.getTotalOfficeMinutesByDay(day)+rules.getTotalRemoteMinutesByDay(day)) / 60;
            minutes = (rules.getTotalOfficeMinutesByDay(day)+rules.getTotalRemoteMinutesByDay(day)) % 60;            
            System.out.printf((rules.getTotalOfficeMinutesByDay(day)+rules.getTotalRemoteMinutesByDay(day)) + "m(%d:%02dh),", hours, minutes);
        }
        day = employe.getTimeSheet(0).getDay(employe.getTimeSheet(0).getDaysNum()-1);
        hours = (rules.getTotalOfficeMinutesByDay(day)+rules.getTotalRemoteMinutesByDay(day)) / 60;
        minutes = (rules.getTotalOfficeMinutesByDay(day)+rules.getTotalRemoteMinutesByDay(day)) % 60;        
        System.out.printf((rules.getTotalOfficeMinutesByDay(day)+rules.getTotalRemoteMinutesByDay(day)) + "m(%d:%02dh)\n", hours, minutes);        
        
        System.out.printf("      Total office by day  : ");               
        for(int i = 0; i < employe.getTimeSheet(0).getDaysNum()-1; i++) {
            day = employe.getTimeSheet(0).getDay(i);
            hours = rules.getTotalOfficeMinutesByDay(day) / 60;
            minutes = rules.getTotalOfficeMinutesByDay(day) % 60;            
            System.out.printf(rules.getTotalOfficeMinutesByDay(day) + "m(%d:%02dh),", hours, minutes);
        }
        day = employe.getTimeSheet(0).getDay(employe.getTimeSheet(0).getDaysNum()-1);
        hours = rules.getTotalOfficeMinutesByDay(day) / 60;
        minutes = rules.getTotalOfficeMinutesByDay(day) % 60;        
        System.out.printf(rules.getTotalOfficeMinutesByDay(day) + "m(%d:%02dh)\n", hours, minutes);

        System.out.printf("      Total remote by day  : ");       
        for(int i = 0; i < employe.getTimeSheet(0).getDaysNum()-1; i++) {
            day = employe.getTimeSheet(0).getDay(i);
            hours = rules.getTotalRemoteMinutesByDay(day) / 60;
            minutes = rules.getTotalRemoteMinutesByDay(day) % 60;            
            System.out.printf(rules.getTotalRemoteMinutesByDay(day) + "m(%d:%02dh),", hours, minutes);
        }
        day = employe.getTimeSheet(0).getDay(employe.getTimeSheet(0).getDaysNum()-1);
        hours = rules.getTotalRemoteMinutesByDay(day) / 60;
        minutes = rules.getTotalRemoteMinutesByDay(day) % 60;        
        System.out.printf(rules.getTotalRemoteMinutesByDay(day) + "m(%d:%02dh)\n", hours, minutes);

        System.out.println("\nDEBUG JSON Output data : " + outputJSON.toString(2)); 
        System.out.println("\nDEBUG JSON Data succesfully writen to : " + outputFileName +"\n");                  
    }
}
