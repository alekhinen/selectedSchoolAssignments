/**
 * Assignment 6 - MyMap Testing
 * @author Nick Alekhine
 * alekhn@ccs.neu.edu
 * 
 */

import java.util.ArrayList;
import java.util.Iterator;

import mymap.MyMap;

/**
 * Testing performance of MyMap: Binary Search Tree (BST) 
 * implementation. Modified from initial version by Will Clinger
 * 
 * @author Jessica Young Schmidt jschmidt
 * @author Nick Alekhine 
 * @version 2014-02-14
 */
public class TestMyMapStructure {

    /**
     * Runs the tests
     * @param args - command line arguments
     */
    public static void main(String [] args) {
        /** instance of TestMyMapA05Performance */
        TestMyMapStructure test = new TestMyMapStructure();

        try {
            test.performance();
        }
        catch (ClassCastException e) {
            System.out.println("Exception thrown during performance tests:");
            System.out.println(e);
        }

    }
    
    




    
    
    /**
     * To run the benchmark testing on three different types of maps.
     */
    private void performance() {
        System.out.println();
        System.out.println("Timing public operations...");
        MyMap<String, Integer> m0 = MyMap.empty();
        MyMap<String, Integer> m1 = MyMap.empty(new StringByLex());
        MyMap<String, Integer> m2 = MyMap.empty(new StringReverseByLex());
        
        m1 = m1.include("jack", 10);
//        System.out.println(m1.toString());
        m1 = m1.include("ellie", 5);
//        System.out.println(m1.toString());
        m1 = m1.include("dan", 4);
//        System.out.println(m1.toString());
        m1 = m1.include("ben", 2);
//        System.out.println("\n\n\n\n");
//        System.out.println(m1.toString());
        m1 = m1.include("carol", 3);
//        System.out.println(m1.toString());
        m1 = m1.include("oliver", 15);
        m1 = m1.include("tim", 20);
//        System.out.println(m1.toString());
        m1 = m1.include("yvette", 25);
//        System.out.println(m1.toString());
        m1 = m1.include("victor", 22);
        System.out.println(m1.toString());
//        System.out.println("\n\n\n\n\n");
//        System.out.println(m1.toString());
        
        
        
        System.out.println("Test completed.");
        
        
    }

}
