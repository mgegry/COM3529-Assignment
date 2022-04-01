/**
 * This enum holds the basic conditions that an operation supports
 *
 * @author  Mircea Gelu Egry
 * @version 1.0
 */
enum Condition {
    EQUAL,
    NOTEQUAL,
    LTOET,
    GTOET,
    GRATERTHAN,
    LESSTHAN,
}

/**
 * This class holds the data structure definition of an operation
 *
 * @author  Mircea Gelu Egry
 * @version 1.0
 */
class Operation {
    String leftSide;
    String rightSide;
    Condition cond;
    boolean state = false;

    /**
     * The class constructor
     *
     * @param leftSide the left side of the operation
     * @param rightSide the right side of the operation
     * @param cond the operator
     */
    Operation(String leftSide, String rightSide, Condition cond) {
        this.leftSide = leftSide;
        this.rightSide = rightSide;
        this.cond = cond;
    }
}