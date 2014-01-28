/**
 * Nick Alekhine
 * CS3500 - Assignment 3
 * 2014-01-14
 */

/**
 * 
 * @author Nick Alekhine
 * @version 2014-01-14
 *
 */
public class ConsList extends MyList {
    ///////////////////////////////////////////////////////////////////////////
    // Fields /////////////////////////////////////////////////////////////////
    
    /** To represent the first String element in this list */
    String first;
    /** To represent the rest of the elements in this list */
    MyList rest;
    
    
    
    
    
    ///////////////////////////////////////////////////////////////////////////
    // Constructor ////////////////////////////////////////////////////////////
    
    /** 
     * The constructor for ConsList 
     * @param first String - the first element
     * @param rest MyList - the rest of the list
     * */
    ConsList(String first, MyList rest) {
        this.first = first;
        this.rest = rest;
    }
    
    
    
    
    
    ///////////////////////////////////////////////////////////////////////////
    // Methods ////////////////////////////////////////////////////////////////
    
    /**
     * Method to determine if this list is equal to the given Object.
     * @param o Object - element checked for equivalence
     * @return boolean
     */
    public boolean equals(Object o) {
        // if o is an instance of ConsList
        // and is the same size as this.
        if (o instanceof ConsList
            && MyList.size(this) == MyList.size((ConsList) o)) {
            
            MyList list = (ConsList) o; // Cast o into ConsList
            boolean sameElements = true; // boolean value for while loop
            int i = 0; // starting into for while loop
            
            // check if each element in both lists are the same. 
            while (i < MyList.size(list)) {
                if (MyList.get(this, i).equals(MyList.get(list, i))) {
                    i += 1;
                }
                else {
                    sameElements = false;
                    break;
                }
            }
            
            return sameElements;
        }
        // else false.
        else {
            return false;
        }
    }
    
    /**
     * Method to return the hashCode of this list. 
     * @return int - value of hashCode
     */
    public int hashCode() {
        int hashValue = 0; // set initial value

        // Go through each element in list and add the string hashCode to the
        // hashValue. 
        for (int i = 0; i < MyList.size(this); i++) {
            hashValue += MyList.get(this, i).hashCode();
        }
        
        return hashValue;
    }
    
    /**
     * Method to return a string value of this list.
     * @return string - value of this list.
     */
    public String toString() {
        String list = ""; // list to be returned
        list += "[";
        
        int i = 0;
        // loop through each element except for last one. 
        while (i < MyList.size(this) - 1) {
            list += MyList.get(this, i);
            list += ", ";
            i++;
        }
        
        list += MyList.get(this, i);
        list += "]";
        
        return list;
    }
    
    /**
     * To determine whether the given list is empty
     * @return boolean 
     */
    boolean isEmptyMethod() {
        return false;
    }
    
    /**
     * Method to get the String element at the index specified from this list
     * @param i int - the given index to find in the list
     * @return String value of the element at the given index
     */
    String getMethod(int i) {
        if (i == 0) {
            return this.first;
        }
        else {
            return MyList.get(this.rest, i - 1);
        }
    }
    
    /**
     * Method to set the element at the given index to the given string from 
     * this list
     * @param i int - the given index value
     * @param s String - the element to be set at the given index
     * @return MyList with the new set element.
     */
    MyList setMethod(int i, String s) {
        if (i == 0) {
            return MyList.add(this.rest, s);
        }
        else {
            return MyList.add(MyList.set(this.rest, i - 1, s), this.first);
        }
        
    }
    
    /**
     * Method to determine the amount of elements in this list.
     * @return integer value of the amount of elements
     */
    int sizeMethod() {
        return 1 + MyList.size(this.rest);
    }
    
    /**
     * Method to determine if the given string is contained within this list
     * @param s String - the element that is trying to be found in the list
     * @return boolean
     */
    boolean containsMethod(String s) {
        if (this.first.equals(s)) {
            return true;
        }
        else {
            return MyList.contains(this.rest, s);
        }
    }

}
