/**
 * This class holds the static definition of the methods under testing
 *
 * @author  Mircea Gelu Egry
 * @version 1.0
 */

public class TestMethods {

    /// PREDICATE EXAMPLES

    /**
     * Check if a number is bigger than the other one
     *
     * @param a the first number
     * @param b the second number
     * @return true if a <= b otherwise false
     */
    public static boolean checkIfNumberBigger(int a, int b) {
        return a <= b;
    }

    /**
     * Check if a number is between another two
     *
     * @param a the first number
     * @param b the second number
     * @param c the third number
     * @return boolean if number in range or not
     */
    public static boolean checkNumberInRange(int a, int b, int c)  {
        return a >= b && a <= c;
    }

    /**
     * Just a more complicated branch predicate to test
     *
     * @param a the first number
     * @param b the second number
     * @param c the third number
     * @param d the fourth number
     * @return boolean if logic expression is true or false
     */
    public static boolean testBranchPredicate(int a, int b, int c, int d) {
        return (a <= b && a >= c) || (a <= d && a != 0);
    }
}
