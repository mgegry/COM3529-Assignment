enum Condition {
    EQUAL,
    NOTEQUAL,
    LTOET,
    GTOET,
    GRATERTHAN,
    LESSTHAN,
}

/*
    array operation[]
    array operationOrder[] - operation order

    operationOrder {
        index operationTerm
        logicalOperator
    }

    (a || b) && c
 */

class Operation {
    String leftSide;
    String rightSide;
    Condition cond;
    boolean state = false;

    Operation(String leftSide, String rightSide, Condition cond) {
        this.leftSide = leftSide;
        this.rightSide = rightSide;
        this.cond = cond;
    }

    @Override
    public String toString() {
        String opString = "";

        switch (cond) {
            case LTOET:
                opString = "<=";
                break;
            case GTOET:
                opString = ">=";
                break;
            case GRATERTHAN:
                opString = ">";
                break;
            case LESSTHAN:
                opString = "<";
                break;
            case EQUAL:
                opString = "==";
                break;
            case NOTEQUAL:
                opString = "!=";
                break;
        }

        return leftSide + " " + opString + " " + rightSide;
    }
}