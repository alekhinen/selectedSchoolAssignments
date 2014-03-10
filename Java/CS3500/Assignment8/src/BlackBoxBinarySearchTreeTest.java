/**
 * Assignment 7 - MyMap using RB Binary Trees
 * @author Nick Alekhine
 * alekhn@ccs.neu.edu
 * 
 */


// Libraries //////////////////////////////////////////////////////////////////
import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Iterator;
import java.util.NoSuchElementException;

import mymap.IntegerComparator;
import mymap.MyMap;

import org.junit.Test;


// BinarySearchTreeTest ///////////////////////////////////////////////////////
/**
 * To test MyMap's methods for BinarySearchTree, EmptyNode, and Node.
 * @author Nick Alekhine
 * @version 2014-02-05
 */
public class BlackBoxBinarySearchTreeTest {

    // Initialize MyMap Instances /////////////////////////////////////////////
    /** Empty */
    MyMap<Integer, String> m0;
    /** Include 1 */
    MyMap<Integer, String> m1;
    /** Include 2 */
    MyMap<Integer, String> m2;
    /** Include 3 */
    MyMap<Integer, String> m3;
    /** Include 4 */
    MyMap<Integer, String> m4;
    /** Include 5 */
    MyMap<Integer, String> m5;
    /** Include 6 */
    MyMap<Integer, String> m6;
    
    /** Iterator for m0 */
    Iterator<Integer> i0;
    /** Iterator for m2 */
    Iterator<Integer> i2;
    /** Iterator for m4 */
    Iterator<Integer> i4;
    
    /** IntegerComparator */
    Comparator<Integer> compI = new IntegerComparator();
    
    /** Ordered Iterator for m0 */
    Iterator<Integer> oi0;
    /** Ordered Iterator for m0 */
    Iterator<Integer> oi2;
    /** Ordered Iterator for m0 */
    Iterator<Integer> oi4;
    
    
    
    
    
    // Set/Reset Values ///////////////////////////////////////////////////////
    /**
     * To set/reset the values of the initialized MyMap instances.
     */
    void reset() {
        m0 = MyMap.empty(compI);
        m1 = m0.include(1, "a");
        m2 = m1.include(2, "b");
        m3 = m2.include(3, "c");
        m4 = m3.include(1, "d");
        m5 = m4.include(0, "z");
        
        m6 = MyMap.empty(compI);
        m6 = m6.include(10, "a");
        m6 = m6.include(15, "b");
        m6 = m6.include(20, "c");
        m6 = m6.include(25, "d");
        m6 = m6.include(23, "e");
        m6 = m6.include(5, "f");
        m6 = m6.include(4, "g");
        m6 = m6.include(7, "h");
        m6 = m6.include(8, "i");
        
        i0 = m0.iterator();
        i2 = m2.iterator();
        i4 = m4.iterator();
        
        oi0 = m0.iterator(compI);
        oi2 = m2.iterator(compI);
        oi4 = m4.iterator(compI);
    }
    
    
    
    
    
    // Method-Specific Tests /////////////////////////////////
    
    /**
     * To test the isEmpty method in MyMap.
     */
    @Test
    public void testIsEmpty() {
        this.reset();
        assertTrue(m0.isEmpty());
        assertFalse(m1.isEmpty());
        assertFalse(m4.isEmpty());
    }
    
    
    /**
     * To test the size method in MyMap.
     */
    @Test
    public void testSize() {
        this.reset();
        assertEquals(m0.size(), 0);
        assertEquals(m1.size(), 1);
        assertEquals(m2.size(), 2);
        assertEquals(m3.size(), 3);
        assertEquals(m4.size(), 3);
    }
    
    
    /**
     * To test the containsKey method in MyMap.
     */
    @Test
    public void testContainsKey() {
        this.reset();
        assertFalse(m0.containsKey(1));
        assertTrue(m1.containsKey(1));
        assertFalse(m2.containsKey(4));
        assertTrue(m4.containsKey(3));
        assertTrue(m5.containsKey(3));
        assertTrue(m5.containsKey(0));
        assertTrue(m5.containsKey(2));
    }
    
    
    /**
     * To test the get method in MyMap.
     */
    @Test
    public void testGet() {
        this.reset();
        assertEquals(m1.get(1), "a");
        assertEquals(m4.get(1), "d");
        assertEquals(m5.get(0), "z");
        assertEquals(m5.get(3), "c");
        
        // Test get() for Empty class
        try {
            m0.get(20);
        }
        catch (RuntimeException e) {
            System.out.println("Cannot call get on an empty map.");
            System.out.println(e);
            assertTrue(true);
        }
    }
    
    
    /**
     * To test the set method in MyMap.
     */
    @Test
    public void testSet() {
        this.reset();
        assertEquals(m0.set(1, "bobby"), m0.include(1, "bobby"));
        assertEquals(m1.set(1, "bobby"), m0.include(1, "bobby"));
        assertEquals(m3.set(10, "bot"), m3.include(10, "bot"));
        assertEquals(m4.set(1, "robb"), m3.include(1, "robb"));
        assertEquals(m1.set(100, "boo"), m1.include(100, "boo"));
        assertEquals(m2.set(1, "yup"), m0.include(1, "yup").include(2, "b"));
        assertEquals(m5.set(0, "plop"), m4.include(0, "plop"));
        assertEquals(m0.set(1, "bob"), m0.include(1, "bob"));
    }
    
    
    /**
     * To test the toString method in MyMap.
     */
    @Test
    public void testToString() {
        this.reset();
        assertEquals(m0.toString(), "{...(0 key(s) mapped to value(s))...}");
        assertEquals(m1.toString(), "{...(1 key(s) mapped to value(s))...}");
        assertEquals(m4.toString(), "{...(3 key(s) mapped to value(s))...}");
    }
    
    
    /**
     * To test the hashCode method in MyMap.
     */
    @Test
    public void testHashCode() {
        this.reset();
        assertEquals(m0.hashCode(), 0);
        assertNotSame(m1.hashCode(), 0);
        assertNotSame(m4.hashCode(), m2.hashCode());
        assertNotSame(m4.hashCode(), m3.hashCode());
    }
    
    
    /**
     * To test the equals & containsAll method in MyMap.
     */
    @Test
    public void testEquals() {
        this.reset();
        assertFalse(m1.equals(null));
        assertFalse(m1.equals("this"));
        assertTrue(m0.equals(MyMap.empty(compI)));
        assertTrue(m1.equals(m0.include(1, "a")));
        assertFalse(m0.equals(m1));
        assertFalse(m0.equals("bobby"));
        assertFalse(m3.equals(m1));
        assertTrue(m4.equals(m4));
        assertFalse(m4.equals(m3));
        assertFalse(m4.equals(m3.include(1, "yoooo")));
        assertTrue(m4.equals(m3.include(1, "d")));
    }
    
    
    /**
     * To test the keyList method in MyMap
     */
    @Test
    public void testKeyList() {
        this.reset();
        ArrayList<Integer> testAr = new ArrayList<Integer>();
        assertEquals(m0.keyList(new ArrayList<Integer>()), testAr);
        
        testAr.add(1);
        testAr.add(2);
        assertEquals(m2.keyList(new ArrayList<Integer>()), testAr);
        
        testAr.add(3);
        assertEquals(m3.keyList(new ArrayList<Integer>()), testAr);
    }
    
    
    /**
     * To test the KeyIterator that is created when called by the 
     * iterator method in MyMap.
     */
    @Test
    public void testKeyIterator() {
        this.reset();
        assertFalse(i0.hasNext());
        assertTrue(i2.hasNext());
        assertEquals(i4.next(), new Integer(1));
        assertEquals(i4.next(), new Integer(2));
        assertEquals(i4.next(), new Integer(3));
        assertEquals(i2.next(), new Integer(1));
        assertEquals(i2.next(), new Integer(2));
        
        // Testing next method on empty.
        try {
            i2.next();
        }
        catch (NoSuchElementException e) {
            System.out.println("Cannot iterate past empty");
            System.out.println(e);
            assertTrue(true);
        }
        
        
        // Testing the remove method.
        try {
            i4.remove();
        }
        catch (UnsupportedOperationException e) {
            System.out.println("remove() is an unsupported method.");
            System.out.println(e);
            assertTrue(true);
        }
    }
    
    
    /**
     * To test the OrderedKeyIterator that is created when called by the 
     * iterator method in MyMap.
     */
    @Test
    public void testOrderedKeyIterator() {
        this.reset();
        assertFalse(oi0.hasNext());
        assertTrue(oi2.hasNext());
        assertEquals(oi4.next(), new Integer(1));
        assertEquals(oi4.next(), new Integer(2));
        assertEquals(oi4.next(), new Integer(3));
        assertEquals(oi2.next(), new Integer(1));
        assertEquals(oi2.next(), new Integer(2));
        
        // Testing next method on empty.
        try {
            oi2.next();
        }
        catch (NoSuchElementException e) {
            System.out.println("Cannot iterate past empty");
            System.out.println(e);
            assertTrue(true);
        }
        
        
        
        // Testing the remove method.
        try {
            oi4.remove();
        }
        catch (UnsupportedOperationException e) {
            System.out.println("remove() is an unsupported method.");
            System.out.println(e);
            assertTrue(true);
        }
    }
    
}
