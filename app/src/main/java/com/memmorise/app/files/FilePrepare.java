package com.memmorise.app.files;

import com.memmorise.app.library.Library;
import com.memmorise.app.user.User;

public class FilePrepare {

    private User user;
    private Library currentLibrary;
    private DiskWorker diskWorker;

    public FilePrepare(User user) {
        this.user = user;
        diskWorker = new DiskWorker(user);
        try {
            diskWorker.setup();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void setCurrntLibrary(Library library) {
        this.currentLibrary = library;
    }

    public 



}
