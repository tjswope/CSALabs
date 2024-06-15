import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class DigitMatcher {

    /* Array to hold the dataset of digits */
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
     * Calculates the similarity between two handwritten digits.
     * 
     * The similarity is calculated by taking the difference (percentage of pixels) 
     * between the two digits.
     *
     * @param digitA
     * @param digitB
     * @return the difference between two digits
     */
    public int similarity ( Digit digitA, Digit digitB ) {

        return 0;
    }

    /*
     * Task 3:
     * 
     */

    /*
     * Task 4: rank the Digits from the digits ArrayList by similarity.
     * 
     * The lowest the similarity (the highest the rank) is expected
     * to be at index 0, the next lowest similarity at index 1 and so on.
     * 
     * @param k is the number of Digits we are interested in.
     * @param digit is the digit used to find similar digits in the ArrayList of digits.
     * @return an ArrayList with k Digits ranked by similarity.
     */
    public ArrayList<Digit> rankBySimilarity (int k, Digit digit) {

        ArrayList<Digit> rank = new ArrayList<Digit>(k);
        return rank;
    }

    /*
     * Task 5
     */
    public Digit kNearestNeighbors () {

        return null;
    }
    
    /*
     * Task 6
     */
    public Digit weightedKNearestNeighbors () {
        return null;
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
}
