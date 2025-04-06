import org.junit.jupiter.api.Test;
import unisinos.models.BinarySearchTree;
import static org.junit.jupiter.api.Assertions.assertEquals;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.List;

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
    public void test_preOrderTraversal() {
        var list = getList();

        var outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));

        list.preOrderTraversal();

        var expected = List.of("10", "8", "3", "2", "5", "9", "12", "27", "15", "28", "29");
        var actual = outContent.toString().trim().split("\\R");

        assertEquals(expected, List.of(actual));
    }



    @Test
    public void test_inOrderTraversal() {
        var list = getList();

        var outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));

        list.inOrderTraversal();

        var expected = List.of("2", "3", "5", "8", "9", "10", "12", "15", "27", "28", "29");
        var actual = outContent.toString().trim().split("\\R");

        assertEquals(expected, List.of(actual));
    }

    @Test
    public void test_postOrderTraversal() {
        var list = getList();

        var outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));

        list.postOrderTraversal();

        var expected = List.of("2", "5", "3", "9", "8", "15", "29", "28", "27", "12", "10");
        var actual = outContent.toString().trim().split("\\R");

        assertEquals(expected, List.of(actual));
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
}
