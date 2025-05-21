import java.util.*;

public class BreadthFirstSearch<V> extends Search<V> {
    private Map<V, Boolean> marked;

    public BreadthFirstSearch(UnweightedGraph<V> graph, V start) {
        super();
        this.start = start;
        this.marked = new HashMap<>();
        bfs(graph, start);
    }

    private void bfs(UnweightedGraph<V> graph, V start) {
        Queue<V> queue = new LinkedList<>();
        queue.add(start);
        marked.put(start, true);

        while (!queue.isEmpty()) {
            V v = queue.remove();
            for (V w : graph.adjacencyList(v)) {
                if (!marked.getOrDefault(w, false)) {
                    edgeTo.put(w, v);
                    marked.put(w, true);
                    queue.add(w);
                }
            }
        }
    }

    @Override
    public boolean hasPathTo(V destination) {
        return marked.getOrDefault(destination, false);
    }

    @Override
    public Iterable<V> pathTo(V destination) {
        if (!hasPathTo(destination)) return Collections.emptyList();
        return buildPath(destination);
    }
}