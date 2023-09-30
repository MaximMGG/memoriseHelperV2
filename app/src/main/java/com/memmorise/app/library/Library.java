package com.memmorise.app.library;

import java.nio.file.Path;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.memmorise.app.files.Pathes;
import com.memmorise.app.tranlations.Lenguages;
import com.memmorise.app.tranlations.Translator;
import com.memmorise.app.user.User;

public class Library {

    private String libraryName;
    private Map<String, String> libraryContent;
    private Path pathToLibrary;
    private Lenguages from;
    private Lenguages to;
    private int length;
    private Translator translator;


    public Library() {
        libraryContent = new HashMap<>();
    }

    public Library(String libraryName) {
        User user = User.getInstance();
        this.libraryName = libraryName;
        libraryContent = new HashMap<>();
        setPathToLibrary(user);
    }

    public Library(String libraryName, Map<String, String> libraryContent) {
        this.libraryName = libraryName;
        this.libraryContent = libraryContent == null ? new HashMap<>() : libraryContent;
        
    }

    public int length() {
        return length;
    }

    public void setLenguages(Lenguages from, Lenguages to) {
        this.from = from;
        this.to = to;
    }

    public String[] getLenguages() {
        return translator.getFromTo().split("-");
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
        length = libraryContent.size();
    }

    public void setLibraryContent(List<String> libraryContent) {
        this.libraryContent = mapLibraryListToMap(libraryContent);
        length = libraryContent.size();

    }

    public Translator getTranslator() {
        return translator;
    }

    public void setTranslator(Translator translator) {
        this.translator = translator;
    }

    public void showLibraryContent() {
        int index = 1;
        for(Map.Entry<String, String> entry : libraryContent.entrySet()) {
            System.out.println("%d. %s - %s".formatted(index, entry.getKey(), entry.getValue()));
        }
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
