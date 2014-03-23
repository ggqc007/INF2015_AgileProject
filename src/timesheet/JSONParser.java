package timesheet;

import java.util.List;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

public class JSONParser {

    private static final String NOM_CHAMP_NUMERO_EMPLOYE = "numero_employe";
    private static final String NOM_CHAMP_PROJET = "projet";
    private static final String NOM_CHAMP_MINUTES = "minutes";

    public static TimeSheetData toTimeSheetData(JSONObject jsonObjectFromFile) throws Exception {
        TimeSheetData timeSheetData = new TimeSheetData();
        employeIdToTimeSheetData(timeSheetData, jsonObjectFromFile);
        createDaysWithTasksInTimeSheetData(timeSheetData, jsonObjectFromFile);
        if (!timeSheetData.hasValidWeek())
            throw new Exception("Semaine invalide! Ne comporte pas 7 jours...");
        return timeSheetData;
    }

    public static JSONArray reportToJSONArray(List errorReport) {
        JSONArray jsonErrorReport = new JSONArray();
        for (int i = 0; i < errorReport.size(); i++)
            jsonErrorReport.add(errorReport.get(i));
        return jsonErrorReport;
    }

    protected static void employeIdToTimeSheetData(TimeSheetData timeSheetData, JSONObject jsonObjectFromFile) {
        JSONArray jsonKeysFromFile = jsonObjectFromFile.names();
        for (int i = 0; i < jsonKeysFromFile.size(); i++)
            if (jsonKeysFromFile.get(i).equals(NOM_CHAMP_NUMERO_EMPLOYE))
                timeSheetData.setEmployeId(jsonObjectFromFile.getInt(jsonKeysFromFile.getString(i))); 
    }

    protected static void createDaysWithTasksInTimeSheetData(TimeSheetData timeSheetData, 
        JSONObject jsonObjectFromFile) {
        JSONArray jsonKeysFromFile = jsonObjectFromFile.names();
        for (int i = 0; i < jsonKeysFromFile.size(); i++)
            if (!jsonKeysFromFile.get(i).equals(NOM_CHAMP_NUMERO_EMPLOYE)) {
                Day aDay = timeSheetData.setDayByName(new Day(jsonKeysFromFile.getString(i)));
                JSONArray taskForADay = jsonObjectFromFile.getJSONArray(jsonKeysFromFile.getString(i));
                tasksToADayIfAny(aDay, taskForADay);
            }
    }
    
    protected static void tasksToADayIfAny(Day aDay, JSONArray taskForADay) {
        if (!taskForADay.isEmpty())
            for (int i = 0; i < taskForADay.size(); i++) {
                JSONObject taskObj = taskForADay.getJSONObject(i);
                if (!taskObj.isNullObject()) 
                    aDay.addTask(taskObj.getInt(NOM_CHAMP_PROJET), taskObj.getInt(NOM_CHAMP_MINUTES));
            }
    }
}
