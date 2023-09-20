package com.memmorise.app.interective.createLibrary;

import com.memmorise.app.interective.InterectiveUtils;
import com.memmorise.app.tranlations.Lenguages;
import com.memmorise.app.tranlations.RuEnTranslator;
import com.memmorise.app.tranlations.Translator;

public class CreateLibraryStarter {
    
    private Translator translator;
    private Lenguages from;
    private Lenguages to;

    
    public void createLibrary() {
        setLenguages();
        setTranslator();
    }

    private void setLenguages() {
        System.out.println("Please write from lenguage ");
        Lenguages.printAllLenguaes();
        from = InterectiveUtils.getChoosenLenguage();
        System.out.println("Please write to lenguage ");
        Lenguages.printAllLenguaes();
        to = InterectiveUtils.getChoosenLenguage();
        if (from == to) {
            System.out.println("Sorry, we can't tranlate from the same laguage, please write agane");
        }
    }


    private void setTranslator() {
        if(from == Lenguages.RUSSIAN && to == Lenguages.ENGLISH) {
            translator = new RuEnTranslator();
        }
    }
}
