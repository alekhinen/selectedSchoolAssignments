// CS 2510 Fall 2013
// assignment 5B
// partner1-alekhine partner1-nicholas
// partner1-alekhn
// partner2-kaminsky partner2-tyler
// partner2-tykam993





/* CLASS DIAGRAM
 *  
 *   +------------------------+
 *   | DequeT                 |
 *   +------------------------+
 *   | SentinelT sentinel     |--------+
 *   +------------------------+        |
 *   | int size()             |        |
 *   | void addAtHead(T)      |        |
 *   | void addAtTail(T)      |        |
 *   | void removeFromHead()  |        |
 *   | void removeFromTail()  |        |
 *   | Node find(T)           |        |
 *   | void removeNode(NodeT) |        |
 *   +------------------------+        |
 *                                     |
 *                                     |
 *                                     |
 * +-------------------------------+   |
 * | NodeT                         |   |
 * +-------------------------------+   |
 * | T data                        |   |
 * | NodeT next                    |   |
 * | NodeT prev                    |   |
 * +-------------------------------+   |
 * | int size(NodeT)               |   |
 * | void addNodeNext(NodeT)       |   |
 * | void removeFromList()         |   |
 * | Node find(T, NodeT)           |   |
 * | void removeNode(NodeT, NodeT) |   |
 * +-------------------------------+   |
 *                ^                    |
 *                |                    |
 *                +--------------------+
 *                |
 *           +-----------+
 *           | SentinelT |
 *           +-----------+
 * 
 * 
 * +-------------+
 * | Person      | 
 * +-------------+
 * | String name |
 * | int age     |
 * +-------------+
 * 
 * 
 * */





///////////////////////////////////////////////////////////////////////////////
// DEQUE //////////////////////////////////////////////////////////////////////
// wrapper for sentinel class 
public class DequeT<T> {
    SentinelT<T> sentinel;

    DequeT(SentinelT<T> sentinel) {
        this.sentinel = sentinel;
    }
    /* TEMPLATE
     * FIELDS
     * ...this.sentinel... -- SentinelT
     * 
     * METHODS
     * ...this.size()...            -- int
     * ...this.addAtHead(T)...      -- void
     * ...this.addAtTail(T)...      -- void
     * ...this.removeFromHead()...  -- void
     * ...this.removeFromTail()...  -- void
     * ...this.find(T)...           -- NodeT
     * ...this.removeNode(Node)...  -- void
     * 
     * METHODS FOR FIELDS
     * ...this.sentinel.size()...                   -- int
     * ...this.sentinel.addNodeNext(NodeT)...       -- void
     * ...this.sentinel.size(NodeT)...              -- int
     * ...this.sentinel.addNodeNext(NodeT)...       -- void
     * ...this.sentinel.removeFromList()...         -- void
     * ...this.sentinel.find(String, nodeT)...      -- NodeT
     * ...this.sentinel.removeNode(NodeT, NodeT)... -- void
     * 
     * 
     * */

    
    /////////////////////////////////////////////////////////////////
    // to determine the size of the deque
    public int size() {
        // if the list is empty
        if (this.sentinel.next.equals(this.sentinel)) {
            return 0;
        }
        // if the list is non-empty
        else {
            return this.sentinel.next.size(this.sentinel.next);
        }
    }

    
    /////////////////////////////////////////////////////////////////
    // to add a node to the head of this list using the given string
    void addAtHead(T s) {
        NodeT<T> neue = new NodeT<T>(s);

        // if the list is empty
        if (this.sentinel.next.equals(this.sentinel)) {
            this.sentinel.prev = neue;
            this.sentinel.next = this.sentinel.prev;
        }
        // if the list is non-empty
        else {
            this.sentinel.prev.addNodeNext(new NodeT<T>(s));
            this.sentinel.next = this.sentinel.prev.next;
        }
    }

    
    /////////////////////////////////////////////////////////////////
    // to add a node to the tail of this list using the given string
    void addAtTail(T s) {
        NodeT<T> neue = new NodeT<T>(s);

        // if the list is empty
        if (this.sentinel.next.equals(this.sentinel)) {
            this.sentinel.prev = neue;
            this.sentinel.next = this.sentinel.prev;
        }
        // if the list is non-empty
        else {
            this.sentinel.prev.addNodeNext(new NodeT<T>(s));
            this.sentinel.prev = this.sentinel.prev.next;
        }
    }

    
    /////////////////////////////////////////////////////////////////
    // to remove the head node from this list
    void removeFromHead() {
        // if list is empty
        if (this.sentinel.next.equals(this.sentinel)) {
            throw new RuntimeException("Cannot remove a node from "
                    + "an empty list!");
        }
        // if list only contains one node [excluding sentinel]
        else if (this.sentinel.next.equals(this.sentinel.prev)) {
            this.sentinel.next = this.sentinel;
            this.sentinel.prev = this.sentinel;
        }
        // if list is non-empty
        else {
            this.sentinel.next.removeFromList();
            this.sentinel.next = this.sentinel.prev.next;
        }
    }

    
    /////////////////////////////////////////////////////////////////
    // to remove the tail node from this list
    void removeFromTail() {
        // if list is empty
        if (this.sentinel.next.equals(this.sentinel)) {
            throw new RuntimeException("Cannot remove a node from "
                    + "an empty list!");
        }
        // if list only contains one node [excluding sentinel]
        else if (this.sentinel.next.equals(this.sentinel.prev)) {
            this.sentinel.next = this.sentinel;
            this.sentinel.prev = this.sentinel;
        }
        // if list is non-empty
        else {
            this.sentinel.prev.removeFromList();
            this.sentinel.prev = this.sentinel.next.prev;
        }
    }

    
    /////////////////////////////////////////////////////////////////
    // to find the node that contains the given string
    NodeT<T> find(T s) {
        return this.sentinel.next.find(s, this.sentinel);
    }

    
    /////////////////////////////////////////////////////////////////
    // to remove the given node from this list
    void removeNode(NodeT<T> n) {
        // if the given node is not the sentinel
        if (!this.sentinel.equals(n)) {
            // if the given node is the head of the list
            if (this.sentinel.next.equals(n)) {
                this.removeFromHead();
            }
            // if the given node is the tail of the list
            else if (this.sentinel.prev.equals(n)) {
                this.removeFromTail();
            }
            // if the given node is somewhere in the middle of the list
            else {
                this.sentinel.next.removeNode(n, this.sentinel.next);
            }
        }
    }
}





///////////////////////////////////////////////////////////////////////////////
// NODE ///////////////////////////////////////////////////////////////////////
// a node in a list
class NodeT<T> {
    T data;
    NodeT<T> next;
    NodeT<T> prev;

    NodeT(T data) {
        this.data = data;
        this.next = this;
        this.prev = this;
    }
    /* TEMPLATE
     * FIELDS
     * ...this.data... -- T
     * ...this.next... -- NodeT
     * ...this.prev... -- NodeT
     * 
     * METHODS
     * ...this.size(NodeT)...              -- Int
     * ...this.addNodeNext(NodeT)...       -- void
     * ...this.removeFromList()...         -- void
     * ...this.find(T, Node)...            -- NodeT
     * ...this.removeNode(NodeT, NodeT)... -- void
     * 
     * METHODS FOR FIELDS
     * ...this.prev.size(NodeT)...              -- int
     * ...this.next.size(NodeT)...              -- int
     * 
     * ...this.prev.addNodeNext(NodeT)...       -- void
     * ...this.next.addNodeNext(NodeT)...       -- void
     * 
     * ...this.prev.removeFromList()...         -- void
     * ...this.next.removeFromList()...         -- void
     * 
     * ...this.prev.find(T, NodeT)...           -- NodeT
     * ...this.next.find(T, NodeT)...           -- NodeT
     * 
     * ...this.prev.removeNode(NodeT, NodeT)... -- void
     * ...this.next.removeNode(NodeT, NodeT)... -- void
     * 
     * */

    
    /////////////////////////////////////////////////////////////////
    // to count the size of this list of nodes
    public int size(NodeT<T> base) {
        if (this.next.equals(base)) {
            return 1;
        }
        else {
            return 1 + this.next.size(base);
        }
    }

    
    /////////////////////////////////////////////////////////////////
    // to add a node to the list
    void addNodeNext(NodeT<T> n) {
        n.next = this.next;
        n.prev = this;
        this.next.prev = n;
        this.next = n;
    }

    
    /////////////////////////////////////////////////////////////////
    // to remove this node from the list 
    void removeFromList() {
        this.prev.next = this.next;
        this.next.prev = this.prev;
    }

    
    /////////////////////////////////////////////////////////////////
    // to determine if the given string is somewhere in this list
    NodeT<T> find(T s, NodeT<T> base) {
        // if this is the node that contains the string
        if (this.data == null || this.data.equals(s)) {
            return this;
        }
        // if we have reached the end of the list
        else if (this.next.equals(base.next)) {
            return base;
        }
        // continue through the list
        else {
            return this.next.find(s, base);
        }
    } 

    
    /////////////////////////////////////////////////////////////////
    // to remove the given node from this list
    void removeNode(NodeT<T> n, NodeT<T> base) {
        // if this node is the same as the given node
        if (this.equals(n)) {
            this.removeFromList();
        }
        else if (!this.next.equals(base)) {
            this.next.removeNode(n, base);
        }
    }

}





///////////////////////////////////////////////////////////////////////////////
// SENTINEL ///////////////////////////////////////////////////////////////////
// to represent a sentinel
class SentinelT<T> extends NodeT<T> {
    
    SentinelT() {
        super((T) null);
    }
    /* TEMPLATE
     * ** EXACTLY THE SAME AS NODE CLASS **
     * 
     * In fact, this entire class is unnecessary and this program
     * could have just used Node and Deque
     * 
     * */
}




///////////////////////////////////////////////////////////////////////////////
// PERSON /////////////////////////////////////////////////////////////////////
// to represent a person
class Person {
    String name;
    int age;
    
    Person(String name, int age) {
        this.name = name;
        this.age = age;
    }
}