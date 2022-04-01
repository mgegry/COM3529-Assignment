/**
 * This class responsibility is to initialize the functions
 *
 * @author  Mircea Gelu Egry
 * @version 1.0
 */

import java.util.ArrayList;

public class DataInitializer {


    /**
     * Initialize the data for the first method from TestMethods class
     * @return the initialized function
     */
    public Function initializeData1() {
        Operation op1 = new Operation("a", "b", Condition.LTOET);
        LogicOperation logicOperation = new LogicOperation(op1);
        BranchPredicate branchPredicate = new BranchPredicate(logicOperation);

        ArrayList<BranchPredicate> predicates = new ArrayList<>();
        predicates.add(branchPredicate);

        return new Function(predicates);
    }

    /**
     * Initialize the data for the second method from TestMethods class
     * @return the initialized function
     */
    public Function initializeData2() {
        Operation op1 = new Operation("a", "b", Condition.LTOET);
        Operation op2 = new Operation("a", "c", Condition.GTOET);

        ArrayList<Operation> operations = new ArrayList<>();
        operations.add(op1);
        operations.add(op2);

        LogicOperation logicOperation = new LogicOperation(operations, LogicOperator.AND);

        BranchPredicate bp = new BranchPredicate(logicOperation);

        ArrayList<BranchPredicate> predicates = new ArrayList<>();
        predicates.add(bp);

        return new Function(predicates);
    }

    /**
     * Initialize the data for the third method from TestMethods class
     * @return the initialized function
     */
    public Function initializeData3() {
        Operation op1 = new Operation("a", "b", Condition.LTOET);
        Operation op2 = new Operation("a", "c", Condition.GTOET);
        Operation op3 = new Operation("a", "d", Condition.LTOET);
        Operation op4 = new Operation("a", "0", Condition.NOTEQUAL);

        ArrayList<Operation> arrayOp1 = new ArrayList<>();
        ArrayList<Operation> arrayOp2 = new ArrayList<>();

        arrayOp1.add(op1);
        arrayOp1.add(op2);
        arrayOp2.add(op3);
        arrayOp2.add(op4);

        LogicOperation logicOperation1 = new LogicOperation(arrayOp1, LogicOperator.AND);
        LogicOperation logicOperation2 = new LogicOperation(arrayOp2, LogicOperator.AND);

        ArrayList<LogicOperation> logicOperations = new ArrayList<>();
        logicOperations.add(logicOperation1);
        logicOperations.add(logicOperation2);

        BranchPredicate bp = new BranchPredicate(logicOperations, LogicOperator.OR);

        ArrayList<BranchPredicate> predicates = new ArrayList<>();
        predicates.add(bp);

        return new Function(predicates);
    }

}
