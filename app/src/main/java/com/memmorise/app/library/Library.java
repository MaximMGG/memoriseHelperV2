package com.memmorise.app.library;

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
}
