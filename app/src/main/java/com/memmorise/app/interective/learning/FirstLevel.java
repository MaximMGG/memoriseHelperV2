package com.memmorise.app.interective.learning;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Random;

public class FirstLevel {
    
    
    private Map<String, String> currentLib;
    private LearnMap<Integer, String> help;
    private Random random;
    private List<Integer> learnedWords;
    private List<Integer> currentFive;

    public FirstLevel(Map<String, String> currentLib) {
        this.currentLib = currentLib;
        this.learnedWords = new ArrayList<>();
        run();
    }


    private void run() {
        prepereHelp();
        prepereCurrentFive();
        letsLearnFive();
    }

    private void letsLearnFive() {
        List<Integer> progress = List.of(0, 0, 0, 0, 0);

        while(!checkProgress(progress)) {

        }
    }

    private boolean checkProgress(List<Integer> progress) {
        for(Integer i : progress) {
            if (i < 5) {
                return false;
            }
        }
        return true;
    }


    private void prepereCurrentFive() {
        currentFive = new ArrayList<>();
        int index;
        for (int i = 0; i < 5; i++) {
            index = random.nextInt(currentLib.size());
            if (!learnedWords.contains(index)) {
                learnedWords.add(index);
                currentFive.add(index);
            } else {
                i = currentLib.size() == learnedWords.size() ? i : i - 1;
            }
        }
    }


    private void prepereHelp() {
        int index = 0;
        for (Map.Entry<String, String> entry : currentLib.entrySet()) {
            help.put(index, entry.getKey(), entry.getValue());
            index++;
        }
    }
}
