package Exercise4.Version2;

public class Main {
    public static void main(String[] args) {
        try {
            Calculator.divide(3, 0);
        } catch (Exception e) {
            System.out.println("Du darfst nicht durch 0 dividieren");
        }
    }
}