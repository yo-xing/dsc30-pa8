import org.junit.Before;
import org.junit.Test;

import java.util.LinkedList;

import static org.junit.Assert.*;

public class BinaryHeapTester<T extends Comparable<? super T>> {
    BinaryHeap maxH;
    BinaryHeap minH;
    @Before
    public void setUp() throws Exception {
        maxH = new BinaryHeap();
        minH = new BinaryHeap(5,false);
    }
    @Test
    public void size() {
        assertEquals(0,maxH.size());
        maxH.add(1);
        assertEquals(1,maxH.size());
        maxH.add(2);
        assertEquals(2, maxH.size());
        maxH.add(3);
        assertEquals(3, maxH.size());
        minH.add(4);
        assertEquals(1,minH.size());
        minH.add(5);
        assertEquals(2,minH.size());
        minH.add(6);
        assertEquals(3,minH.size());
    }
    @Test
    public void add() {
        maxH.add(1);
        maxH.add(4);
        maxH.add(9);
        assertEquals(3,maxH.size());
        maxH.clear();
        maxH.add(11);
        assertEquals(1, maxH.size());
        minH.add(99);
        minH.add(88);
        assertEquals(2, minH.size());
    }
    @Test
    public void remove() {
        maxH.add(3);
        maxH.add(2);
        maxH.add(1);
        System.out.println(maxH.size());
        System.out.print("maxH.element()");System.out.println(maxH.element());
        assertEquals(maxH.remove(), 3);
        System.out.print(maxH.size());
        System.out.print("maxH.element()");System.out.println(maxH.element());
        assertEquals(maxH.remove(),new Integer(2));
        assertEquals(maxH.remove(),new Integer(1));
        minH.add(99);
        minH.add(88);
        minH.add(885);
        minH.add(68);
        int elem = 2;
        while(elem < 1000){
            minH.add(elem);
            elem = elem*2;
        }
        assertEquals(new Integer(2),minH.remove());
        assertEquals(new Integer(4),minH.remove());
        assertEquals(new Integer(8),minH.remove());
        assertEquals(new Integer(16),minH.remove());
        assertEquals(new Integer(32),minH.remove());
        assertEquals(new Integer(64),minH.remove());
    }
    @Test
    public void clear() {
        maxH.add(1);
        maxH.add(4);
        maxH.add(9);
        maxH.clear();
        assertEquals(0, maxH.size());
        minH.add(1);
        minH.clear();
        assertEquals(0, minH.size());
        maxH.add(1);
        maxH.clear();
        assertEquals(0, maxH.size());
    }
    @Test
    public void element() {
        maxH.add(1);
        assertEquals(maxH.element(),1);
        maxH.clear();
        maxH.add(12);
        assertEquals(maxH.element(),12);
        minH.add(7);
        maxH.add(1);
    }


}