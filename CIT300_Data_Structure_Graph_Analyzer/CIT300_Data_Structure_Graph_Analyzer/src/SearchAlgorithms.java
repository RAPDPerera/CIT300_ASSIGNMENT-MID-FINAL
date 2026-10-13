
public class SearchAlgorithms {
    public static SearchResult linearSearch(int[] data, int target) {
        long start = System.nanoTime();
        long steps = 0;
        for (int i = 0; i < data.length; i++) {
            steps++;
            if (data[i] == target) return new SearchResult("Linear Search", i, steps, System.nanoTime() - start);
        }
        return new SearchResult("Linear Search", -1, steps, System.nanoTime() - start);
    }

    public static SearchResult binarySearch(int[] sortedData, int target) {
        long start = System.nanoTime();
        int low = 0, high = sortedData.length - 1;
        long steps = 0;
        while (low <= high) {
            steps++;
            int mid = low + (high - low) / 2;
            if (sortedData[mid] == target)
                return new SearchResult("Binary Search", mid, steps, System.nanoTime() - start);
            if (sortedData[mid] < target) low = mid + 1;
            else high = mid - 1;
        }
        return new SearchResult("Binary Search", -1, steps, System.nanoTime() - start);
    }
}
