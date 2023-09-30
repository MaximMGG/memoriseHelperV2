
package com.memmorise.app.interective.chengeLibrary;
import java.io.IOException;
import java.sql.SQLException;
import java.util.Map;

import com.memmorise.app.files.DiskWorker;
import com.memmorise.app.interective.ClientTach;
import com.memmorise.app.interective.ClientWordBufer;
import com.memmorise.app.interective.CrossRoad;
import com.memmorise.app.interective.logic.AddWordWorker;
import com.memmorise.app.library.Library;
import com.memmorise.app.user.User;
import com.memmorise.app.utils.ChecksUtils;

public class ChangeLibraryStarter {

    private User user;
    private Library library;
    private DiskWorker diskWorker;
    private boolean work = true;
    private AddWordWorker addWordWorker;
    private Map<String, String> currentLibrary;
    private ClientTach clientTach;

    public void startChenging(int libIndex) throws IOException, InterruptedException, SQLException {
        user = User.getInstance();
        library = user.getLibraries().get(libIndex - 1);
        diskWorker = new DiskWorker();
        library = diskWorker.getLibraryFromDisk(library);
        addWordWorker = new AddWordWorker(library.getTranslator(), library);
        currentLibrary = library.getLibraryContent();
        clientTach = ClientTach.getInstance();
        changeProcess();
    }

    public void changeProcess() throws InterruptedException, SQLException, IOException {
        while (work) {
            library.showLibraryContent();
            System.out.println("Please chose word that you want to change or enter 0 if you want add new word");
            int index = ChecksUtils.writeInt(0, library.length());
            if (index == 0) {
                addNewWord();
            } else {
                changeWord(getWordByNumber(index));
            }
            System.out.println("Continue?");
            if (ChecksUtils.yesNo()) {

            } else {
                work = false;
            }
        }
        library.setLibraryContent(currentLibrary);
        changeLibraryRedirection(CrossRoad.changeLibraryCrossroad());
    }

    private void changeLibraryRedirection(int red) throws IOException, SQLException, InterruptedException {
        switch (red) {
            case 1 -> {
                diskWorker.saveLibraryOnDisk(library);
                clientTach.startApp();
            }
            case 2 -> {
                library = null;
                clientTach.startApp();
            }
            case 3 -> {
                changeProcess();
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

    
    private void addNewWord() throws InterruptedException, SQLException {
        System.out.println("Please write word");
        String word = ChecksUtils.writeString();
        String[] wordAndTranslation = addWordWorker.addWord(word);
        currentLibrary.put(wordAndTranslation[0], wordAndTranslation[1]);
    }
    
    private void changeWord(String[] wordAndTranslation) throws InterruptedException, SQLException {
        System.out.println("Word is : %d".formatted(wordAndTranslation[0]));
        System.out.println("Translations are : %d".formatted(wordAndTranslation[1]));
        System.out.println("1. Change translations\n2.Delete word");
        if (ChecksUtils.writeInt(1, 2) == 1) {
            String[] changedTrans = addWordWorker.addWord(wordAndTranslation[0]);
            currentLibrary.put(changedTrans[0], changedTrans[1]);
        } else {
            currentLibrary.remove(wordAndTranslation[0]);
        }
    }

    private String[] getWordByNumber(int index) {
        int i = 1;

        for(Map.Entry<String, String> entry : currentLibrary.entrySet()) {
            if (i == index) {
                return new String[]{entry.getKey(), entry.getValue()};
            }
            i++;
        }
        return null;
    }
    
}
