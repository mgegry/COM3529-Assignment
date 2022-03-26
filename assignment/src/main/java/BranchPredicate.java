import java.util.ArrayList;

public class BranchPredicate {
    ArrayList<LogicOperation> logicOperations;
    LogicOperator mainOperator;

    BranchPredicate(ArrayList<LogicOperation> logicOperations, LogicOperator operator) {
        this.logicOperations = logicOperations;
        this.mainOperator = operator;
    }
}
