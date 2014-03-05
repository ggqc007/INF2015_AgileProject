# Conventions de codage du projet de l'équipe 8

----------


## Cadre ##
 ***INF2015 - Developpement de logiciel dans un environnement Agile*** - *Projet de session, UQAM, Hiver 2014*

## Membres de l'équipe 8 ##

- Christian Cornejo ***cornejo.christian@courrier.uqam.ca***
- Guillaume Gagnon ***gagnon.guillaume.5@courrier.uqam.ca***
- Khaled El-Sheikh ***khaled_elshikh@yahoo.com***
- Thomas Robert de Massy ***robert_de_massy.thomas@courrier.uqam.ca***


## Règles de codage ##
Les règles pour ce projet sont basées sur le "[Code Conventions For The Java Programming Language](http://www.oracle.com/technetwork/java/codeconv-138413.html)" à l'exeption des points suivants:


- La langue utilisée pour le nom des variables, méthodes, classe, commentaires est l'anglais *(precision)*.
- Il n'est pas nécessaire de mettre les {} si un if, while ou for comporte une seule instruction.
- Un maximum de 120 caracteres sur une ligne est accepté.
- Un maximum de 10 lignes pour une methode.


Voici un exemple du code attendu:
    
    public Day setDayByName(Day day) {  
        if (containsDay(day.getName()))
            throw new IllegalArgumentException("Day name " + day.getName() + " is already in the timesheet!"); 
        for (int i = 0; i < WEEKDAYS_NAMES.length; i++)
            if (WEEKDAYS_NAMES[i].equals(day.getName())) {
                days.set(i, day);                
                return day;
            }  
        throw new IllegalArgumentException("Day name " + day.getName() + " is not a valid day name!");
    }
    


----------
