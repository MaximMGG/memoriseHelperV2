package com.memmorise.app.interective.learning;

import java.util.Random;

import com.memmorise.app.interective.learning.LearnMap.Node;
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
    }

    @Override
    public int[] learnRandomPackOfWords() {
        int[] pack = new int[packOfWords];

        for (int i = 0; i < packOfWords; i++) {
            int count = rand.nextInt(learnMap.size()); 
            pack[i] = count;
            Node node = learnMap.map.get(count);
            if (node.levelOfNow == 0) {
                System.out.printf("%s - %s\n", node.word, node.translation);
                node.levelOfNow++;
            }
        } 
        return pack;
    }

    @Override
    public int[] learnPackOfWords(int[] pack) {
        for (int i = 0; i < pack.length; i++) {
            Node node = learnMap.map.get(pack[i]); 
            System.out.printf("%s - %s\n", node.word, node.translation);
            node.levelOfNow++;
        }
        return null;
    }

    @Override
    public void doProcess() {
        System.out.println("Memorise this word");
        int[] pack = learnRandomPackOfWords();
    }

}
