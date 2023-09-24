package com.memmorise.app.database;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import com.memmorise.app.tranlations.Lenguages;

public class DBRunner {


    private String word;
    private String translations;
    private String to = "to_word";
    private String from = "from_word";

    
    private String checkWord;
    private String selectWord;
    private String updateWord;
    private String insertWord;

    public void checkTranslation(Lenguages[] lengs, String word, String translations) throws SQLException {

        checkWord = Querries.getCheckWordQuerry(lengs[0], lengs[1]);
        to = to.replaceAll("to", lengs[1].getLeng());

        try (PreparedStatement statement = ConnectionManager.getConnection().prepareStatement(checkWord)) {
            statement.setString(1, word);
            ResultSet res = statement.executeQuery();

            String s = res.getString(to);
        }

    }

    public List<String> getTranlations(Lenguages[] lengs, String word) throws SQLException {
        selectWord = Querries.getSelectWordQuerry(lengs[0], lengs[1]);
        to = to.replaceAll("to", lengs[1].getLeng());

        try (PreparedStatement statement = ConnectionManager.getConnection().prepareStatement(selectWord)) {
            statement.setString(1, word);
            ResultSet res = statement.executeQuery();
            if (res == null) {
                return null;
            }

            String tr = "";

            while (res.next()) {
                tr = res.getString(to);
            }

            return List.of(tr.split(", "));
        }
    }

}
