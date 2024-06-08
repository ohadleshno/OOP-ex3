import ascii_art.AsciiArtAlgorithm;
import ascii_output.AsciiOutput;
import ascii_output.ConsoleAsciiOutput;
import ascii_output.HtmlAsciiOutput;
import image.Image;

import java.io.IOException;

public class Main {

    public static final String DEFAULT_ASCII_CHARS = "0123456789";
    public static final int DEFAULT_RESOLUTION = 128;
    public static final String DEFAULT_IMAGE_ADDRESS = "./cat.jpeg";
    public static final String SAVING_METHOD = "Console";

    public static void main(String[] args) throws IOException {
        // Load image
        Image image = new Image(DEFAULT_IMAGE_ADDRESS);
        Character[] charArray = DEFAULT_ASCII_CHARS.chars().mapToObj(c -> (char) c).toArray(Character[]::new);

        // Run algorithm
        AsciiArtAlgorithm algorithm = new AsciiArtAlgorithm(image, DEFAULT_RESOLUTION, charArray);
        char[][] asciiArt = algorithm.run();

        // Output to console
        AsciiOutput output = new ConsoleAsciiOutput();
        HtmlAsciiOutput htmlOutput = new HtmlAsciiOutput("output.html", "Courier New");
        output.out(asciiArt);
        htmlOutput.out(asciiArt);

        // Save to file
        image.saveImage("output");
    }


    private static AsciiOutput getOutputMethod(String method) {
        switch (method) {
            case "Console":
                return new ConsoleAsciiOutput();
            case "Html":
                return new HtmlAsciiOutput("output.html", "Courier New");
            default:
                return new ConsoleAsciiOutput();
        }
    }
}
