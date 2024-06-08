package ascii_art;

import image_char_matching.SubImgCharMatcher;

public class AddCommand extends CharChangeCommand {

    public AddCommand(SubImgCharMatcher subImgCharMatcher) {
        super(subImgCharMatcher);
    }

    @Override
    public void charChangeCommand(char c) {
        subImgCharMatcher.addChar(c);
    }
}
