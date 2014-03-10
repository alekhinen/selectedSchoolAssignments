/**
 * Assignment 4 - MyMap
 * @author Nick Alekhine
 * alekhn@ccs.neu.edu
 * 
 */

package mymap;
import java.util.ArrayList;

/**
 * To represent the empty Map.
 * @author Nick Alekhine
 * @version 2014-01-28
 *
 * @param <K> key
 * @param <V> value associated with key
 */
public class Empty<K, V> extends AList<K, V> {

    ///////////////////////////////////////////////////////////////////////////
    // Methods ////////////////////////////////////////////////////////////////
    
    /** CONSTRUCTOR
     * To generate a Map that includes the given Key-Value pair and all the
     * pairs from this Map. 
     * @param key K - the key value
     * @param value V - the value associated with the key
     * @return Map with the given key-value pair and this Map's pairs. 
     */
    public MyMap<K, V> include(K key, V value) {
        return new Include<K, V>(key, value, this);
    }
    
    
    /**
     * To determine if this Map is empty.
     * @return boolean
     */
    public boolean isEmpty() {
        return true;
    }
    
    
    /**
     * To calculate the amount of elements in this Map.
     * @return integer value of the size
     */
    public int size() {
        return 0;
    }
    
    
    /**
     * To determine whether this Map contains the given key.
     * @param key K - the key that is to be found in the Map. 
     * @return boolean 
     */
    public boolean containsKey(K key) {
        return false;
    }
    
    
    /**
     * To return the value associated with the given key from this Map. 
     * @param key K - the key that is to be searched for. 
     * @return V the value
     */
    public V get(K key) {
        throw new RuntimeException("Value for this key does not exist!");
    }
    
    
    /**
     * To set the given value at the given key in this Map.
     * @param key K - the key associated with the value
     * @param value V - the value to be set in the map. 
     * @return MyMap<K, V> with the new key, value pair. 
     */
    public MyMap<K, V> set(K key, V value) {
        return this.include(key, value);
    }
    
    
    /**
     * To return a string value of this Map. 
     * @return string containing information about the map. 
     */
    public String toString() {
        return "{...(" + this.size() + " key(s) mapped to value(s))...}";
    }
    
    
    /**
     * Method to return the hashCode of this Map. 
     * @return integer value of this Map.
     */
    public int hashCode() {
        return 0;
    }


    /**
     * To return a list of keys from this map (UNORDERED)
     * @param acc ArrayList<K> - the accumulator for storing keys
     * @return ArrayList with keys from this map.
     */
    public ArrayList<K> keyList(ArrayList<K> acc) {
        return acc;
    }

}
