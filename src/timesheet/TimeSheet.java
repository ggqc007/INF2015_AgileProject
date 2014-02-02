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
            FileWriter.writeStringIntoFile(jsonArray.toString(), FILE_OUT_PATH, FILE_ENCODING);
        } catch (IOException e) {
            System.out.println(e);
        }
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        String inputFileName;
        String ouputFileName;
        Employe employe = new Employe();

        employees = new ArrayList();
        employees.add(employe);

        // Vérification des arguments de ligne de commande (TURNED OFF WHILE TESTING!)
        //verifyCmdArgs(args);
        
        // Lecture des arguments
        //inputFileName = args[0];
        //ouputFileName = args[1];

        
        // TODO: ATTENTION!!!
        // Guillaume, je n'ai pas modifier ton code pour ajouter les noms de fichiers
        // passés en argument!
        // Input test
        JSONText = readFile(); // eventuellement faire avec args
        JSONObject objectFromFile = JSONObject.fromObject(JSONText);
        TimeSheetData testToTimeSheetData = JSONParser.toTimeSheetData(objectFromFile);
        System.out.println("DEBUG toTimeSheetData: " + testToTimeSheetData.getDays());
        
        // Output test
        String[] testReportToJSONArray = {"Message Erreur 1", "Message Erreur 2", "Message Erreur 3", ",Message Erreur 4", "Message Erreur 5"};
        System.out.println(JSONParser.reportToJSONArray(testReportToJSONArray));
        writeFile(JSONParser.reportToJSONArray(testReportToJSONArray));
        
    }

    public void setUserId(int id) {
        // TO DO: Thomas, est-ce que tu peux définir ces méthodes, elles sont appelées dans le main. J'ai mis du code de manière proivoir pour pouvoir compiler. Merci! CC
    }

    public Day addDay(String str) {
        // TO DO: Thomas, est-ce que tu peux définir ces méthodes, elles sont appelées dans le main. J'ai mis du code de manière proivoir pour pouvoir compiler. Merci! CC
        Day d = new Day();
        return d;
    }

}
