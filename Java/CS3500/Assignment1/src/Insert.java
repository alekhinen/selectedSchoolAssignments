/**
 * @author - Nick Alekhine
 * @email  - alekhn@ccs.neu.edu
 * @course - CS3500
 */

/**
 * Insert represents an non-empty set of longs
 * @author Nick Alekhine
 * @version 2014-01-11
 * 
 */
public class Insert extends MySet {
    
    /** The top element in the set */
    Long top; 
    /** The rest of the elements in the set */
    MySet bot;
    
    /**
     * The constructor for Insert
     * @param top - the element getting inserted
     * @param bot - the set that is getting added onto
     */
    Insert(Long top, MySet bot) {
        this.top = top;
        this.bot = bot;
    }
    
    

    
    
    /**
     * Method returns a string value of this set
     * @return String value of this set 
     */
    public String toString() {
        return "{...(This set contains " + this.sizeMethod() 
                + " elements)...}";
    }
    
    
    /**
     * Method returns whether this set is equal to the given object.
     * @param o Object - the given Object
     * @return boolean 
     */
    public boolean equals(Object o) {
        return o instanceof Insert
               && MySet.isSubset(this, (Insert) o)
               && MySet.isSubset((Insert) o, this);
    }
    
    
    /**
     * Method returns an int value for this set.
     * @return int - value for this set
     */
    public int hashCode() {
        if (MySet.contains(this.bot, this.top)) {
            return this.bot.hashCode();
        }
        else {
            return this.top.intValue()
                   + this.bot.hashCode();
        }
    }
    
    
    /**
     * Method returns whether the set is empty
     * @return boolean stating whether this set is empty
     */
    boolean isEmptyMethod() {
        return false;
    }

    
    /**
     * Returns the size of the set
     * @return size of this set
     */
    int sizeMethod() {
        if (MySet.contains(this.bot, this.top)) {
            return MySet.size(this.bot);
        }
        else {
            return 1 + MySet.size(this.bot);
        }
    }
    
    
    /**
     * Returns whether the long is in the set
     * @param l - the long that is being searched for
     * @return boolean value
     */
    boolean containsMethod(Long l) {
        if (this.top.equals(l)) {
            return true;
        }
        else {
            return MySet.contains(this.bot, l);
        }
    }
    
    
    /**
     * Returns whether the second given set is within this
     * set.
     * @param m - the set that is to be found within this set.
     * @return boolean
     */
    boolean isSubsetMethod(MySet m) {
        // if the top element in this set is contained within 
        // the given set
        if (MySet.contains(m, this.top)) {
            return MySet.isSubset(this.bot, m);
        }
        else {
            return false;
        }
    }
    
    
    /**
     * Returns a set that has the given element removed from this set
     * @param l Long - the element to be removed from the set
     * @return MySet that has the given element removed
     */
    MySet removeMethod(Long l) {
        if (this.top.equals(l)) {
            return this.bot;
        }
        else {
            return MySet.insert(MySet.remove(this.bot, l), this.top);
        }
    }
    
    
    /**
     * Method returns a set with unique elements from this set and the given.
     * @param m MySet - another given set
     * @return MySet - the set of unique elements from both sets
     */
    MySet joinMethod(MySet m) {
        if (MySet.contains(m, this.top)) {
            return MySet.join(this.bot, m);
        }
        else {
            return MySet.insert(MySet.join(this.bot, m), this.top);
        }
    }
    
    
    /**
     * Returns a set with elements that are found in this set and the 
     * given set.
     * @param m MySet - the given set
     * @return MySet that contains elements from both sets.
     */
    MySet intersectMethod(MySet m) {
        if (MySet.contains(m, this.top)) {
            return MySet.insert(MySet.intersect(this.bot, m), this.top);
        }
        else {
            return MySet.intersect(this.bot, m);
        }
    }
    
    
    /**
     * Method returns a set where the first given long is found
     * in this set and replaced with the second given long.
     * @param l1 Long - the Long to be found and replaced in the set
     * @param l2 Long - the Long to be placed in the set.
     * @return MySet with a swapped element. 
     */
    MySet replaceMethod(Long l1, Long l2) {
        if (this.top.equals(l1)) {
            return MySet.insert(this.bot, l2);
        }
        else {
            return MySet.insert(MySet.replace(this.bot, l1, l2), 
                                this.top);
        }
    }
}
