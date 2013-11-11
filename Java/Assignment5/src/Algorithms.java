// CS 2510 Fall 2013
// assignment 5
// partner1-alekhine partner1-nicholas
// partner1-alekhn
// partner2-kaminsky partner2-tyler
// partner2-tykam993





/* CLASS DIAGRAM
 * 
 * +------------------------------------------------------------+
 * | Algorithms<T>                                              |
 * +------------------------------------------------------------+
 * | NO FIELDS                                                  |
 * +------------------------------------------------------------+
 * | int binarySearch(int, int, ArrayList<T>, Comparator<T>, T) |
 * +------------------------------------------------------------+
 * 
 *                      +------------+
 *                      | Comparator |
 *                      +------------+
 *                             ^
 *                             |
 *             +-----------------------------+
 *             |                             |
 * +-----------------------------+ +-----------------------+
 * | OrderStringByLex            | | OrderIntByMag         |
 * +-----------------------------+ +-----------------------+
 * | int compare(String, String) | | int compare(int, int) |
 * +-----------------------------+ +-----------------------+
 * 
 * */





///////////////////////////////////////////////////////////////////////////////
// import libraries ///////////////////////////////////////////////////////////
import java.util.Comparator;
import java.util.ArrayList;





///////////////////////////////////////////////////////////////////////////////
// Algorithms /////////////////////////////////////////////////////////////////
// to represent parametrized algorithms
public class Algorithms<T> {
    // to determine if the given object is in the given ArrayList,
    // using a binary search with a supplied upper and lower bounds 
    // and a supplied comparator. 
    public int binarySearch(int lower, int upper, ArrayList<T> data, 
                           Comparator<T> order, T obj) {
        
        if (lower <= upper) {
            // initialize middle index
            int middle = ((upper - lower) / 2) + lower;
            
            // if middle element < given object
            if (order.compare(data.get(middle), obj) > 0) {
                return binarySearch(lower, middle - 1, data, order, obj); 
            }
            // if middle element > given object
            else if (order.compare(data.get(middle), obj) < 0) {
                return binarySearch(middle + 1, upper, data, order, obj);
            }
            // middle element == given object
            else {
                return middle;
            }
        }
        else {
            return -1;
        }
    }


}





///////////////////////////////////////////////////////////////////////////////
// OrderStringByLex ///////////////////////////////////////////////////////////
// FUNCTION OBJECT
// compares strings by lexicographical ordering
class OrderStringByLex implements Comparator<String> {
    // to compare two strings by lexicographic ordering
    public int compare(String s1, String s2) {
        return s1.compareTo(s2);
    }
}





///////////////////////////////////////////////////////////////////////////////
// OrderIntByMag //////////////////////////////////////////////////////////////
// FUNCTION OBJECT
// compares integers by magnitude ordering
class OrderIntByMag implements Comparator<Integer> {
    // to compare two strings by lexicographic ordering
    public int compare(Integer n1, Integer n2) {
        return n1 - n2;
    }
}