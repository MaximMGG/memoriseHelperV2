package com.memmorise.app.interective.learning;

import java.util.List;
import java.util.Random;

import com.memmorise.app.interective.learning.LearnMap.Node;
import com.memmorise.app.library.Library;
import com.memmorise.app.utils.ChecksUtils;
public class TherdLevel implements LearnLevel{

    private Library curLibrary;
    private LearnMap learnMap;
    private int packOfWords;
    private Random rand;
    private SecondLevel secondLevel;
    private FirstLevel firstLevel;

    @Override
    public void init(Library curLibrary, LearnMap learnMap, int packOfWords) {
        this.curLibrary = curLibrary;
        this.learnMap = learnMap;
        this.packOfWords = packOfWords;
        secondLevel = new SecondLevel();
        firstLevel = new FirstLevel();
        rand = new Random(System.currentTimeMillis());
    }

    @Override
    public void doProcess() {
    }

    @Override
    public List<Node> learnPackOfWords(List<Node> pack) {
        for (int i = 0; i < pack.size(); i++) {
            Node node = pack.get(i);
            System.out.printf("Here is word %s, please write tranlation.\n", node.word);
            String userTranslation = ChecksUtils.writeString();
            if (checkCorrectWord(node.word, userTranslation)) {
                node.levelOfNow += node.levelOfNow > 5 ? 0 : 1;
                node.mistakes = 0;
            }
            else {
                node.mistakes++;
                node.levelOfNow--;
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
            mark[i] = origin[i] == userOrigin[i] ? '*' : '^';
            result = false;
        }
        if (!result) {
            for (int i = 0; i < mark.length; i++) {
                System.out.print(userOrigin[i]);
            }
            for (int i = 0; i < mark.length; i++) {
                System.out.print(mark[i]);
            }
        }


        return result;
    }

}
