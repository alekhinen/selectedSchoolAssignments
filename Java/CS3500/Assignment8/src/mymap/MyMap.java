/**
 * Assignment 7 - MyMap using Red-Black Trees
 * @author Nick Alekhine
 * alekhn@ccs.neu.edu
 * 
 */


package mymap;

import java.util.ArrayList;
import java.util.Iterator;


/**
 * Represents a HashMap with Key-Value pairs. 
 * @author Nick Alekhine
 * @version 2014-02-04
 * 
 * @param <K> key
 * @param <V> value associated with key
 */
public abstract class MyMap<K, V> implements Iterable<K> {
    
    ///////////////////////////////////////////////////////////////////////////
    // Static Methods /////////////////////////////////////////////////////////
    
    /** CONSTRUCTOR
     * To generate an instance of an empty MyMap.
     * @param <K> - the key type
     * @param <V> - the value type associated with the key
     * 
     * @return an empty MyMap
     */
    public static <K, V> MyMap<K, V> empty() {
        return new Empty<K, V>();
    }
    
    
    /** CONSTRUCTOR
     * To generate an instance of an empty MyMap.
     * @param <K> - the key type
     * @param <V> - the value type associated with the key
     * @param c Comparator - the comparator to compare different values. 
     * 
     * @return an empty MyMap
     */
    public static <K, V> MyMap<K, V> empty(java.util.Comparator<? super K> c) {
        return new EmptyNode<K, V>(c);
    }
    
    
    
    
    
    ///////////////////////////////////////////////////////////////////////////
    // Dynamic Methods ////////////////////////////////////////////////////////
    
    /** CONSTRUCTOR
     * To generate a Map that includes the given Key-Value pair and all the
     * pairs from this Map. 
     * @param key K - the key value
     * @param value V - the value associated with the key
     * @return Map with the given key-value pair and this Map's pairs. 
     */
    public abstract MyMap<K, V> include(K key, V value);
    
    
    /**
     * To determine if this Map is empty.
     * @return boolean
     */
    public abstract boolean isEmpty();
    
    
    /**
     * To calculate the amount of elements in this Map.
     * @return integer value of the size
     */
    public abstract int size();
    
    
    /**
     * To determine whether this Map contains the given key.
     * @param key K - the key that is to be found in the Map. 
     * @return boolean 
     */
    public abstract boolean containsKey(K key);
    
    
    /**
     * To return the value associated with the given key from this Map. 
     * @param key K - the key that is to be searched for. 
     * @return V the value
     */
    public abstract V get(K key);
    
    
    /**
     * To set the given value at the given key in this Map.
     * @param key K - the key associated with the value
     * @param value V - the value to be set in the map. 
     * @return MyMap<K, V> with the new key, value pair. 
     */
    public abstract MyMap<K, V> set(K key, V value);   
    
    
    /**
     * To return a string value of this Map. 
     * @return string containing information about the map. 
     */
    public abstract String toString();
    
    
    /**
     * Method to return the hashCode of this Map. 
     * @return integer value of this Map.
     */
    public abstract int hashCode();
    
    
    /**
     * To determine whether the given object is equivalent to this Map. 
     * @param o Object - the given object
     * @return boolean
     */
    @SuppressWarnings("unchecked")
    public boolean equals(Object o) {
        return (o instanceof MyMap
                && ((MyMap<K, V>) o).containsAll(this)
                && this.containsAll((MyMap<K, V>) o));
    }
    
    
    /**
     * To return a list of keys from this map
     * @param acc ArrayList<K> - the accumulator for storing keys
     * @return ArrayList with keys from this map.
     */
    public abstract ArrayList<K> keyList(ArrayList<K> acc); 
    
    
    /**
     * To determine if the given map contains the same keys and values 
     * as this map.
     * @param iMap MyMap<K, V> - the map to search against
     * @return boolean
     */
    public boolean containsAll(MyMap<K, V> iMap) {
        // Get all the keys in this BinarySearchTree
        ArrayList<K> listOfKeys = this.keyList(new ArrayList<K>());
        boolean result = true;
        
        // Go through each key in the list and compare keys and values
        // with the given Map
        for (K key : listOfKeys) {
            V value = this.get(key);
            
            if (iMap.containsKey(key)
                && iMap.get(key).equals(value)) {
                result = true;
            }
            else {
                return false;
            }
        }
        
        return result;
    }

    
    /** CONSTRUCTOR
     * To generate an iterator that iterates through each key in this Map.
     * @return Iterator<K> - iterator that generates all the keys in this Map.
     */
    @Override
    public Iterator<K> iterator() {
        return new KeyIterator<K>(this.keyList(new ArrayList<K>()));
    }
    
    
    /** CONSTRUCTOR
     * To generate an iterator that iterates through each key in this Map
     * in the order specified by the comparator.
     * @param c Comparator - the comparator to compare different values. 
     * @return Iterator<K> - the next iteration of this Map.
     */
    public Iterator<K> iterator(java.util.Comparator<? super K> c) {
        return new OrderedKeyIterator<K>(this.keyList(new ArrayList<K>()), c);
    }

}

