/**
 * This class holds generates the requirements for a given branch predicate
 *
 * @author  Mircea Gelu Egry
 * @version 1.0
 */

import java.util.ArrayList;

public class RequirementGenerator {

    /**
     * Calculate the branch predicate result based on the given states for the operations
     *
     * @param bp the branch predicate
     * @return boolean containing the result of the branch predicate
     */
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

    /**
     * Set the starting states for the operations for the given branch predicate
     * @param bp the branch predicate
     */
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

    /**
     * Get all the operation states as an array of booleans for the given branch predicate
     *
     * @param bp the branch predicate
     * @return an array list of booleans containing the states of the operations
     */
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

    /**
     * Checks that the given test requirements are unique in a list of test requirements
     *
     * @param checkList the list of test requirements
     * @param list the list to be checked if unique
     * @return boolean stating if list unique or not
     */
    public boolean checkTestRequirementUnique(ArrayList<ArrayList<Boolean>> checkList, ArrayList<Boolean> list) {
        for (ArrayList<Boolean> l : checkList) {
            if (list.equals(l)) {
                return false;
            }
        }
        return true;
    }

    /**
     * This method returns the restricted mcdc test requirements for the given branch predicate
     *
     * @param bp the branch predicate
     * @return an array list of array lists of booleans containing the mcdc test requirements
     */
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
                    setStartingStateOperations(bp);

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
}
