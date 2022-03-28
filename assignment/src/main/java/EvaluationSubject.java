import java.util.ArrayList;
import java.util.Random;

public class EvaluationSubject {

    static final int ITERATIONS = 0;

    public static boolean checkListUnique(ArrayList<ArrayList<Boolean>> checkList, ArrayList<Boolean> list) {
        for (ArrayList<Boolean> l : checkList) {
            if (list.equals(l)) {
                return false;
            }
        }
        return true;
    }

//    [[false, false, true, true], [false, true, true, false], [true, true, false, false], [true, false, false, true]]
    // [[false, true, false, false], [true, false, false, false], [false, false, false, true], [false, false, true, false]]
    public static boolean TestBranch1(int a, int b, int c, ArrayList<ArrayList<Boolean>> result) {
        ArrayList<Boolean> r = new ArrayList<>();
        r.add(a <= b);
        r.add(a <= c);
        r.add(a >= b);
        r.add(a >= c);

        if (checkListUnique(result, r)) {
            result.add(r);
        }

        return (a <= b && a <= c) || (a >= b && a >= c);
    }

    public static void main(String[] args) {

        ArrayList<ArrayList<Boolean>> results = new ArrayList<>();
        Random rand = new Random();

        TestBranch1(5, 1, 8, results);

        for (int i = 0; i <= ITERATIONS; i++) {
            int a = rand.nextInt();
            int b = rand.nextInt();
            int c = rand.nextInt();

            TestBranch1(a, b, c, results);

            System.out.println(results);
        }

    }

}
