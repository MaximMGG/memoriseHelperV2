package com.memmorise.app.library;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Library {

    private String libraryName;
    private Map<String, String> libraryContent;

    public Library() {}

    public Library(String libraryName, Map<String, String> libraryContent) {
        this.libraryName = libraryName;
        this.libraryContent = libraryContent;
    }

    public String getLibraryName() {
        return libraryName;
    }

    public void setLibraryName(String libraryName) {
        this.libraryName = libraryName;
    }

    public Map<String, String> getLibraryContent() {
        return libraryContent;
    }

    public void setLibraryContent(Map<String, String> libraryContent) {
        this.libraryContent = libraryContent;
    }

    public void setLibraryContent(List<String> libraryContent) {
        this.libraryContent = mapLibraryListToMap(libraryContent);

    }

    private Map<String, String> mapLibraryListToMap(List<String> libraryListContent) {
        final Map<String, String> libraryMapContent = new HashMap<>();

        libraryListContent.stream()
                            .map(x -> x.split(" : "))
                            .forEach(x -> libraryMapContent.put(x[0], x[1]));

        return libraryMapContent;
    }
}
