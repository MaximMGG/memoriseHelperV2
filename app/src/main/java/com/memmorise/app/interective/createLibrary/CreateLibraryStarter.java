package com.memmorise.app.interective.createLibrary;

import com.memmorise.app.files.DiskWorker;
import com.memmorise.app.interective.ClientTach;
import com.memmorise.app.interective.ClientWordBufer;
import com.memmorise.app.interective.CrossRoad;
import com.memmorise.app.interective.InterectiveUtils;
import com.memmorise.app.library.Library;
import com.memmorise.app.tranlations.Lenguages;
import com.memmorise.app.tranlations.RuEnTranslator;
import com.memmorise.app.tranlations.Translator;
import com.memmorise.app.user.User;
import com.memmorise.app.utils.ChecksUtils;

public class CreateLibraryStarter {
    
    private Translator translator;
    private Lenguages from;
    private Lenguages to;
    private ClientTach clientTach;
    private Library library;
    private DiskWorker diskWorker;
    private User user;

    
    public void createLibrary(Library library) {
        this.library = library;
        clientTach = ClientTach.getInstance();
        diskWorker = new DiskWorker();
        user = User.getInstance();
        setLenguages();
        setTranslator();
    }


    public void startAddingWords() {
        System.out.println("Please write word or write '0' for stop writing words");
        String word = ChecksUtils.writeString();
        if (word.equals("0")) {
            createLibraryRedirection(CrossRoad.createLibraryCrossroad());
        }
    }

    public void createLibraryRedirection(int index) {
        switch (index) {
            case 1 -> {
                diskWorker.saveLibraryOnDisk(library);
                clientTach.startApp();
            }
            case 2 -> {
                library = null;
                clientTach.startApp();
            }
            case 3 -> {
                startAddingWords();
            }
            case 4 -> {
                diskWorker.saveLibraryOnDisk(library);
                System.out.println(ClientWordBufer.SEY_GOODBYE_TO_USER.formatted(user.getUsername()));
            }
            case 5 -> {
                System.out.println(ClientWordBufer.SEY_GOODBYE_TO_USER.formatted(user.getUsername()));
            }
        }
    }

    private void addWord(String word) {

    }

    private void setLenguages() {
        System.out.println("Please write from lenguage ");
        Lenguages.printAllLenguaes();
        from = InterectiveUtils.getChoosenLenguage();
        System.out.println("Please write to lenguage ");
        Lenguages.printAllLenguaes();
        to = InterectiveUtils.getChoosenLenguage();
        if (from == to) {
            System.out.println("Sorry, we can't tranlate from the same laguage, please write agane");
        }
    }


    private void setTranslator() {
        if(from == Lenguages.RUSSIAN && to == Lenguages.ENGLISH) {
            translator = new RuEnTranslator();
        }
    }

    private void saveLibrary() {

    }
}
