package com.memmorise.app.database;

import com.memmorise.app.tranlations.Lenguages;

import lombok.experimental.UtilityClass;

@UtilityClass
public class Querries {

    private static String INSERT_WORD = """
                insert into fr_to_translations(fr_word, to_word)
                values(?, ?)
            """;

    private static String UPDATE_WORD = """
            update fr_to_translations
            set
                to_word = ?
                
            where fr_word = ?
            """;

    private static String SELECT_WORD = """
            select
                fr_word,
                to_word
            from fr_to_translations
            where fr_word = ?
            """;

    public static String getInsertWordQuerry(Lenguages from, Lenguages to) {
        String result = INSERT_WORD.replaceAll("fr_", from.getLeng() + "_");
        return result.replaceAll("to_", to.getLeng() + "_");
    }

    public static String getUpdateWordQuerry(Lenguages from, Lenguages to) {
        String result = UPDATE_WORD.replaceAll("fr_", from.getLeng() + "_");
        return result.replaceAll("to_", to.getLeng() + "_");
    }

    public static String getSelectWordQuerry(Lenguages from, Lenguages to) {
        String result = SELECT_WORD.replaceAll("fr_", from.getLeng() + "_");
        return result.replaceAll("to_", to.getLeng() + "_");
    }
    
}
