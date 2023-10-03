package com.memmorise.app.interective.learning.util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import com.memmorise.app.interective.learning.LearnMap;

public class LearnUtil {

    private static Random rand;

    public static List<Integer> prepareFive(LearnMap<Integer, String> help, List<Integer> learnedWords) {

        List<Integer> currentFive = new ArrayList<>();
        int buf;

        for (int i = 0; i < 5; i++) {
           buf = rand.nextInt(help.length() + 1);
           if (!learnedWords.contains(buf)) {
                currentFive.add(buf);
           } else {
               i--;
           }

           if (help.length() == learnedWords.size()) {
               return currentFive;
           }
        }
        return currentFive;
    }

    public static LearnMap<Integer, String> prepareLearnMap(Map<String, String> currentLib) {
        LearnMap<Integer, String> help = new LearnMap<>();
        int index = 0;
        for (Map.Entry<String, String> entry : currentLib.entrySet()) {
            help.put(index, entry.getKey(), entry.getValue());
        }
        return help;
    }

    /*
     * Checking progress of learngin, 
     * @param level : if 1 -> First level @return true if entry.getValue >= 1
     *              if 2 -> SecondLevel @return true if entry.getValue >= 3
     *              if 3 -> TherdLevel @return true if entry.getValue >= 5
     */

    public static boolean checkProgress(Map<Integer, Integer> progress, int level) {


        switch (level) {
            case 1 -> {
                for(Map.Entry<Integer, Integer> entry : progress.entrySet()) {
                    if (entry.getValue() < 1) {
                        return false;
                    }
                }
                return true;
            } 
            case 2 -> {
                for(Map.Entry<Integer, Integer> entry : progress.entrySet()) {
                    if (entry.getValue() < 3) {
                        return false;
                    }
                }
                return true;

            }
            case 3 -> {
                for(Map.Entry<Integer, Integer> entry : progress.entrySet()) {
                    if (entry.getValue() < 5) {
                        return false;
                    }
                }
                return true;

            }
        }
        return true;
    }



    public static Map<Integer, Integer> getMapOfProgress(List<Integer> currentFive){
        Map<Integer, Integer> mapOfProgress = new HashMap<>();
        for (int i = 0; i < 5; i++) {
           mapOfProgress.put(currentFive.get(i), 0);
        }
        return mapOfProgress;
    }
}
