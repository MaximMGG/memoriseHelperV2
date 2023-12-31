package com.memmorise.app.tranlations;

import java.io.IOException;
import java.util.List;

public interface Translator {

    String getFromTo();

    List<String> getTranclations(String word) throws IOException;

    String checkWord(String word) throws IOException;

}
