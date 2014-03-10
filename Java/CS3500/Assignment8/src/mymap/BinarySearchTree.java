/**
 * Assignment 5 - MyMap using Binary Trees
 * @author Nick Alekhine
 * alekhn@ccs.neu.edu
 * 
 */

package mymap;

/**
 * To represent a Binary Search Tree with nodes that contain key-value pairs.
 * @author Nick Alekhine
 * @version 2014-02-04
 *
 * @param <K> key
 * @param <V> value associated with key
 */
public abstract class BinarySearchTree<K, V> extends MyMap<K, V> {
    
    /**
     * To insert the given key and value into this Node.
     * @param k K - the key value
     * @param v V - the value associated with the key
     * @return MyMap with the given key-value pair and this BST's pairs
     */
    protected abstract Node<K, V> insert(K k, V v);
    
    
    /**
     * Is this Binary Search Tree's color black?
     * @return boolean 
     */
    protected abstract boolean isBlack();
    
    /**
     * To make this BST have a black color value. 
     * @return a BST with all the same values as this but with a black color
     */
    protected abstract BinarySearchTree<K, V> makeBlack();
    
    
    /**
     * To get the current key of this BST
     * @return K key
     */
    protected abstract K getKey();
    
    
    /**
     * To get the current value of this BST
     * @return V value
     */
    protected abstract V getValue();
    
    
    /**
     * To get the left element of this BST
     * @return left element of this BST
     */
    protected abstract BinarySearchTree<K, V> getLeft();
    
    
    /**
     * To get the right element of this BST
     * @return right element of this BST
     */
    protected abstract BinarySearchTree<K, V> getRight();
    
    
    /**
     * To set the given value at the given key in this Map.
     * @param k K - the key associated with the value
     * @param v V - the value to be set in the map. 
     * @return BinarySearchTree<K, V> with the new key, value pair. 
     */
    public abstract BinarySearchTree<K, V> set(K k, V v);
}

