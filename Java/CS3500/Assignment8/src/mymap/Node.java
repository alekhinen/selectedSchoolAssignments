/**
 * Assignment 7 - MyMap using RB Binary Trees
 * @author Nick Alekhine
 * alekhn@ccs.neu.edu
 * 
 */

package mymap;
import java.util.ArrayList;

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
    /** Color of this Node */
    boolean black;
    /** The comparator */
    java.util.Comparator<? super K> c;
    /** The left-hand-side of the BinarySearchTree */
    BinarySearchTree<K, V> left;
    /** The right-hand-side of the BinarySearchTree */
    BinarySearchTree<K, V> right;
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
     * @param black boolean - the color associated with this node
     */
    Node(K key, V value, java.util.Comparator<? super K> c, boolean black) {
        this.key = key;
        this.value = value;
        this.c = c;
        this.left = new EmptyNode<K, V>(c);
        this.right = new EmptyNode<K, V>(c);
        this.black = black;
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
     * @param black boolean - the color associated with this node
     */
    Node(K key, V value, 
         java.util.Comparator<? super K> c,
         BinarySearchTree<K, V> left,
         BinarySearchTree<K, V> right,
         boolean black) {
        this.key = key;
        this.value = value;
        this.c = c;
        this.left = left;
        this.right = right;
        this.black = black;
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
        return this.insert(k, v).makeBlack();
    }
    
    
    ///////////////////////////////////////////////////////////////////////////
    // Node-Specific Methods ////////////////////////////////////////////////// 
    
    
    /**
     * To balance the given Binary Search Tree Node
     * @return a balanced version of this BinarySearchTree
     */
    private Node<K, V> balance() {
        if (this.isBlack()) {
            // PATTERN 1 & 2
            if (!this.getLeft().isEmpty()
                && !this.getLeft().isBlack()) {
                BinarySearchTree<K, V> l = this.getLeft();
                //////////////////////////////
                // PATTERN 1 : Left -> Left //
                //////////////////////////////
                if (!l.getLeft().isEmpty() 
                    && !l.getLeft().isBlack()) {
                    return new Node<K, V>(l.getKey(), 
                                          l.getValue(), 
                                          this.c, // was l.c 
                                          l.getLeft().makeBlack(),
                                          new Node<K, V>(this.key, 
                                                         this.value,
                                                         this.c,
                                                         l.getRight(),
                                                         this.getRight(),
                                                         true),
                                          false);
                }
                //////////////////////////////
                // PATTERN 2: Left -> Right //
                //////////////////////////////
                else if (!l.getRight().isEmpty() 
                         && !l.getRight().isBlack()) {
                    BinarySearchTree<K, V> lr = l.getRight();
                        
                    return new Node<K, V>(lr.getKey(),
                                          lr.getValue(),
                                          this.c, // was lr.c
                                          new Node<K, V>(l.getKey(),
                                                         l.getValue(),
                                                         this.c, // was l.c
                                                         l.getLeft(),
                                                         lr.getLeft(),
                                                         true),
                                          new Node<K, V>(this.key,
                                                         this.value,
                                                         this.c,
                                                         lr.getRight(),
                                                         this.getRight(),
                                                         true),
                                          false);
                }
            }
            // PATTERN 3 & 4
            else if (!this.getRight().isEmpty()
                     && !this.getRight().isBlack()) {
                BinarySearchTree<K, V> r = this.getRight();
                ///////////////////////////////
                // PATTERN 3: Right -> Right //
                ///////////////////////////////
                if (!r.getRight().isEmpty() 
                    && !r.getRight().isBlack()) {
                    return new Node<K, V>(r.getKey(),
                                          r.getValue(),
                                          this.c, // was r.c
                                          new Node<K, V>(this.key,
                                                         this.value,
                                                         this.c,
                                                         this.getLeft(),
                                                         r.getLeft(),
                                                         true),
                                          r.getRight().makeBlack(),
                                          false);
                }
                //////////////////////////////
                // PATTERN 4: Right -> Left //
                //////////////////////////////
                else if (!r.getLeft().isEmpty() 
                         && !r.getLeft().isBlack()) {
                    BinarySearchTree<K, V> rl = r.getLeft();
                        
                    return new Node<K, V>(rl.getKey(),
                                          rl.getValue(),
                                          this.c, // was rl.c
                                          new Node<K, V>(this.key,
                                                         this.value,
                                                         this.c,
                                                         this.getLeft(),
                                                         rl.getLeft(),
                                                         true),
                                          new Node<K, V>(r.getKey(),
                                                         r.getValue(),
                                                         this.c, // was r.c
                                                         rl.getRight(),
                                                         r.getRight(),
                                                         true),
                                         false);
                }
            }
        }
        return this;
    }
    
    
    ///////////////////////////////////////////////////////////////////////////
    ///////////////////////////////////////////////////////////////////////////
    
    
    ///////////////////////////////////////////////////////////////////////////
    // BinarySearchTree-Specific Methods //////////////////////////////////////
    
    
    /**
     * To insert the given key and value into this Node.
     * @param k K - the key value
     * @param v V - the value associated with the key
     * @return BST with the given key-value pair and this BST's pairs
     */
    protected Node<K, V> insert(K k, V v) {
        // if the given key is smaller than this key
        if (this.c.compare(k, this.key) < 0) {
            return new Node<K, V>(this.key, 
                                  this.value, 
                                  this.c, 
                                  this.left.insert(k, v), 
                                  this.right,
                                  this.black).balance();
        }
        // if the given key is equal to this key, set 
        // this key's value to the given value
        else if (this.c.compare(k, this.key) == 0) {
            return new Node<K, V>(k, v, 
                                  this.c, 
                                  this.left, 
                                  this.right, 
                                  this.black);
        }
        // if the given key is bigger than this key
        else {
            return new Node<K, V>(this.key, 
                                  this.value, 
                                  this.c, 
                                  this.left, 
                                  this.right.insert(k, v), 
                                  this.black).balance();
        }
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
        return new Node<K, V>(this.key, this.value, 
                              this.c, this.left, 
                              this.right, true);
    }
    
    
    /**
     * To get the current key of this BST
     * @return K key
     */
    protected K getKey() {
        return this.key;
    }
    
    
    /**
     * To get the current value of this BST
     * @return V value
     */
    protected V getValue() {
        return this.value;
    }
    
    
    /**
     * To get the left element of this BST
     * @return left element of this BST
     */
    protected BinarySearchTree<K, V> getLeft() {
        return this.left;
    }
    
    
    /**
     * To get the right element of this BST
     * @return right element of this BST
     */
    protected BinarySearchTree<K, V> getRight() {
        return this.right;
    }
    
    
    ///////////////////////////////////////////////////////////////////////////
    ///////////////////////////////////////////////////////////////////////////

    
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
    public BinarySearchTree<K, V> set(K k, V v) {
        // if this key equals the given key, set this value
        if (this.c.compare(k, this.key) == 0) {
            return new Node<K, V>(k, v, this.c, this.left, 
                                  this.right, this.black);
        }
        // if the given k is smaller than this key, search leftwards
        else if (this.c.compare(k, this.key) < 0) {
            return new Node<K, V>(this.key, this.value, this.c,
                                  this.left.set(k, v),
                                  this.right, this.black);
        }
        // else search rightwards
        else {
            return new Node<K, V>(this.key, this.value, this.c,
                                  this.left,
                                  this.right.set(k, v),
                                  this.black);
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
        return (7 + this.key.hashCode() + this.value.hashCode() / 5)  
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
