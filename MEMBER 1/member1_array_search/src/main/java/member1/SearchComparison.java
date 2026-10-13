package member1;

import java.util.Arrays;

/**
 * Fair Linear-vs-Binary comparison: both algorithms search the SAME sorted copy
 * for the SAME target. Sorting time is measured separately and is NOT included
 * in either search time.
 *
 * Member 5 usage:
 *   SearchComparison c = SearchComparison.run(array.toArray(), target);
 *   c.getLinearResult().getComparisons();  c.getBinaryResult().getComparisons();
 */
public final class SearchComparison {

    private final int[] originalSnapshot;
    private final int[] sortedData;
    private final int target;
    private final SearchResult linearResult;
    private final SearchResult binaryResult;
    private final long preparationNanos;

    private SearchComparison(int[] original, int[] sorted, int target,
                             SearchResult linear, SearchResult binary, long prepNanos) {
        this.originalSnapshot = original;
        this.sortedData = sorted;
        this.target = target;
        this.linearResult = linear;
        this.binaryResult = binary;
        this.preparationNanos = prepNanos;
    }

    public static SearchComparison run(int[] snapshot, int target) {
        int[] original = snapshot.clone();

        long start = System.nanoTime();
        int[] sorted = SearchAlgorithms.sortedCopy(original);
        long prepNanos = System.nanoTime() - start;

        SearchResult linear = SearchAlgorithms.linearSearch(sorted, target,
                SearchResult.SORTED_COPY_INDEX);
        SearchResult binary = SearchAlgorithms.binarySearch(sorted, target);
        return new SearchComparison(original, sorted, target, linear, binary, prepNanos);
    }

    public int[] getOriginalSnapshot() { return originalSnapshot.clone(); }
    public int[] getSortedData() { return sortedData.clone(); }
    public int getTarget() { return target; }
    public SearchResult getLinearResult() { return linearResult; }
    public SearchResult getBinaryResult() { return binaryResult; }
    public long getPreparationNanos() { return preparationNanos; }

    /** Printable comparison table. */
    public String toTable() {
        String line = "------------------------------------------------------------";
        StringBuilder sb = new StringBuilder();
        sb.append("=============================================\n");
        sb.append("           SEARCH PERFORMANCE\n");
        sb.append("=============================================\n");
        sb.append("Original : ").append(Arrays.toString(originalSnapshot)).append('\n');
        sb.append("Sorted   : ").append(Arrays.toString(sortedData)).append('\n');
        sb.append("Target   : ").append(target).append("\n\n");
        sb.append(String.format("%-16s%-8s%-8s%-13s%s%n",
                "Algorithm", "Found", "Index", "Comparisons", "Time (ns)"));
        sb.append(line).append('\n');
        appendRow(sb, linearResult);
        appendRow(sb, binaryResult);
        sb.append(line).append('\n');
        sb.append("Index = position in the SORTED copy (not the original array).\n");
        sb.append("Sorting preparation took ").append(preparationNanos)
          .append(" ns and is NOT included in the search times above.\n");
        sb.append("Comparisons are the main metric; nanosecond timings on small\n");
        sb.append("arrays are noisy (JVM warm-up, system activity).\n");
        return sb.toString();
    }

    private static void appendRow(StringBuilder sb, SearchResult r) {
        sb.append(String.format("%-16s%-8s%-8s%-13d%d%n",
                r.getAlgorithmName(),
                r.isFound() ? "Yes" : "No",
                r.isFound() ? String.valueOf(r.getIndex()) : "N/A",
                r.getComparisons(),
                r.getElapsedNanos()));
    }
}
