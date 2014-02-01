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
    private static final String[] REPLACE_KEYS = {"weekend1", "weekend2"};
    private static final String[] REPLACEMENT_KEYS = {"jour6", "jour7"};
    private static int replaceCount;

    public static TimeSheetData toTimeSheetData(JSONObject json) {

        TimeSheetData timeSheetData = new TimeSheetData();
        JSONArray jsonElements = json.names();

        for (int i = 0; i < jsonElements.size(); i++) {
            if (jsonElements.get(i).equals(NOM_CHAMP_NUMERO_EMPLOYE)) {
                timeSheetData.setEmployeId(json.getInt(jsonElements.getString(i)));
            } else {
                Day newDay = timeSheetData.addDay(jsonElements.getString(i));
                JSONArray day = json.getJSONArray(jsonElements.getString(i));

                if (!day.isEmpty()) {
                    for (int j = 0; j < day.size(); j++) {
                        JSONObject taskObj = day.getJSONObject(j);
                        // Lire le nom du jour ici
                        if (!taskObj.isNullObject()) {
                            newDay.addTask(taskObj.getInt(NOM_CHAMP_PROJET), taskObj.getInt(NOM_CHAMP_MINUTES));
                        }
                    }
                }
            }
        }
        return timeSheetData;
    }

    public static String format(String theString) {
        // creation instance de timesheet ici??
        return replaceKeys(theString);
    }

    public static String reportToJSONText(JSONObject JSONObj) {
        // est-ce plutot un array dans l'enonce???
        return JSONObj.toString();
    }

    /**
     * Retourne une nouvelle String apres avoir fait le remplacement des
     * elements du tableau REPLACE_KEYS par ceux de REPLACEMENT_KEYS dans
     * l'ordre.
     */
    private static String replaceKeys(String theString) {
        replaceCount = 0;
        for (String REPLACE_KEY : REPLACE_KEYS) {
            if (theString.contains(REPLACE_KEY)) {
                theString = theString.replace(REPLACE_KEY, REPLACEMENT_KEYS[replaceCount]);
                replaceCount++;
            }
        }
        return theString;
    }

}
