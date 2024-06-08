package ascii_art;

import image_char_matching.SubImgCharMatcher;

public class RemoveCommand extends CharChangeCommand {
    public RemoveCommand(SubImgCharMatcher subImgCharMatcher) {
        super(subImgCharMatcher);
    }

    @Override
    public void charChangeCommand(char c) {
        subImgCharMatcher.removeChar(c);
    }
}
