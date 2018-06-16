package cz.alisma.alej.text.wrapping;

import java.util.List;

public class CenterAligner implements Aligner {

    private final int TOTAL_WIDTH;

    public CenterAligner(int w) {
        TOTAL_WIDTH = w;
    }

    @Override
    public String format(List<String> words) {
        StringBuilder result = new StringBuilder();
        int spacesBefore = TOTAL_WIDTH - (words.size() - 1);
        for (String word : words) {
            spacesBefore -= word.length();
        }
        if (spacesBefore % 2 == 0) {
            spacesBefore /= 2;
        } else {
            spacesBefore -= 1;
            spacesBefore /= 2;
        }
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