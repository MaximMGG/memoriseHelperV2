package com.memmorise.app.utils;

import com.memmorise.app.tranlations.EnRuTranslator;
import com.memmorise.app.tranlations.Translator;

public class LangUtils {
    

    private LangUtils() {}


    public static Translator getTranlator(String fromTo) {

        //TODO add new translators here
        switch (fromTo) {
            case "en-ru" -> {return new EnRuTranslator();}

            default -> {return null;}
        }
    }

    public static String mapCharArrToString(char[] forMap) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < forMap.length; i++) {
            sb.append(forMap[i]);
        }

        return sb.toString();
    }
}
