package timesheet;

import java.io.IOException;
import java.io.OutputStreamWriter;
import net.sf.json.JSONArray;

public class OutputStreamWriterWrapper {

    private static final int JSON_OUTPUT_IDENTATION = 2;
    private final OutputStreamWriter fileWriter;
    
    public OutputStreamWriterWrapper(OutputStreamWriter fileWriter) {
        this.fileWriter = fileWriter;
    }

    public void writeJSONFile(JSONArray jsonArray) {
        try {
            fileWriter.write(jsonArray.toString(JSON_OUTPUT_IDENTATION));
            fileWriter.close();
        } catch (IOException e) {
            System.out.println(e);
        }
    }
}