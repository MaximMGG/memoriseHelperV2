package com.memmorise.app.utils;

import java.util.InputMismatchException;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import lombok.experimental.UtilityClass;

@UtilityClass
public class ChecksUtils {

    private static final Scanner scan = new Scanner(System.in);
    
    public static int writeInt() {
        int number = -1;
        try (scan) {
            number = scan.nextInt();
        } catch (InputMismatchException ex) {
            System.out.println("You wrote not Number, please try agane");
            writeInt();
        }
        return number;
    }

    public static String writeString() {
        String word = "";
        try (scan) {
            word = scan.nextLine();
            Matcher m = Pattern.compile("[A-z]?").matcher(word);
            if (m.find()) {
                return word;
            } else {
                throw new IllegalStateException();
            }

        } catch (IllegalStateException e) {
            System.out.println("You wrote not a String, please try agane");
            writeString();
        }
        return word;
    }
}
