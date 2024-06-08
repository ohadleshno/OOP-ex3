package ascii_art;

import image_char_matching.SubImgCharMatcher;

public abstract class CharChangeCommand implements Command {
    protected SubImgCharMatcher subImgCharMatcher;

    public CharChangeCommand(SubImgCharMatcher subImgCharMatcher) {
        this.subImgCharMatcher = subImgCharMatcher;
    }

    @Override
    public void execute(String[] args) {
        String argument = args[1];

        switch (argument) {
            case "all":
                subImgCharMatcher.addChar(args[1].charAt(0));
                break;
            case "space":
                subImgCharMatcher.addChar(' ');
            default:
                try {
                    addRange(argument);
                } catch (IllegalArgumentException e) {
                    System.out.println("Did not add due to incorrect format.");
                }
        }
    }

    private void addRange(String range) {
        char start = range.charAt(0);
        char end = range.charAt(2);
        if (range.charAt(1) != '-') {
            throw new IllegalArgumentException("Invalid range");
        }

        if (start < 32 || start > 126 || end < 32 || end > 126) {
            throw new IllegalArgumentException("Invalid Ascii range. Must be between 32 and 126.");
        }

        if (start > end) {
            char temp = start;
            start = end;
            end = temp;
        }

        for (char c = start; c <= end; c++) {
            charChangeCommand(c);
        }
    }

    public abstract void charChangeCommand(char c);
}
