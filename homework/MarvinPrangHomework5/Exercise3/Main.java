package Exercise3;

import java.util.ArrayList;

public class Main {

    public static void main(String[] args) {
        Professor myProf = new Professor("Andreas", "Vogelsang", "Software & Systems Engineering", 39, new ArrayList<Lecture>(), new ArrayList<Publication>(), new ArrayList<Student>());
        System.out.println(myProf.greet());


        myProf.supervisedStudents.add(new BachelorStudent("Marvin", "Prang", 21, new ArrayList<Lecture>(), "7379881"));
        myProf.supervisedStudents.add(new MasterStudent("Olaf", "Müller", 25, new ArrayList<Lecture>(), "12345"));
        myProf.supervisedStudents.add(new PhDStudent("Helga", "Maier", 27, new ArrayList<Lecture>(), "77812"));
        myProf.supervisedStudents.add(new StudentAssistant("Max", "Mustemann", 30, new ArrayList<Lecture>(), "9966699"));
    }
}
