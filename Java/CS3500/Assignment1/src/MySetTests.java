import junit.framework.TestCase;
import org.junit.Test;

/**
 * @author - Nick Alekhine
 * @email  - alekhn@ccs.neu.edu
 * @course - CS3500
 */

/**
 * Testing class for MySet and all classes that extend it.
 * @author Nick Alekhine
 * @version 2014-01-11
 */
public class MySetTests extends TestCase {
    
    // Initialize MySet objects
    private MySet s0;
    private MySet s1;
    private MySet s2;
    private MySet s3;
    private MySet s4;
    private MySet s5;
    private MySet s6;
    private MySet s7;
    
    
    /**
     * To create MySet objects
     */
    public void creation() {
        s0 = MySet.empty();
        s1 = MySet.insert(s0, new Long(20));
        s2 = MySet.insert(s1, new Long(40));
        s3 = MySet.insert(s2, new Long(60));
        s4 = MySet.insert(s3, new Long(10));
        s5 = MySet.insert(s4, new Long(30));
        s6 = MySet.insert(s5, new Long(50));
        s7 = MySet.insert(s6, new Long(60));
    }
    

    
    
    /**
     * To test the toString method in MySet
     */
    @Test
    public void testToString() {
        this.creation();
        assertEquals(s0.toString(), "{...(This set contains 0 elements)...}");
        assertEquals(s6.toString(), "{...(This set contains 6 elements)...}");
    }
    
    /**
     * To test the equals method in MySet 
     */
    @Test
    public void testEquals() {
        this.creation();
        assertTrue(s0.equals(MySet.empty()));
        assertFalse(s0.equals(s1));
        assertFalse(s0.equals(new Long(20)));
        assertTrue(s1.equals(MySet.insert(s0, new Long(20))));
        assertFalse(s1.equals(s4));
    }
    
    
    /**
     * To test the hashCode method in MySet 
     */
    @Test
    public void testHashCode() {
        this.creation();
        assertEquals(s0.hashCode(), 0);
        assertEquals(s1.hashCode(), 20);
        assertEquals(s7.hashCode(), 210);
    }
    
    
    /**
     * To test the isEmpty method in MySet
     */
    @Test
    public void testIsEmpty() {
        this.creation();
        assertTrue(MySet.isEmpty(s0));
        assertFalse(MySet.isEmpty(s1));
    }
    
    
    /**
     * To test the size method in MySet
     */
    @Test
    public void testSize() {
        this.creation();
        assertEquals(MySet.size(s0), 0);
        assertEquals(MySet.size(s1), 1);
        assertEquals(MySet.size(s6), 6);
        assertEquals(MySet.size(s7), 6);
    }
    
    
    /**
     * To test the contains method in MySet
     */
    @Test
    public void testContains() {
        this.creation();
        assertFalse(MySet.contains(s0, new Long(10)));
        assertTrue(MySet.contains(s6, new Long(50)));
        assertFalse(MySet.contains(s4, new Long(100)));
    }
    
    
    /**
     * To test the isSubset method in MySet
     */
    @Test
    public void testIsSubset() {
        this.creation();
        assertTrue(MySet.isSubset(s0, s1));
        assertFalse(MySet.isSubset(s1, s0));
        assertFalse(MySet.isSubset(s6, s4));
        assertTrue(MySet.isSubset(s4, s6));
    }
    
    
    /**
     * To test the remove method in MySet
     */
    @Test
    public void testRemove() {
        this.creation();
        assertEquals(MySet.remove(s0, new Long(10)), s0);
        assertEquals(MySet.remove(s1, new Long(20)), s0);
        assertEquals(MySet.remove(s1, new Long(30)), s1);
    }
    
    
    /**
     * To test the join method in MySet
     */
    @Test
    public void testJoin() {
        this.creation();
        assertEquals(MySet.join(s0, s0), s0);
        assertEquals(MySet.join(s0, s4), s4);
        assertEquals(MySet.join(s4, s0), s4);
        assertEquals(MySet.join(s4, s6), s6);
    }
    
    
    /**
     * To test the intersect method in MySet
     */
    @Test
    public void testIntersect() {
        this.creation();
        assertEquals(MySet.intersect(s0, s3), s0);
        assertEquals(MySet.intersect(s3, s0), s0);
        assertEquals(MySet.intersect(s2, s3), s2);
        assertEquals(MySet.intersect(s3, s2), s2);
        assertEquals(MySet.intersect(s6, MySet.insert(s0, new Long(5000))), 
                     s0);
    }
    
    
    /**
     * To test the replace method in MySet
     */
    @Test
    public void testReplace() {
        this.creation();
        assertEquals(MySet.replace(s0, new Long(10), new Long(20)), s0);
        assertEquals(MySet.replace(s4, new Long(5000), new Long(100)), s4);
        assertEquals(MySet.replace(s2, new Long(20), new Long(50)), 
                     MySet.insert(
                      MySet.insert(s0, new Long(50)), new Long(40)));
        assertEquals(MySet.replace(s2, new Long(40), new Long(50)),
                     MySet.insert(s1, new Long(50)));
    }

}