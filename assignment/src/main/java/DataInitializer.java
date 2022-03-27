import java.util.ArrayList;

public class DataInitializer {

    public Function initializeData1() {
        Operation op1 = new Operation("a", "b", Condition.LTOET);
        Operation op2 = new Operation("a", "c", Condition.LTOET);
        Operation op3 = new Operation("a", "b", Condition.GTOET);
        Operation op4 = new Operation("a", "c", Condition.GTOET);
        Operation op5 = new Operation("a", "0", Condition.NOTEQUAL);

        ArrayList<Operation> arrayOp1 = new ArrayList<>();
        ArrayList<Operation> arrayOp2 = new ArrayList<>();

        arrayOp1.add(op1);
        arrayOp1.add(op2);
        arrayOp2.add(op3);
        arrayOp2.add(op4);
        //arrayOp2.add(m.op5);

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

}
