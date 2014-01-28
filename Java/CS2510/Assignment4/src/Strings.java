// CS 2510 Fall 2013
// assignment 4
// partner1-alekhine partner1-nicholas
// partner1-alekhn
// partner2-kaminsky partner2-tyler
// partner2-tykam993

///////////////////////////////////////////////////////////////////////////////
// import libraries ///////////////////////////////////////////////////////////
///////////////////////////////////////////////////////////////////////////////
import tester.*;


/*                                  +------+
 *                                  | ILoS |
 *                                  +------+
 *                                      ^
 *                 +--------------------+------------------+
 *                 |                                       |
 *  +--------------------------------+     +--------------------------------+
 *  | MtLoS                          |     | ConsLos                        |
 *  +--------------------------------+     +--------------------------------+
 *  | String combine()               |     | String first                   |
 *  | boolean isSorted()             |     | ILoS rest                      |
 *  | boolean stringComparer(String) |     +--------------------------------+
 *  | ILoS merge(ILoS)               |     | String combine()               |
 *  | ILoS insert(String)            |     | boolean isSorted()             |
 *  | ILoS sort()                    |     | boolean stringComparer(String) |
 *  +--------------------------------+     | ILoS merge(ILoS)               |
 *                                         | ILoS insert(String)            |
 *                                         | ILoS sort()                    |
 *                                         +--------------------------------+
 *                                         
 * */

///////////////////////////////////////////////////////////////////////////////
// ILoS ///////////////////////////////////////////////////////////////////////
///////////////////////////////////////////////////////////////////////////////
// to represent a list of Strings
interface ILoS {
    // combine all Strings in this list into one
    public String combine();
    // is this list sorted lexicographically?
    public boolean isSorted();
    // compares this list's first element to a given string.
    public boolean stringComparer(String that);
    // takes two sorted lists and merges them into one sorted list
    public ILoS merge(ILoS that);
    // to insert that string into the given list, lexicographically.
    public ILoS insert(String that);
    // to sort this list
    public ILoS sort();
}





// MtLoS //////////////////////////////////////////////////
// to represent an empty list of Strings
class MtLoS implements ILoS {

    /* TEMPLATE
     * FIELDS
     * N/A
     * 
     * METHODS
     * ...combine()...              --String
     * ...isSorted()...             --boolean
     * ...stringComparer(String)... --boolean
     * ...merge(ILoS)...            --ILoS
     * ...insert(String)...         --ILoS
     * ...sort()...                 --ILoS
     * 
     * */

    // combine all Strings in this list into one
    public String combine() {
        return "";
    }

    // is this list sorted lexicographically?
    public boolean isSorted() {
        return true;
    }

    //compares this list's first element to
    // a given string.
    public boolean stringComparer(String that) {
        return true;
    }

    // takes two sorted lists and merges them into one sorted list
    public ILoS merge(ILoS that) {
        return that;
    }

    // to insert that string into the given list, lexicographically.
    public ILoS insert(String that) {
        return new ConsLoS(that, new MtLoS());
    }
    
    // to sort this list
    public ILoS sort() {
        return this;
    }
}





// ConLoS /////////////////////////////////////////////////
// to represent a nonempty list of Strings 
class ConsLoS implements ILoS {
    String first;
    ILoS rest;
    StringsCompare type;

    ConsLoS(String first, ILoS rest) {
        this.first = first;
        this.rest = rest;
        this.type = new StringLexComp();
    }

    /*
    TEMPLATE
    FIELDS:
    ... this.first ...         -- String
    ... this.rest ...          -- ILoS
    ... this.type ...          -- StringsCompare

    METHODS
    ... this.combine() ...                  -- String
    ... this.isSorted()...                  -- boolean
    ... this.stringComparer(String)...      -- boolean
    ... this.merge(String)...               -- ILoS     
    ... this.insert(String)...              -- ILoS
    ... this.sort() ...                     -- ILoS        

    METHODS FOR FIELDS
    ... this.first.concat(String) ...       -- String
    ... this.first.compareTo(String) ...    -- int
    ... this.rest.combine() ...             -- String
    ... this.rest.isSorted()...             -- boolean
    ... this.rest.stringComparer(String)... -- boolean
    ... this.rest.merge(String)...          -- ILoS
    ... this.rest.sort()...                 -- ILoS

   */

    // combine all Strings in this list into one
    public String combine() {
        return this.first.concat(this.rest.combine());
    }

    // to check if this list is sorted
    public boolean isSorted() {
        return this.rest.stringComparer(this.first);
    }

    // compares this list's first element to
    // a given string.
    public boolean stringComparer(String that) {
        if (this.type.comesBefore(that.toLowerCase(), 
                                  this.first.toLowerCase())) {
            return this.isSorted();
        }
        else {
            return false;
        }
    }

    // takes two sorted lists and merges them into one sorted list
    public ILoS merge(ILoS that) {
        return this.rest.merge(that.insert(this.first));
    }

    // to insert that string into the given list, lexicographically.
    public ILoS insert(String that) {
        if (this.type.comesBefore(that.toLowerCase(), 
                                  this.first.toLowerCase())) {
            return new ConsLoS(that, this);
        }
        else {
            return new ConsLoS(this.first, this.rest.insert(that));
        }
    }
    
    // to sort this list
    public ILoS sort() {
        return this.rest.sort().insert(this.first);
    }

}





///////////////////////////////////////////////////////////////////////////////
// StringsCompare /////////////////////////////////////////////////////////////
///////////////////////////////////////////////////////////////////////////////
// interface for various function objects
interface StringsCompare {
    
    // to determine if the first given string comes before
    // the second given string. 
    public boolean comesBefore(String s1, String s2);
}





// StringLexComp //////////////////////////////////////////
// function object: compares two strings lexicographically
class StringLexComp implements StringsCompare {
    
    // to determine if the first given string comes before
    // the second given string [lexicographically]
    public boolean comesBefore(String s1, String s2) {
        return s1.compareTo(s2) < 0;
    }
}





// StringLengthComp ///////////////////////////////////////
// function object: compares two strings by their length
class StringLengthComp implements StringsCompare {
    
    // to determine if the first given string comes before
    // the second given string [by length]
    public boolean comesBefore(String s1, String s2) {
        return s1.length() < s2.length(); 
    }
}





///////////////////////////////////////////////////////////////////////////////
// ExamplesStrings ////////////////////////////////////////////////////////////
///////////////////////////////////////////////////////////////////////////////
// to represent examples for lists of strings
class ExamplesStrings {

    ILoS mt = new MtLoS();

    // example cases of ILoS
    ILoS mary = new ConsLoS("Mary ",
                 new ConsLoS("had ",
                  new ConsLoS("a ",
                   new ConsLoS("little ",
                    new ConsLoS("lamb.", new MtLoS())))));
    ILoS marysort = new ConsLoS("a ", 
                     new ConsLoS("had ", 
                      new ConsLoS("lamb.", 
                       new ConsLoS("little ",
                        new ConsLoS("Mary ", new MtLoS())))));

    ILoS bob = new ConsLoS("a", 
                new ConsLoS("c", 
                 new ConsLoS("e", new MtLoS())));

    ILoS jeff = new ConsLoS("B", 
                    new ConsLoS("d", 
                        new ConsLoS("f", new MtLoS())));

    ILoS bobJeff = new ConsLoS("a", 
                       new ConsLoS("B",
                            new ConsLoS("c",
                                new ConsLoS("d",
                                    new ConsLoS("e",
                                        new ConsLoS("f", new MtLoS()))))));

    // examples for StringsCompare
    StringsCompare lexcomp = new StringLexComp();
    StringsCompare lencomp = new StringLengthComp();
    
    // to test the comesBefore method for interface StringsCompare
    void testComesBefore(Tester t) {
        t.checkExpect(this.lexcomp.comesBefore("flying", "lotus"), true);
        t.checkExpect(this.lexcomp.comesBefore("lotus", "fly"), false);
        t.checkExpect(this.lencomp.comesBefore("baaa", "b"), false);
        t.checkExpect(this.lencomp.comesBefore("bb", "bababa"), true);
    }
    
    
    // to test the Combine method
    void testCombine(Tester t) {
        t.checkExpect(this.mary.combine(), "Mary had a little lamb.");
    }

    // to test the method isSorted
    void testIsSorted(Tester t) {
        t.checkExpect(this.mary.isSorted(), false);
        t.checkExpect(this.bob.isSorted(), true);
        t.checkExpect(this.mt.isSorted(), true);
    }

    // to test the method merge
    void testMerge(Tester t) {
        t.checkExpect(this.bob.merge(this.jeff), this.bobJeff);
    }

    // to test the method insert
    void testInsert(Tester t) {
        t.checkExpect(this.mt.insert("that"), new ConsLoS("that", this.mt));
    }
    
    // to test the method sort
    void testSort(Tester t) {
        t.checkExpect(this.mary.sort(), this.marysort);
        t.checkExpect(this.mt.sort(), this.mt);
    }
}
