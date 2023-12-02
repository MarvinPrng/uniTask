package Exercise3;

import java.util.ArrayList;

public class Person {

    protected String vorname;
    protected String nachname;
    protected int age;
    protected ArrayList<Lecture> lectures;

    public Person(String vorname, String nachname, int age, ArrayList<Lecture> lectures) {
        this.vorname = vorname;
        this.nachname = nachname;
        this.age = age;
        this.lectures = new ArrayList<>(lectures);
    }

    public void addLecture(Lecture... lecture) {
        for (Lecture i : lecture) {
            this.lectures.add(i);
        }
    }

    protected String getAllLecturename(ArrayList<Lecture> lectures) {
        String lecturesnames = "";
        for (int i = 0; i < lectures.size(); i++) {
            lecturesnames = lecturesnames + (lectures.get(i)).getLecturename() + ", ";
        }
        return lecturesnames.substring(0, lecturesnames.length() - 2);
    }

    public String greet() {
        return ("Hallo! Ich bin " + this.vorname + " " + this.nachname + " und bin " + this.age + " Jahre alt.");
    }

}
