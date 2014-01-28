/**
 * Nick Alekhine
 * CS3500 - Assignment 3
 * 2014-01-14
 */

/**
 * To represent an empty list
 * @author Nick Alekhine
 * @version 2014-01-14
 *
 */
public class EmptyList extends MyList {
    
    /**
     * Method to determine if this list is equal to the given Object.
     * @param o Object - element checked for equivalence
     * @return boolean
     */
    public boolean equals(Object o) {
        return (o instanceof EmptyList
                && MyList.isEmpty((EmptyList) o));
    }
    
    /**
     * Method to return the hashCode of this list. 
     * @return int - value of hashCode
     */
    public int hashCode() {
        return 0;
    }
    
    /**
     * Method to return a string value of this list.
     * @return string - value of this list.
     */
    public String toString() {
        return "[]";
    }
    
    /**
     * To determine whether the given list is empty
     * @return boolean 
     */
    boolean isEmptyMethod() {
        return true;
    }
    
    /**
     * Method to get the String element at the index specified from this list
     * @param i int - the given index to find in the list
     * @return String value of the element at the given index
     */
    String getMethod(int i) {
        throw new IndexOutOfBoundsException("The supplied index "
                                            + "does not exist!");
    }
    
    /**
     * Method to set the element at the given index to the given string from 
     * this list
     * @param i int - the given index value
     * @param s String - the element to be set at the given index
     * @return MyList with the new set element.
     */
    MyList setMethod(int i, String s) {
        throw new IndexOutOfBoundsException("The supplied index "
                                            + "does not exist!");
    }
    
    /**
     * Method to determine the amount of elements in this list.
     * @return integer value of the amount of elements
     */
    int sizeMethod() {
        return 0;
    }
    
    /**
     * Method to determine if the given string is contained within this list
     * @param s String - the element that is trying to be found in the list
     * @return boolean
     */
    boolean containsMethod(String s) {
        return false;
    }
    
}
