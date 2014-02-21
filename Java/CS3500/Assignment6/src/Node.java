import java.util.ArrayList;

/**
 * Assignment 5 - MyMap using Binary Trees
 * @author Nick Alekhine
 * alekhn@ccs.neu.edu
 * 
 */

/**
 * To represent a Binary Search Tree node that contain key-value pairs and
 * associations with other nodes in an ordered manner.
 * @author Nick Alekhine
 * @version 2014-02-04
 *
 * @param <K> key
 * @param <V> value associated with key
 */
public class Node<K, V> extends BinarySearchTree<K, V> {
    ///////////////////////////////////////////////////////////////////////////
    // Fields /////////////////////////////////////////////////////////////////
    
    /** The key */
    K key;
    /** The value associated with the key */
    V value;
    /** The comparator */
    java.util.Comparator<? super K> c;
    /** The left-hand-side of the BinarySearchTree */
    MyMap<K, V> left;
    /** The right-hand-side of the BinarySearchTree */
    MyMap<K, V> right;
    
    /** The size of this BST */
    int size; 
    
    
    
    
    
    ///////////////////////////////////////////////////////////////////////////
    // Constructors ///////////////////////////////////////////////////////////
    
    /**
     * The basic constructor for a node. Contains a key-value pair and two
     * empty nodes
     * @param key K - the key 
     * @param value V - the value associated with the key
     * @param c Comparator<K> - the comparator
     */
    Node(K key, V value, java.util.Comparator<? super K> c) {
        this.key = key;
        this.value = value;
        this.c = c;
        this.left = new EmptyNode<K, V>(c);
        this.right = new EmptyNode<K, V>(c);
        
        this.size = 1;
    }
    
    
    /**
     * The main constructor for a node. Contains a key-value pair and two 
     * connections to other MyMaps (Node or Empty).
     * @param key K - the key
     * @param value V - the value associated with the key
     * @param c Comparator<K> - the comparator
     * @param left MyMap<K, V> - the left hand side of the tree
     * @param right MyMap<K, V> - the right hand side of the tree
     */
    Node(K key, V value, 
         java.util.Comparator<? super K> c,
         MyMap<K, V> left,
         MyMap<K, V> right) {
        this.key = key;
        this.value = value;
        this.c = c;
        this.left = left;
        this.right = right;
        
        this.size = 1 + this.left.size() + this.right.size();
    }
    
    
    
    
    
    ///////////////////////////////////////////////////////////////////////////
    // Methods ////////////////////////////////////////////////////////////////
    
    /** CONSTRUCTOR
     * To generate a BST that includes the given Key-Value pair and all the
     * pairs from this Node. 
     * @param k K - the key value
     * @param v V - the value associated with the key
     * @return BST with the given key-value pair and this BST's pairs. 
     */
    public MyMap<K, V> include(K k, V v) {
        // if the given key is smaller than this key
        if (this.c.compare(k, this.key) < 0) {
            return new Node<K, V>(this.key, 
                                  this.value, 
                                  this.c, 
                                  this.left.include(k, v), 
                                  this.right);
        }
        // if the given key is equal to this key, set 
        // this key's value to the given value
        else if (this.c.compare(k, this.key) == 0) {
            return new Node<K, V>(k, v, this.c, this.left, this.right);
        }
        // if the given key is bigger than this key
        else {
            return new Node<K, V>(this.key, this.value, this.c, this.left, 
                                  this.right.include(k, v));
        }
    }

    
    /**
     * To determine if this binary tree is empty.
     * @return boolean
     */
    public boolean isEmpty() {
        return false;
    }

    
    /**
     * To calculate the amount of elements in this binary tree.
     * @return integer value of the size
     */
    public int size() {
        return this.size;
    }

    
    /**
     * To determine whether this binary tree contains the given key.
     * @param k K - the key that is to be found in the binary tree. 
     * @return boolean 
     */
    public boolean containsKey(K k) {
        // if this key equals the given key
        if (this.c.compare(k, this.key) == 0) {
            return true;
        }
        // if the given k is smaller than this key, search leftwards
        else if (this.c.compare(k, this.key) < 0) {
            return this.left.containsKey(k);
        }
        // else search rightwards
        else {
            return this.right.containsKey(k);
        }
    }

    
    /**
     * To return the value associated with the given key from this binary tree. 
     * @param k K - the key that is to be searched for. 
     * @return V the value
     */
    public V get(K k) {
        // if this key equals the given key, return this value
        if (this.c.compare(k, this.key) == 0) {
            return this.value;
        }
        // if the given k is smaller than this key, search leftwards
        else if (this.c.compare(k, this.key) < 0) {
            return this.left.get(k);
        }
        // else search rightwards
        else {
            return this.right.get(k);
        }
    }

    
    /**
     * To set the given value at the given key in this binary tree.
     * @param k K - the key associated with the value
     * @param v V - the value to be set in the map. 
     * @return MyMap<K, V> with the new key, value pair. 
     */
    public MyMap<K, V> set(K k, V v) {
        // if this key equals the given key, set this value
        if (this.c.compare(k, this.key) == 0) {
            return new Node<K, V>(k, v, this.c, this.left, this.right);
        }
        // if the given k is smaller than this key, search leftwards
        else if (this.c.compare(k, this.key) < 0) {
            return new Node<K, V>(this.key, this.value, this.c,
                                  this.left.set(k, v),
                                  this.right);
        }
        // else search rightwards
        else {
            return new Node<K, V>(this.key, this.value, this.c,
                                  this.left,
                                  this.right.set(k, v));
        }
    }

    
    /**
     * To return a string value of this binary tree. 
     * @return string containing information about the map. 
     */
    public String toString() {
        return "{...(" + this.size() + " key(s) mapped to value(s))...}";
    }

    
    /**
     * Method to return the hashCode of this binary tree.
     * This method only takes into account the values, not the keys.
     * @return integer value of this binary tree.
     */
    public int hashCode() {
        return this.value.hashCode() 
               + this.left.hashCode()
               + this.right.hashCode();
    }
    
    
    /**
     * To return a list of keys from this map (ORDERED)
     * @param acc ArrayList<K> - the accumulator for storing keys
     * @return ArrayList with keys from this map.
     */
    public ArrayList<K> keyList(ArrayList<K> acc) {
        // Add all the keys from the left to acc
        acc.addAll(this.left.keyList(new ArrayList<K>()));
        // Add this key into acc
        acc.add(this.key);
        // Add all the keys from the right to acc
        acc.addAll(this.right.keyList(new ArrayList<K>()));
        
        return acc;
    }

}
