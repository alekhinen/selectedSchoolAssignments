// CS 2510 Fall 2013
// assignment 5
// partner1-alekhine partner1-nicholas
// partner1-alekhn
// partner2-kaminsky partner2-tyler
// partner2-tykam993





///////////////////////////////////////////////////////////////////////////////
// import libraries ///////////////////////////////////////////////////////////
import tester.*;





///////////////////////////////////////////////////////////////////////////////
// ExamplesBuddies ////////////////////////////////////////////////////////////
// runs tests for the buddies problem
public class ExamplesBuddies {
    
    // instantiate some people
    Person ann;
    Person bob;
    Person cole;
    Person dan;
    Person ed;
    Person fay;
    Person gabi;
    Person hank;
    Person hank2;
    Person jan;
    Person kim;
    Person len;
    
    // set initial values for people
    void reset() {
        ann = new Person("Ann");
        bob = new Person("Bob");
        cole = new Person("Cole");
        dan = new Person("Dan");
        ed = new Person("Ed");
        fay = new Person("Fay");
        gabi = new Person("Gabi");
        hank = new Person("Hank");
        hank2 = new Person("Hank2");
        jan = new Person("Jan");
        kim = new Person("Kim");
        len = new Person("Len");
    } 
    
    // Examples of ILoBuddy
    ILoBuddy mt = new MTLoBuddy();
    ILoBuddy annb = new ConsLoBuddy(this.cole, 
                     new ConsLoBuddy(this.bob, this.mt));
    
    
    
    // method for adding buddies
    void initBuddies() {
        this.reset();
        this.ann.addBuddy(this.bob);
        this.ann.addBuddy(this.cole);
        this.bob.addBuddy(this.ann);
        this.bob.addBuddy(this.ed);
        this.bob.addBuddy(this.hank);
        this.cole.addBuddy(this.dan);
        this.dan.addBuddy(this.cole);
        this.ed.addBuddy(this.fay);
        this.fay.addBuddy(this.ed);
        this.fay.addBuddy(this.gabi);
        this.gabi.addBuddy(this.ed);
        this.gabi.addBuddy(this.fay);
        this.hank2.addBuddy(this.ann);
        this.jan.addBuddy(this.kim);
        this.jan.addBuddy(this.len);
        this.kim.addBuddy(this.jan);
        this.kim.addBuddy(this.len);
        this.len.addBuddy(this.jan);
        this.len.addBuddy(this.kim);
    }
    
    // to test the addBuddy method
    void testAddBuddy(Tester t) {
        this.reset();
        t.checkExpect(this.ann.buddies, this.mt);
        t.checkExpect(this.hank.buddies, this.mt);
        this.initBuddies();
        t.checkExpect(this.hank.buddies, this.mt);
        t.checkExpect(this.ann.buddies, new ConsLoBuddy(this.cole, 
                                         new ConsLoBuddy(this.bob, 
                                                         this.mt)));
    }
    
    // to test the isInBuddyList method
    void testIsInBuddyList(Tester t) {
        this.initBuddies();
        t.checkExpect(this.bob.buddies.isInBuddyList(this.ann), true);
        t.checkExpect(this.hank.buddies.isInBuddyList(this.ann), false);
    }
    
    // to test the hasDirectBuddy method
    void testHasDirectBuddy(Tester t) {
        this.initBuddies();
        t.checkExpect(this.ann.hasDirectBuddy(this.fay), false);
        t.checkExpect(this.ann.hasDirectBuddy(this.cole), true);
        t.checkExpect(this.hank.hasDirectBuddy(this.len), false);
    }
    
    // to test the countCommonBuddies method
    void testCountCommonBuddies(Tester t) {
        this.initBuddies();
        t.checkExpect(this.ann.countCommonBuddies(this.bob), 0);
        t.checkExpect(this.bob.countCommonBuddies(this.fay), 1);
        t.checkExpect(this.hank.countCommonBuddies(this.ann), 0);
        t.checkExpect(this.dan.countCommonBuddies(this.ann), 1);
    }
    
    // to test the hasDistantBuddy method
    void testHasDistantBuddy(Tester t) {
        this.initBuddies();
        t.checkExpect(this.ann.hasDistantBuddy(this.hank), true);
        t.checkExpect(this.hank.hasDistantBuddy(this.ann), false);
        t.checkExpect(this.bob.hasDistantBuddy(this.cole), true);
        t.checkExpect(this.ann.hasDistantBuddy(this.dan), true);
        t.checkExpect(this.bob.hasDistantBuddy(this.gabi), true);
    }
    
    // to test the countDirect method
    void testCountDirect(Tester t) {
        this.initBuddies();
        t.checkExpect(this.hank.buddies.countDirect(this.mt), 0);
        t.checkExpect(this.dan.buddies.countDirect(this.mt), 1);
        t.checkExpect(this.ann.buddies.countDirect(this.mt), 2);
        t.checkExpect(this.bob.buddies.countDirect(this.mt), 3);
    }
    
    // to test the partyCount method
    void testPartyCount(Tester t) {
        this.initBuddies();
        t.checkExpect(this.dan.partyCount(), 1);
        t.checkExpect(this.ann.partyCount(), 7);
        t.checkExpect(this.jan.partyCount(), 3);
    }
    
}