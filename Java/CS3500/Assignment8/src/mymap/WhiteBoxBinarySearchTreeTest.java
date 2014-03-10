/**
 * Assignment 7 - MyMap using RB Binary Trees
 * @author Nick Alekhine
 * alekhn@ccs.neu.edu
 * 
 */

package mymap;
import static org.junit.Assert.*;

import java.util.Comparator;
import java.util.Iterator;
import java.util.NoSuchElementException;

import org.junit.Test;


/**
 * To test the various protected methods inside BinarySearchTree. 
 * This class mainly tests methods for the EmptyNode class. 
 * @author Nick Alekhine
 * @version 2014-02-26
 *
 */
public class WhiteBoxBinarySearchTreeTest {

    // Initialize MyMap Instances /////////////////////////////////////////////
    /** Empty */
    BinarySearchTree<Integer, String> m0;
    /** Include 1 */
    BinarySearchTree<Integer, String> m1;
    /** Include 2 */
    BinarySearchTree<Integer, String> m2;
    /** Include 3 */
    BinarySearchTree<Integer, String> m3;
    /** Include 4 */
    BinarySearchTree<Integer, String> m4;
    
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
    
    
    
    
    
    ///////////////////////////////////////////////////////////////////////////
    // Set/Reset Values ///////////////////////////////////////////////////////
    /**
     * To set/reset the values of the initialized MyMap instances.
     */
    void reset() {
        m0 = new EmptyNode<Integer, String>(compI);
        m1 = (BinarySearchTree<Integer, String>) m0.include(1, "a");
        m2 = (BinarySearchTree<Integer, String>) m1.include(2, "b");
        m3 = (BinarySearchTree<Integer, String>) m2.include(3, "c");
        m4 = (BinarySearchTree<Integer, String>) m3.include(1, "d");
        
        i0 = m0.iterator();
        i2 = m2.iterator();
        i4 = m4.iterator();
        
        oi0 = m0.iterator(compI);
        oi2 = m2.iterator(compI);
        oi4 = m4.iterator(compI);
    }

    
    
    
    
    ///////////////////////////////////////////////////////////////////////////
    // Method-Specific Tests //////////////////////////////////////////////////
    /**
     * To test the isBlack method in BST
     */
    @Test
    public void testIsBlack() {
        this.reset();
        assertTrue(m0.isBlack());
        assertFalse(m1.isBlack());
        assertTrue(m2.isBlack());
    }
    
    
    /**
     * To test the makeBlack method in BST
     */
    @Test
    public void testMakeBlack() {
        this.reset();
        assertEquals(m0.makeBlack(), new EmptyNode<Integer, String>(compI));
    }
    
    
    /**
     * To test the getKey method in BST
     */
    @Test
    public void testGetKey() {
        this.reset();
        
        // Testing next method on empty.
        try {
            m0.getKey();
        }
        catch (NoSuchElementException e) {
            System.out.println("Cannot get key on an empty!");
            System.out.println(e);
            assertTrue(true);
        }
    }
    
    
    /**
     * To test the getValue method in BST
     */
    @Test
    public void testGetValue() {
        this.reset();
        
        // Testing next method on empty.
        try {
            m0.getValue();
        }
        catch (NoSuchElementException e) {
            System.out.println("Cannot get value on an empty!");
            System.out.println(e);
            assertTrue(true);
        }
    }
    
    
    /**
     * To test the getLeft method in BST
     */
    @Test
    public void testGetLeft() {
        this.reset();
        
        // Testing next method on empty.
        try {
            m0.getLeft();
        }
        catch (IndexOutOfBoundsException e) {
            System.out.println("Cannot get LHS on an empty!");
            System.out.println(e);
            assertTrue(true);
        }
    }
    
    
    /**
     * To test the getRight method in BST
     */
    @Test
    public void testGetRight() {
        this.reset();
        
        // Testing next method on empty.
        try {
            m0.getRight();
        }
        catch (IndexOutOfBoundsException e) {
            System.out.println("Cannot get RHS on an empty!");
            System.out.println(e);
            assertTrue(true);
        }
    }

}
