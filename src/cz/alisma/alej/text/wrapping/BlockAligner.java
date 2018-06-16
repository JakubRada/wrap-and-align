package cz.alisma.alej.text.wrapping;

import java.util.List;

public class BlockAligner implements Aligner {
    private final int TOTAL_WIDTH;

    public BlockAligner(int w) {
        TOTAL_WIDTH = w;
    }

    public String format(List<String> words) {
        StringBuilder result = new StringBuilder();
        int spacesBefore = TOTAL_WIDTH;
        for (String word : words) {
            spacesBefore -= word.length();
        }
        int gaps = words.size() - 1;
        if (gaps > 0) {
            int remainingSpaces = spacesBefore % gaps;
            int spacesPerGap = (spacesBefore - remainingSpaces) / gaps;
            boolean first = true;
            for (String word : words) {
                if (!first) {
                    for (int i = 0; i < spacesPerGap; i += 1) {
                        result.append(" ");
                    }
                    if (remainingSpaces > 0) {
                        result.append(" ");
                        remainingSpaces -= 1;
                    }
                } else {
                    first = false;
                }
                result.append(word);
            }
        } else {
            result.append(words.get(0));
        }
        return result.toString();
    }
}