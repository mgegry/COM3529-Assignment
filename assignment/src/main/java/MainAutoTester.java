/**
 * This is the start class for the Software testing and Analysis
 *
 * @author  Mircea Gelu Egry
 * @version 1.0
 */

import java.nio.file.*;
import java.util.ArrayList;
import java.util.Scanner;
import java.io.File;
import java.nio.file.Files;
import java.io.IOException;

public class MainAutoTester {

    /**
     * This is the main method
     * @param args Unused
     */
    public static void main(String[] args) {

        Scanner dataScanner = new Scanner(System.in);
        DataInitializer dataInitializer = new DataInitializer();
        RequirementGenerator requirementGenerator = new RequirementGenerator();
        EvaluationSubject evaluationSubject = new EvaluationSubject();

        ArrayList<ArrayList<Boolean>> requirements;

        int programState = 0;
        Function testMethod;
        EvaluationResult evaluationResult;

        System.out.println("Software Testing Assignment - Mircea Egry\n");

        while (programState != -1) {

            System.out.println("What is the file name you want to write your test to?");
            System.out.print("Filename (please provide file extension \".java\"): ");

            boolean newFile = false;
            String inputFile = "";

            // Create new file
            while (!newFile) {
                inputFile = dataScanner.next();
                inputFile = inputFile.substring(0, 1).toUpperCase() + inputFile.substring(1);
                String createPath = "./src/test/java/" + File.separator + inputFile;

                    try {
                        File myObj = new File(createPath);

                        if (myObj.createNewFile()) {
                            System.out.println("File created: " + myObj.getName());
                            newFile = true;
                        } else {
                            System.out.print("File already exists. Please create a new one!\nNew file name: ");
                        }
                    } catch (IOException e) {
                        System.out.println("An error occurred.");
                        e.printStackTrace();
                        break;
                    }
            }

            Path path = Paths.get("./src/test/java/" + inputFile);

            String className = inputFile.replace(".java", "");
            className = className.substring(0, 1).toUpperCase() + className.substring(1);

            String fileHeader = "public class " + className + " {";
            fileHeader = fileHeader + "\n\tpublic static void generatedTests() {";

            // Add header to the created file
            if (newFile) {
                try {
                    Files.writeString(path, fileHeader, StandardOpenOption.APPEND);
                } catch (IOException e) {
                    System.out.println("Unable to write to file" + e.getLocalizedMessage());
                    break;
                }
            }



            int ok = 0;

            /// Selecting method to test
            while (ok == 0) {

                System.out.println("Available to test methods: ");
                System.out.println("    1. Test a less than or equal to b");
                System.out.println("    2. Test number is in certain range");
                System.out.println("    3. Test predicate: (a <= b && a >= c) || (a <= d && a != 0)");
                System.out.print("Select method to test: ");

                String selection = dataScanner.next();

                switch (selection) {
                    case "1":
                        testMethod = dataInitializer.initializeData1();
                        for (BranchPredicate bp : testMethod.predicates) {
                            requirements = requirementGenerator.getRestrictedMCDCTestRequirements(bp);
                            evaluationResult = evaluationSubject.generateTestInputValues(Integer.parseInt(selection));

                            int i = 0;
                            for(ArrayList<Boolean> result : evaluationResult.conditionResults) {
                                for(ArrayList<Boolean> requirement : requirements) {
                                    if (requirement.equals(result)) {
                                        StringBuilder line = new StringBuilder("\n\t\tTestMethods.checkIfNumberBigger(");
                                        int j = 0;
                                        for(int x : evaluationResult.inputResults.get(i)) {
                                            if(j != evaluationResult.inputResults.get(i).size() - 1) {
                                                line.append(x).append(",");
                                            } else {
                                                line.append(x);
                                            }
                                            j++;
                                        }
                                        line.append(");");
                                        try {
                                            Files.writeString(path, line.toString(), StandardOpenOption.APPEND);
                                        } catch (IOException e) {
                                            System.out.println("Unable to write to file" + e.getLocalizedMessage());
                                        }
                                    }
                                }
                                i++;
                            }
                        }
                        ok = 1;
                        break;

                    case "2":
                        testMethod = dataInitializer.initializeData2();
                        for (BranchPredicate bp : testMethod.predicates) {
                            requirements = requirementGenerator.getRestrictedMCDCTestRequirements(bp);
                            evaluationResult = evaluationSubject.generateTestInputValues(Integer.parseInt(selection));

                            int i = 0;
                            for(ArrayList<Boolean> result : evaluationResult.conditionResults) {
                                for(ArrayList<Boolean> requirement : requirements) {
                                    if (requirement.equals(result)) {
                                        StringBuilder line = new StringBuilder("\n\t\tTestMethods.checkNumberInRange(");
                                        int j = 0;
                                        for (int x : evaluationResult.inputResults.get(i)) {
                                            if (j != evaluationResult.inputResults.get(i).size() - 1) {
                                                line.append(x).append(",");
                                            } else {
                                                line.append(x);
                                            }
                                            j++;
                                        }
                                        line.append(");");
                                        try {
                                            Files.writeString(path, line.toString(), StandardOpenOption.APPEND);
                                        } catch (IOException e) {
                                            System.out.println("Unable to write to file" + e.getLocalizedMessage());
                                        }
                                    }
                                }
                                i++;
                            }
                        }

                        ok = 1;
                        break;

                    case "3":
                        testMethod = dataInitializer.initializeData3();
                        for (BranchPredicate bp : testMethod.predicates) {
                            requirements = requirementGenerator.getRestrictedMCDCTestRequirements(bp);
                            evaluationResult = evaluationSubject.generateTestInputValues(Integer.parseInt(selection));

                            int i = 0;
                            for(ArrayList<Boolean> result : evaluationResult.conditionResults) {
                                for(ArrayList<Boolean> requirement : requirements) {
                                    if (requirement.equals(result)) {
                                        StringBuilder line = new StringBuilder("\n\t\tTestMethods.testBranchPredicate(");
                                        int j = 0;
                                        for (int x : evaluationResult.inputResults.get(i)) {
                                            if (j != evaluationResult.inputResults.get(i).size() - 1) {
                                                line.append(x).append(",");
                                            } else {
                                                line.append(x);
                                            }
                                            j++;
                                        }
                                        line.append(");");
                                        try {
                                            Files.writeString(path, line.toString(), StandardOpenOption.APPEND);
                                        } catch (IOException e) {
                                            System.out.println("Unable to write to file" + e.getLocalizedMessage());
                                        }
                                    }
                                }
                                i++;
                            }
                        }
                        ok = 1;
                        break;

                    default:
                        System.out.println("Please choose one of the above methods to test.");
                }
                try {
                    Files.writeString(path, "\n\t}\n}", StandardOpenOption.APPEND);
                } catch (IOException e) {
                    System.out.println("Unable to write to file" + e.getLocalizedMessage());
                    break;
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
