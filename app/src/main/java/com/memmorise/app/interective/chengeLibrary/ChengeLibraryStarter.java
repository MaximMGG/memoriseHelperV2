package com.memmorise.app.interective.chengeLibrary;

import java.io.IOException;

import com.memmorise.app.files.DiskWorker;
import com.memmorise.app.library.Library;
import com.memmorise.app.user.User;
import com.memmorise.app.utils.ChecksUtils;

public class ChengeLibraryStarter {

    private User user;
    private Library library;
    private DiskWorker diskWorker;
    private boolean work = true;

    public void startChenging(int libIndex) throws IOException {
        user = User.getInstance();
        library = user.getLibraries().get(libIndex - 1);
        diskWorker = new DiskWorker();
        library = diskWorker.getLibraryFromDisk(library);
    }

    public void chengeProcess() {
        while (work) {
            library.showLibraryContent();
            System.out.println("Please chose word that you want to change or enter 0 if you want add new word");
            int index = ChecksUtils.writeInt(0, library.length());
            if (index == 0) {
                addNewWord();
            }
        }
    }

    private void addNewWord() {

    }

    private void chengeWord() {

    }
    
}
