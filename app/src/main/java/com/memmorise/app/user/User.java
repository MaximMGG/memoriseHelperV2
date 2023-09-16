package com.memmorise.app.user;

import java.util.List;

import com.memmorise.app.library.Library;

public class User {

    private String username;
    private List<Library> libraries;
    private Library currentLibrary;

    public User() {}

    public User(String username) {
        this.username = username;
    }

    public User(String username, List<Library> libraries) {
        this.username = username;
        this.libraries = libraries;
    }

    public Library getCurrentLibrary() {
        return currentLibrary;
    }

    public void setCurrentLibrary(Library currentLibrary) {
        this.currentLibrary = currentLibrary;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public List<Library> getLibraries() {
        return libraries;
    }

    public void setLibraries(List<Library> libraries) {
        this.libraries = libraries;
    }


}
