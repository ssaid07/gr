import java.util.*;

public class DijkstraSearch<V> extends Search<V> {
    private Map<V, Double> distTo;

    public DijkstraSearch(WeightedGraph<V> graph, V start) {
        super();
        this.start = start;
        this.distTo = new HashMap<>();
        dijkstra(graph, start);
    }

    private void dijkstra(WeightedGraph<V> graph, V start) {
        PriorityQueue<VertexDist> pq = new PriorityQueue<>();
        Vertex<V> startVertex = graph.getVertex(start);

        for (Vertex<V> v : graph.getEdges(startVertex).stream().map(Edge::getDest).toList()) {
            distTo.put(v.getData(), Double.POSITIVE_INFINITY);
        }
        distTo.put(start, 0.0);
        pq.add(new VertexDist(start, 0.0));

        while (!pq.isEmpty()) {
            V v = pq.remove().vertex;
            Vertex<V> current = graph.getVertex(v);
            for (Edge<V> e : graph.getEdges(current)) {
                relax(e, pq);
            }
        }
    }

    private void relax(Edge<V> e, PriorityQueue<VertexDist> pq) {
        V v = e.getSource().getData();
        V w = e.getDest().getData();
        double newDist = distTo.get(v) + e.getWeight();

        if (distTo.getOrDefault(w, Double.POSITIVE_INFINITY) > newDist) {
            distTo.put(w, newDist);
            edgeTo.put(w, v);
            pq.add(new VertexDist(w, newDist));
        }
    }

    @Override
    public boolean hasPathTo(V destination) {
        return distTo.getOrDefault(destination, Double.POSITIVE_INFINITY) < Double.POSITIVE_INFINITY;
    }

    @Override
    public Iterable<V> pathTo(V destination) {
        if (!hasPathTo(destination)) return Collections.emptyList();
        return buildPath(destination);
    }

    private class VertexDist implements Comparable<VertexDist> {
        V vertex;
        double dist;

        VertexDist(V vertex, double dist) {
            this.vertex = vertex;
            this.dist = dist;
        }

        @Override
        public int compareTo(VertexDist other) {
            return Double.compare(this.dist, other.dist);
        }
    }
}