import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class DigitMatcher {

    /* Array holding the dataset of digits */
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
     * Task 2b
     * Calculates the similarity between two handwritten digits.
     * 
     * The similarity is calculated by taking the difference (percentage of pixels) 
     * between the two digits.
     *
     * @param digit
     */
    public void computeSimilarity ( Digit digit ) {

        for ( Digit d : digits ) {
            d.setSimilarity( digit );
        }
    }

    /*
     * Task 3: returns the Digit with the smallest similarity value.
     * 
     * The lowest the value the most similar the digit is.
     * 
     * This method depends on computeSimilarity()
     */
    public Digit mostSimilar () {

        Digit most = digits.get(0);

        for ( int i = 1; i < digits.size(); i++ ) {

            Digit digit = digits.get(i);
            if ( digit.getSimilarity() < most.getSimilarity() ) {
                most = digit;
            }
        }

        return most;
    }

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
    public ArrayList<Digit> rankBySimilarity ( int k, Digit digit ) {

        ArrayList<Digit> rank = new ArrayList<Digit>(k);
        return rank;
    }
 
    /*
     * Task 5: 
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
