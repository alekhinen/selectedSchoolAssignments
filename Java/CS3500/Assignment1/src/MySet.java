/**
 * @author - Nick Alekhine
 * @email  - alekhn@ccs.neu.edu
 * @course - CS3500
 */

/** An immutable abstract data type whose values represent finite sets within
 *  elements.
 * 
 * @author Nick Alekhine
 * @version 2014-1-11
 *
 */
public abstract class MySet {
    
    /**
     * Method returns a string value of this set
     * @return String value of this set 
     */
    public abstract String toString();
    
    /**
     * Method returns whether this set is equal to the given object.
     * @param o Object - the given Object
     * @return boolean 
     */
    public abstract boolean equals(Object o);
    
    /**
     * Method returns an int value for this set.
     * @return int - value for this set
     */
    public abstract int hashCode();
    
    /**
     * Method returns whether the set is empty
     * @return boolean stating whether this set is empty
     */
    abstract boolean isEmptyMethod();

    /**
     * Returns the size of the set
     * @return size of this set
     */
    abstract int sizeMethod();
    
    /**
     * Returns whether the long is in the set
     * @param l - the long that is being searched for
     * @return boolean value
     */
    abstract boolean containsMethod(Long l);
    
    /**
     * Returns whether the second given set is within this
     * set.
     * @param m - the set that is to be found within this set.
     * @return boolean
     */
    abstract boolean isSubsetMethod(MySet m);
    
    /**
     * Returns a set that has the given element removed from this set
     * @param l Long - the element to be removed from the set
     * @return MySet that has the given element removed
     */
    abstract MySet removeMethod(Long l);
    
    /**
     * Method returns a set with unique elements from this set and the given.
     * @param m MySet - another given set
     * @return MySet - the set of unique elements from both sets
     */
    abstract MySet joinMethod(MySet m);
    
    /**
     * Returns a set with elements that are found in this set and the 
     * given set.
     * @param m MySet - the given set
     * @return MySet that contains elements from both sets.
     */
    abstract MySet intersectMethod(MySet m);
    
    /**
     * Method returns a set where the first given long is found
     * in this set and replaced with the second given long.
     * @param l1 Long - the Long to be found and replaced in the set
     * @param l2 Long - the Long to be placed in the set.
     * @return MySet with a swapped element. 
     */
    abstract MySet replaceMethod(Long l1, Long l2);

    
    
    

    /**
     * Returns an empty stack
     * @return empty set
     */
    public static MySet empty() {
        return new Empty();
    }

    
    /**
     * Returns a set where s is the base and n is the top
     * @param s - a set that will become base
     * @param l - long that will be at the top
     * @return a set where s is the base and n is the top
     */
    public static MySet insert(MySet s, Long l) {
        return new Insert(l, s);
    }
    
    
    /**
     * Method returns the amount of elements in s
     * @param s MySet - the set 
     * @return int    - the size of the set
     */
    public static int size(MySet s) {
        return s.sizeMethod();
    }
    

    /**
     * Method returns whether s is empty
     * @param s MySet  - to check whether it is empty
     * @return boolean - stating whether s is empty
     */
    public static boolean isEmpty(MySet s) {
        return s.isEmptyMethod();
    }
    
    
    /**
     * Method returns whether the given long is in the set
     * @param s MySet - the set where the searching is done
     * @param l Long  - the long that is to be found 
     * @return boolean
     */
    public static boolean contains(MySet s, Long l) {
        return s.containsMethod(l);
    }
    
    
    /**
     * Method returns whether the second set is within the
     * first set.
     * @param s MySet - the set where the searching is done
     * @param m MySet - the set that we are trying to find
     * @return boolean
     */
    public static boolean isSubset(MySet s, MySet m) {
        return s.isSubsetMethod(m);
    }
    
    
    /**
     * Method removes the given long from the given set.
     * @param s MySet - the set that is getting the element removed
     * @param l Long  - the long that is getting removed from the set.
     * @return a set with the given element removed.
     */
    public static MySet remove(MySet s, Long l) {
        return s.removeMethod(l);
    }
    
    
    /**
     * Method returns a set with unique elements from both sets.
     * @param s MySet - one given set
     * @param m MySet - another given set
     * @return MySet - the set of unique elements from both sets
     */
    public static MySet join(MySet s, MySet m) {
        return s.joinMethod(m);
    }
    
    
    /**
     * Method returns a set with values that are contained in both sets
     * @param s MySet - one given set
     * @param m MySet - another given set
     * @return MySet - the set of elements found in both sets
     */
    public static MySet intersect(MySet s, MySet m) {
        return s.intersectMethod(m);
    }
    
    
    /**
     * Method returns a set where the first given long is found
     * in the given set and replaced with the second given long.
     * @param s MySet - the given set
     * @param l1 Long - the Long to be found and replaced in the set
     * @param l2 Long - the Long to be placed in the set.
     * @return MySet with a swapped element. 
     */
    public static MySet replace(MySet s, Long l1, Long l2) {
        return s.replaceMethod(l1, l2);
    }
}
