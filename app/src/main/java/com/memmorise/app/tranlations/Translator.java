package com.memmorise.app.tranlations;

import java.util.List;

public interface Translator {
   
    List<String> getTranclations(String word);

    String checkWord(String word);

}
