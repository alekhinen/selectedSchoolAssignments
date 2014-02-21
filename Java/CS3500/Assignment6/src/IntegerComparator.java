/**
 * Assignment 4 - MyMap
 * @author Nick Alekhine
 * alekhn@ccs.neu.edu
 * 
 */

import java.util.Comparator;

/**
 * A simple integer comparator. Used in testing for MyMapTest.java
 * @author Nick Alekhine
 * @version 2014-01-31
 *
 */
public class IntegerComparator implements Comparator<Integer> {

    /**
     * To compare two different integers. 
     * @param o1 Integer - one given integer
     * @param o2 Integer - the other given integer
     * @return integer representing the difference between the two integers.
     */
    @Override
    public int compare(Integer o1, Integer o2) {
        return o1 - o2;
    }

}
