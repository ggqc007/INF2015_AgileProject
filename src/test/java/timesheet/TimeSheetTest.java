package timesheet;

import org.junit.AfterClass;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.BeforeClass;

public class TimeSheetTest {

    @BeforeClass
    public static void initMockSecurityManager() {
        System.setSecurityManager(new MockSecurityManager());
    }

    @AfterClass
    public static void destroyMockSecurityManager() {
        System.setSecurityManager(null);
    }

    @Test(expected = SecurityException.class)
    public void testVerifyCmdArgsFailNoArgs() {
        String[] args = new String[0];
        TimeSheet.verifyCmdArgs(args);
    }

    @Test(expected = SecurityException.class)
    public void testVerifyCmdArgsFailTooManyArgs() {
        String[] args = new String[3];
        TimeSheet.verifyCmdArgs(args);
    }
    
    public void testVerifyCmdArgs() {
        String[] receivedArgs = new String[2];
        String[] args = new String[2];
        args[0] = "value1";
        args[1] = "value2";
        TimeSheet.verifyCmdArgs(args);
        receivedArgs[0] = TimeSheet.inputFileName;
        receivedArgs[1] = TimeSheet.outputFileName;
        assertEquals(args.toString(), receivedArgs.toString());
    }
}
