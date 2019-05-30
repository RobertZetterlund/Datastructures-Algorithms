import org.junit.*;

import java.util.Iterator;

import static org.junit.Assert.*;

public class TestSplay {


    @Test
    public void testZagZag(){
        SplayWithGet<Integer> splayInt = new SplayWithGet();


        splayInt.add(3);
        splayInt.add(2);
        splayInt.add(1);
        splayInt.add(4);
        splayInt.add(5);

        splayInt.get(5);
        //splayInt.rotateRight(new BinarySearchTree.Entry(1));
        System.out.println(splayInt);
        assertEquals(5,splayInt.root.element.intValue());


    }
    @Test
    public void testZigZig(){
        SplayWithGet<Integer> splayInt = new SplayWithGet();
        splayInt.add(3);
        splayInt.add(2);
        splayInt.add(1);
        splayInt.add(4);
        splayInt.add(5);

        splayInt.get(1);
        assertEquals(1,splayInt.root.element.intValue());
        System.out.println(splayInt);
    }
    @Test
    public void checkLeftChild(){
        SplayWithGet<Integer> splayInt = new SplayWithGet();
        splayInt.add(3);
        splayInt.add(2);
        splayInt.add(1);
        splayInt.add(4);
        splayInt.add(5);

        splayInt.get(2);
        assertEquals(2,splayInt.root.element.intValue());
        System.out.println(splayInt);

    }
}
