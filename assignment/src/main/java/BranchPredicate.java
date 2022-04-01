/**
 * This class holds the data structure for a branch predicate
 *
 * @author  Mircea Gelu Egry
 * @version 1.0
 */

import java.util.ArrayList;

public class BranchPredicate {
    ArrayList<LogicOperation> logicOperations;
    LogicOperator mainOperator;

    LogicOperation logicOperation;

    /**
     * The class constructor for multiple logic operations
     *
     * @param logicOperations an array of logic operations
     * @param operator the operator that divides logic operations
     */
    BranchPredicate(ArrayList<LogicOperation> logicOperations, LogicOperator operator) {
        this.logicOperations = logicOperations;
        this.mainOperator = operator;
    }

    /**
     * The class constructor which for a single logic operation
     *
     * @param logicOperation the logic operation
     */
    BranchPredicate(LogicOperation logicOperation) {
        this.logicOperation = logicOperation;
    }
}
