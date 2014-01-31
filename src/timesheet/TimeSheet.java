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

        Employe employe = new Employe();

        employees = new ArrayList();

        employees.add(employe);
        
        
        
        // Input
        JSONText = readFile(); // eventuellement faire avec args
        JSONObject objTest = JSONObject.fromObject(JSONParser.format(JSONText));
        System.out.println("DEBUG format: " + objTest.names());

        // Output
        writeFile(JSONParser.reportToJSONText(objTest));
        System.out.println("DEBUG report: " + JSONParser.reportToJSONText(objTest));

    }

}
