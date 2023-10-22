package com.memmorise.app.interective.learning;

import java.util.List;

import com.memmorise.app.interective.learning.LearnMap.Node;
import com.memmorise.app.library.Library;

public interface LearnLevel {

    void init(Library curLibrary, LearnMap learnMap, int packOfWords);

    List<Node> learnPackOfWords(List<Node> pack);

    void doProcess();
}
