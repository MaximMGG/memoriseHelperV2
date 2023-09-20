package com.memmorise.app.interective.createLibrary;

import java.io.IOException;
import java.util.List;
import java.util.Map;

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
    private Map<String, String> currentLibrary;

    private Thread thread1;

    private List<String> translations;

    
    public void createLibrary(Library library) {
        this.library = library;
        clientTach = ClientTach.getInstance();
        diskWorker = new DiskWorker();
        user = User.getInstance();
        currentLibrary = library.getLibraryContent();
        library.setPathToLibrary(user);
        setLenguages();
        setTranslator();
    }


    public void startAddingWords() throws IOException {
        while (true) {
            System.out.println("Please write word or write '0' for stop writing words");
            String word = ChecksUtils.writeString();
            if (word.equals("0")) break;
            try {
                addWord(word);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        createLibraryRedirection(CrossRoad.createLibraryCrossroad());
    }

    public void createLibraryRedirection(int index) throws IOException {
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

    private void addWord(String word) throws InterruptedException {

        thread1 = new Thread(() -> {
            try {
                String checkWord = translator.checkWord(word);
                if (!checkWord.equals(word) && !checkWord.isEmpty()) {
                    System.out.println("Maybe you mean " + checkWord + " ?");
                    if (ChecksUtils.yesNo()) {
                       translations = translator.getTranclations(checkWord);
                    } else {
                       translations = translator.getTranclations(word);
                    }
                } else {
                    translations = translator.getTranclations(word);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
        thread1.start();
        InterectiveUtils.awesomePrinting("Checking your word...");
        thread1.join();
        
        boolean agreed = false;
        String concatinatedTranslations = "";

        while (!agreed) {

            System.out.println("Here is traslations of your word -> " + word);
            InterectiveUtils.printTranslations(translations);
            System.out.println("You can chose one or more translations, or write you own");
            System.out.println("For example -> 1, 3, my own translation");
            concatinatedTranslations = ChecksUtils.getUserChoose(translations);
            System.out.println("Your word -> " + word + " translations -> " + concatinatedTranslations);
            System.out.println("Write 1 if you want to save result or 2 if your want to write anather translations");
            if (ChecksUtils.yesNo()) {
                agreed = true;
            } else {
                System.out.println("Okey let's do it agane");
            }
        }
        currentLibrary.put(word, concatinatedTranslations);
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
        System.out.println("We translate from : " + from.name() + "to : " + to.name() );
    }


    private void setTranslator() {
        if(from == Lenguages.RUSSIAN && to == Lenguages.ENGLISH) {
            translator = new RuEnTranslator();
        }
    }

    private void saveLibrary() {

    }
}
