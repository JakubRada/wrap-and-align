/*
 * MIT License
 * Copyright (c) 2017 Gymnazium Nad Aleji
 * Copyright (c) 2017 Vojtech Horky
 * 
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 * 
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 * 
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */

package cz.alisma.alej.text.wrapping;

import java.util.Scanner;

public class WrapAndAlign {

    public static String[] processArguements(String[] args) {
        String[] processed = new String[] {"", ""};
        for (int i = 0; i < args.length; i += 1) {
            if (args[i].equals("--right")) {
                processed[0] = "r";
            } else if (args[i].equals("--left")) {
                processed[0] = "l";
            } else if (args[i].equals("--center") || args[i].equals("--centre")) {
                processed[0] = "c";
            } else if (args[i].equals("--justify")) {
                processed[0] = "j";
            } else if (args[i].equals("-w")) {
                try {
                    int w = Integer.parseInt(args[i + 1]);
                    processed[1] = args[i + 1];
                } catch (Exception e){}
            } else {
                try {
                    String[] parts = args[i].split("=");
                    if (parts[0].equals("--width")) {
                        int w = Integer.parseInt(parts[1]);
                        processed[1] = parts[1];
                    }
                } catch (Exception e){}
            }
        }
        return processed;
    }

    public static void main(String[] args) {
        // default values in case when user does not specify them or does not specify them correctly
        int maxWidth = 50;
        Aligner aligner = new LeftAligner();
        // getting arguements from user
        String[] processedArgs = processArguements(args);
        if (!processedArgs[1].equals("")) {
            maxWidth = Integer.parseInt(processedArgs[1]);
        }
        if (processedArgs[0].equals("r")) {
            aligner = new RightAligner(maxWidth);
        } else if (processedArgs[0].equals("c")) {
            aligner = new CenterAligner(maxWidth);
        } else if (processedArgs[0].equals("j")) {
            aligner = new BlockAligner(maxWidth);
        } else if (processedArgs[0].equals("l")) {
            aligner = new LeftAligner();
        }
        Scanner input = new Scanner(System.in);
        ParagraphDetector pd = new ParagraphDetector(input);

        while (pd.hasNextParagraph()) {
            Paragraph para = pd.nextParagraph();
            LinePrinter line = new LinePrinter(System.out, maxWidth, aligner);
            while (para.hasNextWord()) {
                String word = para.nextWord();
                line.addWord(word);
            }
            line.flush();
            System.out.println();
        }
    }
}
