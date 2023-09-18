package com.memmorise.app.utils;

import java.util.InputMismatchException;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import lombok.experimental.UtilityClass;

@UtilityClass
public class ChecksUtils {

    private static Scanner scan;
    
    public static int writeInt() {
        scan = new Scanner(System.in);
        int number = -1;
        try {
            number = scan.nextInt();
        } catch (InputMismatchException ex) {
                scan.close();
            System.out.println("You wrote not Number, please try agane");
            writeInt();
        }
        return number;
    }

    public static String writeString() {
        scan = new Scanner(System.in);
        String word = "";
        try {
            word = scan.nextLine();
            Matcher m = Pattern.compile("^\\b[A-z]+$").matcher(word);
            if (m.find()) {
                scan.close();
                return word;
            } else {
                scan.close();
                throw new IllegalStateException();
            }
        } catch (IllegalStateException e) {
            System.out.println("You wrote not a String, please try agane");
            writeString();
        }
        return word;
    }
}
