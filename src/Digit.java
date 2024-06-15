import java.awt.image.BufferedImage;

public class Digit {

	private int label;
    private int[][] matrix;
    private int similarity;

    public Digit(int label, int[] pixels) {
    	this.label = label;
        if (pixels.length != 784) {
            throw new IllegalArgumentException("Pixel array must contain exactly 784 elements.");
        }
        matrix = new int[28][28];
        constructMatrix(pixels);
    }

    private void constructMatrix(int[] pixels) {
    	this.label = -1;
        for (int i = 0; i < 28; i++) {
            for (int j = 0; j < 28; j++) {
                matrix[i][j] = pixels[i * 28 + j];
            }
        }
    }

    public int[][] getMatrix() {
        return matrix;
    }

    public int getLabel() {
    	return label;
    }
    
    /*
     * Task 2a
     * Calculates how similar this Digit is to other.
     * 
     * The similarity is calculated by taking the difference (percentage of pixels) 
     * between the two handwritten digits.
     * 
     * The lowest the value the greater the similarity.
     * 
     * @param other the digit this object is compared to.
     * @param pixelMatchRange Range of grey values that is considered a match. If the 
     * value is 150 then two pixels with values of 80 and 200 would
     * be considered a match. 200 - 80 = 120. 120 < 150.
     */
    public void setSimilarity (Digit other, int pixelMatchRange) {
        
        int difference = matrix.length * matrix.length;

        for ( int r = 0; r < matrix.length; r++ ) {
            for ( int c = 0; c < matrix[r].length; c++ ) {

                int diff = Math.abs(matrix[r][c] - other.getMatrix()[r][c]);
                if ( diff < pixelMatchRange ) {
                    difference -= 1;
                }
            }
        }
        similarity = difference;
    }

    public int getSimilarity () {
        return similarity;
    }

    public int compareTo (Digit other) {
        return this.similarity - other.similarity;
    }

    public void printMatrix() {
        for (int i = 0; i < 28; i++) {
            for (int j = 0; j < 28; j++) {
                System.out.printf("%3d ", matrix[i][j]);
            }
            System.out.println();
        }
    }

    public BufferedImage toBufferedImage() {
        BufferedImage image = new BufferedImage(28, 28, BufferedImage.TYPE_BYTE_GRAY);
        for (int i = 0; i < 28; i++) {
            for (int j = 0; j < 28; j++) {
                int pixelValue = matrix[i][j];
                int rgb = pixelValue | (pixelValue << 8) | (pixelValue << 16);
                image.setRGB(j, i, rgb);
            }
        }
        return image;
    }
}
