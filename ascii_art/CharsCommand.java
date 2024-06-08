package ascii_art;

import java.util.List;

public class CharsCommand implements Command {
    private List<Character> charset;

    public CharsCommand(List<Character> charset) {
        this.charset = charset;
    }

    @Override
    public void execute(String [] args) {
        for (char c : charset) {
            System.out.print(c + " ");
        }
    }
}
