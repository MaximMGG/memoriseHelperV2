package com.memmorise.app.interective.learning;

import com.memmorise.app.library.Library;

public interface LearnLevel {

    void init(Library curLibrary, LearnMap learnMap, int packOfWords);

    int[] learnRandomPackOfWords();

    int[] learnPackOfWords(int[] pack);

    void doProcess();
}
