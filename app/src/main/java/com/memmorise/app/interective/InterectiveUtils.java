package com.memmorise.app.interective;

import java.util.Scanner;

import com.memmorise.app.tranlations.Lenguages;
import com.memmorise.app.utils.ChecksUtils;

import lombok.experimental.UtilityClass;

@UtilityClass
public class InterectiveUtils {
    
    private static Scanner scan;


    public static Lenguages getChoosenLenguage() {
        scan = new Scanner(System.in);
        switch (ChecksUtils.writeInt(1, 2)) {
            case 1 -> {return Lenguages.ENGLISH;}
            case 2 -> {return Lenguages.RUSSIAN;}
            default -> {return null;}
        }
    }
}
