package com.memmorise.app.utils;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
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

    public static int writeInt(int from, int to) {
        scan = new Scanner(System.in);
        int number = -1;
        try {
            number = scan.nextInt();
            if (number < from && number > to) {
                throw new InputMismatchException();
            }
            scan.close();
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

    public static List<String> getUserChoose(List<String> translations) {
        scan = new Scanner(System.in);
        List<String> userChoose = new ArrayList<>();

        String[] buffer = scan.nextLine().split(", "); // 1, 2, Пока
        for (String s : buffer) {
            int number = 0;
            try {
                number = Integer.parseInt(s);
                userChoose.add(translations.get(number - 1));
            } catch (Exception e){
                userChoose.add(s);
            }
        }
        return userChoose;
    }
}
