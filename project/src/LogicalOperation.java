enum LogicalAndOrNot {
    AND,
    OR,
    NOT
}

class LogicalOperation {

    Operation leftSide;
    Operation rightSide;
    LogicalAndOrNot op;

    LogicalOperation(Operation leftSide, Operation rightSide, LogicalAndOrNot op) {

        this.leftSide = leftSide;
        this.rightSide = rightSide;
        this.op = op;
    }

    @Override
    public String toString() {
        String stringOp = "";
        switch (op) {
            case OR:
                stringOp = "||";
                break;
            case AND:
                stringOp = "&&";
            case NOT:
                stringOp = "!";
        }

        return leftSide.toString() + " " + stringOp + " " + rightSide.toString();
    }
}