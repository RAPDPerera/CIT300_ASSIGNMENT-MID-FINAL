package member1;

import java.io.PrintStream;
import java.util.Scanner;

/**
 * Validated line-based console input. Shares ONE Scanner and never closes it.
 * Reads whole lines, so there are no leftover-newline problems.
 */
public final class ConsoleInput {

    /** Thrown when the input stream ends (e.g. Ctrl+D). */
    public static final class InputClosedException extends RuntimeException {
        private static final long serialVersionUID = 1L;
        public InputClosedException() { super("Input stream closed."); }
    }

    private final Scanner scanner;
    private final PrintStream out;

    public ConsoleInput(Scanner scanner, PrintStream out) {
        this.scanner = scanner;
        this.out = out;
    }

    /** Asks until the user types a valid int. */
    public int readInt(String prompt) {
        while (true) {
            out.print(prompt);
            if (!scanner.hasNextLine()) {
                throw new InputClosedException();
            }
            String text = scanner.nextLine().trim();
            if (text.isEmpty()) {
                out.println("Invalid input.\nInput cannot be empty. Please enter a valid number.");
                continue;
            }
            try {
                return Integer.parseInt(text);
            } catch (NumberFormatException e) {
                if (text.matches("[+-]?\\d+")) {
                    out.println("Number out of range.\nPlease enter a whole number between "
                            + Integer.MIN_VALUE + " and " + Integer.MAX_VALUE + ".");
                } else {
                    out.println("Invalid input.\nPlease enter a valid number.");
                }
            }
        }
    }

    /** Asks until the user types an int within [min, max]. */
    public int readIntInRange(String prompt, int min, int max) {
        while (true) {
            int value = readInt(prompt);
            if (value >= min && value <= max) {
                return value;
            }
            out.println("Please enter a number between " + min + " and " + max + ".");
        }
    }
}
