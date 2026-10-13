
import java.util.*;

public class PerformanceAnalyzer {
    public static void compareSearch(int[] data, int target) {
        if (data.length == 0) {
            System.out.println("Array is empty.");
            return;
        }
        int[] sorted = Arrays.copyOf(data, data.length);
        Arrays.sort(sorted);
        SearchResult linear = SearchAlgorithms.linearSearch(data, target);
        SearchResult binary = SearchAlgorithms.binarySearch(sorted, target);

        System.out.println("\n================ SEARCH PERFORMANCE ================");
        System.out.printf("%-20s %-12s %-15s %-15s%n", "Algorithm", "Index", "Steps", "Time (ns)");
        System.out.println("-----------------------------------------------------");
        System.out.printf("%-20s %-12s %-15d %-15d%n", linear.getAlgorithm(),
                linear.getIndex(), linear.getSteps(), linear.getNanoseconds());
        System.out.printf("%-20s %-12s %-15d %-15d%n", binary.getAlgorithm(),
                binary.getIndex(), binary.getSteps(), binary.getNanoseconds());
        System.out.println("Note: Binary search requires sorted data.");
    }

    public static void displayPerformance(DynamicArray array, Graph graph) {
        System.out.println("Performance method started.");
        
    
        int[] data = array.toArray();
        if (data.length == 0) {
            System.out.println("Load some array data first.");
            return;
        }
        int target = data[data.length - 1];
        int[] sorted = Arrays.copyOf(data, data.length);
        Arrays.sort(sorted);

        SearchResult linear = SearchAlgorithms.linearSearch(data, target);
        SearchResult binary = SearchAlgorithms.binarySearch(sorted, target);

        String start = graph.vertexCount() > 0 ? "A" : "";
        long bfsStart = System.nanoTime();
        List<String> bfs = start.isEmpty() ? Collections.emptyList() : graph.bfs(start);
        long bfsTime = System.nanoTime() - bfsStart;

        long dfsStart = System.nanoTime();
        List<String> dfs = start.isEmpty() ? Collections.emptyList() : graph.dfs(start);
        long dfsTime = System.nanoTime() - dfsStart;

        System.out.println("\n=============================================");
        System.out.println("          PERFORMANCE COMPARISON");
        System.out.println("=============================================");
        System.out.printf("%-20s %-18s %-12s %-15s%n", "Operation", "Algorithm", "Steps", "Time (ns)");
        System.out.println("-------------------------------------------------------------");
        System.out.printf("%-20s %-18s %-12d %-15d%n", "Search", "Linear Search", linear.getSteps(), linear.getNanoseconds());
        System.out.printf("%-20s %-18s %-12d %-15d%n", "Search", "Binary Search", binary.getSteps(), binary.getNanoseconds());
        System.out.printf("%-20s %-18s %-12d %-15d%n", "Graph Traversal", "BFS", bfs.size(), bfsTime);
        System.out.printf("%-20s %-18s %-12d %-15d%n", "Graph Traversal", "DFS", dfs.size(), dfsTime);
        System.out.println("-------------------------------------------------------------");
        System.out.println("Linear result: " + linear.getIndex());
        System.out.println("Binary result (sorted array index): " + binary.getIndex());
        System.out.println("BFS result: " + bfs);
        System.out.println("DFS result: " + dfs);
        System.out.println("Complexity: Linear O(n), Binary O(log n), BFS O(V+E), DFS O(V+E).");
        System.out.println("=============================================");
    }
}
