import java.util.ArrayList;

public class DataInitializer {

    public Function initializeData1() {
        Operation op1 = new Operation("a", "b", Condition.LTOET);
        Operation op2 = new Operation("a", "c", Condition.LTOET);
        Operation op3 = new Operation("a", "b", Condition.GTOET);
        Operation op4 = new Operation("a", "c", Condition.GTOET);

        ArrayList<Operation> arrayOp1 = new ArrayList<>();
        ArrayList<Operation> arrayOp2 = new ArrayList<>();

        arrayOp1.add(op1);
        arrayOp1.add(op2);
        arrayOp2.add(op3);
        arrayOp2.add(op4);

        LogicOperation logicOperation1 = new LogicOperation(arrayOp1, LogicOperator.OR);
        LogicOperation logicOperation2 = new LogicOperation(arrayOp2, LogicOperator.OR);

        ArrayList<LogicOperation> logicOperations = new ArrayList<>();
        logicOperations.add(logicOperation1);
        logicOperations.add(logicOperation2);

        BranchPredicate bp = new BranchPredicate(logicOperations, LogicOperator.AND);

        ArrayList<BranchPredicate> predicates = new ArrayList<>();
        predicates.add(bp);

        return new Function(predicates);
    }

    public Function initializeData2() {
        Operation op1 = new Operation("a", "b", Condition.LTOET);
        LogicOperation logicOperation = new LogicOperation(op1);
        BranchPredicate branchPredicate = new BranchPredicate(logicOperation);

        ArrayList<BranchPredicate> predicates = new ArrayList<>();
        predicates.add(branchPredicate);

        return new Function(predicates);
    }

    public Function initializeData3() {
        Operation op1 = new Operation("a", "b", Condition.LTOET);
        Operation op2 = new Operation("a", "b", Condition.LTOET);
        Operation op3 = new Operation("a", "b", Condition.LTOET);

        LogicOperation logicOperation1 = new LogicOperation(op1);

        ArrayList<Operation> operations = new ArrayList<>();
        operations.add(op2);
        operations.add(op3);

        LogicOperation logicOperation2 = new LogicOperation(operations, LogicOperator.AND);

        ArrayList<LogicOperation> logicOperations = new ArrayList<>();
        logicOperations.add(logicOperation1);
        logicOperations.add(logicOperation2);

        BranchPredicate bp = new BranchPredicate(logicOperations, LogicOperator.OR);

        ArrayList<BranchPredicate> predicates = new ArrayList<>();
        predicates.add(bp);

        return new Function(predicates);
    }

}
