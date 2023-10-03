package com.memmorise.app.interective.learning;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Scanner;

import com.memmorise.app.interective.learning.util.LearnUtil;

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
        LearnUtil.prepareLearnMap(currentLib);
        LearnUtil.prepareFive(help, learnedWords);
        System.out.println("Lets start to memorising word!");
        letsLearnFive();
    }


    /**
     * Main methof for learning 5 words, if all numbers in List progress will
     * be 5 or gratter, will be prepere new 5 words and recurcive back to begin of method,
     * when will be learndedWords.size() == currentLib.size() exit forom method
     */
    private void letsLearnFive() {
        Map<Integer, Integer> progress = LearnUtil.getMapOfProgress(currentFive);
        System.out.println("Here five word for memorise: ");

        while(!LearnUtil.checkProgress(progress, 1)) { 
            for(Integer i : currentFive) {
                String word = help.getFirstVal(i);
                String translations = help.getSecondVal(i);
                System.out.println("%s -> %s".formatted(word, translations));
                System.out.println("Type enter for continue");
                scan.nextLine();
                progress.put(i, progress.get(i) + 1);
            }
        }
        progress = secondLevel.letsLearnFive(progress, currentFive);
    }
}
