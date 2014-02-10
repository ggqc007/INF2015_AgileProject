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

public class TimeSheet {

    private static String inputFileName;
    private static String ouputFileName;
    
    public static void verifyCmdArgs(String[] args) {
        if (args.length != 2) {
            System.out.println("Utilisation: TimeSheet.jar input.json output.json");
            System.exit(1);
        }
        inputFileName = args[0];
        ouputFileName = args[1];        
    }

    public static void main(String[] args) {                
        verifyCmdArgs(args);                
        Employe employe = new Employe();
        JSONObject objectFromFile = JSONObject.fromObject(FileReader.readJSONFile(inputFileName));
        employe.initFromFirstTimeSheet(JSONParser.toTimeSheetData(objectFromFile));        
        Report report = new Report(employe);             
        JSONArray outputJSON = JSONParser.reportToJSONArray(report.generate(employe));        
        FileWriter.writeJSONFile(outputJSON, ouputFileName);   
        
        // Déboguage à retirer plus tard
        debug(employe, objectFromFile, outputJSON);   
        
        /* Nous pourions avec quelques petites modifications reduire le main à ceci :
        
            verifyCmdArgs(args);
            Employe employe = new Employe();
            employe.initFromFirstTimeSheet(JSONParser.readTimeSheetData(inputFileName));
            FileWriter.writeJSONFile(JSONParser.reportToJSONArray(Report.generate(employe), ouputFileName)); 
        
        */
    }
    
    /**
     * Affichage temporaire d'informations de déboguage
     * 
     * @param employe
     * @param objectFromFile
     * @param outputJSON 
     */
    private static void debug (Employe employe, JSONObject objectFromFile, JSONArray outputJSON) { 
        System.out.println("\nDEBUG JSON Input filename : " + inputFileName);
        System.out.println("\nDEBUG JSON Input data : " + objectFromFile.toString(2));        
        System.out.println("\nDEBUG Parsed TimeSheetData : " + employe.getTimeSheet(0));

        System.out.print("\nDEBUG Employe ID " + employe.getId());
        if (employe.isAdmin())
            System.out.println(" is an ADMIN employe");
        else
            System.out.println(" is a NORMAL employe");
        
        Day day;        
        Rules rules;   
        int hours, minutes;
        if (employe.isAdmin()) {
            rules = new RulesAdmins();
        } else {
            rules = new RulesEmployes();
        }        
        rules.setEmploye(employe);
        rules.prepData();    
        
        System.out.println("\nDEBUG Working hours :");
        System.out.printf("      Office day min       : " + rules.getMinOfficeDailyMinutes() + "m(%.0fh)\n", rules.getMinOfficeDailyMinutes()/60.0);

        System.out.printf("      Office week min/max  : " + rules.getMinOfficeWeekMinutes() + "m(%.0fh)/", rules.getMinOfficeWeekMinutes()/60.0);
        System.out.printf(rules.getMaxOfficeWeekMinutes() + "m(%.0fh)\n", rules.getMaxOfficeWeekMinutes()/60.0);

        if (rules.getMaxRemoteWeekMinutes() != 0)
            System.out.printf("      Remote week max      : " + rules.getMaxRemoteWeekMinutes() + "m(%.0fh)\n", rules.getMaxRemoteWeekMinutes()/60.0);
        else        
            System.out.println("      Remote week max      : N/A");               

        hours = rules.getTotalWeekMinutes() / 60;
        minutes = rules.getTotalWeekMinutes() % 60;
        System.out.printf("      Total by week        : " + rules.getTotalWeekMinutes() + "m(%d:%02dh)\n", hours, minutes);
        hours = rules.getTotalOfficeWeekMinutes() / 60;
        minutes = rules.getTotalOfficeWeekMinutes() % 60;        
        System.out.printf("      Total office by week : " + rules.getTotalOfficeWeekMinutes() + "m(%d:%02dh)\n", hours, minutes);        
        hours = rules.getTotalRemoteWeekMinutes() / 60;
        minutes = rules.getTotalRemoteWeekMinutes() % 60;        
        System.out.printf("      Total remote by week : " + rules.getTotalRemoteWeekMinutes() +"m(%d:%02dh)\n", hours, minutes);        

        System.out.printf("      Total office by day  : ");               
        for(int i = 0; i < employe.getTimeSheet(0).getDaysNum()-1; i++) {
            day = employe.getTimeSheet(0).getDay(i);
            hours = rules.getTotalOfficeMinutesByDay(day) / 60;
            minutes = rules.getTotalOfficeMinutesByDay(day) % 60;            
            System.out.printf(rules.getTotalOfficeMinutesByDay(day) + "m(%d:%02dh),", hours, minutes);
        }
        day = employe.getTimeSheet(0).getDay(employe.getTimeSheet(0).getDaysNum()-1);
        hours = rules.getTotalOfficeMinutesByDay(day) / 60;
        minutes = rules.getTotalOfficeMinutesByDay(day) % 60;        
        System.out.printf(rules.getTotalOfficeMinutesByDay(day) + "m(%d:%02dh)\n", hours, minutes);

        System.out.printf("      Total remote by day  : ");       
        for(int i = 0; i < employe.getTimeSheet(0).getDaysNum()-1; i++) {
            day = employe.getTimeSheet(0).getDay(i);
            hours = rules.getTotalRemoteMinutesByDay(day) / 60;
            minutes = rules.getTotalRemoteMinutesByDay(day) % 60;            
            System.out.printf(rules.getTotalRemoteMinutesByDay(day) + "m(%d:%02dh),", hours, minutes);
        }
        day = employe.getTimeSheet(0).getDay(employe.getTimeSheet(0).getDaysNum()-1);
        hours = rules.getTotalRemoteMinutesByDay(day) / 60;
        minutes = rules.getTotalRemoteMinutesByDay(day) % 60;        
        System.out.printf(rules.getTotalRemoteMinutesByDay(day) + "m(%d:%02dh)\n", hours, minutes);

        System.out.println("\nDEBUG JSON Output data : " + outputJSON.toString(2)); 
        System.out.println("\nDEBUG JSON Data succesfully writen to : " + ouputFileName +"\n");                  
    }
}
