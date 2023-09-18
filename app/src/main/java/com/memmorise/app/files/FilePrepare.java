package com.memmorise.app.files;

import com.memmorise.app.library.Library;
import com.memmorise.app.user.User;

public class FilePrepare {

    private User user;
    private Library currentLibrary;
    private DiskWorker diskWorker;

    public FilePrepare() {
        this.user = User.getInstance();
        diskWorker = new DiskWorker();
        try {
            diskWorker.setup();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void setCurrntLibrary(Library library) {
        this.currentLibrary = library;
    }




}
