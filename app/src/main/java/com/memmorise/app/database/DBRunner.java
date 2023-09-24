package com.memmorise.app.database;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.memmorise.app.tranlations.Lenguages;

public class DBRunner {


    private String word;
    private String translations;

    
    private String checkWord;

    public void checkTranslation(Lenguages[] lengs, String word, String translations) throws SQLException {

        checkWord = Querries.getCheckWordQuerry(lengs[0], lengs[1]);

        try (PreparedStatement statement = ConnectionManager.getConnection().prepareStatement(checkWord)) {
            statement.setString(1, word);
            ResultSet res = statement.executeQuery();
        }

    }

}
