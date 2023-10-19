package com.memmorise.app.interective.learning;

import java.util.Random;

import com.memmorise.app.interective.learning.LearnMap.Node;
import com.memmorise.app.library.Library;
import com.memmorise.app.utils.ChecksUtils;
import com.memmorise.app.utils.LangUtils;

public class TherdLevel implements LearnLevel{

    private Library curLibrary;
    private LearnMap learnMap;
    private int packOfWords;
    private Random rand;
    private FirstLevel secondLevel;
    private TherdLevel therdLevel;

    @Override
    public void init(Library curLibrary, LearnMap learnMap, int packOfWords) {
    }

    @Override
    public int[] learnRandomPackOfWords() {
        return null;
    }

    @Override
    public void doProcess() {
    }

    @Override
    public int[] learnPackOfWords(int[] pack) {
        for (int i = 0; i < pack.length; i++) {
            Node node = learnMap.map.get(i);
            System.out.printf("Here the word %s, please enter tranlation\n", node.word);
            String userEnter = ChecksUtils.writeString();
            if (checkCorrectWord(node.translation, userEnter)) {
                node.levelOfNow++;
                node.mistakes = 0;
            } else {
                node.mistakes++;
            }
        }

        return pack;
    }


    private boolean checkCorrectWord(String tranlation, String userEnter) {
        boolean result = true;
        char origin[] = tranlation.toCharArray();
        char userOrigin[] = userEnter.toCharArray();
        char mark[] = new char[origin.length];

        for (int i = 0; i < mark.length; i++) {
            if (origin[i] != userOrigin[i]) {
                result = false;
                mark[i] = '^';
            } else
                mark[i] = ' ';
        }

        String markS = LangUtils.mapCharArrToString(mark);
        System.out.println(userEnter);
        System.out.println(markS);

        return result;
    }

}
