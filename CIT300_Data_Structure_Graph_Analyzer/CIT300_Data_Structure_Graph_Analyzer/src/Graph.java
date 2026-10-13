
import java.util.*;

public class Graph {
    private final Map<String, List<String>> adjacency = new LinkedHashMap<>();

    public boolean addVertex(String vertex) {
        if (adjacency.containsKey(vertex)) return false;
        adjacency.put(vertex, new ArrayList<>());
        return true;
    }

    public boolean addEdge(String from, String to) {
        if (!adjacency.containsKey(from) || !adjacency.containsKey(to)) return false;
        if (!adjacency.get(from).contains(to)) adjacency.get(from).add(to);
        if (!adjacency.get(to).contains(from)) adjacency.get(to).add(from);
        return true;
    }

    public boolean containsVertex(String vertex) { return adjacency.containsKey(vertex); }

    public void display() {
        if (adjacency.isEmpty()) { System.out.println("Graph is empty."); return; }
        System.out.println("Adjacency List:");
        for (Map.Entry<String, List<String>> e : adjacency.entrySet())
            System.out.println(e.getKey() + " -> " + e.getValue());
    }

    public List<String> bfs(String start) {
        if (!adjacency.containsKey(start)) return Collections.emptyList();
        List<String> order = new ArrayList<>();
        Set<String> visited = new LinkedHashSet<>();
        Queue<String> queue = new ArrayDeque<>();
        queue.add(start); visited.add(start);
        while (!queue.isEmpty()) {
            String current = queue.poll();
            order.add(current);
            for (String neighbour : adjacency.get(current)) {
                if (visited.add(neighbour)) queue.add(neighbour);
            }
        }
        return order;
    }

    public List<String> dfs(String start) {
        if (!adjacency.containsKey(start)) return Collections.emptyList();
        List<String> order = new ArrayList<>();
        Set<String> visited = new LinkedHashSet<>();
        dfsRecursive(start, visited, order);
        return order;
    }

    private void dfsRecursive(String vertex, Set<String> visited, List<String> order) {
        visited.add(vertex);
        order.add(vertex);
        for (String neighbour : adjacency.get(vertex))
            if (!visited.contains(neighbour)) dfsRecursive(neighbour, visited, order);
    }

    public int vertexCount() { return adjacency.size(); }
    public void clear() { adjacency.clear(); }
}
