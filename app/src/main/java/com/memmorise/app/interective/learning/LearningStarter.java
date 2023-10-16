package com.memmorise.app.interective.learning;

import java.io.IOException;
import java.sql.SQLException;

import com.memmorise.app.files.DiskWorker;
import com.memmorise.app.interective.ClientTach;
import com.memmorise.app.library.Library;
import com.memmorise.app.user.User;
import com.memmorise.app.utils.ChecksUtils;

public class LearningStarter {

    private User user;
    private DiskWorker dw;
    private ClientTach ct;
    private LearnMap learnMap;


    public LearningStarter() {
        user = User.getInstance();
        dw = new DiskWorker();
        ct = ClientTach.getInstance();
    }

    public void start() throws IOException, SQLException, InterruptedException {
        user.showUserLibraries();
        System.out.println("Chose the library, or enter 0 for go back");
        int libIndex = ChecksUtils.writeInt(0, user.getLibraries().size());
        if (libIndex == 0) {
            ct.startApp();
        } else {
            learnProces(libIndex);
        }
    }

    private void learnProces(int libIndex) throws IOException {
        Library curLib = user.getLibraries().get(libIndex -1);
        learnMap = new LearnMap();
        learnMap.setMap(curLib);
    }
}
