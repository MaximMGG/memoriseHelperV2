package com.memmorise.app.interective.learning;

import java.util.List;
import java.util.Map;

import com.memmorise.app.interective.learning.util.LearnUtil;

public class SecondLevel {

    private Map<String, String> currentLib;
    private List<Integer> currentFive;
    private LearnMap<Integer, String> help;

    public SecondLevel(Map<String, String> currentLibrary) {
        this.currentLib = currentLibrary;
    }
    


    public Map<Integer, Integer> letsLearnFive(Map<Integer, Integer> progress, List<Integer> five){

        for(Integer i : five) {
            String word = help.getFirstVal(i);
            String translations = help.getSecondVal(i);
            List<String> randomFour = LearnUtil.getFourRandmTranslations(help, translations);
        }



        return progress;
    };

}
