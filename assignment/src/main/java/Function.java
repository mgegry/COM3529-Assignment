/**
 * This class holds the data structure for a function
 *
 * @author  Mircea Gelu Egry
 * @version 1.0
 */

import java.util.ArrayList;

public class Function {
    ArrayList<BranchPredicate> predicates;

    /**
     * The class constructor
     *
     * @param predicates an array of branch predicates that the tested method holds
     */
    Function(ArrayList<BranchPredicate> predicates) {
        this.predicates = predicates;
    }
}
