public class MarvinPrangHomework5 {
    
    public static void main(String[] args) {

    }

/*
 *  Die Coding Aufgaben werden diesmal in eigenen Unterordneren gelöst (.../MarvinPrangHomework5/ExerciseX) damit diese Datei nicht zu voll wird und übersichtlicher ist.
 *  Erklärungen und antworten mit Text werden weiterhin in dieser Datei beantwortet.
 */

/*
 * 1)   - Output:
 *          [Student{name='Tom'}, Student{name='Alice'}]
 *          [Student{name='Tom'}, Student{name='Alice'}]
 *          [Student{name='Tim'}, Student{name='Alice'}]
 *          [Student{name='Tim'}, Student{name='Alice'}]
 * 
 *          Das Problem besteht beim kopieren des Arrays. Zwar wird der Array korrekt kopiert, so könnte students ein neues Element bekommen was in students2 nicht enthalten ist.
 *          Jedoch wird beim Kopieren vom Array nicht der Student kopiert sondern nur die Referenz zu den Studenten, so zeigen in den beiden Arrays auf die selben zwei Studenten.
 *          Um das zu beheben muss das Array anderes kopiert werden so dass die Student neu erstellt werden und so die Referenzen nicht auf den selben Studenten zeigen.
 */


/*
 * 2)   - myProf.greet gibt aus:
 *          Hallo! Ich bin Andreas Vogelsang an der Universität zu Köln am Lehrstuhl Software & Systems Engineering
 *        Das ist weil myProf den dynamischen Type Professor hat und somit auf die greet Methode von Professor zugreift, weil Professor diese überschreibt, obwohl myProf eigentlich
 *        vom statischen Type Person ist.
 */

/*
 * 3)   - Die Benutzung von Generics erlaubt es verschiedene Typen miteinander zu vergleichen, sowie auch Subtypen. Durch die Nutzung von Generics kann es passieren, dass der
 *        Code nicht mehr Type-safe ist wodurch der Compiler Warnungen auswirft.
 */

/*
 *  4)   - Beide Versionen können ihre Berechtigung haben, in diesem Beispiel würde ich mich für die erste Version entscheiden und der Error im Calculator abfangen.
 *         Aus dem Grund, da ich nicht möchte dass mein Programm aufhört zu laufen wegen einer fälschlicherweise übergebenen 0. So wird eine 0 als Ergebnis zurückgegeben
 *         mit welcher das Programm weiterarbeiten kann. Auch wenn ich den Calculator potenziell häufiger im Code verarbeite, ist es weniger aufwändig jedes mal beim Benutzer ein
 *         eigenes Try and Catch zu schreiben.
 * 
 *         An kritischen Stellen, wo der Code von der korrekten Eingabe/Berechnung/Verarbeitung abhängt würde ich die Fehler direkt abfangen und nicht in der Methode.
 *         Oder auch wenn die Methoden nicht anders im Code verwendet werden oder mehrer potentziell fehleranfällige Methoden hintereinander aufgerufen werden.
 */

/*
 *  5)   -  Die Anforderung dass ein Security Check bei einem Upload durchgeführt werden soll wird in KlipsiasService.java simuliert indem der Input auf Zeichen kontrolliert wird,
 *          die der simulierten Datenbank schaden könntent. Zudem werden nur bestimmt Dateitypen zugelassen.
 *       -  Zudem wurde ein User Rektorat (Passwort: GanzSicher!) angelegt der neue Benutzer anlegen kann und damit die zweite funktionale Anforderung erfüllt.
 *       -  Fehler werden beim Lesen und Schreiben in die simulierte Datenbank abgefangen AccountService und IliasService
 *       -  Zudem wurde die Qualitätsanforderung umgesetzt, dass Klipsias auch komplett in englisch nutzbar ist.
 * 
 *       -  Die anderen 3 Aspekte des Aufgabenblattes wurden (noch) nicht umgesetzt. Dabei ist mir bewusste das deswegen Aufgabe 5 nur kaum oder nicht bewertet werden kann.
 *          Potenziell ist sharing in den Klassen AccountService oder IliasService nur möglich, da die anderen Klasse nicht mit Objekten arbeiten.
 *          Eine generalisierte Hierachie wäre bspw. in Zukunft beim AccountService möglich umso die User zu spezifizieren und Berechtigungsmatcher in den Code einzubauen anstelle von
 *          den Code über die UserId zu steuern. 
 *          Für eine generische Klasse besteht momentan keine Idee zum einbauen.
 * 
 *       -  Leider war es auf Grund von Zeit-Missmanagment nicht möglich die Aufgabe vollständing zu bearbeiten, nach Möglichkeit werden die nicht berücksichtigten 3 Aspekte bei der nächste Abgabe mit abgegben.
 *          Auch wenn diese dann nicht mehr benotet werden können.
 */
}
