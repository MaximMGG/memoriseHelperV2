package com.memmorise.app.library;

import java.nio.file.Path;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.memmorise.app.files.Pathes;
import com.memmorise.app.user.User;

public class Library {

    private String libraryName;
    private Map<String, String> libraryContent;
    private Path pathToLibrary;

    public Library() {}

    public Library(String libraryName) {
        this.libraryName = libraryName;
    }

    public Library(String libraryName, Map<String, String> libraryContent) {
        this.libraryName = libraryName;
        this.libraryContent = libraryContent;
    }

    public void setPathToLibrary(User user) {
        pathToLibrary =
                Path.of(Pathes.PATH_TO_USER_LIBRARIES + user.getUsername() + "Libraries/" + libraryName + ".txt");
    }

    public Path getPathToLibrary() {
        return pathToLibrary;
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

    public List<String> getLibraryContentByList() {
        return mapLibraryMapToList();
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
    
    private List<String> mapLibraryMapToList() {
        List<String> listLibraryContetnt = new ArrayList<>();
        for(Map.Entry<String, String> entry : libraryContent.entrySet()) {
            listLibraryContetnt.add(entry.getKey() + " : " + entry.getValue());
        }
        return listLibraryContetnt;
    }

}
