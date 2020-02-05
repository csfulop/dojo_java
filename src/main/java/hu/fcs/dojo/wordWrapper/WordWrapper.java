package hu.fcs.dojo.wordWrapper;

import java.util.ArrayList;
import java.util.List;

import static java.lang.String.join;

public class WordWrapper {
    private final int maxLineLenght;
    private String input;

    private String[] words;

    private int cursor = 0;
    private List<String> lines = new ArrayList<>();
    private List<String> line = new ArrayList<>();

    public WordWrapper(int maxLineLenght, String input) {
        this.maxLineLenght = maxLineLenght;
        this.input = input;
    }

    public String wrap() {
        if (input == null) {
            return "";
        }
        words = input.split("\\s+");
        for (String word : words) {
            add(word);
        }
        startNewLine();
        return join("\n", lines);
    }

    private void add(String word) {
        if (lineLengthWith(word) > maxLineLenght) {
            startNewLine();
        }
        while (word.length() > maxLineLenght) {
            line.add(word.substring(0, maxLineLenght));
            startNewLine();
            word = word.substring(maxLineLenght);
        }
        line.add(word);
        cursor += word.length();
    }

    private int lineLengthWith(String word) {
        return cursor + word.length() + line.size();
    }

    private void startNewLine() {
        if (line.size() > 0) {
            lines.add(join(" ", line));
            line.clear();
            cursor = 0;
        }
    }
}
