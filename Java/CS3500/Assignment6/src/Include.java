import java.util.ArrayList;

/**
 * Assignment 4 - MyMap
 * @author Nick Alekhine
 * alekhn@ccs.neu.edu
 * 
 */


/**
 * To represent the non-empty Map with Key-Value pairs.
 * @author Nick Alekhine
 * @version 2014-01-29
 *
 * @param <K> key
 * @param <V> value associated with key
 */
public class Include<K, V> extends AList<K, V> {
    
    ///////////////////////////////////////////////////////////////////////////
    // Fields /////////////////////////////////////////////////////////////////
    
    /** A key */
    K key;
    /** A value */
    V value;
    /** The rest of the Map */
    MyMap<K, V> rest;
    
    
    
    
    
    ///////////////////////////////////////////////////////////////////////////
    // Constructors ///////////////////////////////////////////////////////////
    
    /**
     * The constructor for an Include
     * @param key K - the key 
     * @param value V - the value associated with the key
     * @param rest MyMap<K, V> - the rest of the Map
     */
    Include(K key, V value, MyMap<K, V> rest) {
        this.key = key;
        this.value = value;
        this.rest = rest;
    }
    
    
    
    
    
    ///////////////////////////////////////////////////////////////////////////
    // Methods ////////////////////////////////////////////////////////////////
    
    /** CONSTRUCTOR
     * To include the given Key-Value pair in this Map.
     * @param k K - the given key
     * @param v V - the value associated with the key
     * @return Map with the given key-value pair added to this Map. 
     */
    public MyMap<K, V> include(K k, V v) {
        if (this.containsKey(k)) {
            return this.set(k, v);
        }
        else {
            return new Include<K, V>(k, v, this);
        }
    }
    
    
    /**
     * To determine if this Map is empty.
     * @return boolean
     */
    public boolean isEmpty() {
        return false;
    }
    
    
    /**
     * To calculate the amount of elements in this Map.
     * @return integer value of the size
     */
    public int size() {
        return 1 + this.rest.size();
    }
    
    
    /**
     * To determine whether this Map contains the given key.
     * @param k K - the key that is to be found in the Map. 
     * @return boolean 
     */
    public boolean containsKey(K k) {
        if (this.key.equals(k)) {
            return true;
        }
        else {
            return this.rest.containsKey(k);
        }
    }
    
    
    /**
     * To return the value associated with the given key from this Map. 
     * @param k K - the key that is to be searched for
     * @return V the value
     */
    public V get(K k) {
        if (this.key.equals(k)) {
            return this.value;
        }
        else {
            return this.rest.get(k);
        }
    }
    
    
    /**
     * To set the given value at the given key in this Map.
     * @param k K - the key associated with the value
     * @param v V - the value to be set in the map. 
     * @return MyMap<K, V> with the new key, value pair. 
     */
    public MyMap<K, V> set(K k, V v) {
        // if this key equals the given key
        if (this.key.equals(k)) {
            return this.rest.include(k, v);
        }
        // if this key does not exist in this map.
        else if (!this.containsKey(k)) {
            return this.include(k, v);
        }
        // continue searching through this map.
        else {
            return this.rest.set(k, v).include(this.key, this.value);
        }
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
        return this.value.hashCode() + this.rest.hashCode();
    }


    /**
     * To return a list of keys from this map (UNORDERED)
     * @param acc ArrayList<K> - the accumulator for storing keys
     * @return ArrayList with keys from this map.
     */
    public ArrayList<K> keyList(ArrayList<K> acc) {
        acc.add(this.key);
        return this.rest.keyList(acc);
    }
}
