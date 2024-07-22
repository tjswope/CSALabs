import java.awt.image.BufferedImage;

public class Digit implements Comparable<Digit>{

	private int label;
    private int[][] pixels;
    private double similarity;

    public Digit(int label, int[] pixels) {
    	this.label = label;
        if (pixels.length != 784) {
            throw new IllegalArgumentException("Pixel array must contain exactly 784 elements.");
        }
        this.pixels = new int[28][28];
        constructpixels(pixels);
    }

    private void constructpixels(int[] pixels) {
        for (int i = 0; i < 28; i++) {
            for (int j = 0; j < 28; j++) {
                this.pixels[i][j] = pixels[i * 28 + j];
            }
        }
    }

    public int[][] getpixels() {
        return pixels;
    }

    public int getLabel() {
    	return label;
    }
    
    /*
     * Calculates how similar this Digit is to another Digit.
     * 
     * The similarity is calculated by taking the difference (percentage of pixels) 
     * between the two handwritten digits.
     * 
     * The lower the value the greater the similarity.
     * 
     * @param other the digit this object is compared against.
     */
    public void setSimilarity (Digit other) {
        
        int similarityCount = 0;

        for ( int r = 0; r < pixels.length; r++ ) {
            for ( int c = 0; c < pixels[r].length; c++ ) {

                if (pixels[r][c] == other.getpixels()[r][c]) {
                	similarityCount += 1;
                }
            }
        }
        similarity = (double)(similarityCount) / (pixels.length * pixels[0].length);
    }

    public double getSimilarity() {
        return similarity;
    }

    public int compareTo (Digit other) {
        return Double.compare(getSimilarity(), other.getSimilarity());
    }

    public void printPixels() {
        for (int i = 0; i < 28; i++) {
            for (int j = 0; j < 28; j++) {
                System.out.printf("%3d ", pixels[i][j]);
            }
            System.out.println();
        }
    }

    public BufferedImage toBufferedImage() {
        BufferedImage image = new BufferedImage(28, 28, BufferedImage.TYPE_BYTE_GRAY);
        for (int i = 0; i < 28; i++) {
            for (int j = 0; j < 28; j++) {
                int pixelValue = pixels[i][j];
                int rgb = pixelValue | (pixelValue << 8) | (pixelValue << 16);
                image.setRGB(j, i, rgb);
            }
        }
        return image;
    }

    public String toString () {
        return String.format("[Label %d, Similarity %f]", label, similarity);
    }
}
