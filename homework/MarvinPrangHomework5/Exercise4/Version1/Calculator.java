package Exercise4.Version1;

public class Calculator {
    public static int divide(int a, int b) {
        try {
            return a / b;
        } catch (Exception e) {
            System.out.println("Du darfst nicht durch 0 dividieren");
            return 0;
        }
    }
}
