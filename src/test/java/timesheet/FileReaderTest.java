package timesheet;

import java.io.FileNotFoundException;
import org.junit.Test;

public class FileReaderTest {
    
    public FileReaderTest() {
    }

    @Test (expected = FileNotFoundException.class)
    public void testInvalidFilePath() throws Exception {
        String filePath = "FakeMissingFile.txt";
        String fileEncoding = "UTF-8";
        String result = FileReader.loadFileIntoString(filePath, fileEncoding);
    }
}
