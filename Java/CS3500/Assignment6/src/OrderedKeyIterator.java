/**
 * Assignment 4 - MyMap
 * @author Nick Alekhine
 * alekhn@ccs.neu.edu
 * 
 */


import java.util.ArrayList;
import java.util.NoSuchElementException;


/**
 * MapIterator iterates through a Map's Keys 0-SIZE
 * @param <K> - the key type
 * 
 * @author Nick Alekhine
 * @version 2014-01-29
 * 
 */
public class OrderedKeyIterator<K> extends KeyIterator<K> {

    // Fields /////////////////////////////////////////////////////////////////
    /** The keys from map */
    ArrayList<K> keys;
    /** The comparator */
    java.util.Comparator<? super K> c;
    /** Boolean for determining if listOfKeys has been sorted */
    boolean hasBeenSorted = false;


    
    
    
    // Constructors ///////////////////////////////////////////////////////////
    /**
     * Constructor that takes in a MyMap
     * @param keys ArrayList<K> - the list of Keys
     * @param c java.util.Comparator<? super K> - the comparator
     */   
    OrderedKeyIterator(ArrayList<K> keys, 
                       java.util.Comparator<? super K> c) {
        super(keys);
        this.keys = keys;
        this.c = c;
    }
    
    
    
    

    // Methods ////////////////////////////////////////////////////////////////
    
    /**
     * To determine if there is another key in the iterator
     * @return whether there is another key in the iterator
     */
    @Override
    public boolean hasNext() {
        return !keys.isEmpty();
    }
    
    
    /**
     * To sort the given list with the given comparator and return a new
     * sorted list. 
     * @param a ArrayList<K> - an ArrayList
     * @return a sorted ArrayList<K>
     */
    public ArrayList<K> sortKeyList(ArrayList<K> a) {
        ArrayList<K> result = new ArrayList<K>();
        boolean hasBeenAdded;
        int j;
        
        // go through each element in the given array
        for (int i = 0; i < a.size(); i++) {
            hasBeenAdded = false;
            j = 0;
            
            // go through each element in result and compare it to the current
            // element in the given array
            while (j < result.size() && !hasBeenAdded) {
                // if a's element is smaller than result's element, add it.
                if (this.c.compare(a.get(i), result.get(j)) <= 0) {
                    // adding to result
                    result.add(j, a.get(i));
                    // break out of loop
                    hasBeenAdded = true;
                    break;
                }
                else {
                    j += 1;
                }
            }
            
            // if the given element was bigger than every other element in 
            // result, add it to the end. 
            if (!hasBeenAdded) {
                result.add(a.get(i));
            }
            
        }
        
        return result;
    }
    
    
    /**
     * To return the next key in the iterator
     * @return the next key in the iterator
     */
    @Override
    public K next() {
        if (this.hasNext()) {
            // if the key list has not been sorted, sort it.
            if (!this.hasBeenSorted) {
                this.keys = this.sortKeyList(this.keys);
                this.hasBeenSorted = true;
            }
            
            // get the first key in the list
            K key = this.keys.get(0); 
            // remove the first key in the list
            this.keys.remove(0);
            
            return key;
        }
        else {
            throw new NoSuchElementException("No more keys to iterate to!");
        }
    }
        

}
