 /**
 * Nick Alekhine
 * CS3500 - Assignment 2
 * 2014-01-14
 */


/**
 * A test program for MyList
 * @author Nick Alekhine
 * @version 2014-01-15
 */
public class TestMyList {
 
    
    // Run the tests
    /**
     * Runs the tests.
     * @param args the command line arguments
     */ 
    public static void main(String[] args) {
        TestMyList test = new TestMyList();
        test.creation();
        
        test.testEquals();
        test.testHashCode();
        test.testToString();
        test.testIsEmpty();
        test.testGet();
        test.testSet();
        test.testSize();
        test.testContains();
        
        test.summarize();
    }
    
    /**
     * Prints a summary of the tests.
     */
    private void summarize() {
        System.out.println();
        System.out.println(totalErrors + " errors found in " +
                           totalTests + " tests.");
    }
    
    
    
    // Initialize variables
    /** EmptyList */
    MyList m0;
    /** ConsList 1 */
    MyList m1;
    /** ConsList 2 */
    MyList m2;
    /** ConsList 3 */
    MyList m3;
    /** ConsList 4 */
    MyList m4;
    /** ConsList 5 */
    MyList m5;
    /** ConsList 6 */
    MyList m6;
    
    
    
    /**
     * Creates some MyList objects
     */
    private void creation() {
        try {
            m0 = MyList.emptyList();
            m1 = MyList.add(m0, "1");
            m2 = MyList.add(m1, "2");
            m3 = MyList.add(m2, "3");
            m4 = MyList.add(m0, "1");
            m5 = MyList.add(m2, "5");
            m6 = MyList.add(m5, "6");
        }
        catch (Exception e) {
            assertTrue("creation", false);
        }
    }
    
    
    
    
    
    ///////////////////////////////////////////////////////////////////////////
    // Method Specific Testing ////////////////////////////////////////////////
    /**
     * Tests the equals method in MyList.
     */
    private void testEquals() {
        try {
            assertTrue("equals:m0", m0.equals(m0));
            assertFalse("equals:m0", m0.equals(m1));
            assertTrue("equals:m3", m3.equals(m3));
            assertFalse("equals:m3", m3.equals(m6));
            assertFalse("equals:m6", m6.equals(m3));
            assertFalse("equals:m3", m3.equals(m0));
            assertFalse("equals:m3", m3.equals(null));
            assertTrue("equals:m4", m4.equals(m1));
        }
        catch (Exception e) {
            assertTrue("testEquals", false);
        }
    }
    
    /**
     * Tests the hashCode method in MyList.
     */
    private void testHashCode() {
        try {
            assertTrue("hashCode:m0", m0.hashCode() == 0);
            assertTrue("hashCode:m1", m1.hashCode() > 0);
            assertFalse("hashCode:m1", m1.hashCode() == 0);
            assertTrue("hashCode:m2", m2.hashCode() != m1.hashCode());
            assertTrue("hashCode:m4", m4.hashCode() == m1.hashCode());
        }
        catch (Exception e) {
            assertTrue("testHashCode", false);
        }
    }
    
    /**
     * Tests the toString method in MyList.
     */
    private void testToString() {
        try {
            assertTrue("toString:m0", m0.toString().equals("[]"));
            assertTrue("toString:m1", m1.toString().equals("[1]"));
            assertTrue("toString:m3", m3.toString().equals("[3, 2, 1]"));
            assertTrue("toString:m5", m5.toString().equals("[5, 2, 1]"));
        }
        catch (Exception e) {
            assertTrue("testToString", false);
        }
    }
    
    /**
     * Tests the isEmpty method in MyList.
     */
    private void testIsEmpty() {
        try {
            assertTrue("isEmpty:m0", MyList.isEmpty(m0));
            assertFalse("isEmpty:m1", MyList.isEmpty(m1));
            assertFalse("isEmpty:m6", MyList.isEmpty(m6));
        }
        catch (Exception e) {
            assertTrue("testIsEmpty", false);
        }
    }
    
    /**
     * Tests the get method in MyList
     */
    private void testGet() {
        try {
            // Not sure how to test IndexOutOfBoundsException
            // Unable to test case for emptyList or when index out of bounds.
            assertTrue("get:m1", MyList.get(m1, 0).equals("1"));
            assertFalse("get:m1", MyList.get(m2, 1).equals("0"));
            assertTrue("get:m4", MyList.get(m4, 0).equals("1"));
            assertTrue("get:m5", MyList.get(m5, 0).equals("5"));
            assertTrue("get:m5", MyList.get(m5, 2).equals("1"));
        }
        catch (Exception e) {
            assertTrue("testGet", false);
        }
    }
    
    /**
     * Tests the set method in MyList
     */
    private void testSet() {
        try {
            // Unable to test case for emptyList or when index out of bounds.
            assertTrue("set:m1", 
                       MyList.set(m1, 0, "hi").equals(MyList.add(m0, "hi")));
            assertTrue("set:m2", 
                       MyList.set(m2, 0, "bob").equals(MyList.add(m1, "bob")));
            assertFalse("set:m6",
                        MyList.set(m6, 3, "waka").equals(m6));
        }
        catch (Exception e) {
            assertTrue("testSet", false);
        }
    }
    
    /**
     * Tests the size method in MyList
     */
    private void testSize() {
        try {
            assertTrue("size:m0", MyList.size(m0) == 0);
            assertTrue("size:m1", MyList.size(m1) == 1);
            assertTrue("size:m3", MyList.size(m3) == 3);
            assertTrue("size:m6", MyList.size(m6) == 4);
        }
        catch (Exception e) {
            assertTrue("testSize", false);
        }
    }
    
    /**
     * Tests the contains method in MyList
     */
    private void testContains() {
        try {
            assertFalse("contains:m0", MyList.contains(m0, "barack"));
            assertFalse("contains:m6", MyList.contains(m6, "obama"));
            assertTrue("contains:m3", MyList.contains(m3, "2"));
            assertTrue("contains:m5", MyList.contains(m5, "1"));
        }
        catch (Exception e) {
            assertTrue("testContains", false);
        }
    }
    
    
    
    ///////////////////////////////////////////////////////////////////////////
    // General Testing Setup //////////////////////////////////////////////////
    
    private int totalTests = 0;       // tests run so far
    private int totalErrors = 0;      // errors so far


   /**
    * Prints failure report if the result is not true.
    * @param name the name of the test
    * @param result the result to test
    */
    private void assertTrue(String name, boolean result) {
        if (!result) {
            System.out.println();
            System.out.println("***** Test Failed ***** "
                               + name + ": " + totalTests);
            totalErrors = totalErrors + 1;
        }
        else {
            System.out.println("----- Test Passed ----- "
                               + name + ": " + totalTests);
        }
        
        totalTests = totalTests + 1;
    }
   
    /**
     * Prints failure report if the result is not false.
     * @param name the name of the test
     * @param result the result to test
     */
    private void assertFalse(String name, boolean result) {
        assertTrue(name, !result);
    }
    
}
