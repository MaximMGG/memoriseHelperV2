package com.memmorise.app.interective.chengeLibrary;

import com.memmorise.app.library.Library;
import com.memmorise.app.user.User;

public class ChengeLibraryStarter {

    private User user;
    private Library library;

    public void startChenging(int libIndex) {
        user = User.getInstance();
        library = user.getLibraries().get(libIndex - 1);

    }
    
}
