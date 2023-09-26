package com.memmorise.app.interective;

import java.util.List;

import com.memmorise.app.tranlations.Lenguages;
import com.memmorise.app.utils.ChecksUtils;

import lombok.experimental.UtilityClass;

@UtilityClass
public class InterectiveUtils {
    

    public static Lenguages getChoosenLenguage() {
        switch (ChecksUtils.writeInt(1, 2)) {
            case 1 -> {return Lenguages.ENGLISH;}
            case 2 -> {return Lenguages.RUSSIAN;}
            default -> {return null;}
        }
    }

    public static void printTranslations(List<String> translations) {
        int i = 1;
        for(String s : translations) {
            System.out.println(i++ + ". " + s);
        }
    }

    public void awesomePrinting(String line) {
        String[] buf = line.split("");
        for(String s : buf) {
            try {
                Thread.sleep(50);
                System.out.print(s);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println();
    }

    public int getUserShoseLibrary(int zizeOfUserLibrariesList) {
        return ChecksUtils.writeInt(1, zizeOfUserLibrariesList);
    }

}
