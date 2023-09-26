package com.memmorise.app.interective;

import java.io.IOException;
import java.sql.SQLException;

import com.memmorise.app.interective.chengeLibrary.ChengeLibraryStarter;
import com.memmorise.app.interective.createLibrary.CreateLibraryStarter;
import com.memmorise.app.library.Library;
import com.memmorise.app.user.User;
import com.memmorise.app.utils.ChecksUtils;

public class ClientWork {

    private CreateLibraryStarter libraryStrater;
    private ChengeLibraryStarter chengeLibraryStarter;
    private User user;

    public void createLibrary() throws IOException, SQLException {
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
        user = User.getInstance();
        Library library = new Library();
        chengeLibraryStarter = new ChengeLibraryStarter();
        user.showUserLibraries();
        System.out.println("Please chose library.");
        int libIndex = InterectiveUtils.getUserShoseLibrary(user.getLibraries().size());
        chengeLibraryStarter.startChenging(libIndex);
    }

    public void changeLibrary() {
    }

    public void startToMemorise() {
    }

    
}
