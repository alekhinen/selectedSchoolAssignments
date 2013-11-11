// CS 2510 Fall 2013
// assignment 5
// partner1-alekhine partner1-nicholas
// partner1-alekhn
// partner2-kaminsky partner2-tyler
// partner2-tykam993


// CLASS DIAGRAM -- SEE PICTURE ATTACHED IN ZIP



///////////////////////////////////////////////////////////////////////////////
// Person /////////////////////////////////////////////////////////////////////
//represents a Person with a user name and a list of buddies
class Person {

    String username;
    ILoBuddy buddies;

    Person(String username) {
        this.username = username;
        this.buddies = new MTLoBuddy();
    }
    /* TEMPLATE
     * FIELDS
     * ...username... String
     * ...buddies... ILoBuddy
     * 
     * METHODS
     * ...this.hasDirectBuddy(Person)... boolean
     * ...this.countCommonBuddies(Person)... int
     * ...this.hasDistantBuddy(Person)... boolean
     * ...this.hasDistantBuddyHelper(Person, ILoBuddy)... boolean
     * ...this.addBuddy(Person)... void
     * ...this.partyCount()... int
     * ...this.partyCountHelper(ILoBuddy)... int
     * 
     * METHODS FOR FIELDS
     * ...this.buddies.isInBuddyList(Person)... boolean
     * ...this.buddies.countCommonBuddies(Person, ILoBuddy)... int
     * ...this.buddies.contains(Person)... boolean
     * ...this.buddies.distantBuddies(Person, ILoBuddy)... boolean
     * ...this.buddies.countDirect(ILoBuddy)... int
     * ...this.buddies.partyCount(ILoBuddy)... int
     * */


    ///////////////////////////////////////////////////////////////////////////
    // returns true if this Person has that as a direct buddy
    boolean hasDirectBuddy(Person that) {
        return this.buddies.isInBuddyList(that);
    }


    ///////////////////////////////////////////////////////////////////////////
    // returns the number of people that are direct buddies 
    // of both this and that person
    int countCommonBuddies(Person that) {
        return this.buddies.countCommonBuddies(that.buddies);
    }


    ///////////////////////////////////////////////////////////////////////////
    // will the given person be invited to a party 
    // organized by this person?
    boolean hasDistantBuddy(Person that) {
        return this.hasDistantBuddyHelper(that, new MTLoBuddy());
    }


    ///////////////////////////////////////////////////////////////////////////
    // helper for hasDistantBuddy
    boolean hasDistantBuddyHelper(Person that, ILoBuddy acc) {
        if (!acc.isInBuddyList(this) 
            && this.buddies.isInBuddyList(that)) {
            return true;
        }
        else {
            return this.buddies.distantBuddies(that, 
                    new ConsLoBuddy(this, 
                            acc));
        }
    }


    ///////////////////////////////////////////////////////////////////////////
    //Effect: change this person's buddy list to include the given person
    void addBuddy(Person buddy) {
        this.buddies = new ConsLoBuddy(buddy, this.buddies);
    }

    
    ///////////////////////////////////////////////////////////////////////////
    // to determine how many people are coming to this party 
    // [excluding this person].
    int partyCount() {
        return this.partyCountHelper(new MTLoBuddy());
    }
    
    
    ///////////////////////////////////////////////////////////////////////////
    // to determine how many people are coming to this party 
    // [excluding this person].
    int partyCountHelper(ILoBuddy acc) {
        return  this.buddies.countDirect(new ConsLoBuddy(this, acc)) + 
                this.buddies.partyCount(new ConsLoBuddy(this, acc));
    }
}





///////////////////////////////////////////////////////////////////////////////
// ILoBuddy ///////////////////////////////////////////////////////////////////
// represents a list of Person's buddies
interface ILoBuddy {


    ///////////////////////////////////////////////////////////////////////////
    // to determine if the given person is in this list
    public boolean isInBuddyList(Person that);


    ///////////////////////////////////////////////////////////////////////////
    // to count the amount of buddies that appear in this list and the given 
    // list
    public int countCommonBuddies(ILoBuddy that);


    ///////////////////////////////////////////////////////////////////////////
    // to determine whether the given person is within this list
    public boolean contains(Person that);

    ///////////////////////////////////////////////////////////////////////////
    // will the given person be invited to a party organized by this person?
    public boolean distantBuddies(Person that, ILoBuddy acc);


    ///////////////////////////////////////////////////////////////////////////
    // calculates the number of items in a list
    public int countDirect(ILoBuddy acc);


    ///////////////////////////////////////////////////////////////////////////
    // to count the number of people in this party
    public int partyCount(ILoBuddy acc);
}





///////////////////////////////////////////////////////////////////////////////
// ConsLoBuddy ////////////////////////////////////////////////////////////////
//represents a list of Person's buddies
class ConsLoBuddy implements ILoBuddy {

    Person first;
    ILoBuddy rest;

    ConsLoBuddy(Person first, ILoBuddy rest) {
        this.first = first;
        this.rest = rest;
    }
    /* TEMPLATE
     * FIELDS
     * ...first... Person
     * ...rest...  ILoBuddy
     * 
     * METHODS
     * ...this.isInBuddyList(Person)... boolean
     * ...this.countCommonBuddies(Person, ILoBuddy)... int
     * ...this.contains(Person)... boolean
     * ...this.distantBuddies(Person, ILoBuddy)... boolean
     * ...this.countDirect(ILoBuddy)... int
     * ...this.partyCount(ILoBuddy)... int
     * 
     * METHODS FOR FIELDS
     * ...this.first.hasDirectBuddy(Person)... boolean
     * ...this.first.countCommonBuddies(Person)... int
     * ...this.first.hasDistantBuddy(Person)... boolean
     * ...this.first.hasDistantBuddyHelper(Person, ILoBuddy)... boolean
     * ...this.first.addBuddy(Person)... void
     * ...this.first.partyCount()... int
     * ...this.first.partyCountHelper(ILoBuddy)... int
     * 
     * ...this.rest.isInBuddyList(Person)... boolean
     * ...this.rest.countCommonBuddies(Person, ILoBuddy)... int
     * ...this.rest.contains(Person)... boolean
     * ...this.rest.distantBuddies(Person, ILoBuddy)... boolean
     * ...this.rest.countDirect(ILoBuddy)... int
     * ...this.rest.partyCount(ILoBuddy)... int
     * 
     * */


    ///////////////////////////////////////////////////////////////////////////
    // to determine if the given person is in this list
    public boolean isInBuddyList(Person that) {
        if (this.first.username.equals(that.username)) {
            return true;
        }
        else {
            return this.rest.isInBuddyList(that);
        }
            
    }


    ///////////////////////////////////////////////////////////////////////////
    // to count the amount of buddies that appear in this 
    // list and the given list
    public int countCommonBuddies(ILoBuddy that) {
        if (that.contains(this.first)) {
            return 1 + this.rest.countCommonBuddies(that);
        }
        else {
            return this.rest.countCommonBuddies(that);
        }
    }


    ///////////////////////////////////////////////////////////////////////////
    // to determine whether the given person is within
    // this list
    public boolean contains(Person that) {
        if (that.username.equals(this.first.username)) {
            return true;
        }  
        else {
            return this.rest.contains(that);
        }
            
    }

    ///////////////////////////////////////////////////////////////////////////
    // will the given person be invited to a party 
    // organized by this person?
    public boolean distantBuddies(Person that, ILoBuddy acc) {
        if (!acc.isInBuddyList(this.first) && 
                this.first.hasDistantBuddyHelper(that, acc)) {
            return true;
        }
        else {
            return this.rest.distantBuddies(that, 
                                            new ConsLoBuddy(this.first, acc));
        }
    }


    ///////////////////////////////////////////////////////////////////////////
    // counts the direct buddies. 
    public int countDirect(ILoBuddy acc) {
        if (!acc.isInBuddyList(this.first)) {
            return 1 + this.rest.countDirect(new ConsLoBuddy(this.first, acc));
        }
        else {
            return this.rest.countDirect(acc);
        }
    }


    ///////////////////////////////////////////////////////////////////////////
    // to count the number of people in this party
    public int partyCount(ILoBuddy acc) {
        if (!acc.isInBuddyList(this.first)) {
            return this.first.partyCountHelper(acc) 
                    + this.rest.partyCount(new ConsLoBuddy(this.first, acc)); 
        }
        else {
            return this.rest.partyCount(acc);
        }
    }
}





///////////////////////////////////////////////////////////////////////////////
// MTLoBuddy //////////////////////////////////////////////////////////////////
//represents an empty list of Person's buddies
class MTLoBuddy implements ILoBuddy {
    /* TEMPLATE
     * FIELDS
     * N/A
     * 
     * METHODS
     * ...this.isInBuddyList(Person)... boolean
     * ...this.countCommonBuddies(Person, ILoBuddy)... int
     * ...this.contains(Person)... boolean
     * ...this.distantBuddies(Person, ILoBuddy)... boolean
     * ...this.countDirect(ILoBuddy)... int
     * ...this.partyCount(ILoBuddy)... int
     * 
     * */


    ///////////////////////////////////////////////////////////////////////////
    // to determine if the given person is in this list
    public boolean isInBuddyList(Person that) {
        return false;
    }


    ///////////////////////////////////////////////////////////////////////////
    // to count the amount of buddies that appear in this 
    // list and the given list
    public int countCommonBuddies(ILoBuddy that) {
        return 0;
    }


    ///////////////////////////////////////////////////////////////////////////
    // to determine whether the given person is within
    // this list
    public boolean contains(Person that) {
        return false;
    }

    ///////////////////////////////////////////////////////////////////////////
    // will the given person be invited to a party 
    // organized by this person?
    public boolean distantBuddies(Person that, ILoBuddy acc) {
        return false;
    }


    ///////////////////////////////////////////////////////////////////////////
    // to count the number of buddies in this list [excluding duplicates]
    public int countDirect(ILoBuddy acc) {
        return 0;
    }


    ///////////////////////////////////////////////////////////////////////////
    // to count the number of people in this party
    public int partyCount(ILoBuddy acc) {
        return 0;
    }
}






