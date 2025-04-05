package unisinos.models;

public abstract class BinarySearchTreeADT<T> {

    public abstract void clear();

    public abstract Boolean isEmpty();

    public abstract T search(T key);

    public abstract void insert(T key);

    public abstract Boolean delete(T key);

    public abstract void preOrderTraversal();

    public abstract void inOrderTraversal();

    public abstract void postOrderTraversal();

    public abstract void levelOrderTraversal();

    public abstract int countInternal();

    public abstract int degree(T key);

    public abstract int height(T key);

    public abstract int level(T key);

    public abstract String ancestor(T key);
}
