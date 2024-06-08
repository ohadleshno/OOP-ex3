import ascii_art.AsciiArtAlgorithm;
import ascii_output.AsciiOutput;
import ascii_output.ConsoleAsciiOutput;
import ascii_output.HtmlAsciiOutput;
import image.Image;

import java.io.IOException;

public class Main {

    public static final String ASCII_CHARS = "0123456789";
    public static final int RESOLUTION = 128;
    public static final String IMAGE_ADDRESS = "./cat.jpeg";

    public static void main(String[] args) throws IOException {
        // Load image
        Image image = new Image(IMAGE_ADDRESS);
        Character[] charArray = ASCII_CHARS.chars().mapToObj(c -> (char)c).toArray(Character[]::new);

        // Run algorithm
        AsciiArtAlgorithm algorithm = new AsciiArtAlgorithm(image, RESOLUTION, charArray);
        char[][] asciiArt = algorithm.run();

        // Output to console
        AsciiOutput output = new ConsoleAsciiOutput();
        HtmlAsciiOutput htmlOutput = new HtmlAsciiOutput("output.html", "Courier New");
        output.out(asciiArt);
        htmlOutput.out(asciiArt);

        // Save to file
        image.saveImage("output");
    }
}
