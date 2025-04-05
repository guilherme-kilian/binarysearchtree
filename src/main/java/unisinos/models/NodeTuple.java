package unisinos.models;

public class NodeTuple<TKey extends Integer, TValue> {
    public Node<TKey, TValue> parent;
    public Node<TKey, TValue> current;

    public NodeTuple(Node<TKey, TValue> parent, Node<TKey, TValue> current) {
        this.parent = parent;
        this.current = current;
    }
}
