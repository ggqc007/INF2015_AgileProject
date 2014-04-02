package timesheet;

//import java.io.FileOutputStream;
//import java.io.FileWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import net.sf.json.JSONArray;
//import org.apache.commons.io.IOUtils;

public class FileWriterWrapper {

    //private static final String FILE_ENCODING = "UTF-8";
    private static final int JSON_OUTPUT_IDENTATION = 2;
    OutputStreamWriter fileWriter;
    
    public FileWriterWrapper(OutputStreamWriter fileWriter) {
        this.fileWriter = fileWriter;
    }

    public void writeJSONFile(JSONArray jsonArray) {
        try {
            fileWriter.write(jsonArray.toString(JSON_OUTPUT_IDENTATION));
            
            //FileWriterWrapper.writeStringIntoFile(jsonArray.toString(JSON_OUTPUT_IDENTATION), fileOutPath, FILE_ENCODING);
        } catch (IOException e) {
            System.out.println(e);
        }
    }

    //protected static void writeStringIntoFile(String data, String unFile, String encoding) throws IOException {
    //    IOUtils.write(data, new FileOutputStream(unFile), encoding);
    //}
}