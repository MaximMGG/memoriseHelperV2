package com.memmorise.app.interective;

import com.memmorise.app.files.FilePrepare;
import com.memmorise.app.user.User;
import com.memmorise.app.utils.ChecksUtils;

public class ClientTach {

    private User user = User.getInstance();
    private FilePrepare filePrepare;
    private CrossRoad crossRoad;
    private Thread thread1;
    
    public void startApp() {
        greetsAndSetUser();
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
}
