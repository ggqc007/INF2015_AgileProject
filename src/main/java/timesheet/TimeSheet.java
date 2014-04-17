package timesheet;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

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
        OutputStreamWriterWrapper writer = initializeOutputStreamWriterWrapper();
        writer.writeJSONFile(outputJSON);
    }      
}