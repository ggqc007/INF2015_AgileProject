/* INF2015 – Développement de logiciels dans un environnement Agile
 *
 * Projet de session - Hiver 2014
 *
 * Equipe 8 :
 *
 *   Christian Cornejo
 *   Khaled Elsheikh
 *   Guillaume Gagnon
 *   Thomas Robert de Massy
 *
 */
package timesheet;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

public class JSONParser {

    private static final String NOM_CHAMP_NUMERO_EMPLOYE = "numero_employe";
    private static final String NOM_CHAMP_PROJET = "projet";
    private static final String NOM_CHAMP_MINUTES = "minutes";

    public static TimeSheetData toTimeSheetData(JSONObject jsonObjectFromFile) {
        TimeSheetData timeSheetData = new TimeSheetData();
        JSONArray jsonElements = jsonObjectFromFile.names();

        for (int i = 0; i < jsonElements.size(); i++) {
            if (jsonElements.get(i).equals(NOM_CHAMP_NUMERO_EMPLOYE)) {
                timeSheetData.setEmployeId(jsonObjectFromFile.getInt(jsonElements.getString(i)));
            } else {
                //Day newDay = timeSheetData.addDay(jsonElements.getString(i));
                timeSheetData.setDayByName(new Day(jsonElements.getString(i)));
                Day test = timeSheetData.getDayByName(jsonElements.getString(i));
                
                JSONArray dayFromFile = jsonObjectFromFile.getJSONArray(jsonElements.getString(i));

                
                if (!dayFromFile.isEmpty()) {
                    for (int j = 0; j < dayFromFile.size(); j++) {
                        JSONObject taskObj = dayFromFile.getJSONObject(j);
                        // Lire le nom du jour ici
                        if (!taskObj.isNullObject()) {
                            //newDay.addTask(taskObj.getInt(NOM_CHAMP_PROJET), taskObj.getInt(NOM_CHAMP_MINUTES));
                            test.addTask(taskObj.getInt(NOM_CHAMP_PROJET), taskObj.getInt(NOM_CHAMP_MINUTES));
                        }
                    }
                }
            }
        }
        return timeSheetData;
    }

    public static String reportToJSONText(JSONObject JSONObj) {
        // est-ce plutot un array dans l'enonce???
        return JSONObj.toString();
    }
}
