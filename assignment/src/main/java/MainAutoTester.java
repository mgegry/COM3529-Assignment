import java.util.ArrayList;
import java.util.Scanner;

// (a <= b && a <= c) || (a >= b && a >= c)
// (a <= b && a <= c) || (a >= b && a >= c && a != 0)

public class MainAutoTester {

    public boolean calculateState(BranchPredicate bp) {
        boolean result;

        if (bp.logicOperations != null) {
            result = bp.mainOperator != LogicOperator.OR;
            for (LogicOperation logicOperation : bp.logicOperations) {
                if (logicOperation.operations != null) {
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
                } else {
                    if (bp.mainOperator == LogicOperator.OR) {
                        result = result || logicOperation.operation.state;
                    } else if (bp.mainOperator == LogicOperator.AND) {
                        result = result && logicOperation.operation.state;
                    }
                }
            }
        } else {
            if (bp.logicOperation.operations != null) {
                result = bp.logicOperation.operations.get(0).state;

                for(Operation operation : bp.logicOperation.operations) {
                    if (bp.logicOperation.operator == LogicOperator.OR) {
                        result = result || operation.state;
                    } else if (bp.logicOperation.operator == LogicOperator.AND) {
                        result = result && operation.state;
                    }
                }
            } else {
                result = bp.logicOperation.operation.state;
            }
        }

        return result;
    }

    public void setStartingStateOperations(BranchPredicate bp) {
        boolean state = bp.mainOperator != LogicOperator.OR;

        if (bp.logicOperations != null) {
            for (LogicOperation logicOperation : bp.logicOperations) {
                if (logicOperation.operations != null) {
                    for (Operation operation : logicOperation.operations) {
                        operation.state = state;
                    }
                } else {
                    logicOperation.operation.state = state;
                }
            }
        } else {
            if (bp.logicOperation.operations != null) {
                for (Operation operation : bp.logicOperation.operations) {
                    operation.state = state;
                }
            } else {
                bp.logicOperation.operation.state = state;
            }
        }
    }

    public ArrayList<Boolean> getOperationStates(BranchPredicate bp) {
        ArrayList<Boolean> returnList = new ArrayList<>();
        if (bp.logicOperations != null) {
            for (LogicOperation logicOperation : bp.logicOperations) {

                if (logicOperation.operations != null) {
                    for (Operation operation : logicOperation.operations) {
                        returnList.add(operation.state);
                    }
                } else {
                    returnList.add(logicOperation.operation.state);
                }
            }
        } else {
            if (bp.logicOperation.operations != null) {
                for (Operation operation : bp.logicOperation.operations) {
                    returnList.add(operation.state);
                }
            } else {
                returnList.add(bp.logicOperation.operation.state);
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

    public ArrayList<ArrayList<Boolean>> getRestrictedMCDCTestRequirements(BranchPredicate bp) {
        ArrayList<ArrayList<Boolean>> returnList = new ArrayList<>();

        if (bp.logicOperations != null) {
            setStartingStateOperations(bp);

            for (LogicOperation logicOperation : bp.logicOperations) {

                if (logicOperation.operations != null) {
                    boolean s = logicOperation.operator != LogicOperator.OR;

                    for (Operation operation : logicOperation.operations) {
                        setStartingStateOperations(bp);

                        for (Operation op : logicOperation.operations) {
                            if(!operation.equals(op)) {
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
                } else {

                    boolean ss =  bp.mainOperator != LogicOperator.OR;

                    logicOperation.operation.state = ss;
                    boolean result1 = calculateState(bp);

                    logicOperation.operation.state = !ss;
                    boolean result2 = calculateState(bp);

                    if (result1 != result2) {
                        logicOperation.operation.state = ss;
                        ArrayList<Boolean> requirements = getOperationStates(bp);
                        if (checkTestRequirementUnique(returnList, requirements)) {
                            returnList.add(requirements);
                        }

                        logicOperation.operation.state = !ss;
                        requirements = getOperationStates(bp);
                        if (checkTestRequirementUnique(returnList, requirements)) {
                            returnList.add(requirements);
                        }
                    }


                }
            }
        } else {
            if (bp.logicOperation.operations != null) {
                boolean s = bp.logicOperation.operator != LogicOperator.OR;

                for (Operation operation : bp.logicOperation.operations) {
                    setStartingStateOperations(bp);

                    for (Operation op : bp.logicOperation.operations) {
                        if(!operation.equals(op)) {
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
            } else {
                ArrayList<Boolean> requirements = new ArrayList<>();
                requirements.add(true);
                returnList.add(requirements);
                ArrayList<Boolean> requirements2 = new ArrayList<>();
                requirements2.add(false);
                returnList.add(requirements2);
            }
        }

        return returnList;
    }

    public static void main(String[] args) {
        MainAutoTester m = new MainAutoTester();
        Scanner dataScanner = new Scanner(System.in);

        DataInitializer dataInitializer = new DataInitializer();

        int programState = 0;
        Function testMethod = null;

        while (programState != -1) {
            System.out.println("Available to test methods: ");
            System.out.println("    1. Test method one");
            System.out.println("    2. Triangle method");
            System.out.print("Select method to test: ");

            int selection = dataScanner.nextInt();

            switch (selection) {
                case 1:
                    testMethod = dataInitializer.initializeData1();
                    break;
                case 2:
                    testMethod = dataInitializer.initializeData2();
                    break;
                case 3:
                    testMethod = dataInitializer.initializeData3();
                    break;
            }

            System.out.println("Generating test requirements for each branch predicate...");

            if (testMethod != null) {
                for (BranchPredicate bp : testMethod.predicates) {
                    System.out.println(m.getRestrictedMCDCTestRequirements(bp));
                }
            }

            System.out.println("Quit or continue? (type Q for quit or anything to continue)");
            String qc = dataScanner.next();

            if (qc.equalsIgnoreCase("q")) {
                programState = -1;
            }
        }

    }

}
