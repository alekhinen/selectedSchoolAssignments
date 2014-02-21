/**
 * Assignment 6 - MyMap Testing
 * @author Nick Alekhine
 * alekhn@ccs.neu.edu
 * 
 */

import java.util.ArrayList;
import java.util.Iterator;

/**
 * Testing performance of MyMap: Binary Search Tree (BST) 
 * implementation. Modified from initial version by Will Clinger
 * 
 * @author Jessica Young Schmidt jschmidt
 * @author Nick Alekhine 
 * @version 2014-02-14
 */
public class TestMyMapPerformance {

    /**
     * Runs the tests
     * @param args - command line arguments
     */
    public static void main(String [] args) {
        /** instance of TestMyMapA05Performance */
        TestMyMapPerformance test = new TestMyMapPerformance();

        try {
            test.performance();
        }
        catch (ClassCastException e) {
            System.out.println("Exception thrown during performance tests:");
            System.out.println(e);
        }

    }
    
    
    /**
     * To output the time it takes to run several methods on the given
     * map with the given input file. 
     * @param compName String - the name of the comparator used
     * @param map MyMap<String, Integer> - the given map to run on
     * @param inputFile String - the file to be added to the map
     * @param numString int - the amount strings to iterate through 
     */
    private void iterationTimer(String compName, 
                                MyMap<String, Integer> map, 
                                String inputFile,
                                int numString) {

        System.out.print(compName + ", "
                         + inputFile + ", " 
                         + numString + ", ");

        
        
        ///////////////////////////////////////////////////////////////////////
        // Include Benchmark //////////////////////////////////////////////////
        long tStart = System.currentTimeMillis();
        
        Iterator<String> sit = new StringIterator(inputFile);
        int count = 0;
        while (count < numString && sit.hasNext()) {
            count++;
            map = map.include(sit.next(), count);
        }
        
        long tFinish = System.currentTimeMillis();
        System.out.print(map.size() + ", ");
        System.out.print((tFinish - tStart) + ", ");
        
        
        
        ///////////////////////////////////////////////////////////////////////
        // Iterator Benchmark /////////////////////////////////////////////////
        tStart = System.currentTimeMillis();
        
        Iterator<String> it = map.iterator();
        
        tFinish = System.currentTimeMillis();
        System.out.print((tFinish - tStart) + ", ");
        
        
        
        ///////////////////////////////////////////////////////////////////////
        // Iterate Benchmark //////////////////////////////////////////////////
        tStart = System.currentTimeMillis();
        
        while (it.hasNext()) { 
            it.next(); 
        }
        
        tFinish = System.currentTimeMillis();
        System.out.print((tFinish - tStart) + ", ");
        
        
        
        ///////////////////////////////////////////////////////////////////////
        // ContainsKey Benchmark //////////////////////////////////////////////
        tStart = System.currentTimeMillis();
        
        int countContainsTrue = 0;
//        int countContains = 0;
        StringIterator si = new StringIterator("contains_list.txt");
        for (String s : si) {
            if (map.containsKey(s)) {
                countContainsTrue++;
            }
//            countContains++;
        }
        
        tFinish = System.currentTimeMillis();
        System.out.print((tFinish - tStart) + ", ");
        System.out.println(countContainsTrue);
        
        

        
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
        
        System.out.println("Comparator, File, Num Strings, Size (#), "
                            + "Build (ms), Iterator (ms), Iterate (ms), "
                            + "Contains (ms), Num Contained \n");
        
        
        // the number of strings to iterate through
        int size = 2000;
        
        // the list of files to test on.
        ArrayList<String> files = new ArrayList<String>();
        files.add("random_order.txt");
        files.add("lexicographically_ordered.txt");
        files.add("reverse_ordered.txt");
        
        ///////////////////////////////////////////////////////////////////////
        // No Comparator //////////////////////////////////////////////////////
        // go through each element in the file list
        for (String f : files) {
            // set size back down to 2000
            size = 2000;
            // go through each size iteration
            while (size <= 16000) {
                // benchmark this map on this file on this size.
                this.iterationTimer("null", m0, f, size);
                
                size = size * 2;
            }
        }

        
        ///////////////////////////////////////////////////////////////////////
        // StringByLex ////////////////////////////////////////////////////////
        // go through each element in the file list
        for (String f : files) {
            // set size back down to 2000
            size = 2000;
            // go through each size iteration
            while (size <= 16000) {
                // benchmark this map on this file on this size.
                this.iterationTimer("StringByLex", m1, f, size);
                
                size = size * 2;
            }
        }
        
        
        ///////////////////////////////////////////////////////////////////////
        // StringReverseByLex /////////////////////////////////////////////////
        // go through each element in the file list
        for (String f : files) {
            // set size back down to 2000
            size = 2000;
            // go through each size iteration
            while (size <= 16000) {
                // benchmark this map on this file on this size.
                this.iterationTimer("StringReverseByLex", m2, f, size);
                
                size = size * 2;
            }
        }
        
        
        System.out.println("\n\n");
        ///////////////////////////////////////////////////////////////////////
        // No Comparator //////////////////////////////////////////////////////
        this.iterationTimer("null", m0, "hippooath.txt", 466);
        this.iterationTimer("null", m0, 
                            "Confucius_The_Great_Learning.txt", 3156);
        this.iterationTimer("null", m0, "Apology_Plato.txt", 11571);
        
        
        
        
        
        ///////////////////////////////////////////////////////////////////////
        // StringByLex ////////////////////////////////////////////////////////
        this.iterationTimer("StringByLex", m1, "hippooath.txt", 466);
        this.iterationTimer("StringByLex", m1, 
                            "Confucius_The_Great_Learning.txt", 3156);
        this.iterationTimer("StringByLex", m1, "Apology_Plato.txt", 11571);
        
        
        
        
        ///////////////////////////////////////////////////////////////////////
        // StringReverseByLex /////////////////////////////////////////////////
        this.iterationTimer("StringReverseByLex", m2, "hippooath.txt", 466);
        this.iterationTimer("StringReverseByLex", m2, 
                            "Confucius_The_Great_Learning.txt", 3156);
        this.iterationTimer("StringReverseByLex", m2, 
                            "Apology_Plato.txt", 11571);
        
        
    }

}
