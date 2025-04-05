package unisinos.models;

import java.sql.Struct;

public class Node<TKey extends Integer, TValue> {
    public TValue value;
    public TKey key;
    public Node<TKey, TValue> left;
    public Node<TKey, TValue> right;

    public Node(TKey key, TValue value) {
        this.value = value;
        this.key = key;
    }

    public Node<TKey, TValue> next(TKey key){
        if(greaterKey(key)){
            return left;
        }
        else {
            return right;
        }
    }

    public Boolean greaterKey(int otherKey){
        return (Integer)this.key > otherKey;
    }

    public Boolean lessKey(int otherKey){
        return (Integer)this.key < otherKey;
    }
}
