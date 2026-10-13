package member1;

import java.util.Arrays;

/** Tiny assertion helper (no external test framework needed). */
final class TestSupport {
    static int passed = 0;
    static int failed = 0;

    static void check(String name, boolean condition) {
        if (condition) { passed++; }
        else { failed++; System.out.println("FAIL: " + name); }
    }

    static void equal(String name, Object expected, Object actual) {
        boolean ok = expected == null ? actual == null : expected.equals(actual);
        check(name + " (expected " + expected + ", got " + actual + ")", ok);
    }

    static void arrayEquals(String name, int[] expected, int[] actual) {
        check(name + " (expected " + Arrays.toString(expected) + ", got "
                + Arrays.toString(actual) + ")", Arrays.equals(expected, actual));
    }

    static void throwsEx(String name, Class<? extends Throwable> type, Runnable action) {
        try {
            action.run();
            check(name + " (no exception thrown)", false);
        } catch (Throwable t) {
            check(name + " (wrong exception " + t.getClass().getSimpleName() + ")",
                    type.isInstance(t));
        }
    }
}
