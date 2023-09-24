package com.memmorise.app.database;

import com.memmorise.app.tranlations.Lenguages;

import lombok.experimental.UtilityClass;

@UtilityClass
public class Querries {

     private String INSERT_WORD = """
                insert into fr_to_translations(fr_word, to_word)
                values(?, ?)
            """;

    private String CHECK_WORD = """
            select
                fr_word,
                to_word
            from fr_to_translations
            where fr_word = ?
            """;

    private String UPDATE_WORD = """
            update fr_to_translations
            set
                to_word = ?
                
            where fr_word = ?
            """;

    public String getInsertWordQuerry(Lenguages from, Lenguages to) {
        String result = INSERT_WORD.replaceAll("fr_", from.getLeng() + "_");
        return result.replaceAll("to_", to.getLeng() + "_");
    }

    public String getCheckWordQuerry(Lenguages from, Lenguages to) {
        String result = CHECK_WORD.replaceAll("fr_", from.getLeng() + "_");
        return result.replaceAll("to_", to.getLeng() + "_");
    }

    public String getUpdateWordQuerry(Lenguages from, Lenguages to) {
        String result = UPDATE_WORD.replaceAll("fr_", from.getLeng() + "_");
        return result.replaceAll("to_", to.getLeng() + "_");
    }
    
}
