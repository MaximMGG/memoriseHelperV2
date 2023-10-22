package com.memmorise.app.interective.learning;

import static com.memmorise.app.interective.learning.util.LearnUtil.*;

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
            aPrint(String.format("%s - %s", node.word, node.translation), 20L);
            enter();
            node.levelOfNow++;
        }
        return pack;
    }

    @Override
    public void doProcess() {
        aPrint("Memorise this word", 30L);
        List<Node> pack;

        while((pack = learnMap.getNextPackOfWords(packOfWords)).size() > 0){
            pack = learnPackOfWords(pack);
            
            List<Node> tmp;
            
            waitM(2000);
            aPrint("Going to the next level", 30L);
            while ((tmp = LearnUtil.checkLevelOfNow(pack, 3, learnMap)).size() > 0) {
                pack = secondLevel.learnPackOfWords(tmp);
            }


            aPrint("Going to the next level", 30L);
            while ((tmp = LearnUtil.checkLevelOfNow(pack, 5, learnMap)).size() > 0) {
                pack = therdLevel.learnPackOfWords(tmp);
            }

            aPrint(String.format("Well done, going to the next %d words", packOfWords), 20L);
        }
    }
}
