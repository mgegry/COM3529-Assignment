public class TestMethods {

    /// COMPLICATED PREDICATE EXAMPLE

    public static boolean TestBranchPredicate1(int a, int b, int c) {
        return (a <= b && a <= c) || (a >= b && a >= c);
    }

    public static boolean TestBranchPredicate2(int a, int b, int c)  {
        return true;
    }


    /// TRIANGLE EXAMPLE

    public enum Type {
        INVALID,
        SCALENE,
        EQUILATERAL,
        ISOSCELES;
    }

    public static Type classify(int side1, int side2, int side3) {
        Type type;

        if (side1 > side2) {
            int temp = side1;
            side1 = side2;
            side2 = temp;
        }
        if (side1 > side3) {
            int temp = side1;
            side1 = side3;
            side3 = temp;
        }
        if (side2 > side3) {
            int temp = side2;
            side2 = side3;
            side3 = temp;
        }

        if (side1 + side2 <= side3) {
            type = Type.INVALID;
        } else {
            type = Type.SCALENE;
            if (side1 == side2) {
                if (side2 == side3) {
                    type = Type.EQUILATERAL;
                }
            } else {
                if (side2 == side3) {
                    type = Type.ISOSCELES;
                }
            }
        }
        return type;
    }
}
