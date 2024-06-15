import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class DigitMatcherRunner {

    public static ArrayList<Digit> populateArrayOfDigits (String testDigitsFilePath) throws IOException {

        ArrayList<Digit> testDigits = new ArrayList<>();
        
        Scanner s = new Scanner(new File(testDigitsFilePath));
        
        // Skip the header line if present
        s.nextLine();
        while (s.hasNext()) {
            String[] values = s.nextLine().split(",");
            int label = Integer.parseInt(values[0]);
            int[] pixels = new int[784];
            for (int i = 0; i < 784; i++) {
                pixels[i] = Integer.parseInt(values[i + 1]);
            }
            testDigits.add(new Digit(label, pixels));
        }
        s.close();
        return testDigits;
    }

    public static void main(String[] args) {
        try {
        	DigitMatcher digitCollection = new DigitMatcher("src\train.csv");
            digitCollection.printDigits();

            ArrayList<Digit> testDigits = populateArrayOfDigits("src\test.csv");

            // Example for one digit, say index 0
            Digit digit = testDigits.get(0);

            digitCollection.computeSimilarity(digit);
            digitCollection.rankBySimilarity();
            int matchingLabel = digitCollection.kNearestNeighbors(7);

            System.out.printf("Digit %s was matched to label %d. Is that a match?", digit, matchingLabel);

            
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
