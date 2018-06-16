package cz.alisma.alej.text.wrapping;

import java.util.List;

public class RightAligner implements Aligner {

    private final int TOTAL_WIDTH;

    public RightAligner(int w) {
        TOTAL_WIDTH = 50;
    }

    @Override
    public String format(List<String> words) {
        StringBuilder result = new StringBuilder();
        int spacesBefore = TOTAL_WIDTH;
        for (String word : words) {
            spacesBefore -= word.length();
        }
        spacesBefore -= words.size() - 1;
        for (int i = 0; i < spacesBefore; i += 1) {
            result.append(" ");
        }
        boolean first = true;
        for (String word : words) {
            if (!first) {
                result.append(" ");
            } else {
                first = false;
            }
            result.append(word);
        }
        return result.toString();
    }
}