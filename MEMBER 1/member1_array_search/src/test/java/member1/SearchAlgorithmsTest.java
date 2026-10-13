package member1;

import static member1.TestSupport.*;

final class SearchAlgorithmsTest {

    private static void expect(String name, SearchResult r, boolean found, int index, int comps) {
        check(name + " found flag", r.isFound() == found);
        check(name + " index (expected " + index + ", got " + r.getIndex() + ")", r.getIndex() == index);
        check(name + " comparisons (expected " + comps + ", got " + r.getComparisons() + ")",
                r.getComparisons() == comps);
        check(name + " time measured", r.getElapsedNanos() >= 0);
    }

    static void run() {
        linear();
        binary();
        comparison();
    }

    private static void linear() {
        int[] data = {40, 10, 30, 20};
        expect("linear beginning", SearchAlgorithms.linearSearch(data, 40), true, 0, 1);
        expect("linear middle (spec example)", SearchAlgorithms.linearSearch(data, 30), true, 2, 3);
        expect("linear end", SearchAlgorithms.linearSearch(data, 20), true, 3, 4);
        expect("linear missing", SearchAlgorithms.linearSearch(data, 99), false, -1, 4);
        expect("linear empty", SearchAlgorithms.linearSearch(new int[0], 1), false, -1, 0);
        expect("linear one element hit", SearchAlgorithms.linearSearch(new int[]{7}, 7), true, 0, 1);
        expect("linear one element miss", SearchAlgorithms.linearSearch(new int[]{7}, 8), false, -1, 1);
        expect("linear duplicates -> first", SearchAlgorithms.linearSearch(new int[]{5, 7, 5}, 5), true, 0, 1);
        expect("linear negatives", SearchAlgorithms.linearSearch(new int[]{-3, -1, -2}, -2), true, 2, 3);
        expect("linear sorted data", SearchAlgorithms.linearSearch(new int[]{1, 2, 3}, 3), true, 2, 3);
        arrayEquals("linear does not modify input", new int[]{40, 10, 30, 20}, data);
        equal("linear name", "Linear Search", SearchAlgorithms.linearSearch(data, 1).getAlgorithmName());
        equal("linear default context", SearchResult.ORIGINAL_ARRAY_INDEX,
                SearchAlgorithms.linearSearch(data, 1).getIndexContext());
    }

    private static void binary() {
        int[] s = {10, 20, 30, 40, 50};
        expect("binary present (odd)", SearchAlgorithms.binarySearch(s, 40), true, 3, 2);
        expect("binary first element", SearchAlgorithms.binarySearch(s, 10), true, 0, 2);
        expect("binary middle element", SearchAlgorithms.binarySearch(s, 30), true, 2, 1);
        expect("binary last element", SearchAlgorithms.binarySearch(s, 50), true, 4, 3);
        expect("binary missing", SearchAlgorithms.binarySearch(s, 35), false, -1, 2);
        expect("binary empty", SearchAlgorithms.binarySearch(new int[0], 1), false, -1, 0);
        expect("binary single hit", SearchAlgorithms.binarySearch(new int[]{7}, 7), true, 0, 1);
        expect("binary single miss", SearchAlgorithms.binarySearch(new int[]{7}, 9), false, -1, 1);
        int[] even = {1, 2, 3, 4};
        expect("binary even last", SearchAlgorithms.binarySearch(even, 4), true, 3, 3);
        expect("binary even first", SearchAlgorithms.binarySearch(even, 1), true, 0, 2);
        expect("binary negatives", SearchAlgorithms.binarySearch(new int[]{-9, -4, -1, 0}, -4), true, 1, 1);

        int[] dups = {1, 2, 2, 2, 3};
        SearchResult d = SearchAlgorithms.binarySearch(dups, 2);
        check("binary duplicates: some valid index", d.isFound() && dups[d.getIndex()] == 2);

        int[] extremes = {Integer.MIN_VALUE, 0, Integer.MAX_VALUE};
        expect("binary MAX_VALUE", SearchAlgorithms.binarySearch(extremes, Integer.MAX_VALUE), true, 2, 2);
        expect("binary MIN_VALUE", SearchAlgorithms.binarySearch(extremes, Integer.MIN_VALUE), true, 0, 2);
        equal("binary context", SearchResult.SORTED_COPY_INDEX, d.getIndexContext());

        // sorted copy / original unchanged
        int[] original = {50, 10, 40, 20, 30};
        int[] sorted = SearchAlgorithms.sortedCopy(original);
        arrayEquals("sortedCopy sorted", new int[]{10, 20, 30, 40, 50}, sorted);
        arrayEquals("original unchanged after sortedCopy", new int[]{50, 10, 40, 20, 30}, original);
        arrayEquals("sortedCopy negatives+dups", new int[]{-5, -5, 0, 3},
                SearchAlgorithms.sortedCopy(new int[]{3, -5, 0, -5}));
        arrayEquals("sortedCopy empty", new int[0], SearchAlgorithms.sortedCopy(new int[0]));
        expect("binary on sorted copy (spec example)", SearchAlgorithms.binarySearch(sorted, 40), true, 3, 2);
    }

    private static void comparison() {
        int[] original = {50, 10, 40, 20, 30};
        SearchComparison c = SearchComparison.run(original, 40);
        arrayEquals("comparison keeps original", new int[]{50, 10, 40, 20, 30}, c.getOriginalSnapshot());
        arrayEquals("comparison sorted data", new int[]{10, 20, 30, 40, 50}, c.getSortedData());
        expect("comparison linear (sorted data)", c.getLinearResult(), true, 3, 4);
        expect("comparison binary", c.getBinaryResult(), true, 3, 2);
        equal("linear context is sorted copy", SearchResult.SORTED_COPY_INDEX,
                c.getLinearResult().getIndexContext());
        check("both found the same index", c.getLinearResult().getIndex() == c.getBinaryResult().getIndex());
        check("preparation time measured separately", c.getPreparationNanos() >= 0);
        check("table mentions both algorithms",
                c.toTable().contains("Linear Search") && c.toTable().contains("Binary Search"));
        check("table discloses sort excluded", c.toTable().contains("NOT included"));

        SearchComparison miss = SearchComparison.run(original, 99);
        check("comparison missing: both not found",
                !miss.getLinearResult().isFound() && !miss.getBinaryResult().isFound());
        expect("comparison missing linear", miss.getLinearResult(), false, -1, 5);
        expect("comparison missing binary", miss.getBinaryResult(), false, -1, 3);

        SearchComparison none = SearchComparison.run(new int[0], 1);
        expect("comparison empty linear", none.getLinearResult(), false, -1, 0);
        expect("comparison empty binary", none.getBinaryResult(), false, -1, 0);

        // larger data: binary should use far fewer comparisons than linear
        int[] big = new int[1000];
        for (int i = 0; i < big.length; i++) big[i] = i * 2;
        SearchComparison large = SearchComparison.run(big, 1998);
        check("1000 elements: linear = 1000 comparisons", large.getLinearResult().getComparisons() == 1000);
        check("1000 elements: binary <= 10 comparisons", large.getBinaryResult().getComparisons() <= 10);
    }
}
