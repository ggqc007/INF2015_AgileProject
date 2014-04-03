package timesheet;

import net.sf.json.JSONArray;

public class MockOutputStreamWriterWrapper extends OutputStreamWriterWrapper {
    
    private static final int JSON_OUTPUT_IDENTATION = 2;
    private String content = "";
    
    public MockOutputStreamWriterWrapper() {
        super(null);
    }
    
    @Override
    public void writeJSONFile(JSONArray jsonArray) {
        this.content += jsonArray.toString(JSON_OUTPUT_IDENTATION);
    }
    
    public String getContent() {
        return this.content;
    }
}

