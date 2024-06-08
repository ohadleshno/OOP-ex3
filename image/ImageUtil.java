package image;

import java.awt.*;

public class ImageUtil {

    public static final int RGB_MAX = 255;

    public static final Color WHITE_COLOR = new Color(255, 255, 255);

    public static double calculateBrightness(Image image) {
        double sumGreyPixel =0 ;
        for (int i = 0; i < image.getHeight(); i++) {
            for (int j = 0; j < image.getWidth(); j++) {
                Color color = image.getPixel(i, j);
                sumGreyPixel += color.getRed() * 0.2126 + color.getGreen() * 0.7152 + color.getBlue() * 0.0722;
            }
        }
        int pixels_amount = image.getHeight() * image.getWidth();
        // we divide by 255 to normalize the value to be between 0 and 1
        return (sumGreyPixel / pixels_amount) / RGB_MAX;
    }

    public static Image[][] splitImage(Image image, int resolution) {
        int subImageWidth = image.getWidth() / resolution;
        int subImageHeight = image.getHeight() / resolution;
        int numRows = image.getHeight() / subImageHeight;
        int numCols = image.getWidth() / subImageWidth;
        Image[][] subImages = new Image[numCols][numRows];


        for (int i = 0; i < numCols; i++) {
            for (int j = 0; j < numRows; j++) {
                Color[][] subImagePixelArray = createSubImage(image, subImageHeight, subImageWidth, subImageHeight * i, subImageWidth * j);
                subImages[i][j] = new Image(subImagePixelArray, subImageWidth, subImageHeight);
            }
        }
        return subImages;
    }

    public static Image padImage(Image image) {
        int newHeight = findNearestPowerOfTwo(image.getHeight());
        int newWidth = findNearestPowerOfTwo(image.getWidth());
        return createWhitePaddedImageInCenter(image, newWidth, newHeight);
    }


    private static Color[][] createSubImage(Image image,int subImageHeight, int subImageWidth, int xOffset, int yOffset) {
        Color[][] subImagePixelArray = new Color[subImageHeight][subImageWidth];
        for (int x = 0; x < subImageHeight; x++) {
            for (int y = 0; y < subImageWidth; y++) {
                subImagePixelArray[x][y] = image.getPixel(xOffset + x, yOffset + y);
            }
        }
        return subImagePixelArray;
    }

    private static Image createWhitePaddedImageInCenter(Image image, int newWidth, int newHeight) {
        Color[][] newPixelArray = createNewWhitePixelArray(newWidth, newHeight);
        copyImageToCenter(image, newWidth, newHeight, newPixelArray);
        return new Image(newPixelArray, newWidth, newHeight);
    }

    private static void copyImageToCenter(Image image, int newWidth, int newHeight, Color[][] newPixelArray) {
        int xOffset = (newWidth - image.getWidth()) / 2;
        int yOffset = (newHeight - image.getHeight()) / 2;

        for (int i = 0; i < image.getHeight(); i++) {
            for (int j = 0; j < image.getWidth(); j++) {
                newPixelArray[i + xOffset][j + yOffset] = image.getPixel(i, j);
            }
        }
    }


    private static Color[][] createNewWhitePixelArray(int newWidth, int newHeight) {
        Color[][] newPixelArray = new Color[newHeight][newWidth];
        for (int i = 0; i < newHeight; i++) {
            for (int j = 0; j < newWidth; j++) {
                newPixelArray[i][j] = WHITE_COLOR;
            }
        }
        return newPixelArray;
    }

    private static int findNearestPowerOfTwo(int n) {
        return (int) Math.pow(2, Math.ceil(Math.log(n) / Math.log(2)));
    }
}
