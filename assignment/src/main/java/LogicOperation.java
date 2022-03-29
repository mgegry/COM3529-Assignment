import java.util.ArrayList;

enum LogicOperator {
    AND,
    OR
}

// (a && b && c) || (d && c && b)
// a

public class LogicOperation {
    ArrayList<Operation> operations;

    LogicOperator operator;

    Operation operation;

    LogicOperation(ArrayList<Operation> operations, LogicOperator operator) {
        this.operations = operations;
        this.operator = operator;
    }

    LogicOperation(Operation operation) {
        this.operation = operation;
    }

}
