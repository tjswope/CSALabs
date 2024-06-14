import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class DigitMatcher {
    private ArrayList<Digit> digits;

    public DigitMatcher(String filePath) throws IOException {
        digits = new ArrayList<>();
        
        BufferedReader br = new BufferedReader(new FileReader(filePath));
        String line;
        // Skip the header line if present
        br.readLine();
        while ((line = br.readLine()) != null) {
            String[] values = line.split(",");
            int label = Integer.parseInt(values[0]);
            int[] pixels = new int[784];
            for (int i = 0; i < 784; i++) {
                pixels[i] = Integer.parseInt(values[i + 1]);
            }
            digits.add(new Digit(label, pixels));
        }
        br.close();
    }

    public ArrayList<Digit> getDigits() {
        return digits;
    }

    public void printDigits() {
        for (Digit digit : digits) {
            System.out.println("Label: " + digit.getLabel());
            digit.printMatrix();
            System.out.println();
        }
    }

    public static void main(String[] args) {
        try {
        	DigitMatcher digitCollection = new DigitMatcher("src\train.csv");
            digitCollection.printDigits();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
