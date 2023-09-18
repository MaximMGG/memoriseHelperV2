package com.memmorise.app.library;

import java.util.Map;

public class ChangeLibrary {
    
    private Map<String, String> libraryContent;



    public ChangeLibrary(Library currentLibrary) {
        this.libraryContent = currentLibrary.getLibraryContent();
    }

    public void changeTranslation(String word, String tranlation) {
        libraryContent.put(word, tranlation);
    }

    public void changeWordAndTranslations(String oldWord, String word, String translations) {
        libraryContent.remove(oldWord);
        changeTranslation(word, translations);
    }

    public void removeWord(String word) {
        libraryContent.remove(word);
    }
}
