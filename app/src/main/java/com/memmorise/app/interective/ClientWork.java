package com.memmorise.app.interective;

import java.io.IOException;

import com.memmorise.app.interective.createLibrary.CreateLibraryStarter;
import com.memmorise.app.library.Library;
import com.memmorise.app.user.User;
import com.memmorise.app.utils.ChecksUtils;

public class ClientWork {

    private CreateLibraryStarter libraryStrater;
    private User user;

    public void createLibrary() throws IOException {
        user = User.getInstance();
        Library library = new Library();
        libraryStrater = new CreateLibraryStarter();
        System.out.println("So let's start, please write name of library");
        library.setLibraryName(ChecksUtils.writeString());
        user.setCurrentLibrary(library);
        System.out.println("Please choose lenguage, from -> to");
        libraryStrater.createLibrary(library);
        System.out.println("Good choose, let's start to write some new words");
        libraryStrater.startAddingWords();
    }


    public void showAllUseLibraris() {
    }

    public void changeLibrary() {
    }

    public void startToMemorise() {
    }

    
}
