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

import java.util.List;
import java.util.Arrays;
import java.util.ArrayList;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;


public class TimeSheet {

    public static final String FILE_IN_PATH = "json/timesheet.json";
    public static final String FILE_OUT_PATH = "json/fichier_out.json";
    public static String JSONText;
    private static List<Employe> employes;
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

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {                
        // Vérification des arguments de ligne de commande (TURNED OFF WHILE TESTING!)
        // verifyCmdArgs(args);
        
        /* QUELQUES IDEES POUR LA STRUCTURE DU MAIN
        
           - On peut sortir les variables inputFileName et ouputFileName à l'extérieur du main :
        
             private String inputFileName;
             private String ouputFileName;
        
             et les setter à l'intérieur de verifyCmdArgs()
        
           - Comme c'est là, une fois les instructions de DEBUG retirées, le main va
             contenir 7 lignes (plus verifyCmdArgs(args);)
        
             Soit :
        
                Employe employe = new Employe();
                employees = new ArrayList(Arrays.asList(employe));
                JSONObject objectFromFile = JSONObject.fromObject(readFile());
                employe.initFromFirstTimeSheet(JSONParser.toTimeSheetData(objectFromFile));        
                Report report = new Report(employe);             
                JSONArray outputJSON = JSONParser.reportToJSONArray(report.generate(employe));        
                writeFile(outputJSON);
        
           - Mais on peut passer à 5 (plus verifyCmdArgs(args);) car pour l'instant on n'a pas 
             besoin de la liste d'employé et si on rajoute la ligne suivante dans Report.generate() :
        
             this.employe = employe; // Placé juste après la ligne rules.setEmploye(employe);
        
             On pourrait remplacer :
        
                Report report = new Report(employe);             
                JSONArray outputJSON = JSONParser.reportToJSONArray(report.generate(employe));
        
             Par 
                JSONArray outputJSON = JSONParser.reportToJSONArray(Report.generate(employe));   
        
             Donc ça donnerait :
        
                Employe employe = new Employe();
                JSONObject objectFromFile = JSONObject.fromObject(readFile());
                employe.initFromFirstTimeSheet(JSONParser.toTimeSheetData(objectFromFile));         
                JSONArray outputJSON = JSONParser.reportToJSONArray(Report.generate(employe));        
                writeFile(outputJSON);
        
           - On pourrait même aller jusqu'à 4 lignes (plus verifyCmdArgs(args);) en ajoutant la 
             méthode suivante dans JSONParser
        
             public TimeSheetData readTimeSheetData(String jsonFilename) {
                JSONObject objectFromFile = JSONObject.fromObject(readFile(jsonFilename));
                return JSONParser.toTimeSheetData(objectFromFile);
             }
        
             Donc ça donnerait :
        
                Employe employe = new Employe();
                employe.initFromFirstTimeSheet(JSONParser.readTimeSheetData(jsonFilename));         
                JSONArray outputJSON = JSONParser.reportToJSONArray(Report.generate(employe));        
                writeFile(outputJSON); 
        
             On pourrait même aller jusqu'à 3 mais la dernière ligne devient un peu complexe, il
             ne faut sûrement pas exagérer :
        
                Employe employe = new Employe();
                employe.initFromFirstTimeSheet(JSONParser.readTimeSheetData(jsonFilename)); 
                writeFile(JSONParser.reportToJSONArray(Report.generate(employe)));        
 
           Ce qui nous donnerait plus de marge de manoeuvre pour la suite du projet :-)
        
        */
        
        Employe employe = new Employe();

        // TEST THOMAS                 
        
        //employees = new ArrayList();
        //employees.add(employe);
        
        // POUR FAIRE LE TOUT EN UNE LIGNE
        employes = new ArrayList(Arrays.asList(employe));

        // ON PEUT AUSSI FAIRE LES 2 LIGNES SUIVANTES EN UNE LIGNE :
        //
        // JSONText = readFile(); //TODO: eventuellement faire avec args
        // JSONObject objectFromFile = JSONObject.fromObject(JSONText);
        //
        JSONObject objectFromFile = JSONObject.fromObject(FileReader.readJSONFile(FILE_IN_PATH)); //TODO: eventuellement faire avec args
        
        // FIN TEST THOMAS
        
        // Input test GG
        /*
        JSONText = FileReader.readJSONFile(FILE_IN_PATH); //TODO: eventuellement faire avec args
        JSONObject objectFromFile22 = JSONObject.fromObject(JSONText);
        TimeSheetData testToTimeSheetData = JSONParser.toTimeSheetData(objectFromFile22);
        System.out.println("DEBUG JSONParser.toTimeSheetData: " + testToTimeSheetData.getDays());
        */
        
        // Output test GG
        /*
        List testReportToJSONArray = new ArrayList();
        testReportToJSONArray.add("Bonjour");
        testReportToJSONArray.add("Mon");
        testReportToJSONArray.add("Ami");
        System.out.println("DEBUG JSONParser.ReportToJSONArray: \n"+ JSONParser.reportToJSONArray(testReportToJSONArray).toString(2));
        FileWriter.writeJSONFile(JSONParser.reportToJSONArray(testReportToJSONArray), FILE_OUT_PATH);
        */
        
        
     
        
        // C'EST CE QUI MANQUAIT POUR METTRE LE TIMESHEET DANS L'EMPLOYE    
        //employe.setTimeSheet(0, JSONParser.toTimeSheetData(objectFromFile)); 
        
        // TEMPORAIRE JE VAIS TROUVER UNE MEILLEURE SOLUTION DEMAIN
        //employe.setId(employe.getTimeSheet(0).getEmployeId());
        
        // FINALEMENT J'AI AJOUTE UNE FAÇON PLUS ELEGANTE AUJOURD'HUI
        // Set le ID de l'employé et ajoute le premier timesheet en même temps
        employe.initFromFirstTimeSheet(JSONParser.toTimeSheetData(objectFromFile));
        
        Report report = new Report(employe);             
        JSONArray outputJSON = JSONParser.reportToJSONArray(report.generate(employe));        
        FileWriter.writeJSONFile(outputJSON, FILE_OUT_PATH);
        
        // AFFICHE LES INFORMATIONS DE DEBUG           
        System.out.println("\nDEBUG JSON Input : " + objectFromFile.toString(2));        
        System.out.println("\nDEBUG Parsed TimeSheetData : " + employe.getTimeSheet(0));

        // AFFICHE TYPE EMPLOYE
        System.out.print("\nDEBUG Employe ID " + employe.getId());
        if (employe.isAdmin())
            System.out.println(" is an ADMIN employe");
        else
            System.out.println(" is a NORMAL employe");
        
        // INFORMATIONS DEBUG DES TEMPS
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

        // MIN OFFICE DAY HOURS
        System.out.printf("      Office day min       : " + rules.getMinOfficeDailyMinutes() + "m(%.0fh)\n", rules.getMinOfficeDailyMinutes()/60.0);

        // MIN/MAX WEEK HOURS
        System.out.printf("      Office week min/max  : " + rules.getMinOfficeWeekMinutes() + "m(%.0fh)/", rules.getMinOfficeWeekMinutes()/60.0);
        System.out.printf(rules.getMaxOfficeWeekMinutes() + "m(%.0fh)\n", rules.getMaxOfficeWeekMinutes()/60.0);

        // MAX REMOTE HOURS
        if (rules.getMaxRemoteWeekMinutes() != 0)
            System.out.printf("      Remote week max      : " + rules.getMaxRemoteWeekMinutes() + "m(%.0fh)\n", rules.getMaxRemoteWeekMinutes()/60.0);
        else        
            System.out.println("      Remote week max      : N/A");               
        
        // TOTAL BY WEEK TOTAL/OFFICE/REMOTE
        hours = rules.getTotalWeekMinutes() / 60;
        minutes = rules.getTotalWeekMinutes() % 60;
        System.out.printf("      Total by week        : " + rules.getTotalWeekMinutes() + "m(%d:%02dh)\n", hours, minutes);
        hours = rules.getTotalOfficeWeekMinutes() / 60;
        minutes = rules.getTotalOfficeWeekMinutes() % 60;        
        System.out.printf("      Total office by week : " + rules.getTotalOfficeWeekMinutes() + "m(%d:%02dh)\n", hours, minutes);        
        hours = rules.getTotalRemoteWeekMinutes() / 60;
        minutes = rules.getTotalRemoteWeekMinutes() % 60;        
        System.out.printf("      Total remote by week : " + rules.getTotalRemoteWeekMinutes() +"m(%d:%02dh)\n", hours, minutes);        
        
        // TOTAL OFFICE BY DAY
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
        
        // TOTAL REMOTE BY DAY
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
              
        
        // JSON OUTPUT
        System.out.println("\nDEBUG JSON Output : " + outputJSON.toString(2)+"\n");  
        
        // FIN TEST Thomas
        
    }

}
