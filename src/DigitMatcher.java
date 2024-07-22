import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class DigitMatcher {

	/* Array holding the dataset of digits */
	private ArrayList<Digit> digits;

	/*
	 * Constructor 
	 * Initializes the digits array with digits from a file
	 * @param file containing the digits
	 * 
	 * File format:
	 * 
	 */
	public DigitMatcher(String filePath) throws IOException {       
		Scanner s = new Scanner(new File(filePath));

		digits = new ArrayList<>();

		while (s.hasNext()) {
			String[] values = s.nextLine().split(",");
			int label = Integer.parseInt(values[0]);
			int[] pixels = new int[784];
			for (int i = 0; i < 784; i++) {
				pixels[i] = Integer.parseInt(values[i + 1]) == 0 ? 0 : 1;
			}
			digits.add(new Digit(label, pixels));
		}
		s.close();
	}


	/*
	 * Computes how similar the parameter digit is to all digits in the dataset. More
	 * specifically, calculates the similarity between (two handwritten digits) the 
	 * parameter digit and all the digits in the dataset.
	 * 
	 * The similarity is calculated by taking the difference (percentage of pixels) 
	 * between the two digits.
	 *
	 * @param digit is the digit that will be compared with the dataset of digits.
	 */
	public void computeSimilarity ( Digit digit ) {

		for ( Digit d : digits ) {
			d.setSimilarity(digit);
		}
	}

	/*
	 * Assumes computeSimilarity() has been called to calculate the similaty between
	 * a digit and every digit in the dataset of digits.
	 * 
	 * Returns the Digit from the dataset most similar to the parameter 
	 * digit of computeSimilarity().
	 * 
	 * The most similar digit has the smallest similarity value (the lowest the 
	 * value the greater the similarity).
	 * 
	 */
	public Digit mostSimilar () {

		Digit most = digits.get(0);

		for ( int i = 1; i < digits.size(); i++ ) {

			Digit digit = digits.get(i);
			if ( digit.getSimilarity() > most.getSimilarity() ) {
				most = digit;
			}
		}

		return most;
	}

	/*
	 * Assumes computeSimilarity() has been called to calculate the similaty between
	 * a digit and every digit in the dataset of digits.
	 * 
	 * Rank the Digits in the digits dataset by similarity.
	 * 
	 * The lowest the value the greater the similarity. The most similar
	 * digit is expected to be at index 0, the next lowest similarity at 
	 * index 1 and so on.
	 * 
	 * @return an ArrayList with k Digits ranked by similarity.
	 */
	public void rankBySimilarity() {
		Collections.sort(digits);//, Collections.reverseOrder());
		// Selection sort
		//        for ( int i = 0; i < digits.size(); i++ ) {
		//            int maxSimIndex = greatestSimilarity(i, digits.size()-1);
		//
		//            // swap the Digit at index maxSimIndex with the Digit at i
		//            Digit temp = digits.get(i);
		//            digits.set(i, digits.get(maxSimIndex));
		//            digits.set(maxSimIndex, temp);
		//        }
	}

	/*
	 * Find the Digit from the ArrayList digits with the greatest similarity (lowest value)
	 * between the indices [l, h].
	 * 
	 * The lowest the value the greater the similarity.
	 * 
	 * @param 
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
	 * Depends on rankBySimilarity()
	 * 
	 * Steps: 
	 * 1. Find the k nearest neighbors based on similarity.
	 * 2. Of the k neighbors, finds the label that appears most often.
	 * 
	 * @param k 
	 * @return the label that occurs the most within the first k objects in digits.
	 */
	public int kNearestNeighbors (int k) {
		Collections.sort(digits, Collections.reverseOrder());

		int[] votes = new int[10]; // 10 digits. Cannot hardcode if we use other characters.

		// The first k Digit objects in digits are ranked by similarity.
		// Index 0 has the most similar digit.
		for (int i = 0; i < k; i++ ) {
			int label = digits.get(i).getLabel(); // this value will range from 0-9
			votes[label] += 1;
		}

		// Find the label with most votes
		int mostVotedLabel = 0;
		for ( int i = 1; i < votes.length; i++ ) {
			if (votes[mostVotedLabel] < votes[i]) {
				mostVotedLabel = i;
			}
		}
		return mostVotedLabel;
		//return digits.get(0).getLabel();
	}

	/*
	 * Depends on rankBySimilarity()
	 * 
	 * 1. Find the k nearest neighbors based on similarity.
	 * 2. For each of the k neighbors, compute the votes (based on similarity).
	 * 3. Find the label with the highest confidence level.
	 * 
	 * @param k 
	 * @return the label that occurs the most within the first k objects in digits.
	 */
	public int weightedKNearestNeighbors (int k) {
		Collections.sort(digits, Collections.reverseOrder());

		double[] votes = new double[10]; // 10 digits. Cannot hardcode if we use other characters.

		// The first k Digit objects in digits are ranked by similarity.
		// Index 0 has the most similar digit.
		for (int i = 0; i < k; i++ ) {
			int label = digits.get(i).getLabel(); // this value will range from 0-9
			votes[label] += digits.get(i).getSimilarity();
		}

		// Find the label with most votes
		int mostVotedLabel = 0;
		for ( int i = 1; i < votes.length; i++ ) {
			if ( votes[mostVotedLabel] < votes[i] ) {
				mostVotedLabel = i;
			}
		}
		return mostVotedLabel;
	}


	/*
	 * Returns the dataset of digits.
	 */
	public ArrayList<Digit> getDigits() {
		return digits;
	}

	/*
	 * Prints the dataset of digits.
	 */
	public void printDigits() {
		for ( Digit digit : digits ) {
			System.out.println("Label: " + digit.getLabel());
			digit.printPixels();
			System.out.println();
		}
	}
}
