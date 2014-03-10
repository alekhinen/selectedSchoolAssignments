/**
 * Assignment 7 - MyMap using RB Binary Trees
 * @author Nick Alekhine
 * alekhn@ccs.neu.edu
 * 
 */

package mymap;
import java.util.ArrayList;
import java.util.NoSuchElementException;

/**
 * To represent the empty Map.
 * @author Nick Alekhine
 * @version 2014-02-04
 *
 * @param <K> key
 * @param <V> value associated with key
 */
public class EmptyNode<K, V> extends BinarySearchTree<K, V> {
    
    ///////////////////////////////////////////////////////////////////////////
    // Fields /////////////////////////////////////////////////////////////////
    
    /** The comparator */
    java.util.Comparator<? super K> c;
    /** Color of this Node */
    boolean black;
    /** The size of this BST */
    int size; 
    
    
    
    
    
    
    ///////////////////////////////////////////////////////////////////////////
    // Constructors ///////////////////////////////////////////////////////////
    
    /**
     * To create an EmptyNode with a comparator.
     * @param c Comparator<K> - a comparator.
     */
    EmptyNode(java.util.Comparator<? super K> c) {
        this.c = c;
        this.size = 0;
        this.black = true;
    }
    
    
    
    
    
    ///////////////////////////////////////////////////////////////////////////
    // Methods ////////////////////////////////////////////////////////////////

    /** CONSTRUCTOR
     * To generate a Map that includes the given Key-Value pair and all the
     * pairs from this Map. 
     * @param k K - the key value
     * @param v V - the value associated with the key
     * @return Map with the given key-value pair and this Map's pairs. 
     */
    public MyMap<K, V> include(K k, V v) {
        return this.insert(k, v);
    }
    
    
    ///////////////////////////////////////////////////////////////////////////
    // BinarySearchTree-Specific Methods //////////////////////////////////////
    
    
    /**
     * To insert the given key and value into this Node.
     * @param k K - the key value
     * @param v V - the value associated with the key
     * @return BST with the given key-value pair and this BST's pairs
     */
    protected Node<K, V> insert(K k, V v) {
        return new Node<K, V>(k, v, this.c, false);
    }
    
    
    /**
     * Is this Binary Search Tree's color black?
     * @return boolean 
     */
    protected boolean isBlack() {
        return this.black;
    }
    
    
    /**
     * To make this BST have a black color value. 
     * @return a BST with all the same values as this but with a black color
     */
    protected BinarySearchTree<K, V> makeBlack() {
        return this;
    }
    
    
    /**
     * To get the current key of this BST
     * @return K key
     */
    protected K getKey() {
        throw new NoSuchElementException("No key in an empty!");
    }
    
    
    /**
     * To get the current value of this BST
     * @return V value
     */
    protected V getValue() {
        throw new NoSuchElementException("No value in an empty!");
    }
    
    
    /**
     * To get the left element of this BST
     * @return left element of this BST
     */
    protected BinarySearchTree<K, V> getLeft() {
        throw new IndexOutOfBoundsException("There is no LHS for an empty!");
    }
    
    
    /**
     * To get the right element of this BST
     * @return right element of this BST
     */
    protected BinarySearchTree<K, V> getRight() {
        throw new IndexOutOfBoundsException("There is no RHS for an empty!");
    }
    
    
    ///////////////////////////////////////////////////////////////////////////
    ///////////////////////////////////////////////////////////////////////////
    
    
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
        return this.size;
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
     * @param k K - the key associated with the value
     * @param v V - the value to be set in the map. 
     * @return MyMap<K, V> with the new key, value pair. 
     */
    public BinarySearchTree<K, V> set(K k, V v) {
        return new Node<K, V>(k, v, this.c, false);
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
     * To return a list of keys from this map (ORDERED)
     * @param acc ArrayList<K> - the accumulator for storing keys
     * @return ArrayList with keys from this map.
     */
    public ArrayList<K> keyList(ArrayList<K> acc) {
        return acc;
    }

}
