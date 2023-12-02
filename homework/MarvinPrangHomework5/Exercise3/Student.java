package Exercise3;

import java.util.ArrayList;

public class Student extends Person {
        private String matriculationNumber;

        public Student(String vorname, String nachname, int age, ArrayList<Lecture> lectures,
                String matriculationNumber) {
            super(vorname, nachname, age, lectures);
            this.matriculationNumber = matriculationNumber;
        }

        public String toString() {
            String lecturesString;
            if (lectures.size() == 0) {
                lecturesString = "belegt momentan keine Kurse";
            } else {
                lecturesString = "belegt momentan die folgenden Kurse: " + getAllLecturename(lectures);
            }
            return ("Der Student " + vorname + ", " + matriculationNumber + ", " + lecturesString);
        }
    }