package member1;

/** Immutable outcome of one search. Reusable by the performance-comparison screen (Member 5). */
public final class SearchResult {

    public static final String ORIGINAL_ARRAY_INDEX = "Index in original array";
    public static final String SORTED_COPY_INDEX = "Index in sorted copy";

    private final String algorithmName;
    private final int target;
    private final boolean found;
    private final int index;          // -1 when not found
    private final int comparisons;    // elements examined
    private final long elapsedNanos;  // search time only (no sorting)
    private final String indexContext; // which array 'index' refers to

    public SearchResult(String algorithmName, int target, boolean found, int index,
                        int comparisons, long elapsedNanos, String indexContext) {
        this.algorithmName = algorithmName;
        this.target = target;
        this.found = found;
        this.index = index;
        this.comparisons = comparisons;
        this.elapsedNanos = elapsedNanos;
        this.indexContext = indexContext;
    }

    public String getAlgorithmName() { return algorithmName; }
    public int getTarget() { return target; }
    public boolean isFound() { return found; }
    public int getIndex() { return index; }
    public int getComparisons() { return comparisons; }
    public long getElapsedNanos() { return elapsedNanos; }
    public String getIndexContext() { return indexContext; }

    /** Multi-line, human-readable description. */
    public String describe() {
        return "Algorithm   : " + algorithmName + "\n"
             + "Target      : " + target + "\n"
             + "Result      : " + (found ? "Found" : "Not found") + "\n"
             + indexContext + " : " + (found ? String.valueOf(index) : "N/A") + "\n"
             + "Comparisons : " + comparisons + "\n"
             + "Time        : " + elapsedNanos + " ns (search only)";
    }
}
