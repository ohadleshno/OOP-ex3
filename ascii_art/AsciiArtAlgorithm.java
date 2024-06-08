package ascii_art;

import image.Image;
import image.ImageUtil;
import image_char_matching.SubImgCharMatcher;

public class AsciiArtAlgorithm {

    private Image image;
    private int resolution;
    private SubImgCharMatcher subImgCharMatcher;

    public AsciiArtAlgorithm(Image image, int resolution, Character[] asciiChars) {
        this.image = image;
        this.resolution = resolution;
        subImgCharMatcher = new SubImgCharMatcher(asciiChars);
    }

    public char[][] run() {
        Image paddedImage = ImageUtil.padImage(image);
        Image[][] subImages = ImageUtil.splitImage(paddedImage, resolution);
        int numRows = subImages.length;
        int numCols = subImages[0].length;
        double brightness;
        char[][] asciiArt = new char[numRows][numCols];

        for (int i = 0; i < numRows; i++) {
            for (int j = 0; j < numCols; j++) {
                brightness =  ImageUtil.calculateBrightness(subImages[i][j]);
                asciiArt[i][j] = subImgCharMatcher.getCharByImageBrightness(brightness);
            }
        }

        return asciiArt;
    }
}
