import java.util.ArrayList;

// (a <= b && a <= c) || (a >= b && a >= c)
// (a <= b && a <= c) || (a >= b && a >= c && a != 0)

public class MainAutoTester {

    Operation op1 = new Operation("a", "b", Condition.LTOET);
    Operation op2 = new Operation("a", "c", Condition.LTOET);
    Operation op3 = new Operation("a", "b", Condition.GTOET);
    Operation op4 = new Operation("a", "c", Condition.GTOET);
    Operation op5 = new Operation("a", "0", Condition.NOTEQUAL);

    public boolean calculateState(BranchPredicate bp) {
        boolean result;

        result = bp.mainOperator != LogicOperator.OR;

        for (LogicOperation logicOperation : bp.logicOperations) {
            boolean x = logicOperation.operations.get(0).state;

            for (Operation operation : logicOperation.operations) {
                if (logicOperation.operator == LogicOperator.OR) {
                    x = x || operation.state;
                } else if (logicOperation.operator == LogicOperator.AND) {
                    x = x && operation.state;
                }
            }


            if (bp.mainOperator == LogicOperator.OR) {
                result = result || x;
            } else if (bp.mainOperator == LogicOperator.AND) {
                result = result && x;
            }
        }

        return result;
    }

    public static void main(String[] args) {
        MainAutoTester m = new MainAutoTester();

        ArrayList<Operation> arrayOp1 = new ArrayList<>();
        ArrayList<Operation> arrayOp2 = new ArrayList<>();

        m.op1.state = false;
        m.op2.state = true;
        m.op3.state = true;
        m.op4.state = true;
        m.op5.state = true;

        arrayOp1.add(m.op1);
        arrayOp1.add(m.op2);
        arrayOp2.add(m.op3);
        arrayOp2.add(m.op4);
        arrayOp2.add(m.op5);

        LogicOperation logicOperation1 = new LogicOperation(arrayOp1, LogicOperator.AND);
        LogicOperation logicOperation2 = new LogicOperation(arrayOp2, LogicOperator.AND);

        ArrayList<LogicOperation> logicOperations = new ArrayList<>();
        logicOperations.add(logicOperation1);
        logicOperations.add(logicOperation2);

        BranchPredicate bp = new BranchPredicate(logicOperations, LogicOperator.OR);

        System.out.println(m.calculateState(bp));
    }

}
