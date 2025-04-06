package unisinos.models;

public abstract class BinarySearchTreeADT<TKey extends Integer, TValue> {

    public abstract void clear();

    public abstract Boolean isEmpty();

    public abstract Node<TKey, TValue> search(TKey key);

    public abstract void insert(TKey key, TValue value);

    public abstract Boolean delete(TKey key);

    public abstract void preOrderTraversal();

    public abstract void inOrderTraversal();

    public abstract void postOrderTraversal();

    public abstract void levelOrderTraversal();

    public abstract int countInternal();

    public abstract int degree(TKey key);

    public abstract int height(TKey key);

    public abstract int level(TKey key);

    public abstract String ancestor(TKey key);
}
