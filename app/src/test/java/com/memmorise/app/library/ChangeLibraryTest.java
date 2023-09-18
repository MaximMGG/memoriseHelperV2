package com.memmorise.app.library;

import static org.junit.jupiter.api.Assertions.*;

import java.util.HashMap;
import java.util.Map;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class ChangeLibraryTest {
    

    private static Library library;
    private ChangeLibrary changeLibrary;


    @BeforeAll
    static void createLibrary() {
        library = new Library("TestLibrary");
    }


    @BeforeEach
    void setupLibrary() {
        Map<String, String> currentLibrary = new HashMap<>();
        currentLibrary.put("cat", "кот, кошка, котэ");
        currentLibrary.put("dog", "пес, собака, псина, блохастый");
        library.setLibraryContent(currentLibrary);
        changeLibrary = new ChangeLibrary(library);
    }

    @Test
    void changeTranslationTest() {
        String newTranslations = "ко, кошк, кот";
        String word = "cat";

        changeLibrary.changeTranslation(word, newTranslations);

        assertEquals(newTranslations, library.getLibraryContent().get(word));
    }

    @Test
    void changeWordAndTranslationTest() {
        String newTranslations = "ко, кошк, кот";
        String word = "citty";
        String oldWord = "cat";

        changeLibrary.changeWordAndTranslations(oldWord, word, newTranslations);

        assertNull(library.getLibraryContent().get(oldWord));
        assertEquals(newTranslations, library.getLibraryContent().get(word));
    }

    @Test
    void removeWordTest() {
        String oldWord = "cat";

        changeLibrary.removeWord(oldWord);

        assertNull(library.getLibraryContent().get(oldWord));
    }
}
