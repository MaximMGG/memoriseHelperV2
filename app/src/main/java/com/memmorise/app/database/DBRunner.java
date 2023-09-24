package com.memmorise.app.database;

public class DBRunner {


    private String word;
    private String translations;

     private String insertRuWord = """
                insert into ru_en_translations(ru_word, en_word)
                values(?, ?)
            """;

    private String checkInsertRuWord = """
            select
                ru_word,
                en_word
            from ru_en_translations
            where ru_word = ?
            """;

    private String insertEnWord = """
            insert into en_ru_translations(en_word, ru_word)
            values(?, ?)
            """;

    private String checkInsertEnWord = """
                select
                    en_word,
                    ru_word
                from en_ru_translations
                where en_word = ?
            """;

    private String updateRuWord = """
            update ru_en_translations
            set
                en_word = ?
            where ru_word = ?
            """;

    private String updateEnWord = """
           update en_ru_tranlations
           set ru_word = ?
           where en_word = ?
            """;   

}
