package com.memmorise.app.files;

import com.memmorise.app.library.Library;
import com.memmorise.app.user.User;

public class FilePrepare {


    private static final String PATH_TO_USER_CONFIG = "resources/userconfig.txt";
    private static final String PATH_TO_USER_LIBRARIES = "resources/libraries/";
    private static final String PATH_TO_RESOURCES = "resources/";

    
    private User user;
    private Library currentLibrary;
    private DiskWorker diskWorker;

    public FilePrepare(User user) {
        this.user = user;
    }

}
