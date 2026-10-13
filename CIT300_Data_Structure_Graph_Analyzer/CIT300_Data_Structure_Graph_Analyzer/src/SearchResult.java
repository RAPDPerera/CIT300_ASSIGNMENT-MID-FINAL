
public class SearchResult {
    private final String algorithm;
    private final int index;
    private final long steps;
    private final long nanoseconds;

    public SearchResult(String algorithm, int index, long steps, long nanoseconds) {
        this.algorithm = algorithm;
        this.index = index;
        this.steps = steps;
        this.nanoseconds = nanoseconds;
    }

    public String getAlgorithm() { return algorithm; }
    public int getIndex() { return index; }
    public long getSteps() { return steps; }
    public long getNanoseconds() { return nanoseconds; }

    @Override
    public String toString() {
        return algorithm + " | Result: " + (index >= 0 ? "Found at index " + index : "Not found")
                + " | Steps: " + steps + " | Time: " + nanoseconds + " ns";
    }
}
