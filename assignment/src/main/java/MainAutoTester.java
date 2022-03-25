import java.util.ArrayList;

enum LogicOperator {
    AND,
    OR
}

class OperationIndex {
    int operationId;
    LogicOperator operator;

    OperationIndex(int operationId, LogicOperator operator) {
        this.operationId = operationId;
        this.operator = operator;
    }
}

class Decision {
    Decision leftSide;
    Decision rightSide;

    Operation leftSideO;
    Operation rightSideO;

}

/// ( x <= y || x >= z ) && ( x <= 10 || (x >= 50 && x != -1000))

public class MainAutoTester {
    ArrayList<Operation> operations = new ArrayList<>();
    ArrayList<OperationIndex> operationIndices = new ArrayList<>();


    public void addOperations() {
        Operation op1 = new Operation("x", "y", Condition.LTOET);
        Operation op2 = new Operation("x", "z", Condition.GTOET);
        Operation op3 = new Operation("x", "10", Condition.LTOET);

        op1.state = true;
        op2.state = false;
        op3.state = false;

        OperationIndex i1 = new OperationIndex(1, LogicOperator.OR);
        OperationIndex i2 = new OperationIndex(2, LogicOperator.AND);

        operations.add(op1);
        operations.add(op2);
        operations.add(op3);

        operationIndices.add(i1);
        operationIndices.add(i2);
    }

    void calculateLogicalOperation(ArrayList<Operation> operations, ArrayList<OperationIndex> operationIndices) {
        boolean result = operations.get(0).state;

        for(OperationIndex op : operationIndices) {
            switch (op.operator) {
                case OR:
                    result = result || operations.get(op.operationId).state;
                    break;
                case AND:
                    result = result && operations.get(op.operationId).state;
                    break;
            }
        }

        System.out.println(result);
    }

    public static void main(String[] args) {
        MainAutoTester m = new MainAutoTester();
        m.addOperations();
        m.calculateLogicalOperation(m.operations, m.operationIndices);
    }

}
//
//class BranchPredicate {
//    ArrayList<LogicalOperation> predicates;
//    LogicalAndOrNot mainLogicalOperator;
//
//    LogicalOperation logicalOperation;
//
//    BranchPredicate(ArrayList<LogicalOperation> predicate, LogicalAndOrNot mlo) {
//        this.predicates = predicate;
//        this.mainLogicalOperator = mlo;
//    }
//
//    BranchPredicate(LogicalOperation logicalOperation) {
//        this.logicalOperation = logicalOperation;
//    }
//
//    @Override
//    public String toString() {
//
//        if (predicates == null) {
//            return logicalOperation.toString();
//        }
//
//        String stringOp = "";
//        String returnString = "";
//
//        switch (mainLogicalOperator) {
//            case OR:
//                stringOp = "||";
//                break;
//            case AND:
//                stringOp = "&&";
//                break;
//            case NOT:
//                stringOp = "!";
//                break;
//        }
//
//        for (int i = 0; i < predicates.size(); i++) {
//            if (i != predicates.size() - 1) {
//                returnString = returnString + predicates.get(i).toString() + " " + stringOp + " ";
//            } else {
//                returnString = returnString + predicates.get(i).toString();
//            }
//        }
//
//        return returnString;
//    }
//}
//
//public class MainAutoTester {
//
//    public static void main(String[] args) {
//
//        // TEST DATA - Testing a single predicate
//        // Predicate tested: (x <= y) || (x <= z)
//        // x <= y -- firstOperation
//        // x <= z -- secondOperation
//
//        Operation firstOperation = new Operation("x", "y", Condition.LTOET);
//        Operation secondOperation = new Operation("x", "z", Condition.LTOET);
//
//        Operation thirdOperation = new Operation("x", "y", Condition.LTOET);
//        Operation fourthOperation = new Operation("x", "z", Condition.LTOET);
//
//        LogicalAndOrNot op = LogicalAndOrNot.OR;
//
//        LogicalOperation logicalOperation = new LogicalOperation(firstOperation, secondOperation, op);
//        LogicalOperation logicalOperation2 = new LogicalOperation(firstOperation, secondOperation, op);
//        LogicalOperation logicalOperation3 = new LogicalOperation(firstOperation, secondOperation, op);
//
//        ArrayList<LogicalOperation> array = new ArrayList<LogicalOperation>();
//        array.add(logicalOperation);
//        array.add(logicalOperation2);
//
//        BranchPredicate branchPredicate = new BranchPredicate(array, LogicalAndOrNot.AND);
//
//        System.out.println(branchPredicate);
//
//
//    }
//
//}
