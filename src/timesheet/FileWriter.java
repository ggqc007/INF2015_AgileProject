package timesheet;

import java.io.FileOutputStream;
import java.io.IOException;
import net.sf.json.JSONArray;
import org.apache.commons.io.IOUtils;

public class FileWriter {
    private static final String FILE_ENCODING = "UTF-8";
    private static final int JSON_OUTPUT_IDENTATION = 2;

    private static void writeStringIntoFile(String data, String unFile, String encoding) throws IOException {
        IOUtils.write(data, new FileOutputStream(unFile), encoding);
    }

    public static void writeJSONFile(JSONArray jsonArray, String fileOutPath) {
        try {
            FileWriter.writeStringIntoFile(jsonArray.toString(JSON_OUTPUT_IDENTATION), fileOutPath, FILE_ENCODING);
        } catch (IOException e) {
            System.out.println(e);
        }
    }

}
