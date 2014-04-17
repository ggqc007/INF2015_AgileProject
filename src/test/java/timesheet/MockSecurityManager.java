package timesheet;

import java.security.Permission;

public class MockSecurityManager extends SecurityManager {

    @Override
    public void checkExit(final int status) {
        throw new SecurityException("System.exit(" + status + ") has been used");
    }

    @Override
    public void checkPermission(final Permission perm) {
        // allow full control
    }

    @Override
    public void checkPermission(final Permission perm, final Object context) {
        // allow full control
    }
}
