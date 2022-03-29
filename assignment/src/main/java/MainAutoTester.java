import java.util.Scanner;

// (a <= b && a <= c) || (a >= b && a >= c)
// (a <= b && a <= c) || (a >= b && a >= c && a != 0)

public class MainAutoTester {

    public static void main(String[] args) {
        RequirementGenerator requirementGenerator = new RequirementGenerator();
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
                    System.out.println(requirementGenerator.getRestrictedMCDCTestRequirements(bp));
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
