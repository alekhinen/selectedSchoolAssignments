/**
 * @author - Nick Alekhine
 * @email  - alekhn@ccs.neu.edu
 * @course - CS3500
 */

/**
 * Empty represents a set that contains no elements
 * @author Nick Alekhine
 * @version 2014-01-11
 * 
 */
public class Empty extends MySet {
    
    /**
     * Construct for Empty
     */
    Empty() {
        
    }
    
    
    
    
    /**
     * Method returns a string value of this set
     * @return String value of this set 
     */
    public String toString() {
        return "{...(This set contains 0 elements)...}";
    }
    
    
    /**
     * Method returns whether this set is equal to the given object.
     * @param o Object - the given Object
     * @return boolean 
     */
    public boolean equals(Object o) {
        return o instanceof Empty;
    }
    
    
    /**
     * Method returns an int value for this set.
     * @return int - value for this set
     */
    public int hashCode() {
        return 0;
    }
    
    
    /**
     * Method returns whether the set is empty
     * @return boolean stating whether this set is empty
     */
    boolean isEmptyMethod() {
        return true;
    }

    
    /**
     * Returns the size of the set
     * @return size of this set
     */
    int sizeMethod() {
        return 0;
    }
    
    
    /**
     * Returns whether the long is in the set
     * @param l - the long that is being searched for
     * @return boolean value
     */
    boolean containsMethod(Long l) {
        return false;
    }
    
    
    /**
     * Returns whether the second given set is within this
     * set.
     * @param m - the set that is to be found within this set.
     * @return boolean
     */
    boolean isSubsetMethod(MySet m) {
        return true;
    }
    
    
    /**
     * Returns a set that has the given element removed from this set
     * @param l Long - the element to be removed from the set
     * @return MySet that has the given element removed
     */
    MySet removeMethod(Long l) {
        return this;
    }
    
    
    /**
     * Method returns a set with unique elements from this set and the given.
     * @param m MySet - another given set
     * @return MySet - the set of unique elements from both sets
     */
    MySet joinMethod(MySet m) {
        return m;
    }
    
    
    /**
     * Returns a set with elements that are found in this set and the 
     * given set.
     * @param m MySet - the given set
     * @return MySet that contains elements from both sets.
     */
    MySet intersectMethod(MySet m) {
        return this;
    }
    
    
    /**
     * Method returns a set where the first given long is found
     * in this set and replaced with the second given long.
     * @param l1 Long - the Long to be found and replaced in the set
     * @param l2 Long - the Long to be placed in the set.
     * @return MySet with a swapped element. 
     */
    MySet replaceMethod(Long l1, Long l2) {
        return this;
    }
}
