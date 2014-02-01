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
import net.sf.json.JSONObject;

public class TimeSheet {

    private static List<Employe> employees;
    public static final String FILE_ENCODING = "UTF-8";
    public static final String FILE_IN_PATH = "fichier.json";
    public static final String FILE_OUT_PATH = "fichier_out.json";
    public static String JSONText;

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

    public static void writeFile(String theString) {
        try {
            FileWriter.writeStringIntoFile(theString, FILE_OUT_PATH, FILE_ENCODING);
        } catch (IOException e) {
            System.out.println(e);
        }
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {

        String  inputFileName;
        String  ouputFileName;
        Employe employe = new Employe();

        employees = new ArrayList();

        employees.add(employe);
        
        // Vérification des arguments de ligne de commande
        if ( args.length != 2 ) {
            
            System.out.println("Utilisation: TimeSheet.jar input.json output.json");
            
            System.exit(1);
            
        }
        
        // Lecture des arguments
        inputFileName = args[0];
        ouputFileName = args[1];
        
        // ATTENTION!!!
        // Guillaume, je n'ai pas modifier ton code pour ajouter les noms de fichiers
        // passés en argument!
        
        // Input
        JSONText = readFile(); // eventuellement faire avec args
        JSONObject objTest = JSONObject.fromObject(JSONParser.format(JSONText));
        System.out.println("DEBUG format: " + objTest.names());

        // Output
        writeFile(JSONParser.reportToJSONText(objTest));
        System.out.println("DEBUG report: " + JSONParser.reportToJSONText(objTest));

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
