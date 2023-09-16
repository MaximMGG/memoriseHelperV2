package com.memmorise.app.library;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class LibraryTest {

    private Library library;


    @BeforeEach
    void setup() {
        library = new Library();
    }

    @Test
    void mapListToMapTest() {
        List<String> listLibrary = List.of(
                "cat : кот, котик, кошка",
                "dog : собака, пёс, цуцик"
                );
        Map<String, String> mapLibrary = new HashMap<>();
        mapLibrary.put("cat", "кот, котик, кошка");
        mapLibrary.put("dog", "собака, пёс, цуцик");

        library.setLibraryContent(listLibrary);
        Map<String, String> result = library.getLibraryContent();

        Assertions.assertThat(result).isEqualTo(mapLibrary);
    }
}
