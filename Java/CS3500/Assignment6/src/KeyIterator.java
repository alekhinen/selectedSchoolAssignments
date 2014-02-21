/**
 * Assignment 4 - MyMap
 * @author Nick Alekhine
 * alekhn@ccs.neu.edu
 * 
 */



import java.util.ArrayList;
import java.util.Iterator;
import java.util.NoSuchElementException;


/**
 * MapIterator iterates through a Map's Keys 0-SIZE
 * @param <K> - the key type
 * 
 * @author Nick Alekhine
 * @version 2014-02-08
 * 
 */
public class KeyIterator<K> implements Iterator<K> {

    // Fields /////////////////////////////////////////////////////////////////
    /** The keys to iterate through */
    ArrayList<K> keys;


    // Constructors ///////////////////////////////////////////////////////////
    /**
     * Constructor that takes in a MyMap
     * @param keys ArrayList<K> - the supplied keys
     */
    KeyIterator(ArrayList<K> keys) {
        this.keys = keys;
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
     * To return the next key in the iterator
     * @return the next key in the iterator
     */
    @Override
    public K next() {
        if (this.hasNext()) {
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


    /**
     * remove is an Unsupported Operation
     */
    @Override
    public void remove() {
        throw new UnsupportedOperationException("remove");
    }

}
