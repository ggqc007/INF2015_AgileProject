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

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

public class TimeSheet {

    private static List<Employe> employees;
    public static final String FILE_ENCODING = "UTF-8";
    public static final String FILE_IN_PATH = "json/timesheet.json";
    public static final String FILE_OUT_PATH = "json/fichier_out.json";
    public static String JSONText;

    public static void verifyCmdArgs(String[] args) {
        if (args.length != 2) {
            System.out.println("Utilisation: TimeSheet.jar input.json output.json");
            System.exit(1);
        }
    }

    public static String readFile() {
        try {
            JSONText = FileReader.loadFileIntoString(FILE_IN_PATH, FILE_ENCODING);
        } catch (FileNotFoundException e) {
            System.out.println(e);
        } catch (IOException e) {
            System.out.println(e);
        }
        return JSONText;
    }

    public static void writeFile(JSONArray jsonArray) {
        try {
            FileWriter.writeStringIntoFile(jsonArray.toString(2), FILE_OUT_PATH, FILE_ENCODING);
        } catch (IOException e) {
            System.out.println(e);
        }
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // Vérification des arguments de ligne de commande (TURNED OFF WHILE TESTING!)
        // String inputFileName = args[0];
        // String ouputFileName = args[1];
        // verifyCmdArgs(args);
        
        Employe employe = new Employe();
        employees = new ArrayList();
        employees.add(employe);


        // Input test GG
        JSONText = readFile(); //TODO: eventuellement faire avec args
        JSONObject objectFromFile = JSONObject.fromObject(JSONText);
        TimeSheetData testToTimeSheetData = JSONParser.toTimeSheetData(objectFromFile);
        System.out.println("DEBUG JSONParser.toTimeSheetData: " + testToTimeSheetData.getDays());
        
        // Output test GG
        List testReportToJSONArray = new ArrayList();
        testReportToJSONArray.add("Bonjour");
        testReportToJSONArray.add("Mon");
        testReportToJSONArray.add("Ami");
        System.out.println("DEBUG JSONParser.ReportToJSONArray: \n"+ JSONParser.reportToJSONArray(testReportToJSONArray).toString(2));
        writeFile(JSONParser.reportToJSONArray(testReportToJSONArray));
        
    }

}
