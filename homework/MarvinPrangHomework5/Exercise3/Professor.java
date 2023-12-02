package Exercise3;

import java.util.ArrayList;
import java.util.List;

public class Professor<T> extends Person {
        private String chair;
        private List<T> publications;
        public List<T> supervisedStudents;

        public Professor(String vorname, String nachname, String chair, int age, ArrayList<Lecture> lectures,
                List<T> publications, List<T> supervisedStudents) {
            super(vorname, nachname, age, lectures);
            this.chair = chair;
            this.publications = new ArrayList<>(publications);
            this.supervisedStudents = new ArrayList<>(supervisedStudents);
        }

        public String greet() {
            return ("Hallo! Ich bin " + this.vorname + " " + this.nachname + " an der Universität zu Köln am Lehrstuhl "
                    + this.chair);
        }
    }