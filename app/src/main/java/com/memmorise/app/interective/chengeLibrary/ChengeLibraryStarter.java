package com.memmorise.app.interective.chengeLibrary;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Map;

import com.memmorise.app.files.DiskWorker;
import com.memmorise.app.interective.logic.AddWordWorker;
import com.memmorise.app.library.Library;
import com.memmorise.app.user.User;
import com.memmorise.app.utils.ChecksUtils;

public class ChengeLibraryStarter {

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
            }
        }
    }

    private void addNewWord() throws InterruptedException, SQLException {
        System.out.println("Please write word");
        String word = ChecksUtils.writeString();
        String[] wordAndTranslation = addWordWorker.addWord(word);
        currentLibrary.put(wordAndTranslation[0], wordAndTranslation[1]);
    }

    private void chengeWord() {

    }
    
}
