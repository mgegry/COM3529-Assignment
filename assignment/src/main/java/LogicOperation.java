import java.util.ArrayList;

enum LogicOperator {
    AND,
    OR
}

public class LogicOperation {
    ArrayList<Operation> operations;
    LogicOperator operator;
    boolean  state;

    LogicOperation(ArrayList<Operation> operations, LogicOperator operator) {
        this.operations = operations;
        this.operator = operator;
    }
}
