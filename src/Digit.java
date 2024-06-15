import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

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
     */
    public void setSimilarity (Digit other) {
        


    }

    public int getSimilarity () {
        return similarity;
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
