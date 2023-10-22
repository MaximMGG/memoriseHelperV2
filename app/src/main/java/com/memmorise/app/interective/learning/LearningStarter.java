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
    private FirstLevel firstLevel;
    private SecondLevel secondLevel;
    private TherdLevel therdLevel;


    public LearningStarter() {
        user = User.getInstance();
        dw = new DiskWorker();
        firstLevel = new FirstLevel();
        secondLevel = new SecondLevel();
        therdLevel = new TherdLevel();
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
        System.out.println("Set level of novelage in this library");
        System.out.println("Where 0 -> is you don't now this words");
        System.out.println("1 -> you actually now this words but not good");
        System.out.println("2 -> you now good this words");
        int level = ChecksUtils.writeInt(0, 2);
        System.out.println("Ok, and set please size of pack of words then you will learn for each step");
        int packSize = ChecksUtils.writeInt(1, 30);
        System.out.println("Ok, good");
        learnMap.setMap(curLib, level);
        switch (level) {
            case 0 -> {
                firstLevel.init(curLib, learnMap, packSize);
                firstLevel.doProcess();
            }
            case 1 -> {
                secondLevel.init(curLib, learnMap, packSize);
                secondLevel.doProcess();
            }
            case 2 -> {
                therdLevel.init(curLib, learnMap, packSize);
                therdLevel.doProcess();
            }
        }
    }
}
