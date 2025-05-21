import java.util.*;

public class WeightedGraph<V> {
    private final boolean undirected;
    private final Map<Vertex<V>, List<Edge<V>>> map = new HashMap<>();

    public WeightedGraph() {
        this(true);
    }

    public WeightedGraph(boolean undirected) {
        this.undirected = undirected;
    }

    public void addVertex(V v) {
        map.putIfAbsent(new Vertex<>(v), new LinkedList<>());
    }

    public void addEdge(V source, V dest, double weight) {
        Vertex<V> v1 = new Vertex<>(source);
        Vertex<V> v2 = new Vertex<>(dest);

        if (!hasVertex(v1))
            addVertex(source);

        if (!hasVertex(v2))
            addVertex(dest);

        if (hasEdge(v1, v2))
        return;

        map.get(v1).add(new Edge<>(v1, v2, weight));

        if (undirected)
            map.get(v2).add(new Edge<>(v2, v1, weight));
    }

    public boolean hasVertex(Vertex<V> v) {
        return map.containsKey(v);
    }

    public boolean hasEdge(Vertex<V> source, Vertex<V> dest) {
        if (!hasVertex(source)) return false;
        return map.get(source).stream().anyMatch(e -> e.getDest().equals(dest));
    }

    public List<Vertex<V>> adjacencyList(V v) {
        Vertex<V> vertex = new Vertex<>(v);
        if (!map.containsKey(vertex)) return Collections.emptyList();

        List<Vertex<V>> vertices = new LinkedList<>();
        for (Edge<V> edge : map.get(vertex)) {
            vertices.add(edge.getDest());
        }
        return vertices;
    }

    public List<Edge<V>> getEdges(Vertex<V> v) {
        return map.getOrDefault(v, Collections.emptyList());
    }

    public Vertex<V> getVertex(V data) {
        for (Vertex<V> v : map.keySet()) {
            if (v.getData().equals(data)) {
                return v;
            }
        }
        return null;
    }
}