package com.memmorise.app.interective.learning;

import static com.memmorise.app.interective.learning.util.LearnUtil.*;

import java.util.List;
import java.util.Random;

import com.memmorise.app.interective.learning.LearnMap.Node;
import com.memmorise.app.library.Library;
import com.memmorise.app.utils.ChecksUtils;

public class SecondLevel implements LearnLevel {

    private Library curLibrary;
    private LearnMap learnMap;
    private int packOfWords;
    private Random rand;
    private FirstLevel firstLevel;
    private TherdLevel therdLevel;

    @Override
    public void init(Library curLibrary, LearnMap learnMap, int packOfWords) {
        this.curLibrary = curLibrary;
        this.learnMap = learnMap;
        this.packOfWords = packOfWords;
        firstLevel = new FirstLevel();
        therdLevel = new TherdLevel();
        rand = new Random(System.currentTimeMillis());
    }

    @Override
    public void doProcess() {

    }

    @Override
    public List<Node> learnPackOfWords(List<Node> pack) {
        for (int i = 0; i < pack.size(); i++) {
            String[] sPack = getPackRandomTranslations(pack);
            Node node = pack.get(i);

            int guesedTr = rand.nextInt(sPack.length);
            sPack[guesedTr] = String.format("%d. Tranlation - %s", guesedTr + 1, node.translation);

            aPrint(String.format("Word is \"%s\" chose tranlations", node.word), 20L);
            for (int j = 0; j < sPack.length; j++) {
                aPrint(sPack[j], 20L);
            }
            aPrint("Please write number of tranlation: ", 20L);
            if (ChecksUtils.writeInt(0, sPack.length) == guesedTr + 1) {
                aPrint("Nice, good one.", 20L);
                node.levelOfNow += node.levelOfNow >= 3 ? 0 : 1;
                node.mistakes = 0;
            } else {
                aPrint("Unfotunatly not, try agane", 20L);
                i--;
                node.mistakes++;
                if (node.mistakes >= 2) {
                    node.levelOfNow--;
                }
            }
        }
        return pack;
    }

    private String[] getPackRandomTranslations(List<Node> pack) {
        String sPack[] = new String[packOfWords];
        for (int i = 0; i < sPack.length; i++) {
            int index = rand.nextInt(learnMap.size());
            Node tmp = learnMap.map.get(index);
            if (pack.contains(tmp)) {
                i--;
                continue;
            }
            if (!arrayContains(sPack, tmp.translation))
                sPack[i] = String.format("%d. Tranlation - %s", i + 1, tmp.translation);
            else
                i--;
        }
        return sPack;
    }
}
