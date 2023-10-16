package com.memmorise.app.interective.learning;

import java.util.Random;

import com.memmorise.app.interective.learning.LearnMap.Node;
import com.memmorise.app.library.Library;
import com.memmorise.app.utils.ChecksUtils;

public class SecondLevel implements LearnLevel {

    private Library curLibrary;
    private LearnMap learnMap;
    private int packOfWords;
    private Random rand;
    private FirstLevel secondLevel;
    private TherdLevel therdLevel;

    @Override
    public void init(Library curLibrary, LearnMap learnMap, int packOfWords) {
        this.curLibrary = curLibrary;
        this.learnMap = learnMap;
        this.packOfWords = packOfWords;
        rand = new Random(System.currentTimeMillis());
    }

    @Override
    public void doProcess() {
    }

    @Override
    public int[] learnRandomPackOfWords() {
        return null;
    }

    @Override
    public int[] learnPackOfWords(int[] pack) {
        for (int i = 0; i < pack.length; i++) {
            String[] sPack = getPackRandomTranslations(pack);

            int guesedTr = rand.nextInt(sPack.length);
            Node node = learnMap.map.get(pack[i]);
            sPack[guesedTr] = String.format("%d. Tranlation - %s", guesedTr + 1, node.translation);

            System.out.printf("Word is %s chose tranlations\n", node.word);
            for (int j = 0; j < sPack.length; j++) {
                System.out.println(sPack[i]);
            }
            System.out.printf("Please write number of tranlation: ");
            if (ChecksUtils.writeInt(0, sPack.length) == guesedTr - 1) {
                System.out.println("Nice, good one.");
                node.levelOfNow++;
                node.mistakes = 0;
            } else {
                System.out.println("Unfotunatly not, try agane");
                node.mistakes++;
                if (node.mistakes >= 2) {
                    node.levelOfNow--;
                }
                i--;
            }
        }

        return pack;
    }

    private String[] getPackRandomTranslations(int[] pack) {
        String sPack[] = new String[packOfWords];
        for (int i = 0; i < sPack.length; i++) {
            int index = rand.nextInt(learnMap.size());
            if (index == pack[i] && i != 0) index--;
            if (index == pack[i] && i == 0) index++;
            sPack[i] = String.format("%d. Tranlation - %s", i + 1, learnMap.map.get(index).translation);
        }
        return sPack;
    }
}
