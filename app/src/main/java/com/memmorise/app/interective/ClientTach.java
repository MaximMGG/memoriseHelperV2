package com.memmorise.app.interective;

import com.memmorise.app.files.FilePrepare;
import com.memmorise.app.user.User;
import com.memmorise.app.utils.ChecksUtils;

public class ClientTach {

    private User user = User.getInstance();
    private FilePrepare filePrepare;
    private CrossRoad crossRoad;
    private Thread thread1;
    private ClientWork clientWork;
    
    public void startApp() {
        clientWork = new ClientWork();
        greetsAndSetUser();
        redirectionAfterMainCrossroad(crossRoad.mainCrossroad());
    }


    private void greetsAndSetUser() {
        System.out.println(ClientWordBufer.SEY_HELLO_TO_USER);
        user.setUsername(ChecksUtils.writeString());
        thread1 = new Thread(() -> {filePrepare = new FilePrepare();});
        thread1.start();
        System.out.println("Hello diar " + user.getUsername() + "greetings you in Memorise Helper");
        System.out.println("Here you can create you own libraries with word and their translations, you can memorise words from your existing libraries");
        System.out.println("You can change words in your libraries or remove some translations/words");
        System.out.println("What do you prefer?");
    }

    private void redirectionAfterMainCrossroad(int userChoose) {
        switch (userChoose) {
            case 1 -> {clientWork.createLibrary();}
            case 2 -> {clientWork.showAllUseLibraris();}
            case 3 -> {clientWork.createLibrary();}
            case 4 -> {clientWork.startToMemorise();}
        }
    }

}
