package com.memmorise.app.interective.learning;

import java.util.List;
import java.util.Map;

import com.memmorise.app.interective.learning.util.LearnUtil;
import com.memmorise.app.utils.ChecksUtils;

public class SecondLevel {

    private Map<String, String> currentLib;
    private List<Integer> currentFive;
    private LearnMap<Integer, String> help;

    public SecondLevel(Map<String, String> currentLibrary) {
        this.currentLib = currentLibrary;
    }

    public SecondLevel(Map<String, String> currentLibrary, LearnMap<Integer, String> help) {
        this.currentLib = currentLibrary;
        this.help = help;
    }
    


    public Map<Integer, Integer> letsLearnFive(Map<Integer, Integer> progress, List<Integer> five){

        while(!LearnUtil.checkProgress(progress, 2)) {

            for(Integer i : five) {

                String word = help.getFirstVal(i);
                String translation = help.getSecondVal(i);
                List<String> randFour = LearnUtil.getFourRandmTranslations(help, translation);
                
                System.out.println("Word is : " + word);
                System.out.println("Chose translations :" );
                int index = 1;
                for(String s : randFour) {
                    System.out.println("%d. %s".formatted(index, s));
                    index++;
                }
                int uc = ChecksUtils.writeInt(1, 4);

                if (translation.equals(randFour.get(uc))){
                    progress.put(i, progress.get(i) + 1);
                };
            }

        }

        return progress;
    };

}
