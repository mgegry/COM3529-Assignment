import java.util.ArrayList;

/**
 * This enum holds the two types of logic operations available
 *
 * @author  Mircea Gelu Egry
 * @version 1.0
 */
enum LogicOperator {
    AND,
    OR
}

/**
 * This class holds the data structure for a logic operation
 *
 * @author  Mircea Gelu Egry
 * @version 1.0
 */
public class LogicOperation {
    ArrayList<Operation> operations;
    LogicOperator operator;
    Operation operation;

    /**
     * The class constructor which has more than one operation
     *
     * @param operations an array of operations
     * @param operator the logic operator the divides the operations
     */
    LogicOperation(ArrayList<Operation> operations, LogicOperator operator) {
        this.operations = operations;
        this.operator = operator;
    }

    /**
     * The class constructor which takes only one operation
     * 
     * @param operation the operation
     */
    LogicOperation(Operation operation) {
        this.operation = operation;
    }

}
