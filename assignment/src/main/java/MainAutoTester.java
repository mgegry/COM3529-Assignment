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

    public void setStartingStateOperations(BranchPredicate bp) {
        boolean state = bp.mainOperator != LogicOperator.OR;

        for (LogicOperation logicOperation : bp.logicOperations) {
            for (Operation operation : logicOperation.operations)  {
                operation.state = state;
            }
        }
    }

    public void printOperationsStatesBranch(BranchPredicate bp) {
        for (LogicOperation logicOperation : bp.logicOperations) {
            for (Operation operation : logicOperation.operations)  {
                System.out.print(operation.state + " ");
            }
        }
        System.out.println();
    }

    public ArrayList<Boolean> getOperationStates(BranchPredicate bp) {
        ArrayList<Boolean> returnList = new ArrayList<Boolean>();
        for (LogicOperation logicOperation : bp.logicOperations) {
            for (Operation operation : logicOperation.operations)  {
                returnList.add(operation.state);
            }
        }

        return returnList;
    }

    public boolean checkTestRequirementUnique(ArrayList<ArrayList<Boolean>> checkList, ArrayList<Boolean> list) {
        for (ArrayList<Boolean> l : checkList) {
            if (list.equals(l)) {
                return false;
            }
        }
        return true;
    }

    public ArrayList<ArrayList<Boolean>> getMCDCTestRequirements(BranchPredicate bp) {

        ArrayList<ArrayList<Boolean>> returnList = new ArrayList<>();

        for (LogicOperation logicOperation : bp.logicOperations) {

            boolean s = logicOperation.operator != LogicOperator.OR;

            for (Operation operation : logicOperation.operations) {
                setStartingStateOperations(bp);

                for (Operation op : logicOperation.operations) {
                    if (!operation.equals(op)) {
                        op.state = s;
                    }
                }

                operation.state = !s;
                boolean result1 = calculateState(bp);

                operation.state = !operation.state;
                boolean result2 = calculateState(bp);

                if (result1 != result2) {
                    operation.state = !s;
                    ArrayList<Boolean> requirements = getOperationStates(bp);
                    if (checkTestRequirementUnique(returnList, requirements)) {
                        returnList.add(requirements);
                    }
                    operation.state = s;
                    requirements = getOperationStates(bp);
                    if (checkTestRequirementUnique(returnList, requirements)) {
                        returnList.add(requirements);
                    }
                }
            }
        }
        return returnList;
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
        //arrayOp2.add(m.op5);

        LogicOperation logicOperation1 = new LogicOperation(arrayOp1, LogicOperator.OR);
        LogicOperation logicOperation2 = new LogicOperation(arrayOp2, LogicOperator.OR);

        ArrayList<LogicOperation> logicOperations = new ArrayList<>();
        logicOperations.add(logicOperation1);
        logicOperations.add(logicOperation2);

        BranchPredicate bp = new BranchPredicate(logicOperations, LogicOperator.AND);

        //System.out.println(m.calculateState(bp));
        System.out.println(m.getMCDCTestRequirements(bp));
    }

}
