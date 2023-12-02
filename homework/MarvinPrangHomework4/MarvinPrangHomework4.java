import java.util.ArrayList;
import java.util.Random;

public class MarvinPrangHomework4 {

    public static void main(String[] args) {
    }

/*
 * 1)   - "Pure" Imperative Programming / Unstructured Programming
 *         Unstructured Programming beschreibt die fundamentalen Konzepte darüber wie Programme arbeiten, mit Variablen und Operatoren.
 *         Dazu ist der Schritt bei Schritt geschrieben und auch für ungeübte Programmiere leicht zu verstehen.
 *         Beispiel: function1
 */
    private static void function1() {
        int a = 1;
        int b = 2;

        b = a + b;

        boolean groesser = b < 0;
    }

/*
 *      -  Structured Programming
 *         Bei Structured Programming wird der Programm-Flow organisiert und kontrolliert durch Branching and Looping.
 *         Beispiel: function2
 */
    private static void function2() {
        int a = 1;
        int b = 10;

        while (a < b){
            a = a+2;
            if (a == 9) {
                a = 0;
            } else if (a == 2) {
                b = 12;
            }
        }
    }

/*
 *      -  Procedural Programming
 *         Procedural Programming verfeinert das strukturierte Programmieren indem der Code in Code Blöcke aufgeteilt wird.
 *         Diese Blöcke werden dann aufgerufen und von Code Block zu Code Block gesprungen.
 *         Beispiel: function3
 */
    private static void function3() {
        int a = initialisiereA();
        int b = initialisiereB();

        a = vergroeßereA(a);

        if (checkBKleinerA(a, b)) {
            führeAktionenAus(a, b); //Funktion Aufruf nur beispielhaft. Der Aufruf ändert keine Variablen.
        }
    }
    private static int initialisiereA() { return 1; }
    private static int initialisiereB() { return 2; }
    private static int vergroeßereA(int a) { return a = a*20; }
    private static boolean checkBKleinerA (int a, int b) { return b < a; }
    private static void führeAktionenAus ( int a, int b) { int multiplikator = 5; a = initialisiereA(); b = multipliziereB(b, multiplikator); }
    private static int multipliziereB (int b, int multiplikator) { return b * multiplikator; }

/*
 *      -  Modular Programming
 *         Bei Modular Programming geht es darum den Code in verschiedene Module zu verschieben über welche dann der Code ausgeführt wird.
 *         Beispiel: function4
 */
    private static void function4() {
        MarvinPrangHomework4 hw = new MarvinPrangHomework4();
        Variable x = hw.new Variable(1);
        Constant y = hw.new Constant();
        Addieren addieren = hw.new Addieren();

        System.out.println(addieren.addieren(x, y));
    }

    private class Variable {
        int x;

        private Variable(int x) {
            this.x = x;
        }
    }

    private class Constant {
        int y = 10;

        private Constant() {

        }
    }

    private class Addieren {

        private Addieren() {

        }

        protected int addieren (Variable x, Constant y) {
            return (x.x + y.y);
        }
    }
    

/*
 *       - In Java werden nicht die Variablen weiter an Methoden (Prozeduren) weitergegeben sondern lediglich Kopien der Variablen.
 *         Dadurch kann in der Methode die Variable geändert werden ohne dass die eigentliche Variable (außerhalb der Methode) sich ändert, weil
 *         in der Methode selbst nur auf die Kopie zugegriffen wird.
 */

 /*
 * 2) 
 */
private class ProfCreateCode {
        MarvinPrangHomework4 hw = new MarvinPrangHomework4();
        Person myProf = hw.new Professor("Andreas", "Vogelsang", 39, "Software & Systems Engineering", new ArrayList<Lecture>(), new ArrayList<Publication>(), new ArrayList<Student>());

}

    private class Professor extends Person {
        private String vorname;
        private String nachname;
        private int age;
        private String chair;
        private ArrayList<Lecture> lectures;
        private ArrayList<Publication> publications;
        private ArrayList<Student> supervisedStudents;

        public Professor(String vorname, String nachname, int age, String chair, ArrayList<Lecture> lectures, ArrayList<Publication> publications, ArrayList<Student> supervisedStudents) {
            this.vorname = vorname;
            this.nachname = nachname;
            this.age = age;
            this.chair = chair;
            this.lectures = new ArrayList<>(lectures);
            this.publications = new ArrayList<>(publications);
            this.supervisedStudents = new ArrayList<>(supervisedStudents);
        }

        public String greet() {
            return ("Hello! I am " + this.vorname + " " + this.nachname + " an der Universität zu Köln am Lehrstuhl " + chair);
        }
    }
    private class Lecture {}
    private class Person {}
    private class Publication {}
    private class Student {}

 /*
 * 3)    - 1
 *         Auf der Konsole konnte man sehen dass der Fehler bei ShoppingBasket auftritt bei der Funktion addTobasket in Zeile 16. 
 *         Debug man den Code mit Breakpoints fällt auf das der Fehler auftritt wenn das Item "water" hinzugefügt werden soll. Bei einem Blick auf Items fällt auf, dass
 *         "water" groß geschrieben werden muss und so der Fehler entsteht.
 * 
 *       - 2
 *         Bei der Ausgabe fällt beim vergleichen mit Items.java auf das sara Preis höher ist als er sein sollte. Mit einem Breakpoint bei getTotalPoints sieht man, dass
 *         beim Aufruf saraShoppingBasket.addTobasket("Pasta"); der totalPrice schon auf 2 ist, soviel wie john zahlen musste. Bei einem Blick auf die Klassenvariable 
 *         totalPrice fällt auf, dass diese static ist und sara und john diese teilen. 
 * 
 *       - 3
 *         Probiert man durch alle Monate durch, fällt auf das bei Mai auch Juni ausgegeben wird. Setzt man Breakpoints für alle Monate sieht man dass bei Mai der Code
 *         zu Juni überspringt. Beim Vergleich von Mai mit den anderen Monaten fällt auf, dass bei diesem ein break; fehlt.
 * 
 *         Die gefixten Codes sind auf dem Branch debugHW4 zu sehen. Bilder zum debuggen entweder auf dem eben genannten Branch oder hier bei homework/marvinPrangHomework4/debugImg/Debug.pdf
 */

 /*
 * 4)    - 
 *         Beim Ractoring hat vorallem das der extract von Methoden und umbennen funktioniert. Probleme hat vor allem das inlinen von Variablen verursacht.
 *         Probleme dafür könnten eventuell die IDE (VS Code in dem Fall) oder die Unfähigkeit des Benutzers sein.
 * 
 *         Die gefixten Codes sind auf dem Branch refactorHW4 zu sehen. Bilder zum refactoring entweder auf dem eben genannten Branch oder hier bei homework/marvinPrangHomework4/refactorImg/Refactor.pdf
 */

 /*
 * 5)    - 
 *         Ein Ordner klipsias wurde erstellt in welchem Das Projekt zu finden ist. Bisher wurde das Programm relativ schlicht gehalten und ist funktional nur in Textform bedinbar.
 *         Es wurd sich auf das funktionale Requirement von Teilen von Datein in bestimmten Kurs Räumen beschränkt, dazu:
 *      -> In Browser.java den Code ausführen und einloggen (entweder als Student oder Professor), dann einen Raum auswählen (SoftwareTechnik or SWT Organisation bei Prof).
 *         Dann uploadFile eingeben, dann wird man aufgefordert eine Datei auszuwählen (soll symbolisch den File Explorer symbolisieren) dort bspw. Test.pdf eintippen und danach den gewünschten Inhalt.
 *         Wird das Programm dann neugestartet kann die Datei gefunden werden (Einloggen -> Raum -> Test.pdf) und der Inhalt gelesen werden. Im Raum Software Technik auch vom anderem User.
 * 
 *         Bisher hat das Programm noch keine Funktionen zum ausloggen oder zurück navigieren weshalb es immer neugestartet werden muss.
 *         Login Daten:
 *              Username: TestStudent
 *              Password: ABC
 * 
 *              Username TestProf
 *              Password: 1Password
 */
}
