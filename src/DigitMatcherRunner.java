import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class DigitMatcherRunner {

    /*
     * The test digit files do not contain a label 
     */
    public static ArrayList<Digit> populateArrayOfTestDigits (String testDigitsFilePath) throws IOException {

        ArrayList<Digit> testDigits = new ArrayList<>();
        
        Scanner s = new Scanner(new File(testDigitsFilePath));
        
        // Skip the header line if present
        s.nextLine();
        while (s.hasNext()) {
            String[] values = s.nextLine().split(",");
            int[] pixels = new int[784];
            for (int i = 0; i < 784; i++) {
                pixels[i] = Integer.parseInt(values[i]);
            }
            // label is unknown because this is the test digits.
            int label = 10; // ?????? should we use some other value for unknown label ???????
            testDigits.add(new Digit(label, pixels));
        }
        s.close();
        return testDigits;
    }

    public static void main(String[] args) {
        try {
        	DigitMatcher digitCollection = new DigitMatcher("src/train.csv");
            //digitCollection.printDigits();

            ArrayList<Digit> testDigits = populateArrayOfTestDigits("src/test.csv");

            // Example for one digit, say index 0
            Digit testDigit = testDigits.get(0);

            digitCollection.computeSimilarity(testDigit);
            digitCollection.rankBySimilarity();
            int matchingLabel = digitCollection.kNearestNeighbors(7);

            System.out.printf("Digit %s was matched to label %d. Is that a correct match?\nLabel 10 indicates a test digit.", testDigit, matchingLabel);

            
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
