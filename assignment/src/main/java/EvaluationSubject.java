import java.util.ArrayList;
import java.util.Random;

/**
 * This class holds a result type passed as parameter to the EvaluationSubject methods
 *
 * @author  Mircea Gelu Egry
 * @version 1.0
 */
class EvaluationResult {
    ArrayList<ArrayList<Boolean>> conditionResults;
    ArrayList<ArrayList<Integer>> inputResults;

    /**
     * Constructor of the class
     */
    EvaluationResult() {
        this.conditionResults = new ArrayList<>();
        this.inputResults = new ArrayList<>();
    }
}

/**
 * This class creates new evaluation subjects for the tested methods
 * so that operation states can be watched
 *
 * @author  Mircea Gelu Egry
 * @version 1.0
 */
public class EvaluationSubject {

    /**
     * This method checks if a list of booleans is unique in an array of arrays of booleans
     *
     * @param checkList list in which to check
     * @param list list to check that is unique
     * @return boolean if the list is unique or not
     */
    public boolean checkListUnique(ArrayList<ArrayList<Boolean>> checkList, ArrayList<Boolean> list) {
        for (ArrayList<Boolean> l : checkList) {
            if (list.equals(l)) {
                return false;
            }
        }
        return true;
    }

    /**
     * This is an evaluation subject for the first method from TestMethods class
     *
     * @param a the first number
     * @param b the second number
     * @param result result containing the condition results and the input numbers
     * @return unused
     */
    public boolean checkNumberBigger(int a, int b, EvaluationResult result) {

        ArrayList<Integer> inputs = new ArrayList<>();
        inputs.add(a);
        inputs.add(b);

        ArrayList<Boolean> r = new ArrayList<>();
        r.add(a <= b);

        if (checkListUnique(result.conditionResults, r)) {
            result.conditionResults.add(r);
            result.inputResults.add(inputs);
        }

        return (a <= b);
    }

    /**
     * This is an evaluation subject for the second method from TestMethods class
     *
     * @param a the first number
     * @param b the second number
     * @param c the third number
     * @param result result containing the condition results and the input numbers
     * @return unused
     */
    public boolean checkNumberInRange(int a, int b, int c, EvaluationResult result) {

        ArrayList<Integer> inputs = new ArrayList<>();
        inputs.add(a);
        inputs.add(b);
        inputs.add(c);

        ArrayList<Boolean> r = new ArrayList<>();

        r.add(a >= b);
        r.add(a <= c);

        if (checkListUnique(result.conditionResults, r)) {
            result.conditionResults.add(r);
            result.inputResults.add(inputs);
        }

        return a >= b && a <= c;
    }

    /**
     * This is an evaluation subject for the third method from TestMethods class
     *
     * @param a the first number
     * @param b the second number
     * @param c the third number
     * @param d the fourth number
     * @param result result containing the condition results and the input numbers
     * @return unused
     */
    public boolean testBranchPredicate(int a, int b, int c, int d, EvaluationResult result) {

        ArrayList<Integer> inputs = new ArrayList<>();
        inputs.add(a);
        inputs.add(b);
        inputs.add(c);
        inputs.add(d);

        ArrayList<Boolean> r = new ArrayList<>();
        r.add(a <= b);
        r.add(a >= c);
        r.add(a <= d);
        r.add(a != 0);

        if (checkListUnique(result.conditionResults, r)) {
            result.conditionResults.add(r);
            result.inputResults.add(inputs);
        }

        return (a <= b && a >= c) || (a <= d && a != 0);
    }

    /**
     * This method generates a random int
     *
     * @param r instance of Random
     * @param min the minimum random value to generate
     * @param max the maximum random value to generate
     * @return the random integer
     */
    public int randomInt(Random r, int min, int max) {
        if (min == Integer.MIN_VALUE && max == Integer.MAX_VALUE) {
            return r.nextInt();
        } else {
            return r.nextInt((max - min + 1)) + min;
        }
    }

    /**
     * This method generates random inputs for the specified method
     *
     * @param method method for which to generate random inputs
     * @return an EvaluationResult object containing the condition results and input results
     */
    public EvaluationResult generateTestInputValues(int method) {
        EvaluationResult evaluationResult = new EvaluationResult();

        Random r = new Random();
        int iterations = 10000;
        int min = Integer.MIN_VALUE;
        int max = Integer.MAX_VALUE;

        evaluationResult.conditionResults.clear();
        evaluationResult.inputResults.clear();

        for (int x = 0; x < 3; x++) {

            switch (x) {
                case 1:
                    min = -10000;
                    max = 10000;
                    break;
                case 2:
                    min = -100;
                    max = 100;
                    break;
                default:
                    break;
            }

            for (int i = 0; i < iterations; i++) {
                switch (method) {
                    case 1:
                        int a = randomInt(r, min, max);
                        int b = randomInt(r, min, max);
                        checkNumberBigger(a, b, evaluationResult);
                        break;

                    case 2:
                        a = randomInt(r, min, max);
                        b = randomInt(r, min, max);
                        int c = randomInt(r, min, max);
                        checkNumberInRange(a, b, c, evaluationResult);
                        break;

                    case 3:
                        a = randomInt(r, min, max);
                        b = randomInt(r, min, max);
                        c = randomInt(r, min, max);
                        int d = randomInt(r, min, max);
                        testBranchPredicate(a, b, c, d, evaluationResult);
                        break;
                }
            }
        }
        return  evaluationResult;
    }

}
