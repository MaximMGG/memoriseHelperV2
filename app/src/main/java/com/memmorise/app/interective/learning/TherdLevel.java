package com.memmorise.app.interective.learning;

import com.memmorise.app.library.Library;

public class TherdLevel implements LearnLevel{

    @Override
    public void init(Library curLibrary, LearnMap learnMap, int packOfWords) {
    }

    @Override
    public int[] learnRandomPackOfWords() {
        return null;
    }

    @Override
    public void doProcess() {
    }

    @Override
    public int[] learnPackOfWords(int[] pack) {
        for (int i = 0; i < pack.length; i++) {
            
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
        return result;
    }

}
