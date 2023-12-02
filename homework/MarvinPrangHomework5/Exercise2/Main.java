package Exercise2;

import java.util.ArrayList;

public class Main {

    public static void main(String[] args) {
        Person myProf = new Professor("Andreas", "Vogelsang", "Software & Systems Engineering", 39, new ArrayList<Lecture>(), new ArrayList<Publication>(), new ArrayList<Student>());
        System.out.println(myProf.greet());
    }
}
