package com.memmorise.app.interective.learning;

import java.util.Random;

import com.memmorise.app.interective.learning.LearnMap.Node;
import com.memmorise.app.library.Library;

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
            int index = rand.nextInt(learnMap.size());
            if (index == pack[i] && i != 0) index--;
            if (index == pack[i] && i == 0) index++;
            Node node = learnMap.map.get(pack[i]);
            
            
        }

        return pack;
    }

    private String[] getPackRandomTranslations(int[] pack) {
        String sPack[] = new String[packOfWords];
        for (int i = 0; i < sPack.length; i++) {
            int index = rand.nextInt(learnMap.size());
            if (index == pack[i] && i != 0) index--;
            if (index == pack[i] && i == 0) index++;
        }
    }

}
