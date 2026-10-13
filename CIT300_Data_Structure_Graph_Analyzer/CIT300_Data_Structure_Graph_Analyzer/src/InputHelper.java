
import java.util.Scanner;

public class InputHelper {
    public static int readInt(Scanner scanner, String prompt) {
        while (true) {
            System.out.print(prompt);
            String input = scanner.nextLine().trim();
            try {
                return Integer.parseInt(input);
            } catch (NumberFormatException e) {
                System.out.println("Invalid integer. Please enter a valid number.");
            }
        }
    }

    public static int readInt(Scanner scanner, String prompt, int min, int max) {
        while (true) {
            int value = readInt(scanner, prompt);
            if (value >= min && value <= max) return value;
            System.out.println("Please enter a value between " + min + " and " + max + ".");
        }
    }

    public static String readString(Scanner scanner, String prompt) {
        while (true) {
            System.out.print(prompt);
            String value = scanner.nextLine().trim();
            if (!value.isEmpty()) return value;
            System.out.println("Input cannot be empty.");
        }
    }
}
