import java.util.Arrays;
import java.util.Objects;

public class OID {
    private static Student[] copy(Student[] array) {
        Student[] newArray = new Student[array.length];

        for (int i = 0; i<array.length; i++) {
            newArray[i] = new Student(array[i].getName());
        }

        return newArray;
    }

    public static void main(String[] args) {
        Student[] students = new Student[] {new Student("Tom"), new Student("Alice")};
        Student[] students2 = (OID.Student[]) copy(students);

        System.out.println(Arrays.toString(students));
        System.out.println(Arrays.toString(students2));
        students[0].name = "Tim";
        System.out.println(Arrays.toString(students));
        System.out.println(Arrays.toString(students2));
    }

    private static class Student {
        String name;

        public Student(String name) {
            this.name = name;
        }

        @Override
        public String toString() {
            return "Student{" +
                    "name='" + name + '\'' +
                    '}';
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Student student = (Student) o;
            return Objects.equals(name, student.name);
        }

        @Override
        public int hashCode() {
            return Objects.hash(name);
        }

        public String getName() {
            return this.name;
        }
    }
}
