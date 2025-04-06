import org.junit.jupiter.api.Test;
import unisinos.models.BinarySearchTree;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class BinarySearchTreeTest {

    private BinarySearchTree<Integer, String> getList(){
        var list = new BinarySearchTree<Integer, String>();
        list.insert(10, "batata");
        list.insert(12, "pao");
        list.insert(8, "alface");
        list.insert(9, "cenoura");
        list.insert(3, "tomate");
        list.insert(2, "carne");
        list.insert(5, "carne");
        list.insert(27, "bacon");
        list.insert(15, "ovo");
        list.insert(28, "queijo");
        list.insert(29, "cheddar");

        return list;
    }

    @Test
    public void test_degree(){
        var list = getList();
        assertEquals(2,  list.degree(10));
        assertEquals(0, list.degree(5));
        assertEquals(-1, list.degree(4));
        assertEquals(1, list.degree(28));
    }

    @Test
    public void test_internalNode(){
        var list = getList();
        assertEquals(5,  list.countInternal());
    }

    @Test
    public void test_height() {
        var list = getList();
        assertEquals(4, list.height(10));
        assertEquals(0, list.height(5));
        assertEquals(-1, list.height(4));
        assertEquals(1, list.height(28));
    }

    @Test
    public void test_level() {
        var list = getList();
        assertEquals(0, list.level(10));
        assertEquals(2, list.level(9));
        assertEquals(-1, list.level(4));
        assertEquals(3, list.level(28));
    }

    @Test
    public void test_ancestor() {
        var list = getList();
        assertEquals("10 12 27", list.ancestor(28));
        assertEquals("10 8", list.ancestor(9));
        assertEquals(null, list.ancestor(10));
        assertEquals(null, list.ancestor(4));
    }
}
