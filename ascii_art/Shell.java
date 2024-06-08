package ascii_art;

import image_char_matching.SubImgCharMatcher;

public class Shell {

    public static final String EXIT_COMMAND = "exit";
    public static final String DEFAULT_ASCII_CHARS = "0123456789";
    public static final int DEFAULT_RESOLUTION = 128;
    public static final String DEFAULT_IMAGE_ADDRESS = "./cat.jpeg";
    public static final String SAVING_METHOD = "Console";

    public SubImgCharMatcher subImgCharMatcher;

    public void run() {

        subImgCharMatcher = new SubImgCharMatcher(DEFAULT_ASCII_CHARS.chars().mapToObj(c -> (char) c).toArray(Character[]::new));
        System.out.println(">>>");
        String command = KeyboardInput.readLine();
        while (command != EXIT_COMMAND) {

        }
        System.out.println("Exiting...");
    }

    private void parseCommand(String command) {
        String[] splitCommand = command.toLowerCase().split(" ");
        switch (splitCommand[0]) {
            case "exit":
                break;
            case "chars":
                new CharsCommand(subImgCharMatcher.getCharset()).execute(splitCommand);
                break;
            case "add":
                //@TODO: need to handle when trying to add a character that is in the charset
                new AddCommand(subImgCharMatcher).execute(splitCommand);
                break;
            case "remove":
                //@TODO: need to handle when trying to remove a character that is not in the charset
                new RemoveCommand(subImgCharMatcher).execute(splitCommand);
                break;
            case "res":
                break;
            case "image":
                break;
            case "output":
                break;
            case "asciiArt":
                break;
            default:
                System.out.println("Invalid command");
        }

    }
}
