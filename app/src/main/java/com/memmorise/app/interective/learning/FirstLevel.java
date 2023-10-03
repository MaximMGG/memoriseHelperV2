package com.memmorise.app.interective.learning;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.memmorise.app.interective.learning.util.LearnUtil;
import com.memmorise.app.utils.ChecksUtils;

public class FirstLevel {
    
    
    private Map<String, String> currentLib;
    private LearnMap<Integer, String> help;
    private List<Integer> learnedWords;
    private List<Integer> currentFive;

    private SecondLevel secondLevel;
    private TherdLevel therdLevel;

    public FirstLevel(Map<String, String> currentLib) {
        this.currentLib = currentLib;
        this.learnedWords = new ArrayList<>();
        run();
    }
    
    
    private void run() {
        help = LearnUtil.prepareLearnMap(currentLib);
        this.secondLevel = new SecondLevel(currentLib, help);
        this.therdLevel = new TherdLevel(currentLib);
        currentFive = LearnUtil.prepareFive(help, learnedWords);
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
                ChecksUtils.justTypeEnter();
                progress.put(i, progress.get(i) + 1);
            }
        }
        progress = secondLevel.letsLearnFive(progress, currentFive);
    }
}
