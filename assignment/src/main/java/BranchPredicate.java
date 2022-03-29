import java.util.ArrayList;

public class BranchPredicate {
    ArrayList<LogicOperation> logicOperations;
    LogicOperator mainOperator;

    LogicOperation logicOperation;

    BranchPredicate(ArrayList<LogicOperation> logicOperations, LogicOperator operator) {
        this.logicOperations = logicOperations;
        this.mainOperator = operator;
    }

    BranchPredicate(LogicOperation logicOperation) {
        this.logicOperation = logicOperation;
    }
}
