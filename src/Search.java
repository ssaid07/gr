import java.util.*;

public abstract class Search<V> {
    protected Map<V, V> edgeTo;
    protected V start;

    public Search() {
        this.edgeTo = new HashMap<>();
    }

    public abstract boolean hasPathTo(V destination);
    public abstract Iterable<V> pathTo(V destination);

    protected List<V> buildPath(V destination) {
        List<V> path = new LinkedList<>();
        if (!edgeTo.containsKey(destination)) return path;

        for (V v = destination; v != null && !v.equals(start); v = edgeTo.get(v)) {
            path.add(0, v);
        }
        path.add(0, start);
        return path;
    }
}