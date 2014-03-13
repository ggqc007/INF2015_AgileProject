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
    protected static final int MINIMUM_MINUTES_AMOUNT_FOR_TASK = 0;
    protected static final int MAXIMUM_HOURS_FOR_DAY = 32;

    private static String inputFileName;
    private static String outputFileName;
    private static JSONObject objectFromFile;
    
    public static void verifyCmdArgs(String[] args) {
        if (args.length != 2) {
            System.out.println("Utilisation: TimeSheet.jar input.json output.json\n");
            System.exit(1);
        }
        inputFileName = args[0];
        outputFileName = args[1];        
    }
    
    private static JSONObject validateAndLoadJSONObjectFromFile(String inputFileName) {
        try {
            objectFromFile = JSONObject.fromObject(FileReader.readJSONFile(inputFileName));
        } catch (Exception e) {
            System.out.println("Erreur, fichier JSON non valide, Fin du programme.");
            exitWithEmptyJSONArrayFile();
        }
        return objectFromFile;
    }
    
    private static TimeSheetData tryJSONParserToTimeSheetData(JSONObject objectFromFile) {
        TimeSheetData newTimeSheetData = null;
        try {
            newTimeSheetData = JSONParser.toTimeSheetData(objectFromFile);
        } catch (Exception e) {
            System.out.println("Erreur dans le traitement du fichier JSON, Fin du programme. : (" + e + ")");
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
        validateAndLoadJSONObjectFromFile(inputFileName);
        employe.initFromFirstTimeSheet(tryJSONParserToTimeSheetData(objectFromFile));        
        Report report = new Report(employe);             
        JSONArray outputJSON = JSONParser.reportToJSONArray(report.generateReport(employe));        
        FileWriter.writeJSONFile(outputJSON, outputFileName);              
    }    
}