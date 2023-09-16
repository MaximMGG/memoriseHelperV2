package com.memmorise.app.files;

import com.memmorise.app.exception.MemoriseException;
import com.memmorise.app.library.Library;
import com.memmorise.app.user.User;

public class FilePrepare {

    private User user;
    private Library currentLibrary;
    private DiskWorker diskWorker;

    public FilePrepare(User user) {
        this.user = user;
        try {
            diskWorker.setup(user);
        } catch (MemoriseException e) {
            e.printStackTrace();
        }
    }



}
