// CS 2510 Fall 2013
// assignment 5
// partner1-alekhine partner1-nicholas
// partner1-alekhn
// partner2-kaminsky partner2-tyler
// partner2-tykam993






///////////////////////////////////////////////////////////////////////////////
// import libraries ///////////////////////////////////////////////////////////
import tester.Tester;
import java.util.ArrayList;
import java.util.Comparator;





///////////////////////////////////////////////////////////////////////////////
// ExamplesAlgorithms /////////////////////////////////////////////////////////
// To make examples/tests of Algorithms class
public class ExamplesAlgorithms {
    
    // Sorted Arrays [Integers, Strings]
    ArrayList<Integer> ari1 = new ArrayList<Integer>();
    ArrayList<Integer> ari2 = new ArrayList<Integer>();
    ArrayList<Integer> ari3 = new ArrayList<Integer>();
    
    ArrayList<String> ars1 = new ArrayList<String>();
    ArrayList<String> ars2 = new ArrayList<String>();
    ArrayList<String> ars3 = new ArrayList<String>();
    
    // to initialize the data of sorted arrays 
    void initData() {
        // ari1
        ari1.clear();
        ari1.add(2);
        
        // ari2
        ari2.clear();
        ari2.add(1);
        ari2.add(2);
        ari2.add(3);
        ari2.add(4);
        
        // ari3
        ari3.clear();
        ari3.add(5);
        ari3.add(10);
        ari3.add(20);
        
        // ars1
        ars1.clear();
        ars1.add("a");
        
        // ars2
        ars2.clear();
        ars2.add("a");
        ars2.add("b");
        ars2.add("c");
        ars2.add("d");
        
        // ars3
        ars3.clear();
        ars3.add("e");
        ars3.add("g");
        ars3.add("z");
    }
    
    // example algorithms class
    Algorithms<String> algos = new Algorithms<String>();
    Algorithms<Integer> algoi = new Algorithms<Integer>();
    
    // example comparators
    Comparator<String> osbl = new OrderStringByLex();
    Comparator<Integer> oibm = new OrderIntByMag();
    
    // to test the comparators compare method
    void testComparatorCompare(Tester t) {
        t.checkExpect(this.osbl.compare("bob", "rob"), -16);
        t.checkExpect(this.osbl.compare("bob", "bob"), 0);
        t.checkExpect(this.osbl.compare("rob", "bob"), 16);
        
        t.checkExpect(this.oibm.compare(0, 1), -1);
        t.checkExpect(this.oibm.compare(0, 0), 0);
        t.checkExpect(this.oibm.compare(1, 0), 1);
    }
    
    // to test the binarySearch method
    void testBinarySearch(Tester t) {
        this.initData();
        t.checkExpect(this.algos.binarySearch(0, 1, this.ars1, 
                                              this.osbl, "a"), 0);
        t.checkExpect(this.algos.binarySearch(0, 3, this.ars2, 
                                              this.osbl, "d"), 3);
        t.checkExpect(this.algos.binarySearch(0, 2, this.ars2, 
                                              this.osbl, "b"), 1);
        t.checkExpect(this.algos.binarySearch(0, 1, this.ars2, 
                                              this.osbl, "q"), -1);
        
        t.checkExpect(this.algoi.binarySearch(1, 2, this.ari2, 
                                              this.oibm, 3), 2);
        t.checkExpect(this.algoi.binarySearch(1, 1, this.ari2, 
                                              this.oibm, 3), -1);
        t.checkExpect(this.algoi.binarySearch(0, 0, this.ari2, 
                                              this.oibm, 1), 0);
        t.checkExpect(this.algoi.binarySearch(0, 3, this.ari2, 
                                              this.oibm, 4), 3);
    }

    
}
