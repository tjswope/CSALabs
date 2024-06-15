import java.io.IOException;

public class DigitMatcherRunner {

    public static void main(String[] args) {
        try {
        	DigitMatcher digitCollection = new DigitMatcher("src\train.csv");
            digitCollection.printDigits();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
