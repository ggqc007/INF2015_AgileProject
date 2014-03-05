package timesheet;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import org.apache.commons.io.IOUtils;

public class FileReader {

    private static final String FILE_ENCODING = "UTF-8";
    private static String JSONStringFromFile = "";

    private static String loadFileIntoString(String filePath, String fileEncoding) 
            throws FileNotFoundException, IOException {
        return IOUtils.toString(new FileInputStream(filePath), fileEncoding);
    }

    public static String readJSONFile(String fileInPath) {
        try {
            JSONStringFromFile = FileReader.loadFileIntoString(fileInPath, FILE_ENCODING);
        } catch (FileNotFoundException e) {
            System.out.println(e);
        } catch (IOException e) {
            System.out.println(e);
        }
        return JSONStringFromFile;
    }

}
