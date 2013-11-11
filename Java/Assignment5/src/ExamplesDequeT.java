// CS 2510 Fall 2013
// assignment 5B
// partner1-alekhine partner1-nicholas
// partner1-alekhn
// partner2-kaminsky partner2-tyler
// partner2-tykam993





///////////////////////////////////////////////////////////////////////////////
// import libraries ///////////////////////////////////////////////////////////
import tester.Tester;





///////////////////////////////////////////////////////////////////////////////
// ExamplesDeques /////////////////////////////////////////////////////////////
// to make examples of our deques and nodes
public class ExamplesDequeT {

    // example nodes
    NodeT<String> n1;
    NodeT<String> n2;
    NodeT<String> n3;

    NodeT<String> n4;

    NodeT<Person> n5;
    NodeT<Person> n6;

    // example sentinels
    SentinelT<String> s1;
    SentinelT<String> s2;
    SentinelT<Person> s3;
    SentinelT<Person> s4;

    // example deques
    DequeT<String> d1;
    DequeT<String> d2;
    DequeT<Person> d3;
    DequeT<Person> d4;

    // to initialize data inside nodes, sentinels, and deques;
    void reset() {
        this.n1 = new NodeT<String>("abc");
        this.n2 = new NodeT<String>("def");
        this.n3 = new NodeT<String>("hij");

        this.n4 = new NodeT<String>("bobby");

        this.n5 = new NodeT<Person>(new Person("bob", 20));
        this.n6 = new NodeT<Person>(new Person("clob", 30));

        this.s1 = new SentinelT<String>();
        this.s2 = new SentinelT<String>();
        this.s3 = new SentinelT<Person>();
        this.s4 = new SentinelT<Person>();

        this.d1 = new DequeT<String>(this.s1);
        this.d2 = new DequeT<String>(this.s2);
        this.d3 = new DequeT<Person>(this.s3);
        this.d4 = new DequeT<Person>(this.s4);
    }

    // to connect the initialized nodes, and sentinels
    void connect() {
        this.reset();
        this.n1.addNodeNext(this.n2);
        this.n2.addNodeNext(this.n3);

        this.n5.addNodeNext(this.n6);

        this.s1.next = this.n1;
        this.s1.prev = this.n1.prev;

        this.s2.next = this.n4;
        this.s2.prev = this.n4;

        this.s3.next = this.n5;
        this.s3.prev = this.n5.prev;
    }

    // to test the addNodeNext method
    void testAddNodeNext(Tester t) {
        this.connect();

        t.checkExpect(this.n1.next, this.n2);
        t.checkExpect(this.n1.prev, this.n3);
        t.checkExpect(this.n2.prev, this.n1);
        t.checkExpect(this.n2.next, this.n3);
        t.checkExpect(this.n3.prev, this.n2);
        t.checkExpect(this.s1.next, this.n1);
        t.checkExpect(this.s1.prev, this.n3);
    }

    // to test the size method [for both node and deque]
    void testSize(Tester t) {
        this.connect();

        t.checkExpect(this.n1.size(this.n1), 3);
        t.checkExpect(this.n4.size(this.n4), 1);
        t.checkExpect(this.n5.size(this.n5), 2);

        t.checkExpect(this.d1.size(), 3);
        t.checkExpect(this.d2.size(), 1);
        t.checkExpect(this.d3.size(), 2);
        t.checkExpect(this.d4.size(), 0);

    }

    // to test the addAtHead method
    void testAddAtHead(Tester t) {
        this.connect();

        this.d2.addAtHead("bob");
        t.checkExpect(this.d2.sentinel.prev, this.n4);
        t.checkExpect(this.d2.sentinel.next, this.n4.next);
        t.checkExpect(this.d2.sentinel.next.data, "bob");
        t.checkExpect(this.d2.sentinel.prev.data, "bobby");

        this.d1.addAtHead("johnny");
        t.checkExpect(this.d1.sentinel.prev, this.n3);
        t.checkExpect(this.d1.sentinel.next, this.n3.next);
        t.checkExpect(this.d1.sentinel.next.data, "johnny");
        t.checkExpect(this.d1.sentinel.prev.data, "hij");

        NodeT<Person> robby = new NodeT<Person>(new Person("robby", 50));
        this.d4.addAtHead(new Person("robby", 50));
        t.checkExpect(this.d4.sentinel.prev, robby);
        t.checkExpect(this.d4.sentinel.next, robby);
    }

    // to test the addAtTail method
    void testAddAtTail(Tester t) {
        this.connect();

        this.d2.addAtTail("rick ross");
        t.checkExpect(this.d2.sentinel.prev, this.n4.prev);
        t.checkExpect(this.d2.sentinel.next, this.n4);
        t.checkExpect(this.d2.sentinel.prev.data, "rick ross");
        t.checkExpect(this.d2.sentinel.next.next.data, "rick ross");
        t.checkExpect(this.d2.sentinel.next.data, "bobby");

        this.d1.addAtTail("earl sweatshirt");
        t.checkExpect(this.d1.sentinel.prev, this.n1.prev);
        t.checkExpect(this.d1.sentinel.next, this.n1);
        t.checkExpect(this.d1.sentinel.prev.data, "earl sweatshirt");
        t.checkExpect(this.d1.sentinel.next.data, "abc");

        NodeT<Person> pearl = new NodeT<Person>(new Person("pearl sweater", 
                                                           30));
        this.d4.addAtTail(new Person("pearl sweater", 30));
        t.checkExpect(this.d4.sentinel.prev, pearl);
        t.checkExpect(this.d4.sentinel.next, pearl);
    }

    // to test the removeFromHead method
    void testRemoveFromHead(Tester t) {
        this.connect();

        this.d2.removeFromHead();
        t.checkExpect(this.d2.sentinel.next, this.d2.sentinel);
        t.checkExpect(this.d2.sentinel.prev, this.d2.sentinel);

        this.d1.removeFromHead();
        t.checkExpect(this.d1.sentinel.next, this.n2);
        t.checkExpect(this.d1.sentinel.prev, this.n3);
        t.checkExpect(this.d1.sentinel.next.prev, this.n3);
        t.checkExpect(this.d1.sentinel.prev.next, this.n2);

        t.checkException(
                // the test name - information about this test
                "this.d4.removeFromHead() \n" +
                "The test should fail:",

                // the expected exception and message
                new RuntimeException("Cannot remove a node from "
                                     + "an empty list!"),

                // the instance that invokes the method
                this.d4,

                // the method name
                "removeFromHead"

                // the comma-separated argument list ( or no arguments)
        );
    }

    // to test the removeFromTail method
    void testRemoveFromTail(Tester t) {
        this.connect();

        this.d2.removeFromHead();
        t.checkExpect(this.d2.sentinel.next, this.d2.sentinel);
        t.checkExpect(this.d2.sentinel.prev, this.d2.sentinel);

        this.d1.removeFromTail();
        t.checkExpect(this.d1.sentinel.prev, this.n2);
        t.checkExpect(this.d1.sentinel.next, this.n1);
        t.checkExpect(this.d1.sentinel.prev.next, this.n1);
        t.checkExpect(this.d1.sentinel.next.prev, this.n2);

        t.checkException(
                // the test name - information about this test
                "this.d4.removeFromTail() \n" +
                "The test should fail:",

                // the expected exception and message
                new RuntimeException("Cannot remove a node from "
                                     + "an empty list!"),

                // the instance that invokes the method
                this.d4,

                // the method name
                "removeFromTail"

                // the comma-separated argument list ( or no arguments)
        );
    }

    // to test the find method for Deque
    void testFind(Tester t) {
        this.connect();

        t.checkExpect(this.d1.find("abc"), this.n1);
        t.checkExpect(this.d1.find("asdfdasdfas"), this.s1);
        t.checkExpect(this.d4.find(new Person("a", 1)), this.s4);
    }

    // to test the removeNode method for Deque
    void testRemoveNode(Tester t) {
        this.connect();

        this.d1.removeNode(this.s1);
        t.checkExpect(this.d1, this.d1);
        this.d1.removeNode(this.n1);
        t.checkExpect(this.d1.sentinel.next.data, "def");
        t.checkExpect(this.d1.sentinel.next.next.data, "hij");
        t.checkExpect(this.d1.sentinel.prev.data, "hij");
        
        this.d2.removeNode(this.n4);
        t.checkExpect(this.d2.sentinel.next.data, null);
        t.checkExpect(this.d2.sentinel.prev.data, null);
        
        this.connect();
        this.d1.removeNode(this.n2);
        t.checkExpect(this.d1.sentinel.next.data, "abc");
        t.checkExpect(this.d1.sentinel.prev.data, "hij");
        t.checkExpect(this.d1.sentinel.prev.prev.data, "abc");
        t.checkExpect(this.d1.sentinel.next.next.data, "hij");
    }
}









