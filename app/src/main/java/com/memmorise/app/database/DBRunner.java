package com.memmorise.app.database;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.memmorise.app.tranlations.Lenguages;

public class DBRunner {


    private String to = "to_word";

    
    private String selectWord;
    private String updateWord;
    private String insertWord;


    public List<String> getTranlations(Lenguages[] lengs, String word) throws SQLException {
        selectWord = Querries.getSelectWordQuerry(lengs[0], lengs[1]);
        to = to.replaceAll("to", lengs[1].getLeng());

        try (PreparedStatement statement = ConnectionManager.getConnection().prepareStatement(selectWord)) {
            statement.setString(1, word);
            ResultSet res = statement.executeQuery();

            if (res.next()) {
                List<String> translationFromDB = new ArrayList<>();
                for(String s : res.getString(to).split(", ")) {
                    translationFromDB.add(s);
                }
                return translationFromDB;
            } else {
                return null;
            }
        }
    }

    public void updateTranlations(Lenguages[] lengs, String word, String translations) throws SQLException {
        updateWord = Querries.getUpdateWordQuerry(lengs[0], lengs[1]);
        
        try (PreparedStatement statement = ConnectionManager.getConnection().prepareStatement(updateWord)) {
            statement.setString(1, translations);
            statement.setString(2, word);
            statement.executeUpdate();
        }
    }

    public void insertTranslations(Lenguages[] lengs, String word, String translations) throws SQLException {
        insertWord = Querries.getInsertWordQuerry(lengs[0], lengs[1]);

        try (PreparedStatement statement = ConnectionManager.getConnection().prepareStatement(insertWord)) {
            statement.setString(1, word);
            statement.setString(2, translations);
            statement.executeUpdate();
        }
    }

}
