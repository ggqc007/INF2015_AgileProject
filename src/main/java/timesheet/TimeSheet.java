package timesheet;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

// POUR DEBUG A ENLEVER
import java.util.List;

public class TimeSheet {    
    protected static final int REMOTE_TASK_ID_FLOOR = 900;
    protected static final int SICK_LEAVE_TASK_ID = 999;
    protected static final int SICK_LEAVE_TIME = 480;
    protected static final int PUBLIC_HOLIDAY_TASK_ID = 998;    
    protected static final int PUBLIC_HOLIDAY_TIME = 480;  
    protected static final int HOLIDAY_TASK_ID = 997;    
    protected static final int HOLIDAY_TIME = 480;
    protected static final int PARENTAL_HOLIDAY_TASK_ID = 996;    
    protected static final int PARENTAL_HOLIDAY_TIME = 480;    
    protected static final int EMPLOYE_ADMIN_ID_CEILING = 1000;
    protected static final int EMPLOYE_DEVEL_ID_CEILING = 2000;
    protected static final int EMPLOYE_DIRECTION_ID_FLOOR = 5000;
    protected static final int PREDIDENT_ID = 6000;    
    protected static final int MINIMUM_MINUTES_AMOUNT_FOR_TASK = 0;
    protected static final int MAXIMUM_HOURS_FOR_DAY = 32;
    protected static final int TRANSPORTATION_ID = 777;  
    protected static final int MAX_TRANSPORTATION_TIME_ADMIN = 300;
    protected static final int MAX_TRANSPORTATION_TIME_DIRECTION = 300;
    protected static final int MAX_TRANSPORTATION_TIME_PRESIDENT = 0;
        
    protected static String inputFileName;
    protected static String outputFileName;
    private static final String FILE_ENCODING = "UTF-8";
    private static JSONObject objectFromFile;
    
    protected static void verifyCmdArgs(String[] args) {
        if (args.length != 2) {
            System.out.println("Utilisation: TimeSheet.jar input.json output.json\n");
            System.exit(1);
        }
        inputFileName = args[0];
        outputFileName = args[1];        
    }
    
    protected static JSONObject validateAndLoadJSONObjectFromFile(String inputFileName) {
        try {
            objectFromFile = JSONObject.fromObject(FileReader.readJSONFile(inputFileName));
        } catch (Exception e) {
            System.out.println("Erreur, fichier JSON non valide, Fin du programme.");
            exitWithEmptyJSONArrayFile();
        }
        return objectFromFile;
    }
    
    protected static TimeSheetData tryJSONParserToTimeSheetData(JSONObject objectFromFile) {
        TimeSheetData newTimeSheetData = null;
        try {
            newTimeSheetData = JSONParser.toTimeSheetData(objectFromFile);
        } catch (Exception e) {
            System.out.println("Erreur dans le traitement du fichier JSON, Fin du programme. : (" + e + ")");
            exitWithEmptyJSONArrayFile();
        }
        return newTimeSheetData;
    }
    
    protected static void exitWithEmptyJSONArrayFile() {
        JSONArray fileOutput = new JSONArray();
        OutputStreamWriterWrapper writer = initializeOutputStreamWriterWrapper();
        writer.writeJSONFile(fileOutput);
        System.exit(1);
    }
    
    protected static OutputStreamWriterWrapper initializeOutputStreamWriterWrapper() {
        OutputStreamWriterWrapper writer = null;
        try {
            OutputStreamWriter osw = new OutputStreamWriter(new FileOutputStream(outputFileName), FILE_ENCODING);
            writer = new OutputStreamWriterWrapper(osw);
        } catch (FileNotFoundException | UnsupportedEncodingException e) {
            System.out.println("Erreur! ("+ e +")");
        }
        return writer;
    }

    public static void main(String[] args) throws Exception {                
        verifyCmdArgs(args);                
        Employe employe = new Employe();
        validateAndLoadJSONObjectFromFile(inputFileName);
        employe.initFromFirstTimeSheet(tryJSONParserToTimeSheetData(objectFromFile));        
        Report report = new Report(employe);             
        JSONArray outputJSON = JSONParser.reportToJSONArray(report.generateReport(employe));
        
        // TODO: potentiellement faire une methode avec les 2 lignes pour sauver 1 ligne dans le main...
        OutputStreamWriterWrapper writer = initializeOutputStreamWriterWrapper();
        writer.writeJSONFile(outputJSON);
        
        debug(employe, objectFromFile, outputJSON);
    }  
    
    // TODO: Cette methode a plus de 10 lignes ############################################################################  :o)
    // Mais surtout... ne pas oublier d'enlever ce bloc avant la remise..............................!!!!!       
    protected static void debug (Employe employe, JSONObject objectFromFile, JSONArray outputJSON) { 
        System.out.println("\nDEBUG JSON Input filename : " + inputFileName);
        System.out.println("\nDEBUG JSON Input data : " + objectFromFile.toString(2));        
        System.out.println("\nDEBUG Parsed TimeSheetData : " + employe.getTimeSheet(0));
        System.out.println("\nDEBUG Employe toString : " + employe.toString());

        System.out.print("\nDEBUG Employe ID " + employe.getId());
        if (employe.isAdmin())
            System.out.println(" is an ADMIN employe");
        else {
            if (employe.isExplEmploye())
                System.out.println(" is an EXPLOITATION employe");
            else if (employe.isDevelEmploye())
                System.out.println(" is a DEVELOPMENT employe");
            else if (employe.isDirectionEmploye() && !employe.isPresident())
                System.out.println(" is a DIRECTION employe"); 
            else if (employe.isPresident())
                System.out.println(" is a PRESIDENT (DIRECTION) employe");            
            else
                System.out.println(" is an UNKNOWN employe"); 
            
        }       
        
        Day day;        
        Rules rules;   
        int hours, minutes;
 
        RulesFactory rulesFactory = new RulesFactory();
        rules = rulesFactory.makeRules(employe);
        
        if (rules.canChargeTransportation()) {
            System.out.printf("\n      Can declare transport time : YES\n");
            if (employe.isAdmin() || employe.isPresident())
                System.out.printf("      Declared has               : OFFICE TIME\n");
            else if ((employe.isDirectionEmploye()))
                System.out.printf("      Declared has               : REMOTE TIME\n");                                
            else 
                System.out.printf("      Declared has               : UNKNOWN\n");
        } else {
            System.out.printf("\n      Can declare transport time : NO\n"); 
            System.out.printf("      Declared has               : N/A\n");
        }
        
        System.out.println("\nDEBUG JSON Validation :");
        System.out.print("\n      Weekdays valid : ");
        if (employe.getTimeSheet(0).hasValidWeek())
            System.out.println("YES (all 7 days with valid names)");
        else
            System.out.println("NO (one or more invalid name(s) or not 7 days)");
        
        System.out.println("\nDEBUG Working hours stats :");
        System.out.printf("\n    > Valid days           : ");
        for(int i = 0; i < employe.getTimeSheet(0).getDaysNum(); i++) {
            day = employe.getTimeSheet(0).getDay(i);
            if (day.hasValidMaximumHours())
                System.out.printf(day.getName() + " ");
        }
        System.out.printf("\n      Invalid days   (>24h): ");
        for(int i = 0; i < employe.getTimeSheet(0).getDaysNum(); i++) {
            day = employe.getTimeSheet(0).getDay(i);
            if (!day.hasValidMaximumHours())
                System.out.printf(day.getName() + " ");
        }   
        
        List<Day> days_ph = rules.getInvalidDaysWithPublicHoliday();
        
        System.out.printf("\n    > Valid pub holiday    : ");
        for(int i = 0; i < employe.getTimeSheet(0).getDaysNum(); i++) {
            day = employe.getTimeSheet(0).getDay(i);
            //if (!days.contains(day))
            if (!days_ph.contains(day) && day.hasPublicHolidayTask() && day.isValidPublicHoliday())
            //if (day.hasPublicHolidayTask() && day.isValidPublicHoliday())
                System.out.printf(day.getName() + " ");
        }          
        System.out.printf("\n      Invalid pub holiday  : ");
        for(int i = 0; i < employe.getTimeSheet(0).getDaysNum(); i++) {
            day = employe.getTimeSheet(0).getDay(i);
            if (days_ph.contains(day))
            //if (day.hasPublicHolidayTask() && !day.isValidPublicHoliday())
                System.out.printf(day.getName() + " ");
        }  
        
        List<Day> days_sl = rules.getInvalidDaysWithSickLeave();       
        
        System.out.printf("\n    > Valid sick leave     : ");        
        for(int i = 0; i < employe.getTimeSheet(0).getDaysNum(); i++) {
            day = employe.getTimeSheet(0).getDay(i);
            if (!days_sl.contains(day) && day.hasSickLeaveTask() && day.isValidSickLeave())
            //if (day.hasSickLeaveTask() && day.isValidSickLeave())
                System.out.printf(day.getName() + " ");
        }  
        System.out.printf("\n      Invalid sick leave   : ");
        for(int i = 0; i < employe.getTimeSheet(0).getDaysNum(); i++) {
            day = employe.getTimeSheet(0).getDay(i);
            if (days_sl.contains(day))
            //if (day.hasSickLeaveTask() && !day.isValidSickLeave())
                System.out.printf(day.getName() + " ");
        } 
 
        // DEBUT - NOUVELLE MODIFICATIONS
        
        List<Day> days_parenth = rules.getInvalidDaysOfParentalHoliday();       
        
        System.out.printf("\n    > Valid parent holiday : ");        
        for(int i = 0; i < employe.getTimeSheet(0).getDaysNum(); i++) {
            day = employe.getTimeSheet(0).getDay(i);
            if (!days_parenth.contains(day) && day.hasParentalHolidayTask() && day.isValidParentalHoliday())
                System.out.printf(day.getName() + " ");
        }  
        System.out.printf("\n      Invalid parent holi  : ");
        for(int i = 0; i < employe.getTimeSheet(0).getDaysNum(); i++) {
            day = employe.getTimeSheet(0).getDay(i);
            if (days_parenth.contains(day))
                System.out.printf(day.getName() + " ");
        } 
        
        List<Day> days_holiday = rules.getInvalidDaysOfHoliday();       
        
        System.out.printf("\n    > Valid holiday        : ");        
        for(int i = 0; i < employe.getTimeSheet(0).getDaysNum(); i++) {
            day = employe.getTimeSheet(0).getDay(i);
            if (!days_holiday.contains(day) && day.hasHolidayTask() && day.isValidHoliday())
                System.out.printf(day.getName() + " ");
        }  
        System.out.printf("\n      Invalid holiday      : ");
        for(int i = 0; i < employe.getTimeSheet(0).getDaysNum(); i++) {
            day = employe.getTimeSheet(0).getDay(i);
            if (days_holiday.contains(day))
                System.out.printf(day.getName() + " ");
        }
        
        // FIN - NOUVELLE MODIFICATIONS
        
        hours = rules.getMinOfficeDailyMinutes() / 60;
        minutes = rules.getMinOfficeDailyMinutes() % 60;
        
        System.out.printf("\n\n      Office day min       : " + rules.getMinOfficeDailyMinutes() + "m(%d:%02dh)\n", hours, minutes);

        hours = rules.getMinOfficeWeekMinutes() / 60;
        minutes = rules.getMinOfficeWeekMinutes() % 60;        
        
        System.out.printf("      Office week min/max  : " + rules.getMinOfficeWeekMinutes() + "m(%d:%02dh)/", hours, minutes);
 
        hours = rules.getMaxOfficeWeekMinutes() / 60;
        minutes = rules.getMaxOfficeWeekMinutes() % 60;  
        
        if (rules.getMaxOfficeWeekMinutes() != 0)
            System.out.printf(rules.getMaxOfficeWeekMinutes() + "m(%d:%02dh)\n", hours, minutes);
        else
            System.out.printf(" N/A\n");
        
        hours = rules.getMaxRemoteWeekMinutes() / 60;
        minutes = rules.getMaxRemoteWeekMinutes() % 60; 
        
        if (rules.getMaxRemoteWeekMinutes() != 0)
            System.out.printf("      Remote week max      : " + rules.getMaxRemoteWeekMinutes() + "m(%d:%02dh)\n", hours, minutes);
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

        hours = rules.getTotalTransportTime() / 60;
        minutes = rules.getTotalTransportTime() % 60;  
        System.out.printf("\n      Total transport time : " + rules.getTotalTransportTime()+"m(%d:%02dh)\n", hours, minutes);
        
        if (rules.canChargeTransportation())
            if (rules.hasValidWeeklyTransportTime())
                System.out.printf("      Valid transport time : YES\n");
            else
                System.out.printf("      Valid transport time : NO (too much time)\n");
        else
            System.out.printf("      Valid transport time : NO (not allowed)\n");
        
        System.out.printf("\n      Day name             : ");               
        for(int i = 0; i < employe.getTimeSheet(0).getDaysNum()-1; i++) {
            day = employe.getTimeSheet(0).getDay(i);
            System.out.printf("%11s,", day.getName());
        }   
        
        day = employe.getTimeSheet(0).getDay(employe.getTimeSheet(0).getDaysNum()-1);
        System.out.printf("%11s", day.getName());
            
        System.out.printf("\n      Day type             : ");               
        for(int i = 0; i < employe.getTimeSheet(0).getDaysNum()-1; i++) {
            day = employe.getTimeSheet(0).getDay(i);
            if (day.hasSickLeaveTask()) {
                if (!days_sl.contains(day) && day.isValidSickLeave())
                    System.out.printf("%11s,", "SickLeave");
                else
                    System.out.printf("%11s,", "[SickLeave]");
            } else if (day.hasPublicHolidayTask()) {
                if (!days_ph.contains(day) && day.isValidPublicHoliday())
                    System.out.printf("%11s,", "PubHoli");
                else
                    System.out.printf("%11s,", "[PubHoli]");
            } else if (day.hasParentalHolidayTask()) {
                if (!days_parenth.contains(day) && day.isValidParentalHoliday())
                    System.out.printf("%11s,", "ParentHoli");
                else
                    System.out.printf("%11s,", "[ParentHoli]");
            } else if (day.hasHolidayTask()) {
                if (!days_holiday.contains(day) && day.isValidHoliday())
                    System.out.printf("%11s,", "Holiday");
                else
                    System.out.printf("%11s,", "[Holiday]");
            } else {                
                if (day.isWorkingDay())
                    System.out.printf("%11s,", "Normal");
                else
                    System.out.printf("%11s,", "Weekend");
            }
        }   
        
        day = employe.getTimeSheet(0).getDay(employe.getTimeSheet(0).getDaysNum()-1);
        if (day.hasSickLeaveTask()) {
            if (!days_sl.contains(day) && day.isValidSickLeave())
                System.out.printf("%11s", "SickLeave");
            else
                System.out.printf("%11s", "[SickLeave]");
        } else if (day.hasPublicHolidayTask()) {
            if (!days_ph.contains(day) && day.isValidPublicHoliday())
                System.out.printf("%11s", "PubHoli");
            else
                System.out.printf("%11s", "[PubHoli]");
        } else if (day.hasParentalHolidayTask()) {
                if (!days_parenth.contains(day) && day.isValidParentalHoliday())
                    System.out.printf("%11s,", "ParentHoli");
                else
                    System.out.printf("%11s,", "[ParentHoli]");
        } else if (day.hasHolidayTask()) {
                if (!days_holiday.contains(day) && day.isValidHoliday())
                    System.out.printf("%11s,", "Holiday");
                else
                    System.out.printf("%11s,", "[Holiday]");
        } else {
                if (day.isWorkingDay())
                    System.out.printf("%11s", "Normal");
                else
                    System.out.printf("%11s", "Weekend");                                
        }        
        
        System.out.printf("\n\n      Total by day         : ");               
        for(int i = 0; i < employe.getTimeSheet(0).getDaysNum()-1; i++) {
            day = employe.getTimeSheet(0).getDay(i);
            hours = (rules.getTotalOfficeMinutesByDay(day)+rules.getTotalRemoteMinutesByDay(day)) / 60;
            minutes = (rules.getTotalOfficeMinutesByDay(day)+rules.getTotalRemoteMinutesByDay(day)) % 60; 
            //if ((day.hasPublicHolidayTask() && !day.isValidPublicHoliday()) || (day.hasSickLeaveTask() && !day.isValidSickLeave()))
            if (days_ph.contains(day) || days_sl.contains(day) || days_parenth.contains(day) || days_holiday.contains(day))            
                System.out.printf("[" + (rules.getTotalOfficeMinutesByDay(day)+rules.getTotalRemoteMinutesByDay(day)) + "m(%d:%02dh)],", hours, minutes);
            else
                System.out.printf(" " + (rules.getTotalOfficeMinutesByDay(day)+rules.getTotalRemoteMinutesByDay(day)) + "m(%d:%02dh),", hours, minutes);                
        }
        day = employe.getTimeSheet(0).getDay(employe.getTimeSheet(0).getDaysNum()-1);
        hours = (rules.getTotalOfficeMinutesByDay(day)+rules.getTotalRemoteMinutesByDay(day)) / 60;
        minutes = (rules.getTotalOfficeMinutesByDay(day)+rules.getTotalRemoteMinutesByDay(day)) % 60;  
        
        //if ((day.hasPublicHolidayTask() && !day.isValidPublicHoliday()) || (day.hasSickLeaveTask() && !day.isValidSickLeave()))
        if (days_ph.contains(day) || days_sl.contains(day) || days_parenth.contains(day) || days_holiday.contains(day))
            System.out.printf("[" + (rules.getTotalOfficeMinutesByDay(day)+rules.getTotalRemoteMinutesByDay(day)) + "m(%d:%02dh)]\n", hours, minutes);        
        else
            System.out.printf(" " + (rules.getTotalOfficeMinutesByDay(day)+rules.getTotalRemoteMinutesByDay(day)) + "m(%d:%02dh)\n", hours, minutes); 
        
        System.out.printf("      Total office by day  : ");               
        for(int i = 0; i < employe.getTimeSheet(0).getDaysNum()-1; i++) {
            day = employe.getTimeSheet(0).getDay(i);
            hours = rules.getTotalOfficeMinutesByDay(day) / 60;
            minutes = rules.getTotalOfficeMinutesByDay(day) % 60;
            //if ((day.hasPublicHolidayTask() && !day.isValidPublicHoliday()) || (day.hasSickLeaveTask() && !day.isValidSickLeave()))
            if (days_ph.contains(day) || days_sl.contains(day) || days_parenth.contains(day) || days_holiday.contains(day))
                System.out.printf(" [" + rules.getTotalOfficeMinutesByDay(day) + "m(%d:%02dh)],", hours, minutes);   
            else
                System.out.printf(" " + rules.getTotalOfficeMinutesByDay(day) + "m(%d:%02dh),", hours, minutes);
        }
        day = employe.getTimeSheet(0).getDay(employe.getTimeSheet(0).getDaysNum()-1);
        hours = rules.getTotalOfficeMinutesByDay(day) / 60;
        minutes = rules.getTotalOfficeMinutesByDay(day) % 60;    
        
        //if ((day.hasPublicHolidayTask() && !day.isValidPublicHoliday()) || (day.hasSickLeaveTask() && !day.isValidSickLeave()))
        if (days_ph.contains(day) || days_sl.contains(day) || days_parenth.contains(day) || days_holiday.contains(day))
            System.out.printf(" [" + rules.getTotalOfficeMinutesByDay(day) + "m(%d:%02dh)]\n", hours, minutes);
        else
            System.out.printf(" " + rules.getTotalOfficeMinutesByDay(day) + "m(%d:%02dh)\n", hours, minutes);            

        System.out.printf("      Total remote by day  : ");       
        for(int i = 0; i < employe.getTimeSheet(0).getDaysNum()-1; i++) {
            day = employe.getTimeSheet(0).getDay(i);
            hours = rules.getTotalRemoteMinutesByDay(day) / 60;
            minutes = rules.getTotalRemoteMinutesByDay(day) % 60;    
            //if ((day.hasPublicHolidayTask() && !day.isValidPublicHoliday()) || (day.hasSickLeaveTask() && !day.isValidSickLeave()))
            if (days_ph.contains(day) || days_sl.contains(day) || days_parenth.contains(day) || days_holiday.contains(day))
                System.out.printf(" [" + rules.getTotalRemoteMinutesByDay(day) + "m(%d:%02dh)],", hours, minutes);
            else
                System.out.printf(" " + rules.getTotalRemoteMinutesByDay(day) + "m(%d:%02dh),", hours, minutes);                
        }
        day = employe.getTimeSheet(0).getDay(employe.getTimeSheet(0).getDaysNum()-1);
        hours = rules.getTotalRemoteMinutesByDay(day) / 60;
        minutes = rules.getTotalRemoteMinutesByDay(day) % 60;   
        //if ((day.hasPublicHolidayTask() && !day.isValidPublicHoliday()) || (day.hasSickLeaveTask() && !day.isValidSickLeave()))        
        if (days_ph.contains(day) || days_sl.contains(day) || days_parenth.contains(day) || days_holiday.contains(day))    
            System.out.printf(" [" + rules.getTotalRemoteMinutesByDay(day) + "m(%d:%02dh)]\n", hours, minutes);
        else
            System.out.printf(" " + rules.getTotalRemoteMinutesByDay(day) + "m(%d:%02dh)\n", hours, minutes);            

        System.out.println("\nDEBUG JSON Output data : " + outputJSON.toString(2)); 
        System.out.println("\nDEBUG JSON Data succesfully writen to : " + outputFileName +"\n");                  
    }
    
}