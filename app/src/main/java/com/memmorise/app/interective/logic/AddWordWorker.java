package com.memmorise.app.interective.logic;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import java.util.stream.Collectors;

import com.memmorise.app.database.DBRunner;
import com.memmorise.app.interective.InterectiveUtils;
import com.memmorise.app.library.Library;
import com.memmorise.app.tranlations.Translator;
import com.memmorise.app.utils.ChecksUtils;

public class AddWordWorker {
    
    private Translator translator;
    private List<String> translations;
    private List<String> translationsFromDB;
    private Thread thread1;
    private Library library;
    private String word;

    public AddWordWorker(Translator translator, Library library) {
        this.translator = translator;
        this.library = library;
    }


    public String[] addWord(String w) throws InterruptedException, SQLException {
        this.word = w;
        DBRunner db = new DBRunner();
        translationsFromDB = db.getTranlations(library.getTranslator().getFromTo().split("-"), word);

        if (translationsFromDB == null) {
            System.out.println("Going to the web for find tranlations");
            thread1 = new Thread(() -> word = setTranlations(word));
            thread1.start();
            InterectiveUtils.awesomePrinting("Checking your word...");
            thread1.join();
        } else {
            translations = translationsFromDB;
        }
        
        boolean agreed = false;
        
        while (!agreed) {
            
            if (translations.size() == 0) {
                System.out.println("Sorry, but we don't find any translations of word -> " + word);
                System.out.println("You can write your own or enter 0 for write word agane");

            } else {
                System.out.println("Here is traslations of your word -> " + word);
                InterectiveUtils.printTranslations(translations);
                System.out.println("You can chose one or more translations, or write you own");
                System.out.println("For example -> 1, 3, my own translation");
            }

            final String concatinatedTranslations = ChecksUtils.getUserChoose(translations);

            if (concatinatedTranslations.equals("0")) {
                return null;
            }

            System.out.println("Your word -> " + word + " translations -> " + concatinatedTranslations);
            System.out.println("Write 1 if you want to save result or 2 if your want to write anather translations");

            thread1 = new Thread(() -> {
                try {
                updateOrInsertTranslationsInDB(concatinatedTranslations);
                } catch (SQLException e) {
                    throw new RuntimeException();
                }
            });

            thread1.start();

            if (ChecksUtils.yesNo()) {
                agreed = true;
            } else {
                System.out.println("Okey let's do it agane");
            }
            return new String[]{word, concatinatedTranslations};
        }
        return null;
    }

    private void updateOrInsertTranslationsInDB(String tr) throws SQLException {

        DBRunner db = new DBRunner();
        int before = translationsFromDB == null ? 0 : translationsFromDB.size();

        if (translationsFromDB == null) {
            db.insertTranslations(library.getTranslator().getFromTo().split("-"), word, tr);
            return;
        }

        for(String s : tr.split(", ")) {
            if (!translationsFromDB.contains(s)) {
                translationsFromDB.add(s);
            }
        }

        int after = translationsFromDB.size();

        if (after > before) {
            String concat = translationsFromDB.stream()
                                            .collect(Collectors.joining(", "));
            db.updateTranlations(library.getTranslator().getFromTo().split("-"), word, concat);
        }
        
    }

    private String setTranlations(String word) {
        translator = library.getTranslator();
        try {
            String checkWord = translator.checkWord(word);
            if (!checkWord.equals(word) && !checkWord.isEmpty()) {
                System.out.println("Maybe you mean " + checkWord + " ?");
                if (ChecksUtils.yesNo()) {
                    translations = translator.getTranclations(checkWord);
                    word = checkWord;
                } else {
                    translations = translator.getTranclations(word);
                }
            } else {
                translations = translator.getTranclations(word);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return word;
    }

}
