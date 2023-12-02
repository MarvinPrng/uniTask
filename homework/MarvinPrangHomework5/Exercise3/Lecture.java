package Exercise3;

    public class Lecture {

        String name;
        int creditPoints;
        int workload;

        public Lecture(String name, int creditPoints, int workload) {
            this.name = name;
            this.creditPoints = creditPoints;
            this.workload = workload;
        }

        public String toString() {
            return ("Der Kurs " + name +
                    " erfordert einen wöchentlichen Arbeitsaufwand von " + workload +
                    " Stunden und gibt dem Studentem bei erflogreichem abschließen " + creditPoints + " Credits.");
        }

        public String getLecturename() {
            return name;
        }

    }
