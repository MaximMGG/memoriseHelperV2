package com.memmorise.app.interective.learning;

import java.util.Map;

import com.memmorise.app.library.Library;
import com.memmorise.app.utils.ChecksUtils;

public class LearnEngine {
    
    private Library lib;
    private Map<String, String> currentLibrary;

    public LearnEngine(Library lib) {
        this.lib = lib;
        currentLibrary = lib.getLibraryContent();
        run();
    }

    private void run() {
        lib.showLibraryContent();
        System.out.println("Chose level of learning where: ");
        System.out.println("1. I am don't now this words start from 0");
        System.out.println("2. I am now this words not bad");
        System.out.println("3. I am now well this words just want to check my self");
        switch (ChecksUtils.writeInt(1, 3)) {
            case 1 -> {FirstLevel fl = new FirstLevel(currentLibrary);}
            case 2 -> {SecondLevel sl = new SecondLevel(currentLibrary);}
            case 3 -> {TherdLevel tl = new TherdLevel(currentLibrary);}
        }
    }
}
