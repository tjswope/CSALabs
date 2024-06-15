
import java.io.File;
import java.io.IOException;
import java.util.*;

public class DigitRecognizer {

    private DigitMatcher matcher;
    private ArrayList<Digit> testDigits;

    DigitRecognizer (String baseDigitsFilePath, String testDigitsFilePath) throws IOException {

        // populate the matcher with some digits
        matcher = new DigitMatcher(baseDigitsFilePath);

        // populate testDigits with new digits
        testDigits = new ArrayList<>();
        
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
    }
}
