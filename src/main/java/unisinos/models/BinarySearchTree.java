package unisinos.models;

import java.util.LinkedList;

public class BinarySearchTree<TKey extends Integer, TValue> extends BinarySearchTreeADT<TKey, TValue> {

    public Node<TKey, TValue> root;

    @Override
    public void clear() {
        this.root = null;
    }

    @Override
    public Boolean isEmpty() {
        return this.root == null;
    }

    @Override
    public Node<TKey, TValue> search(TKey key) {
        return this.searchNode(this.root, key);
    }

    private Node<TKey, TValue> searchNode(Node<TKey, TValue> current, TKey key) {
        if (current == null) {
            return null;
        } else if (current.key.equals(key)) {
            return current;
        }

        return searchNode(current.next(key), key);
    }

    @Override
    public void insert(TKey key, TValue value) {

        var newNode = new Node<>(key, value);
        this.root = insertNode(this.root, newNode);
    }

    private Node<TKey, TValue> insertNode(Node<TKey, TValue> current, Node<TKey, TValue> newNode) {

        if (current == null) {
            return newNode;
        } else if (newNode.greaterKey(current.key)) {
            current.right = insertNode(current.right, newNode);
        } else if (newNode.lessKey(current.key)) {
            current.left = insertNode(current.left, newNode);
        }

        return current;
    }

    @Override
    public Boolean delete(TKey key) {
        var tuple = searchParent(key);
        var current = tuple.current;
        var parent = tuple.parent;

        if (current == null) {
            return false;
        } else if (current.left != null && current.right != null) {
            var right = current.right;
            while (right.right != null) {
                right = right.right;
            }
            delete(right.key);
            current.key = right.key;
            current.value = right.value;
        } else {
            var next = current.left == null ? current.right : current.left;

            if (current == this.root) {
                this.root = next;
            } else if (current == parent.left) {
                parent.left = next;
            } else {
                parent.right = next;
            }
        }

        return true;
    }

    private NodeTuple<TKey, TValue> searchParent(TKey key) {
        return this.searchParentNode(null, this.root, key);
    }

    private NodeTuple<TKey, TValue> searchParentNode(Node<TKey, TValue> parent, Node<TKey, TValue> current, TKey key) {

        if (current == null) {
            return null;
        } else if (current.key.equals(key)) {
            return new NodeTuple<>(parent, current);
        }

        return searchParentNode(current, current.next(key), key);
    }

    @Override
    public void preOrderTraversal() {
        preOrderTraversal(this.root);
    }

    private void preOrderTraversal(Node<TKey, TValue> node) {

        if(node == null){
            return;
        }

        System.out.println(node.key);
        preOrderTraversal(node.left);
        preOrderTraversal(node.right);
    }

    @Override
    public void inOrderTraversal() {
        inOrderTraversal(this.root);
    }

    private void inOrderTraversal(Node<TKey, TValue> node) {

        if(node == null){
            return;
        }
       
        inOrderTraversal(node.left);
        System.out.println(node.key);
        inOrderTraversal(node.right);
    }

    @Override
    public void postOrderTraversal() {
        postOrderTraversal(this.root);
    }

    private void postOrderTraversal(Node<TKey, TValue> node) {

        if(node == null){
            return;
        }
       
        postOrderTraversal(node.left);        
        postOrderTraversal(node.right);
        System.out.println(node.key);
    }

    @Override
    public void levelOrderTraversal() {

        if (this.root == null) {
            return;
        }

        var queue = getLinkedList();

        while (!queue.isEmpty()) {
            var current = queue.removeFirst();

            if (current == null) {
                break;
            }

            System.out.println(current.key + " ");

            if (current.left != null) {
                queue.add(current.left);
            }

            if (current.right != null) {
                queue.add(current.right);
            }
        }
    }

    @Override
    public int countInternal() {
        int count = 0;

        if (this.root == null) {
            return count;
        }

        var queue = getLinkedList();

        while (!queue.isEmpty()) {
            var current = queue.removeFirst();

            if (current == null) {
                break;
            }

            if ((current.left != null || current.right != null) && this.root != current) {
                count++;
                System.out.println(current.key + " true");
            } else {
                System.out.println(current.key + " false");
            }

            if (current.left != null) {
                queue.add(current.left);
            }

            if (current.right != null) {
                queue.add(current.right);
            }
        }

        return count;
    }

    private LinkedList<Node<TKey, TValue>> getLinkedList() {
        var queue = new LinkedList<Node<TKey, TValue>>();
        queue.add(root);
        return queue;
    }

    @Override
    public int degree(TKey key) {

        var node = this.search(key);

        if (node == null) {
            return -1;
        }

        if (node.right == null && node.left == null) {
            return 0;
        }

        if (node.right != null && node.left != null) {
            return 2;
        }

        return 1;
    }

    @Override
    public int height(TKey tKey) {
        var node = this.search(tKey);
        if (node == null) {
            return -1;
        }

        return calculateHeight(node);
    }

    private int calculateHeight(Node<TKey, TValue> node) {
        if (node == null) {
            return -1;
        }

        int leftHeight = calculateHeight(node.left);
        int rightHeight = calculateHeight(node.right);
        
        return Math.max(leftHeight, rightHeight) + 1;
    }

    @Override
    public int level(TKey tKey) {
        var node = this.search(tKey);
        if (node == null) {
            return -1;
        }

        int level = 0;
        var current = this.root;

        while (current != null) {
            if (current.key.equals(tKey)) {
            return level;
            }

            current = current.next(tKey);
            level++;
        }

        return -1;
    }

    @Override
    public String ancestor(TKey tKey) {
        var node = this.search(tKey);
        if (node == null || node == this.root) {
            return null;
        }

        var ancestors = new StringBuilder();
        var current = this.root;

        while (current != null) {
            if (current.key.equals(tKey)) {
            break;
            }

            ancestors.append(current.key).append(" ");
            current = current.next(tKey);
        }

        return ancestors.toString().trim();
    }
}
