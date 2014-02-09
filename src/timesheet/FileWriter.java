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

import java.io.FileOutputStream;
import java.io.IOException;
import net.sf.json.JSONArray;
import org.apache.commons.io.IOUtils;

public class FileWriter {
    private static final String FILE_ENCODING = "UTF-8";

    private static void writeStringIntoFile(String data, String unFile, String encoding) throws IOException {
        IOUtils.write(data, new FileOutputStream(unFile), encoding);
    }

    public static void writeFile(JSONArray jsonArray, String fileOutPath) {
        try {
            FileWriter.writeStringIntoFile(jsonArray.toString(2), fileOutPath, FILE_ENCODING);
        } catch (IOException e) {
            System.out.println(e);
        }
    }

}
