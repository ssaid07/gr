import java.util.*;

public class DepthFirstSearch<V> extends Search<V> {
    private Map<V, Boolean> marked;

    public DepthFirstSearch(UnweightedGraph<V> graph, V start) {
        super();
        this.start = start;
        this.marked = new HashMap<>();
        dfs(graph, start);
    }

    private void dfs(UnweightedGraph<V> graph, V v) {
        marked.put(v, true);
        for (V w : graph.adjacencyList(v)) {
            if (!marked.getOrDefault(w, false)) {
                edgeTo.put(w, v);
                dfs(graph, w);
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