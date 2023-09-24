package com.memmorise.app.interective;

import java.io.IOException;
import java.sql.SQLException;

import com.memmorise.app.files.FilePrepare;
import com.memmorise.app.user.User;
import com.memmorise.app.utils.ChecksUtils;

public class ClientTach {

    private User user;
    private FilePrepare filePrepare;
    private Thread thread1;
    private ClientWork clientWork;
    private boolean firstVisit = true;
    private static final ClientTach instance = new ClientTach();

    private ClientTach(){}

    public static ClientTach getInstance() {
        return instance;
    }
    
    public void startApp() throws SQLException {
        clientWork = new ClientWork();
        if (firstVisit) {
            greetsAndSetUser();
            firstVisit = false;
        }
        try {
            redirectionAfterMainCrossroad(CrossRoad.mainCrossroad());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    private void greetsAndSetUser() {
        System.out.println(ClientWordBufer.SEY_HELLO_TO_USER);
        user = User.getInstance();
        user.setUsername(ChecksUtils.writeString());
        thread1 = new Thread(() -> {filePrepare = new FilePrepare();});
        thread1.start();
        System.out.println("Hello diar " + user.getUsername() + " greetings you in Memorise Helper");
        System.out.println("Here you can create you own libraries with word and their translations.\nYou can memorise words from your existing libraries");
        System.out.println("You can change words in your libraries or remove some translations/words");
        System.out.println("What do you prefer?");
    }

    private void redirectionAfterMainCrossroad(int userChoose) throws IOException, SQLException {
        switch (userChoose) {
            case 1 -> {clientWork.createLibrary();}
            case 2 -> {clientWork.showAllUseLibraris();}
            case 3 -> {clientWork.changeLibrary();}
            case 4 -> {clientWork.startToMemorise();}
            case 5 -> {ClientWordBufer.SEY_GOODBYE_TO_USER.formatted(user.getUsername());}
        }
    }

}
