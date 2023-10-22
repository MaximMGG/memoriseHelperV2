package com.memmorise.app.interective.learning;

import java.util.List;
import java.util.Random;

import com.memmorise.app.interective.learning.LearnMap.Node;
import com.memmorise.app.interective.learning.util.LearnUtil;
import com.memmorise.app.library.Library;

public class FirstLevel implements LearnLevel {

    private Library curLibrary;
    private LearnMap learnMap;
    private int packOfWords;
    private Random rand;
    private SecondLevel secondLevel;
    private TherdLevel therdLevel;

    @Override
    public void init(Library curLibrary, LearnMap learnMap, int packOfWords) {
        this.curLibrary = curLibrary;
        this.learnMap = learnMap;
        this.packOfWords = packOfWords;
        rand = new Random(System.currentTimeMillis());
        secondLevel = new SecondLevel();
        secondLevel.init(curLibrary, learnMap, packOfWords);
        therdLevel = new TherdLevel();
        therdLevel.init(curLibrary, learnMap, packOfWords);
    }


    @Override
    public List<Node> learnPackOfWords(List<Node> pack) {
        for (int i = 0; i < pack.size(); i++) {
            Node node = pack.get(i);
            System.out.printf("%s - %s\n", node.word, node.translation);
            node.levelOfNow++;
        }
        return pack;
    }

    @Override
    public void doProcess() {
        System.out.println("Memorise this word");
        List<Node> pack;

        while((pack = learnMap.getNextPackOfWords(packOfWords)).size() > 0){
            pack = learnPackOfWords(pack);
            pack = secondLevel.learnPackOfWords(pack);

            List<Node> tmp;

            while ((tmp = LearnUtil.checkLevelOfNow(pack, 3, learnMap)).size() > 0) {
                secondLevel.learnPackOfWords(tmp);
            }

            pack = therdLevel.learnPackOfWords(pack);
            while ((tmp = LearnUtil.checkLevelOfNow(pack, 5, learnMap)).size() > 0) {
                secondLevel.learnPackOfWords(tmp);
            }

        }
    }
}
