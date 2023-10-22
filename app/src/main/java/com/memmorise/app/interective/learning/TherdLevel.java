package com.memmorise.app.interective.learning;


import static com.memmorise.app.interective.learning.util.LearnUtil.*;

import java.nio.charset.Charset;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

import com.memmorise.app.interective.learning.LearnMap.Node;
import com.memmorise.app.library.Library;
public class TherdLevel implements LearnLevel{

    private Library curLibrary;
    private LearnMap learnMap;
    private int packOfWords;
    private Random rand;
    private SecondLevel secondLevel;
    private FirstLevel firstLevel;
    private Scanner scan;

    @Override
    public void init(Library curLibrary, LearnMap learnMap, int packOfWords) {
        this.curLibrary = curLibrary;
        this.learnMap = learnMap;
        this.packOfWords = packOfWords;
        secondLevel = new SecondLevel();
        firstLevel = new FirstLevel();
        rand = new Random(System.currentTimeMillis());
        scan = new Scanner(System.in, Charset.forName("cp866"));
    }

    @Override
    public void doProcess() {
        
    }

    @Override
    public List<Node> learnPackOfWords(List<Node> pack) {
        for (int i = 0; i < pack.size(); i++) {
            Node node = pack.get(i);

            aPrint(String.format("Here is word %s, please write tranlation.\n", node.word), 20L);

            String userTranslation = scan.nextLine();
            if (checkCorrectWord(node.translation, userTranslation)) {
                aPrint("Yes, you are correct!", 20L);
                node.levelOfNow += node.levelOfNow > 5 ? 0 : 1;
                node.mistakes = 0;
            }
            else {
                aPrint("Unfotunatly you wasn't correct", 20L);
                aPrint(String.format("Correct word is: %s", node.translation), 20L);
                node.mistakes++;
                node.levelOfNow--;
            }
            System.out.println();
        }
        return pack;
    }


    private boolean checkCorrectWord(String tranlation, String userEnter) {
        boolean result = true;
        char origin[] = tranlation.toCharArray();
        char userOrigin[] = userEnter.toCharArray();
        char mark[] = new char[origin.length];
        for (int i = 0; i < origin.length; i++) {
            if (i >= userOrigin.length) {
                mark[i] = '^';
                result = false;
                continue;
            }
            if (origin[i] == userOrigin[i]) {
                mark[i] = '.';
            } else {
                mark[i] = '^';
                result = false;
            }
        }
        if (!result) {
            aPrint(userOrigin, 30L);
            aPrint(mark, 30L);
        }
        return result;
    }

}
