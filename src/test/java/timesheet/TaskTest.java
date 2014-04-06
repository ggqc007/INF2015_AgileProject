/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package timesheet;

import org.junit.Test;
import static org.junit.Assert.*;

public class TaskTest {    
    private static final int NORMAL_TASK_ID = 900;
    private static final int OFFICE_TASK_ID = 900;     
    private static final int REMOTE_TASK_ID = 901;    
    private static final int SICK_LEAVE_TASK_ID = 999;
    private static final int SICK_LEAVE_TIME = 480;
    private static final int PUBLIC_HOLIDAY_TASK_ID = 998;    
    private static final int PUBLIC_HOLIDAY_TIME = 480;  
    private static final int HOLIDAY_TASK_ID = 997;    
    private static final int HOLIDAY_TIME = 480;
    private static final int PARENTAL_HOLIDAY_TASK_ID = 996;    
    private static final int PARENTAL_HOLIDAY_TIME = 480;     

    @Test
    public void testGetTime() {
        Task task = new Task (OFFICE_TASK_ID, 300);
        assertEquals (task.getTime(), 300);        
    } 
    
    @Test
    public void testIsOfficeTaskPass() {
        Task task = new Task (OFFICE_TASK_ID, 300);
        assertTrue (task.isOfficeTask());        
    }
    
    @Test
    public void testIsOfficeTaskFail() {
        Task task = new Task (OFFICE_TASK_ID + 1, 300);
        assertFalse (task.isOfficeTask());        
    } 
    
    @Test
    public void testIsRemoteTaskPass() {
        Task task = new Task (REMOTE_TASK_ID, 300);
        assertTrue (task.isRemoteTask());        
    }
    
    @Test
    public void testIsRemoteTaskFail() {
        Task task = new Task (REMOTE_TASK_ID - 1, 300);
        assertFalse (task.isRemoteTask());        
    }    

    @Test
    public void testIsWorkTaskPass() {
        Task task = new Task (NORMAL_TASK_ID, 300);
        assertTrue (task.isWorkTask());
    }
    
    @Test
    public void testIsWorkTaskFail() {
        Task task = new Task (SICK_LEAVE_TASK_ID, SICK_LEAVE_TIME);
        assertFalse (task.isWorkTask());
    }

    @Test
    public void testIsPublicHolidayTaskPass() {
        Task task = new Task (PUBLIC_HOLIDAY_TASK_ID, PUBLIC_HOLIDAY_TIME);
        assertFalse (task.isWorkTask());        
    }
    
    @Test
    public void testIsPublicHolidayTaskFail() {
        Task task = new Task (HOLIDAY_TASK_ID, HOLIDAY_TIME);
        assertFalse (task.isWorkTask());        
    }    

    @Test
    public void testIsHolidayTaskPass() {
        Task task = new Task (HOLIDAY_TASK_ID, HOLIDAY_TIME);
        assertTrue (task.isHolidayTask());          
    }
    
    @Test
    public void testIsHolidayTaskPassFail() {
        Task task = new Task (PUBLIC_HOLIDAY_TASK_ID, PUBLIC_HOLIDAY_TIME);
        assertFalse (task.isHolidayTask());          
    }    

    @Test
    public void testIsParentalHolidayTaskPass() {
        Task task = new Task (PARENTAL_HOLIDAY_TASK_ID, PARENTAL_HOLIDAY_TIME);
        assertTrue (task.isParentalHolidayTask());         
    }
    
    @Test
    public void testIsParentalHolidayTaskFail() {
        Task task = new Task (HOLIDAY_TASK_ID, HOLIDAY_TIME);
        assertFalse (task.isParentalHolidayTask());         
    }    

    @Test
    public void testIsSickLeaveTaskPass() {
        Task task = new Task (SICK_LEAVE_TASK_ID, SICK_LEAVE_TIME);
        assertTrue (task.isSickLeaveTask());          
    }
    
    @Test
    public void testIsSickLeaveTaskFail() {
        Task task = new Task (PARENTAL_HOLIDAY_TASK_ID, PARENTAL_HOLIDAY_TIME);
        assertFalse (task.isSickLeaveTask());          
    }    

    @Test
    public void testHasMininumMinutesAmountForTaskPass() {
        Task task = new Task (PARENTAL_HOLIDAY_TASK_ID, 1);
        assertTrue (task.hasMininumMinutesAmountForTask());         
    } 
    
    @Test
    public void testHasMininumMinutesAmountForTaskFail() {
        Task task = new Task (PARENTAL_HOLIDAY_TASK_ID, 0);
        assertFalse (task.hasMininumMinutesAmountForTask());         
    }      
}
