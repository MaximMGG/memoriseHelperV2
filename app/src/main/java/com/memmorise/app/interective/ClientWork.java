package com.memmorise.app.interective;

import java.io.IOException;
import java.sql.SQLException;

import com.memmorise.app.interective.chengeLibrary.ChangeLibraryStarter;
import com.memmorise.app.interective.createLibrary.CreateLibraryStarter;
import com.memmorise.app.library.Library;
import com.memmorise.app.user.User;
import com.memmorise.app.utils.ChecksUtils;

public class ClientWork {

    private CreateLibraryStarter libraryStrater;
    private ChangeLibraryStarter chengeLibraryStarter;
    private User user;
    private ClientTach clientTach;

    public ClientWork() {
        user = User.getInstance();
        clientTach = ClientTach.getInstance();
    }

    public void createLibrary() throws IOException, SQLException {
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


    public void showAllUseLibraris() throws IOException, SQLException {
        user = User.getInstance();
        user.showUserLibraries();
        System.out.println("1. Back\n2. Exit");
        switch (ChecksUtils.writeInt(1, 2)) {
            case 1 ->  {clientTach.startApp();}
            case 2 -> {System.out.println(ClientWordBufer.SEY_GOODBYE_TO_USER.formatted(user.getUsername()));}
        }
    }

    public void changeLibrary() throws IOException {
        Library library = new Library();
        chengeLibraryStarter = new ChangeLibraryStarter();
        user.showUserLibraries();
        System.out.println("Please write number of library that you want to change");
        chengeLibraryStarter.startChenging(InterectiveUtils.getUserShoseLibrary(user.getLibraries().size()));
    }

    public void startToMemorise() {
    }

    
}
