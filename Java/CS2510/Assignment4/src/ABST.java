// CS 2510 Fall 2013
// assignment 4
// partner1-alekhine partner1-nicholas
// partner1-alekhn
// partner2-kaminsky partner2-tyler
// partner2-tykam993

import tester.Tester;

/*
 *                    +------------------------------+
 *                    V                              |
 *         +----------------------------+            |
 *         | ABST                       |            |
 *         +----------------------------+            |
 *         | IBookComparator order      |            |
 *         +----------------------------+            |
 *         | ABST insert(Book)          |            |
 *         | ABST sort(ABST)            |            |
 *         | Book getFirst()            |            |
 *         | ABST getRest()             |            |
 *         | boolean sameTree(ABST)     |            |
 *         | Book getBook()             |            |
 *         | ABST getLeft()             |            |
 *         | ABST getRight()            |            |
 *         | boolean sameData(ABST)     |            |
 *         | boolean contains(Book)     |            |
 *         | boolean sameAsList(ILoBook)|            |
 *         | ILoBook buildList(ILoBook) |            |
 *         +----------------------------+            |
 *                        ^                          |
 *                        |                          |
 *                        |                          |
 *            +-----------------------+              |
 *            |                       |              |
 * +----------------------+ +----------------------+ |
 * | Leaf                 | | Node                 | |
 * +----------------------+ +----------------------+ |
 * | Same methods as ABST | | Book data            |-+-------------------+
 * +----------------------+ | ABST left            |-+                   |
 *                          | ABST right           |-+                   |
 *                          +----------------------+                     |
 *                          | Same methods as ABST |                     |
 *                          +----------------------+                     |
 *                                                                       |
 *                                                                       |
 *                        +-----------------------------------+          |
 *                        V                                   |          |
 *     +---------------------------------------+              |          |
 *     | ILoBook                               |              |          |
 *     +---------------------------------------+              |          |
 *     | boolean isEmpty()                     |              |          |
 *     | book getFirst()                       |              |          |
 *     | ILoBook getRest()                     |              |          |
 *     | ABST buildTree(ABST)                  |              |          |
 *     | ILoBook insert(Book, IBookComparator) |              |          |
 *     +---------------------------------------+              |          |
 *                         ^                                  |          |
 *                         |                                  |          |
 *            +--------------------------+                    |          |
 *            |                          |                    |          |
 * +-------------------------+ +-------------------------+    |          |
 * | MtLoBook                | | ConsLoBook              |    |          |
 * +-------------------------+ +-------------------------+    |          |
 * | same methods as ILoBook | | Book first              |----+----------+
 * +-------------------------+ | ILoBook rest            |----+          |
 *                             +-------------------------+               |
 *                             | same methods as ILoBook |               |
 *                             +-------------------------+               |
 *                                                                       |
 *                           +-------------------------------------------+
 *                           |
 *                           V
 *               +------------------------+
 *               | Book                   |
 *               +------------------------+
 *               | String title           |
 *               | String author          |
 *               | int price              |
 *               +------------------------+
 *               | boolean sameBook(Book) |
 *               +------------------------+
 *   
 *        +---------------------------------+
 *        | IBookComparator                 |
 *        +---------------------------------+
 *        | boolean comesBefore(Book, Book) |
 *        +---------------------------------+ 
 *                         ^
 *                         |
 *       +-----------------+-----------------+
 *       |                 |                 |
 * +--------------+ +---------------+ +--------------+
 * | BooksByTitle | | BooksByAuthor | | BooksByPrice |
 * +--------------+ +---------------+ +--------------+
 * | same method  | | same method   | | same method  |
 * +--------------+ +---------------+ +--------------+
 * 
 * 
 * 
 * */

///////////////////////////////////////////////////////////////////////////////
// ABST ///////////////////////////////////////////////////////////////////////
///////////////////////////////////////////////////////////////////////////////
// ABSTRACT: to represent a binary tree
abstract class ABST {
    IBookComparator order;

    ABST(IBookComparator order) {
        this.order = order;
    }

    /* TEMPLATE
     * FIELDS
     * ...this.order... IBookComparator
     * 
     * METHODS
     * ...this.insert(Book)... ABST
     * ...this.sort(ABST)... ABST
     * ...this.getFirst()... Book
     * ...this.getFirstHelper()... Book
     * ...this.isNode()... boolean
     * ...this.isLeaf()... boolean
     * ...this.getRest()... ABST
     * ...this.sameTree(ABST)... boolean
     * ...this.getBook()... Book
     * ...this.getLeft()... ABST
     * ...this.getRight()... ABST
     * ...this.sameData(ABST)... boolean
     * ...this.contains(Book)... boolean
     * ...this.sameAsList(ILoBook)... boolean
     * ...this.buildList(ILoBook)... ILoBook
     * 
     * 
     * METHODS FOR FIELDS
     * ...this.order.ComesBefore(Book, Book)... boolean
     * 
     * */

    // to insert the given book in the correct place in this binary tree
    public abstract ABST insert(Book b);

    // to sort this list using this order
    public abstract ABST sort(ABST acc);

    // to get the first book in the binary tree (using this order)
    // in actuality, it just sorts the list and passes it to the helper
    public abstract Book getFirst();

    // to actually get the first book in the binary tree
    public abstract Book getFirstHelper();

    // to determine if this is a node
    public boolean isNode() {
        return false;
    }

    // to determine if this is a leaf
    public boolean isLeaf() {
        return false;
    }

    // to get the rest of this list
    public abstract ABST getRest();

    // is this tree the same as the given tree
    public abstract boolean sameTree(ABST that);

    // to get the book of this binary tree
    public abstract Book getBook();

    // to get the left hand side of this
    public abstract ABST getLeft();

    // to get the right hand side of this
    public abstract ABST getRight();

    // does this tree contain the same data as the given tree
    public abstract boolean sameData(ABST that);

    // is the given book somewhere in this binary tree
    public abstract boolean contains(Book b);

    // is this tree the same as the given list?
    public abstract boolean sameAsList(ILoBook lob);

    // to build an ordered list with this tree and the given list
    public abstract ILoBook buildList(ILoBook lob);
}





//Node ///////////////////////////////////////////////////////////////////////
//to represent a node in a binary tree

class Node extends ABST {
    Book data;
    ABST left;
    ABST right;

    Node(IBookComparator order, Book data, ABST left, ABST right) {
        super(order);
        this.data = data;
        this.left = left;
        this.right = right;
    }
    /* TEMPLATE
     * FIELDS
     * ...this.order... IBookComparator
     * ...this.data...  Book
     * ...this.left...  ABST
     * ...this.right... ABST
     * 
     * METHODS
     * ...this.insert(Book)... ABST
     * ...this.sort()... ABST
     * ...this.getFirst()... Book
     * ...this.getFirstHelper()... Book
     * ...this.isNode()... boolean
     * ...this.getRest()... ABST
     * ...this.sameTree(ABST)... boolean
     * ...this.getBook()... Book
     * ...this.getLeft()... ABST
     * ...this.getRight()... ABST
     * ...this.sameData(ABST)... boolean
     * ...this.contains(Book)... boolean
     * ...this.sameAsList(ILoBook)... boolean
     * ...this.buildList(ILoBook)... ILoBook
     * 
     * 
     * METHODS FOR FIELDS
     * ...this.order.ComesBefore(Book, Book)... boolean
     * 
     * ...this.left.insert(Book)... ABST
     * ...this.right.insert(Book)... ABST
     * 
     * ...this.left.getFirst()... Book
     * ...this.right.getFirst()... Book
     * 
     * ...this.left.getFirstHelper()... Book
     * ...this.right.getFirstHelper()... Book
     * 
     * ...this.left.isNode()... boolean
     * ...this.right.isNode()... boolean
     * 
     * ...this.left.sameTree(ABST)... boolean
     * ...this.right.sameTree(ABST)... boolean
     * 
     * ...this.left.sameData(ABST)... boolean
     * ...this.right.sameData(ABST)... boolean
     * 
     * ...this.left.buildList(ILoBook)... ILoBook
     * ...this.right.buidlList(ILoBook)... ILoBook
     * 
     * */

    // to insert the given book in the correct place in this binary tree
    public ABST insert(Book b) {
        if (this.order.comesBefore(b, this.data)) {
            return new Node(this.order,
                    this.data,
                    this.left.insert(b),
                    this.right);
        }
        else {
            return new Node(this.order,
                    this.data,
                    this.left,
                    this.right.insert(b));
        }
    }

    // to sort this list using this order
    public ABST sort(ABST acc) {
        acc = acc.insert(this.data);

        acc = this.left.sort(acc);
        acc = this.right.sort(acc);

        return acc;
    }

    // to get the first book in the binary tree (using this order)
    // in actuality, it just sorts the list and passes it to the helper
    public Book getFirst() {
        return this.sort(new Leaf(this.order)).getFirstHelper();
    }

    // to actually get the first book in the binary tree
    public Book getFirstHelper() {
        if (this.left.isNode()) {
            return this.left.getFirstHelper();
        }
        else {
            return this.data;
        }
    }

    // to determine whether this is a node or not
    public boolean isNode() {
        return true;
    }

    // to get the rest of this list
    public ABST getRest() {
        Book firstelement = this.getFirst();

        if (this.data.sameBook(firstelement)) {
            return this.right;
        }
        else {
            return new Node(this.order,
                    this.data,
                    this.left.getRest(),
                    this.right);
        }
    }

    // is this tree the same as the given tree
    public boolean sameTree(ABST that) {
        if (that.isNode()) {
            if (this.data.sameBook(that.getBook())) {
                return (this.left.sameTree(that.getLeft()) &&
                        this.right.sameTree(that.getRight()));
            }
            else {
                return false;
            }
        }
        else {
            return false;
        }
    }

    // to get the book of this binary tree
    public Book getBook() {
        return this.data;
    }

    // to get the left hand side of this
    public ABST getLeft() {
        return this.left;
    }

    // to get the right hand side of this
    public ABST getRight() {
        return this.right;
    }

    // does this tree contain the same data as the given tree
    public boolean sameData(ABST that) {
        boolean samedata = that.contains(this.data);

        if (samedata) {
            if (this.left.isLeaf()) {
                samedata = true;
            }
            if (this.right.isLeaf()) {
                samedata = true;
            }
            if (this.left.isNode()) {
                samedata = this.left.sameData(that);
            }
            if (this.right.isNode()) {
                samedata = this.right.sameData(that);
            }
        }

        return samedata; 
    }

    // is the given book somewhere in this binary tree
    public boolean contains(Book b) {
        if (this.data.sameBook(b)) {
            return true;
        }
        else {
            return (this.left.contains(b) ||
                    this.right.contains(b));
        }
    }

    // is this tree the same as the given list?
    public boolean sameAsList(ILoBook lob) {
        if (lob.isEmpty()) {
            return false;
        }
        if (this.getFirst().sameBook(this.getFirst())) {
            return this.getRest().sameAsList(lob.getRest());
        }
        else {
            return false;
        }
    }

    // to build an ordered list with this tree and the given list
    public ILoBook buildList(ILoBook lob) {
        ILoBook list = lob;

        list = list.insert(this.data, this.order);
        list = this.left.buildList(list);
        list = this.right.buildList(list);

        return list;
    }
}





//Leaf ///////////////////////////////////////////////////////////////////////
//to represent a leaf in a binary tree
class Leaf extends ABST {

    Leaf(IBookComparator order) {
        super(order);
    }
    /* TEMPLATE
     * FIELDS
     * ...this.order... IBookComparator
     * 
     * METHODS
     * ...this.insert(Book)... ABST
     * ...this.sort()... ABST
     * ...this.getFirst()... Book
     * ...this.getFirstHelper()... Book
     * ...this.isNode()... boolean
     * ...this.getRest()... ABST
     * ...this.sameTree(ABST)... boolean
     * ...this.getBook()... Book
     * ...this.getLeft()... ABST
     * ...this.getRight()... ABST
     * ...this.contains(Book)... boolean
     * ...this.sameAsList(ILoBook)... boolean
     * ...this.buildList(ILoBook).... ILoBook
     * 
     * 
     * METHODS FOR FIELDS
     * ...this.order.ComesBefore(Book, Book)... boolean
     * 
     * 
     * */

    // to insert the given book in the correct place in this binary tree
    public ABST insert(Book b) {
        return new Node(this.order, 
                b,
                this,
                this);
    }

    // to sort this list using this order
    public ABST sort(ABST acc) {
        ///return this;
        return acc;
    }

    // to get the first book in the binary tree (using this order)
    // in actuality, it just sorts the list and passes it to the helper
    public Book getFirst() {
        return this.getFirstHelper();
    }

    // to actually get the first book in the binary tree
    public Book getFirstHelper() {
        throw new RuntimeException("No first in an empty tree");
    }

    // to determine if this is a leaf
    public boolean isLeaf() {
        return true;
    }

    // to get the rest of this list
    public ABST getRest() {
        throw new RuntimeException("No rest of an empty tree");
    }

    // is this tree the same as the given tree
    public boolean sameTree(ABST that) {
        if (that.isLeaf()) {
            return true;
        }
        else {
            return false;
        }
    }

    // to get the book of this binary tree
    public Book getBook() {
        throw new RuntimeException("No book in an empty tree");
    }

    // to get the left hand side of this
    public ABST getLeft() {
        throw new RuntimeException("No LHS in an empty tree");
    }

    // to get the right hand side of this
    public ABST getRight() {
        throw new RuntimeException("No RHS in an empty tree");
    }

    // does this tree contain the same data as the given tree
    public boolean sameData(ABST that) {
        if (that.isLeaf()) {
            return true;
        }
        else {
            return false;
        }
    }

    // is the given book somewhere in this binary tree
    public boolean contains(Book b) {
        return false;
    }

    // is this tree the same as the given list?
    public boolean sameAsList(ILoBook lob) {
        if (lob.isEmpty()) {
            return true;
        }
        else {
            return false;
        }
    }

    // to build an ordered list with this tree and the given list
    public ILoBook buildList(ILoBook lob) {
        return lob;
    }
}










///////////////////////////////////////////////////////////////////////////////
//IBookComparator /////////////////////////////////////////////////////////////
///////////////////////////////////////////////////////////////////////////////
//interface to compare one book with another
interface IBookComparator {

    // to determine if the first given book comes before the second
    public boolean comesBefore(Book a, Book b);
}

//BooksByTitle ///////////////////////////////////////////
//function object: compares two books by title
class BooksByTitle implements IBookComparator {

    // to determine if the first given book comes before the second
    // [lexicographically by title]
    public boolean comesBefore(Book a, Book b) {
        return a.title.compareTo(b.title) < 0;
    }
}

//BooksByAuthor //////////////////////////////////////////
//function object: compares two books by author
class BooksByAuthor implements IBookComparator {

    // to determine if the first given book comes before the second
    // [lexicographically by author]
    public boolean comesBefore(Book a, Book b) {
        return a.author.compareTo(b.author) < 0;
    }
}

//BooksByPrice ///////////////////////////////////////////
//function object: compares two books by price
class BooksByPrice implements IBookComparator {

    // to determine if the first given book comes before the second
    // [by price]
    public boolean comesBefore(Book a, Book b) {
        return a.price < b.price;
    }
}










///////////////////////////////////////////////////////////////////////////////
//Book ////////////////////////////////////////////////////////////////////////
///////////////////////////////////////////////////////////////////////////////
//to represent a book
class Book {
    String title;
    String author;
    int price;

    Book(String title, String author, int price) {
        this.title = title;
        this.author = author;
        this.price = price;
    }

    /* TEMPLATE
     * FIELDS
     * ...this.title...  String
     * ...this.author... String
     * ...this.price...  int
     * 
     * METHODS
     * ...this.sameBook(Book)... boolean
     * 
     * */

    // to determine if this book is the same as that
    public boolean sameBook(Book that) {
        return this.title == that.title 
                && this.author == that.author
                && this.price == that.price;
    }
}










///////////////////////////////////////////////////////////////////////////////
// ILoBook ////////////////////////////////////////////////////////////////////
///////////////////////////////////////////////////////////////////////////////
//to represent a list of books
interface ILoBook {
    // is this list empty? 
    public boolean isEmpty();

    // get the first element of this list.
    public Book getFirst();

    // to get the rest of this list
    public ILoBook getRest();

    // to build a BST from this list and the given binary tree
    public ABST buildTree(ABST bt);

    // to insert that book into the given list, given an order
    public ILoBook insert(Book that, IBookComparator order);
}





// ConsLoBook /////////////////////////////////////////////////////////////////
//to represent a consed list of books
class ConsLoBook implements ILoBook {
    Book first;
    ILoBook rest;

    ConsLoBook(Book first, ILoBook rest) {
        this.first = first;
        this.rest = rest;
    }

    /* TEMPLATE
     * FIELDS
     * ...this.first... Book
     * ...this.rest... ILoBook
     * 
     * METHODS
     * ...this.isEmpty()... boolean
     * ...this.getFirst()... book
     * ...this.getRest()... ILoBook
     * ...this.buildTree()... ABST
     * ...this.insert(Book that, IBookComparator order)... ILoBook
     * 
     * METHODS FOR FIELDS
     * ...this.rest.isEmpty()... boolean
     * ...this.rest.getFirst()... book
     * ...this.rest.getRest()... ILoBook
     * ...this.rest.buildTree()... ABST
     * ...this.insert(Book that, IBookComparator order)... ILoBook
     * 
     * */

    // is this empty?
    public boolean isEmpty() {
        return false;
    }

    // to get the first element in this list
    public Book getFirst() {
        return this.first;
    }

    // to get the rest of this list
    public ILoBook getRest() {
        return this.rest;
    }

    // to build a BST from this list
    public ABST buildTree(ABST bt) {
        ABST tree = new Node(new BooksByTitle(),
                this.first, 
                this.rest.buildTree(bt),
                new Leaf(new BooksByTitle()));

        return tree.sort(new Leaf(new BooksByTitle()));
    }

    // to insert that book into the given list, given an order
    public ILoBook insert(Book that, IBookComparator order) {
        if (order.comesBefore(that, this.first)) {
            return new ConsLoBook(that, this);
        }
        else {
            return new ConsLoBook(this.first, 
                    this.rest.insert(that, order));
        }
    }

}




// MtLoBook ///////////////////////////////////////////////////////////////////
//represent an empty list of books
class MtLoBook implements ILoBook {

    // is this empty?
    public boolean isEmpty() {
        return true;
    }

    // to get the first element in this list
    public Book getFirst() {
        throw new RuntimeException("There is no first in an empty list");
    }

    // to get the rest of this list
    public ILoBook getRest() {
        throw new RuntimeException("There is no rest in an empty list");
    }

    // to build a BST from this list
    public ABST buildTree(ABST bt) {
        return bt;
    }

    // to insert that book into the given list, given an order
    public ILoBook insert(Book that, IBookComparator order) {
        return new ConsLoBook(that, new MtLoBook());
    }
}










///////////////////////////////////////////////////////////////////////////////
// ExamplesBooks //////////////////////////////////////////////////////////////
///////////////////////////////////////////////////////////////////////////////
//to make examples of books and test our methods
class ExamplesBooks {

    ///////////////////////////////////////////////////////////////////////////
    // Books //////////////////////////////////////////////////////////////////
    ///////////////////////////////////////////////////////////////////////////

    // examples of books
    Book b1 = new Book("Centennial", "Greg", 100);
    Book b2 = new Book("Commons", "Not Greg", 40);
    Book b3 = new Book("How To Be CRUNK!", "Swagmastah", 21151215);
    Book b4 = new Book("Centennial", "Not Greg", 12);










    ///////////////////////////////////////////////////////////////////////////
    // IBookComparator ////////////////////////////////////////////////////////
    ///////////////////////////////////////////////////////////////////////////

    // example function objects for IBookComparator
    IBookComparator bytitle  = new BooksByTitle();
    IBookComparator byauthor = new BooksByAuthor();
    IBookComparator byprice  = new BooksByPrice();

    // testing IBookComparator methods and function objects
    void testComesBefore(Tester t) {
        // testing byTitle
        t.checkExpect(this.bytitle.comesBefore(this.b1, this.b2), true);
        t.checkExpect(this.bytitle.comesBefore(this.b3, this.b2), false);
        // testing byAuthor
        t.checkExpect(this.byauthor.comesBefore(this.b1, this.b2), true);
        t.checkExpect(this.byauthor.comesBefore(this.b3, this.b2), false);
        // testing byPrice
        t.checkExpect(this.byprice.comesBefore(this.b2, this.b1), true);
        t.checkExpect(this.byprice.comesBefore(this.b3, this.b2), false);
    }










    ///////////////////////////////////////////////////////////////////////////
    // ABST ///////////////////////////////////////////////////////////////////
    ///////////////////////////////////////////////////////////////////////////

    // examples of leaves and nodes
    ABST l1 = new Leaf(this.bytitle);
    ABST n1 = new Node(this.bytitle, this.b2, this.l1, this.l1);
    ABST n2 = new Node(this.bytitle, this.b1, this.l1, this.n1);
    ABST n3 = new Node(this.bytitle, this.b3, this.l1, this.l1);
    ABST n4 = new Node(this.bytitle, this.b2, this.l1, new Node(this.bytitle,
            this.b1,
            this.l1,
            this.l1));
    ABST n5 = new Node(this.bytitle, this.b2, this.n1, this.l1);
    ABST n6 = new Node(this.bytitle, this.b1, this.l1, this.l1);


    // to test the insert method
    void testInsert(Tester t) {
        t.checkExpect(this.l1.insert(b1), new Node(this.bytitle, 
                this.b1, 
                this.l1, 
                this.l1));
        t.checkExpect(this.n1.insert(b1), new Node(this.bytitle,
                this.b2, 
                new Node(this.bytitle,
                        this.b1,
                        this.l1,
                        this.l1),
                        this.l1));
        t.checkExpect(this.n2.insert(b2), new Node(this.bytitle, 
                this.b1, 
                this.l1,
                new Node(this.bytitle,
                        this.b2, 
                        this.l1,
                        new Node(this.bytitle,
                                this.b2,
                                this.l1,
                                this.l1))));
        t.checkExpect(this.mtlob.insert(b1, this.bytitle),
                new ConsLoBook(this.b1, this.mtlob));
    }

    // to test the sort method
    void testSort(Tester t) {
        t.checkExpect(this.n1.sort(this.l1), this.n1);
        t.checkExpect(this.l1.sort(this.l1), this.l1);
        t.checkExpect(this.n4.sort(this.l1), new Node(this.bytitle, 
                this.b2, 
                new Node(this.bytitle,
                        this.b1,
                        this.l1,
                        this.l1), 
                        this.l1));
    }

    // to test the isNode method
    void testIsNode(Tester t) {
        t.checkExpect(this.l1.isNode(), false);
        t.checkExpect(this.n1.isNode(), true);
        t.checkExpect(this.n4.sort(this.l1).isNode(), true);
    }
    
    //to test the getBook method
    void testGetBook(Tester t) {
        t.checkExpect(this.n1.getBook(), this.b2);
        t.checkException(
        // the test name - information about this test
            "this.l1.getBook() \n" +
            "The test should fail:",

        // the expected exception and message
            new RuntimeException("No book in an empty tree"),

        // the instance that invokes the method
            this.l1,

        // the method name
            "getBook"

        // the comma-separated argument list ( or no arguments)
        );
    }

    // to test the getFirst method
    void testGetFirst(Tester t) {
        t.checkExpect(this.n1.getFirst(), this.b2);
        t.checkExpect(this.n2.getFirst(), this.b1);
        t.checkExpect(this.n4.getFirst(), this.b1);
        t.checkException(
                // the test name - information about this test
                "new Leaf(new BooksByTitle).getFirst() \n" +
                "The test should fail:",

                // the expected exception and message
                new RuntimeException("No first in an empty tree"),

                // the instance that invokes the method
                new Leaf(new BooksByTitle()),

                // the method name
                "getFirst"

                // the comma-separated argument list ( or no arguments)
        );
    }

    // to test the getFirstHelper method
    void testGetFirstHelper(Tester t) {
        t.checkExpect(this.n1.sort(this.l1).getFirstHelper(), this.b2);
        t.checkExpect(this.n2.sort(this.l1).getFirstHelper(), this.b1);
        t.checkException(
                // the test name - information about this test
                "new Leaf(new BooksByTitle).getFirst() \n" +
                "The test should fail:",

                // the expected exception and message
                new RuntimeException("No first in an empty tree"),

                // the instance that invokes the method
                new Leaf(new BooksByTitle()),

                // the method name
                "getFirstHelper"

                // the comma-separated argument list ( or no arguments)
        );
    }

    // to test the sameBook method
    void testSameBook(Tester t) {
        t.checkExpect(this.b1.sameBook(b1), true);
        t.checkExpect(this.b2.sameBook(b1), false);
        t.checkExpect(this.b2.sameBook(b3), false);
        t.checkExpect(this.b4.sameBook(b1), false);
    }

    // to test the getLeft method
    void testGetLeft(Tester t) {
        t.checkExpect(this.n1.getLeft(), this.l1);
        t.checkExpect(this.n5.getLeft(), this.n1);
        t.checkException(
                // the test name - information about this test
                "this.l1.getLeft() \n" +
                "The test should fail:",

                // the expected exception and message
                new RuntimeException("No LHS in an empty tree"),

                // the instance that invokes the method
                this.l1,

                // the method name
                "getLeft"

                // the comma-separated argument list ( or no arguments)
        );

    }

    // to test the getRight method
    void testGetRight(Tester t) {
        t.checkExpect(this.n1.getRight(), this.l1);
        t.checkExpect(this.n2.getRight(), this.n1);
        t.checkException(
                // the test name - information about this test
                "this.l1.getRight() \n" +
                "The test should fail:",

                // the expected exception and message
                new RuntimeException("No RHS in an empty tree"),

                // the instance that invokes the method
                this.l1,

                // the method name
                "getRight"

                // the comma-separated argument list ( or no arguments)
        );

    }

    // to test the getRest method
    void testGetRest(Tester t) {
        t.checkExpect(this.n1.getRest(), this.l1);
        t.checkExpect(this.n2.getRest(), this.n1);
    }

    // to test the sameTree method
    void testSameTree(Tester t) {
        t.checkExpect(this.n1.sameTree(n1), true);
        t.checkExpect(this.n2.sameTree(n1), false);
        t.checkExpect(this.n4.sameTree(n4.sort(this.l1)), false);
        t.checkExpect(this.l1.sameTree(this.l1), true);
        t.checkExpect(this.l1.sameTree(this.n1), false);
    }

    // to test the contains method
    void testContains(Tester t) {
        t.checkExpect(this.n1.contains(this.b2), true);
        t.checkExpect(this.n1.contains(this.b1), false);
        t.checkExpect(this.l1.contains(this.b1), false);
    }

    // to test the sameData method
    void testSameData(Tester t) {
        t.checkExpect(this.n2.sameData(this.n4), true);
        t.checkExpect(this.n1.sameData(this.n3), false);
        t.checkExpect(this.l1.sameData(this.l1), true);
        t.checkExpect(this.l1.sameData(this.n1), false);
        t.checkExpect(this.n5.sameData(this.l1), false);
    }










    ///////////////////////////////////////////////////////////////////////////
    // ILoBooks ///////////////////////////////////////////////////////////////
    ///////////////////////////////////////////////////////////////////////////

    // examples of ILoBooks
    ILoBook mtlob = new MtLoBook();
    ILoBook cons1 = new ConsLoBook(this.b1, this.mtlob);
    ILoBook cons2 = new ConsLoBook(this.b1, 
            new ConsLoBook(this.b2, this.mtlob));
    ILoBook cons3 = new ConsLoBook(this.b1, 
            new ConsLoBook(this.b2,
                    new ConsLoBook(this.b3, this.mtlob)));
    ILoBook cons4 = new ConsLoBook(this.b2, this.mtlob);


    // to test the isEmpty method
    void testIsEmpty(Tester t) {
        t.checkExpect(this.mtlob.isEmpty(), true);
        t.checkExpect(this.cons1.isEmpty(), false);
    }

    // to test the getFirst method
    void testGetFirstILoB(Tester t) {
        t.checkExpect(this.cons1.getFirst(), this.b1);
        t.checkExpect(this.cons2.getFirst(), this.b1);
        t.checkException(
                // the test name - information about this test
                "this.mtlob \n" +
                "The test should fail:",

                // the expected exception and message
                new RuntimeException("There is no first in an empty list"),

                // the instance that invokes the method
                new MtLoBook(),

                // the method name
                "getFirst"

                // the comma-separated argument list ( or no arguments)
        );
    }

    // to test the getRest method
    void testGetRestILoB(Tester t) {
        t.checkExpect(this.cons1.getRest(), this.mtlob);
        t.checkExpect(this.cons2.getRest(), 
                new ConsLoBook(this.b2, this.mtlob));
        t.checkException(
                // the test name - information about this test
                "this.mtlob \n" +
                "The test should fail:",

                // the expected exception and message
                new RuntimeException("There is no rest in an empty list"),

                // the instance that invokes the method
                new MtLoBook(),

                // the method name
                "getRest"

                // the comma-separated argument list ( or no arguments)
        );
        t.checkException(
                // the test name -information about this test
                "this.leaf \n" +
                "The test should fail:",
                
                // the exception and message
                new RuntimeException("No rest of an empty tree"),
                
                //the instance that invokes the method
                this.l1,
                
                // the method name
                "getRest"
                
                //the comma-separated argument list ( or no arguments)
        );
    }

    // to test the buildTree method
    void testBuildTree(Tester t) {
        t.checkExpect(this.mtlob.buildTree(this.l1), this.l1);
        t.checkExpect(this.cons2.buildTree(this.l1), this.n2);
    }










    ///////////////////////////////////////////////////////////////////////////
    // ABST [again] ///////////////////////////////////////////////////////////
    ///////////////////////////////////////////////////////////////////////////

    // to test the sameAsList method
    void testSameAsList(Tester t) {
        t.checkExpect(this.n6.sameAsList(this.cons1), true);
        t.checkExpect(this.l1.sameAsList(this.mtlob), true);
        t.checkExpect(this.n2.sameAsList(this.cons2), true);
        t.checkExpect(this.n3.sameAsList(this.cons2), false);
        t.checkExpect(this.n3.sameAsList(this.mtlob), false);
    }

    // to test the buildList method
    void testBuildList(Tester t) {
        t.checkExpect(this.l1.buildList(new MtLoBook()), new MtLoBook());
        t.checkExpect(this.l1.buildList(this.cons1), this.cons1);
        t.checkExpect(this.n1.buildList(this.cons1), 
                new ConsLoBook(this.b1, 
                        new ConsLoBook(this.b2, this.mtlob)));
    }

}

