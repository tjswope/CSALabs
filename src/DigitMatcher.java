import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class DigitMatcher {
    private ArrayList<Digit> digits;

    /*
     * Task 1
     * Initializes the digits array with digits from a file
     * @param file containing the digits
     * 
     * File format:
     * 
     */
    public DigitMatcher(String filePath) throws IOException {
        digits = new ArrayList<>();
        
        Scanner s = new Scanner(new File(filePath));
        
        // Skip the header line if present
        s.nextLine();
        while (s.hasNext()) {
            String[] values = s.nextLine().split(",");
            int label = Integer.parseInt(values[0]);
            int[] pixels = new int[784];
            for (int i = 0; i < 784; i++) {
                pixels[i] = Integer.parseInt(values[i + 1]);
            }
            digits.add(new Digit(label, pixels));
        }
        s.close();
    }

    /*
     * Task 2
     * Calculates the difference (percentage of pixels) between two handwritten digits
     * @param digitA
     * @param digitB
     * @return the difference between two digits
     */
    public int difference ( Digit digitA, Digit digitB ) {

        return 0;
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
