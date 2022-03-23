import java.util.ArrayList;

class BranchPredicate {
    ArrayList<LogicalOperation> predicates;
    LogicalAndOrNot mainLogicalOperator;

    LogicalOperation logicalOperation;

    BranchPredicate(ArrayList<LogicalOperation> predicate, LogicalAndOrNot mlo) {
        this.predicates = predicate;
        this.mainLogicalOperator = mlo;
    }

    BranchPredicate(LogicalOperation logicalOperation) {
        this.logicalOperation = logicalOperation;
    }

    @Override
    public String toString() {

        if (predicates == null) {
            return logicalOperation.toString();
        }

        String stringOp = "";
        String returnString = "";

        switch (mainLogicalOperator) {
            case OR:
                stringOp = "||";
                break;
            case AND:
                stringOp = "&&";
                break;
            case NOT:
                stringOp = "!";
                break;
        }

       for (int i = 0; i < predicates.size(); i++) {
           if (i != predicates.size() - 1) {
               returnString = returnString + predicates.get(i).toString() + " " + stringOp + " ";
           } else {
               returnString = returnString + predicates.get(i).toString();
           }
       }

        return returnString;
    }
}

public class MainAutoTester {

    public static void main(String[] args) {

        // TEST DATA - Testing a single predicate
        // Predicate tested: (x <= y) || (x <= z)
        // x <= y -- firstOperation
        // x <= z -- secondOperation

        Operation firstOperation = new Operation("x", "y", Condition.LTOET);
        Operation secondOperation = new Operation("x", "z", Condition.LTOET);

        Operation thirdOperation = new Operation("x", "y", Condition.LTOET);
        Operation fourthOperation = new Operation("x", "z", Condition.LTOET);

        LogicalAndOrNot op = LogicalAndOrNot.OR;

        LogicalOperation logicalOperation = new LogicalOperation(firstOperation, secondOperation, op);
        LogicalOperation logicalOperation2 = new LogicalOperation(firstOperation, secondOperation, op);
        LogicalOperation logicalOperation3 = new LogicalOperation(firstOperation, secondOperation, op);

        ArrayList<LogicalOperation> array = new ArrayList<LogicalOperation>();
        array.add(logicalOperation);
        array.add(logicalOperation2);

        BranchPredicate branchPredicate = new BranchPredicate(array, LogicalAndOrNot.AND);

        System.out.println(branchPredicate);


    }

}
