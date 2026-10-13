package member1;

/** Runs every Member 1 test. Exit code 0 = all passed, 1 = at least one failure. */
public final class RunAllTests {
    public static void main(String[] args) {
        IntArrayTest.run();
        SearchAlgorithmsTest.run();
        MenuTest.run();
        System.out.println("Passed: " + TestSupport.passed + "  Failed: " + TestSupport.failed);
        System.exit(TestSupport.failed == 0 ? 0 : 1);
    }
}
