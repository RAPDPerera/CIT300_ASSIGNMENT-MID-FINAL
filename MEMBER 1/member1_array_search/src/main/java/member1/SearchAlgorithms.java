package member1;

import java.util.Objects;

/**
 * Manually written searching algorithms (no java.util.Arrays search/sort calls).
 * Comparison rule used by BOTH searches: one comparison = one array element examined.
 * None of these methods modify the array they receive.
 */
public final class SearchAlgorithms {

    private SearchAlgorithms() { }

    /** Linear search; reports the index as an index in the original array. */
    public static SearchResult linearSearch(int[] data, int target) {
        return linearSearch(data, target, SearchResult.ORIGINAL_ARRAY_INDEX);
    }

    /**
     * Linear search. Works on sorted or unsorted data and returns the FIRST match.
     * Best O(1), average O(n), worst O(n); extra space O(1).
     *
     * @param indexContext label saying which array the returned index belongs to
     */
    public static SearchResult linearSearch(int[] data, int target, String indexContext) {
        Objects.requireNonNull(data, "data must not be null");
        int comparisons = 0;
        int foundIndex = -1;

        long start = System.nanoTime();
        for (int i = 0; i < data.length; i++) {
            comparisons++;                 // examined element i
            if (data[i] == target) {
                foundIndex = i;
                break;                     // first match wins
            }
        }
        long elapsed = System.nanoTime() - start;

        return new SearchResult("Linear Search", target, foundIndex != -1,
                foundIndex, comparisons, elapsed, indexContext);
    }

    /**
     * Iterative binary search.
     *
     * PRECONDITION: sortedData is sorted in ascending order. This method does NOT
     * check that (a scan would add O(n) to an O(log n) algorithm). Callers must
     * satisfy it, e.g. with {@link #sortedCopy(int[])}.
     *
     * With duplicates it returns SOME matching index, not necessarily the first.
     * The index refers to the sorted array that was passed in.
     * Best O(1), average O(log n), worst O(log n); extra space O(1).
     */
    public static SearchResult binarySearch(int[] sortedData, int target) {
        Objects.requireNonNull(sortedData, "sortedData must not be null");
        int comparisons = 0;
        int foundIndex = -1;

        long start = System.nanoTime();
        int left = 0;
        int right = sortedData.length - 1;
        while (left <= right) {
            int middle = left + (right - left) / 2;   // overflow-safe midpoint
            comparisons++;                            // examined the middle element
            if (sortedData[middle] == target) {
                foundIndex = middle;
                break;
            } else if (sortedData[middle] < target) {
                left = middle + 1;                    // target can only be on the right
            } else {
                right = middle - 1;                   // target can only be on the left
            }
        }
        long elapsed = System.nanoTime() - start;

        return new SearchResult("Binary Search", target, foundIndex != -1,
                foundIndex, comparisons, elapsed, SearchResult.SORTED_COPY_INDEX);
    }

    /**
     * Returns a NEW ascending array using insertion sort; the input is untouched.
     * This is PREPARATION for binary search, not part of the binary search loop.
     * Time O(n^2) worst case, extra memory O(n) for the copy.
     */
    public static int[] sortedCopy(int[] data) {
        Objects.requireNonNull(data, "data must not be null");
        int[] copy = data.clone();
        for (int i = 1; i < copy.length; i++) {
            int key = copy[i];
            int j = i - 1;
            while (j >= 0 && copy[j] > key) {   // shift larger values right
                copy[j + 1] = copy[j];
                j--;
            }
            copy[j + 1] = key;
        }
        return copy;
    }
}
