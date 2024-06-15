import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class DigitMatcher {

    /* Array holding the dataset of digits */
    private ArrayList<Digit> digits;

    /*  
     * Range of grey values that is considered a match. If the 
     * value is 150 then two pixels with values of 80 and 200 would
     * be considered a match.
     */
    private int pixelMatchRange;

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

    public DigitMatcher(String filePath, int pixelMatchRange) throws IOException {

        this(filePath);
        setPixelMatchRange(pixelMatchRange);
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
            d.setSimilarity( digit, pixelMatchRange );
        }
    }

    /*
     * Task 3: returns the Digit with the smallest similarity value.
     * 
     * The lowest the value the greater the similarity.
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
     * The lowest the value the greater the similarity. The most similar
     * digit is expected to be at index 0, the next lowest similarity at 
     * index 1 and so on.
     * 
     * @return an ArrayList with k Digits ranked by similarity.
     */
    public void rankBySimilarity () {

        // Selection sort
        for ( int i = 0; i < digits.size(); i++ ) {
            int maxSimIndex = greatestSimilarity(i, digits.size());

            // swap the Digit at index maxSimIndex with the Digit at i
            Digit temp = digits.get(i);
            digits.set(i, digits.get(maxSimIndex));
            digits.set(maxSimIndex, temp);
        }
        
    }
 
    /*
     * Task 4
     * 
     * Find the Digit from the ArrayList digits with the greatest similarity (lowest value)
     * between the indices [l, h].
     * 
     * The lowest the value the greater the similarity.
     * 
     * @return the index (from digits array) of the Digit with greatest similarity.
     */
    public int greatestSimilarity (int l, int h) {

        int index = l;

        for ( int i = l+1; i <= h; i++  ) {
            
            if ( digits.get(index).getSimilarity() > digits.get(i).getSimilarity() ) {
                // found a more similar digit
                index = i;
            }
        }

        return index;
    }

    /*
     * Task 5
     * 
     * Find the k nearest neighbors based on similarity.
     * 
     * Depends on rankBySimilarity()
     * 
     * @param k 
     * @return the label that occurs the most within the first k objects in digits.
     */
    public int kNearestNeighbors (int k) {

        int[] votes = new int[10]; // 10 digits. Cannot hardcode if we use other characters.

        // The first k Digit objects in digits are ranked by similarity.
        // Index 0 has the most similar digit.
        for ( int i = 0; i < k; i++ ) {
            int label = digits.get(i).getLabel(); // this value will range from 0-9
            votes[label] += 1;
        }
        
        // find the label with most votes
        int mostVotedLabel = votes[0];
        for ( int i = 1; i < votes.length; i++ ) {
            if ( mostVotedLabel < votes[1] ) {
                mostVotedLabel = i;
            }
        }
        return mostVotedLabel;
    }
    
    /*
     * Task 6
     */
    public Digit weightedKNearestNeighbors () {
        return null;
    }

    /*
     * Task 7
     */

    public void setPixelMatchRange (int pixelMatchRange ) {
        this.pixelMatchRange = pixelMatchRange;
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
