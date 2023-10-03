package com.memmorise.app.interective.learning;

import java.io.IOException;

import com.memmorise.app.files.DiskWorker;
import com.memmorise.app.library.Library;
import com.memmorise.app.user.User;
import com.memmorise.app.utils.ChecksUtils;

public class LearningStarter {

    private User user;
    private DiskWorker dw;


    public LearningStarter() {
        user = User.getInstance();
        dw = new DiskWorker();
    }

    public void start() throws IOException {
        user.showUserLibraries();
        System.out.println("Chose the library, or enter 0 for go back");
        int libIndex = ChecksUtils.writeInt(0, user.getLibraries().size());
        if (libIndex == 0) {
            //TODO(Maxim) go back to main menu
        } else {
            learnProces(libIndex);
        }
    }

    private void learnProces(int libIndex) throws IOException {
        Library curLib = user.getLibraries().get(libIndex -1);
        Library libraryFromDisk = dw.getLibraryFromDisk(curLib);
        LearnEngine engine = new LearnEngine(libraryFromDisk);
    }
}
