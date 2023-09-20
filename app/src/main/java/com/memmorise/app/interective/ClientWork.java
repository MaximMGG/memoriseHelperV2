package com.memmorise.app.interective;

import com.memmorise.app.interective.createLibrary.CreateLibraryStarter;
import com.memmorise.app.library.Library;
import com.memmorise.app.utils.ChecksUtils;

public class ClientWork {

    private CreateLibraryStarter libraryStrater;

    public void createLibrary() {
        Library library = new Library();
        System.out.println("So let's start, please write name of library");
        library.setLibraryName(ChecksUtils.writeString());
        System.out.println("Please choose lenguage, from -> to");
        System.out.println("Good choose, let's start to write some new words");
        System.out.println();
    }


    public void showAllUseLibraris() {
    }

    public void changeLibrary() {
    }

    public void startToMemorise() {
    }

    
}
