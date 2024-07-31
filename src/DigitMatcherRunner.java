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

		while (s.hasNext()) {
			String[] values = s.nextLine().split(",");
			int label = Integer.parseInt(values[0]);
			int[] pixels = new int[784];
			for (int i = 0; i < 784; i++) {
				pixels[i] = Integer.parseInt(values[i + 1]) == 0 ? 0 : 1;
			}
			testDigits.add(new Digit(label, pixels));
		}
		s.close();
		return testDigits;
	}

	public static void testMostSimilar() {
		System.out.println("testing most similar");
		try {
			DigitMatcher digitCollection = new DigitMatcher("src/train2.csv");

			ArrayList<Digit> testDigits = populateArrayOfTestDigits("src/test2.csv");
			int matchCount = 0;

			//for(int i = 0; i < 1000; i++) {
				int j = 0;
			for(Digit d: testDigits){
				//Digit d = testDigits.get(i);
				digitCollection.computeSimilarity(d);
				int matchingLabel = digitCollection.mostSimilar().getLabel();


				if(d.getLabel() == matchingLabel)
					matchCount++;
				j++;
				if(j%100 == 0) System.out.print(".");
			}
			System.out.println();
			System.out.println("match percentage = " + (double)matchCount/testDigits.size());
			//System.out.println("match percentage = " + (double)matchCount/1000);

		} catch (IOException e) {
			e.printStackTrace();
		}	 
	}

	public static void testKNearestNeighbors(int k) {
		System.out.println("Testing k nearest neightbors with k = " + k);
		try {
			DigitMatcher digitCollection = new DigitMatcher("src/train2.csv");

			ArrayList<Digit> testDigits = populateArrayOfTestDigits("src/test2.csv");
			int matchCount = 0;
			
			int j = 0;
			//for(int i = 0; i < 1000; i++) {
			for(Digit d: testDigits){
				//Digit d = testDigits.get(i);
				digitCollection.computeSimilarity(d);

				int matchingLabel = digitCollection.kNearestNeighbors(k);

				//System.out.println("The digit's actual label is " + d.getLabel());
				//System.out.println("Best match is " + matchingLabel);

				if(d.getLabel() == matchingLabel)
					matchCount++;
				j++;
				if(j%100 == 0) System.out.print(".");
			}
			System.out.println();
			System.out.println("match percentage = " + (double)matchCount/testDigits.size());
			//System.out.println("match percentage = " + (double)matchCount/1000);
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	public static void testWeightedKNearestNeighbors(int k) {
		System.out.println("Testing weighted k nearest neightbors with k = " + k);
		try {
			DigitMatcher digitCollection = new DigitMatcher("src/train2.csv");

			ArrayList<Digit> testDigits = populateArrayOfTestDigits("src/test2.csv");
			int matchCount = 0;
			int j = 0;
			//for(int i = 0; i < 1000; i++) {
			for(Digit d: testDigits){
				//Digit d = testDigits.get(i);
				digitCollection.computeSimilarity(d);

				int matchingLabel = digitCollection.weightedKNearestNeighbors(k);

				//System.out.println("The digit's actual label is " + d.getLabel());
				//System.out.println("Best match is " + matchingLabel);

				if(d.getLabel() == matchingLabel)
					matchCount++;
				j++;
				if(j%100 == 0) System.out.print(".");
			}
			System.out.println();
			System.out.println("match percentage = " + (double)matchCount/testDigits.size());
			//System.out.println("match percentage = " + (double)matchCount/1000);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) throws IOException {
		
		ArrayList<Digit> testDigits = populateArrayOfTestDigits("src/test2.csv");
		System.out.println(testDigits.get(500));
		
		//testMostSimilar();
		//testKNearestNeighbors(4);
		//testWeightedKNearestNeighbors(5);
	}
}
