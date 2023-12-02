package Exercise2;

import java.util.ArrayList;

public class Professor extends Person {
        private String chair;
        private ArrayList<Publication> publications;
        private ArrayList<Student> supervisedStudents;

        public Professor(String vorname, String nachname, String chair, int age, ArrayList<Lecture> lectures,
                ArrayList<Publication> publications, ArrayList<Student> supervisedStudents) {
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