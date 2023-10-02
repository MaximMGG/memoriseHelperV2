package com.memmorise.app.interective.learning;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Scanner;

public class FirstLevel {
    
    
    private Map<String, String> currentLib;
    private LearnMap<Integer, String> help;
    private Random random;
    private List<Integer> learnedWords;
    private List<Integer> currentFive;

    private SecondLevel secondLevel;
    private TherdLevel therdLevel;

    private Scanner scan;

    public FirstLevel(Map<String, String> currentLib) {
        this.currentLib = currentLib;
        this.learnedWords = new ArrayList<>();
        this.secondLevel = new SecondLevel(currentLib);
        this.therdLevel = new TherdLevel(currentLib);
        run();
    }


    private void run() {
        prepereHelp();
        prepereCurrentFive();
        System.out.println("Lets start to memorising word!");
        letsLearnFive();
    }


    /**
     * Main methof for learning 5 words, if all numbers in List progress will
     * be 5 or gratter, will be prepere new 5 words and recurcive back to begin of method,
     * when will be learndedWords.size() == currentLib.size() exit forom method
     */
    private void letsLearnFive() {
        Map<Integer, Integer> progress = getMapOfProgress();
        System.out.println("Here five word for memorise: ");

        while(!checkProgress(progress)) {
            for(Integer i : currentFive) {
                String word = help.getFirstVal(i);
                String translations = help.getSecondVal(i);
                System.out.println("%s -> %s".formatted(word, translations));
                System.out.println("Type enter for continue");
                scan.nextLine();
                progress.put(i, progress.get(i) + 1);
            }
            progress = secondLevel.letsLearnFive(progress, currentFive);
        }
    }


    private Map<Integer, Integer> getMapOfProgress() {
        Map<Integer, Integer> mapOfProgress = new HashMap<>();
        for (int i = 0; i < 5; i++) {
           mapOfProgress.put(currentFive.get(i), 0);
        }
        return mapOfProgress;
    }


    /**
     *Checking list of progress, if all numbers 5 or gratter return true
     * @param progress
     * @return boolean
     */
    private boolean checkProgress(Map<Integer, Integer> progress) {
        for(Map.Entry<Integer, Integer> entry : progress.entrySet()) {
            if (entry.getValue() < 5) {
                return false;
            }
        }
        return true;
    }

    /**
     *Methof for prepare 5 word for learning, @currentFive List with 5 words
     *if currentLib.size() == learnedWords.size() do not do enithing
     */
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


    /**
     *Method for add all word and tranlations in LearnMap structure of data
     */
    private void prepereHelp() {
        int index = 0;
        for (Map.Entry<String, String> entry : currentLib.entrySet()) {
            help.put(index, entry.getKey(), entry.getValue());
            index++;
        }
    }
}
