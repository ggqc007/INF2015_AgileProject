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
import org.apache.commons.io.IOUtils;

public class FileWriter {

    public static void writeStringIntoFile(String data, String unFile, String encoding) throws IOException {
        IOUtils.write(data, new FileOutputStream(unFile), encoding);
    }

}