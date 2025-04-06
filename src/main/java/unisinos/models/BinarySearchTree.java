package unisinos.models;

import com.sun.jdi.Value;

import java.sql.Struct;
import java.util.ArrayDeque;
import java.util.LinkedList;
import java.util.Optional;
import java.util.Queue;

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

    private Node<TKey, TValue> searchNode(Node<TKey, TValue> current, TKey key){
        if(current == null){
            return null;
        }
        else if(current.key.equals(key)){
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

        if(current == null){
            return newNode;
        }
        else if(newNode.greaterKey(current.key)){
            current.right = insertNode(current.right, newNode);
        }
        else if(newNode.lessKey(current.key)){
            current.left = insertNode(current.left, newNode);
        }

        return current;
    }

    @Override
    public Boolean delete(TKey key) {
        var tuple = searchParent(key);
        var current = tuple.current;
        var parent = tuple.parent;

        if(current == null){
            return false;
        }
        else if(current.left != null && current.right != null){
            var right = current.right;
            while (right.right != null){
                right = right.right;
            }
            delete(right.key);
            current.key = right.key;
            current.value = right.value;
        }
        else{
            var next = current.left == null ? current.right : current.left;

            if(current == this.root){
                this.root = next;
            }
            else if(current == parent.left){
                parent.left = next;
            }
            else{
                parent.right = next;
            }
        }

        return true;
    }

    private NodeTuple<TKey, TValue> searchParent(TKey key) {
        return this.searchParentNode(null, this.root, key);
    }

    private NodeTuple<TKey, TValue> searchParentNode(Node<TKey, TValue> parent, Node<TKey, TValue> current, TKey key){

        if(current == null){
            return null;
        }
        else if(current.key.equals(key)){
            return new NodeTuple<>(parent, current);
        }

        return searchParentNode(current, current.next(key), key);
    }

    @Override
    public void preOrderTraversal() {

    }

    @Override
    public void inOrderTraversal() {

    }

    @Override
    public void postOrderTraversal() {

    }

    @Override
    public void levelOrderTraversal() {
        if(this.root == null){
            return;
        }

        var queue = new LinkedList<Node<TKey,TValue>>();
        queue.add(root);

        while(!queue.isEmpty()){
            var current = queue.removeFirst();

            if (current == null) {
                break;
            }

            System.out.println(current.key + " ");
            if(current.left != null){
                queue.add(current.left);
            }
            if(current.right != null){
                queue.add(current.right);
            }
        }
    }

    @Override
    public int countInternal() {
        return 0;
    }

    @Override
    public int degree(TKey tKey) {
        return 0;
    }

    @Override
    public int height(TKey tKey) {
        return 0;
    }

    @Override
    public int level(TKey tKey) {
        return 0;
    }

    @Override
    public String ancestor(TKey tKey) {
        return "";
    }
}
