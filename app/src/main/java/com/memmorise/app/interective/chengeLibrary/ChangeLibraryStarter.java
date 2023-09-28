
package com.memmorise.app.interective.chengeLibrary;
import java.io.IOException;
import java.sql.SQLException;
import java.util.Map;

import com.memmorise.app.files.DiskWorker;
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

    public void startChenging(int libIndex) throws IOException {
        user = User.getInstance();
        library = user.getLibraries().get(libIndex - 1);
        diskWorker = new DiskWorker();
        library = diskWorker.getLibraryFromDisk(library);
        addWordWorker = new AddWordWorker(library.getTranslator(), library);
        currentLibrary = library.getLibraryContent();
    }

    public void chengeProcess() throws InterruptedException, SQLException {
        while (work) {
            library.showLibraryContent();
            System.out.println("Please chose word that you want to change or enter 0 if you want add new word");
            int index = ChecksUtils.writeInt(0, library.length());
            if (index == 0) {
                addNewWord();
            } else {
                chageWord(getWordByNumber(index));
            }
            System.out.println("Continue?");
            if (ChecksUtils.yesNo()) {

            } else {
                work = false;
            }
        }
        CrossRoad.changeLibraryCrossroad();
    }

    
    private void addNewWord() throws InterruptedException, SQLException {
        System.out.println("Please write word");
        String word = ChecksUtils.writeString();
        String[] wordAndTranslation = addWordWorker.addWord(word);
        currentLibrary.put(wordAndTranslation[0], wordAndTranslation[1]);
    }
    
    private void chageWord(String[] wordAndTranslation) throws InterruptedException, SQLException {
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
