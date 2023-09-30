package com.memmorise.app.database;

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

    public static String getInsertWordQuerry(String from, String to) {
        String result = INSERT_WORD.replaceAll("fr_", from + "_");
        return result.replaceAll("to_", to + "_");
    }

    public static String getUpdateWordQuerry(String from, String to) {
        String result = UPDATE_WORD.replaceAll("fr_", from + "_");
        return result.replaceAll("to_", to + "_");
    }

    public static String getSelectWordQuerry(String from, String to) {
        String result = SELECT_WORD.replaceAll("fr_", from + "_");
        return result.replaceAll("to_", to + "_");
    }
    
}
