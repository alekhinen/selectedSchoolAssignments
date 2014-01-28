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
public abstract class MyList {    
    
    ///////////////////////////////////////////////////////////////////////////
    // Basic Constructors /////////////////////////////////////////////////////
    
    /**
     * To return an empty list
     * @return an empty list
     */
    public static MyList emptyList() {
        return new EmptyList();
    }
    
    /**
     * To return a list with the given String added to the given list.
     * @param m MyList - a given list to be added onto
     * @param s String - the given string to add
     * @return MyList - a list with the string added onto the given list
     */
    public static MyList add(MyList m, String s) {
        return new ConsList(s, m);
    }
    
    
    
    
    
    ///////////////////////////////////////////////////////////////////////////
    // Abstract Classes ///////////////////////////////////////////////////////
    /**
     * Method to determine if this list is equal to the given Object.
     * @param o Object - element checked for equivalence
     * @return boolean
     */
    public abstract boolean equals(Object o);
    
    /**
     * Method to return the hashCode of this list. 
     * @return int - value of hashCode
     */
    public abstract int hashCode();
    
    /**
     * Method to return a string value of this list.
     * @return string - value of this list.
     */
    public abstract String toString();
    
    /**
     * To determine whether the given list is empty
     * @return boolean 
     */
    abstract boolean isEmptyMethod();
    
    /**
     * Method to get the String element at the index specified from this list
     * @param i int - the given index to find in the list
     * @return String value of the element at the given index
     */
    abstract String getMethod(int i);
    
    /**
     * Method to set the element at the given index to the given string from 
     * this list
     * @param i int - the given index value
     * @param s String - the element to be set at the given index
     * @return MyList with the new set element.
     */
    abstract MyList setMethod(int i, String s);
    
    /**
     * Method to determine the amount of elements in this list.
     * @return integer value of the amount of elements
     */
    abstract int sizeMethod();
    
    /**
     * Method to determine if the given string is contained within this list
     * @param s String - the element that is trying to be found in the list
     * @return boolean
     */
    abstract boolean containsMethod(String s);
    
    
    
    
    
    ///////////////////////////////////////////////////////////////////////////
    // Static Classes /////////////////////////////////////////////////////////
    
    /**
     * Method to determine whether the given list is empty
     * @param m MyList the given list 
     * @return boolean
     */
    public static boolean isEmpty(MyList m) {
        return m.isEmptyMethod();
    }
    
    /**
     * Method to get the String element at the index specified from the given
     * list
     * @param m MyList - the given list to search through
     * @param i int - the given index to find in the list
     * @return String value of the element at the given index
     */
    public static String get(MyList m, int i) {
        return m.getMethod(i);
    }
    
    /**
     * Method to set the element at the given index to the given string from 
     * the given list.
     * @param m MyList - the given list to find and set the element
     * @param i int - the given index value
     * @param s String - the element to be set at the given index
     * @return MyList with the new set element.
     */
    public static MyList set(MyList m, int i, String s) {
        return m.setMethod(i, s);
    }
    
    /**
     * Method to determine the amount of elements in the given list.
     * @param m MyList - the list where the elements are counted
     * @return integer value of the amount of elements
     */
    public static int size(MyList m) {
        return m.sizeMethod();
    }
    
    /**
     * Method to determine if the given string is contained within the given
     * list. 
     * @param m MyList - the list where the searching is done.
     * @param s String - the element that is trying to be found in the list
     * @return boolean
     */
    public static boolean contains(MyList m, String s) {
        return m.containsMethod(s);
    }
    
}
