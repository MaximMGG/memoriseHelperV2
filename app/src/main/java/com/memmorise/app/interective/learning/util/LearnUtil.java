package com.memmorise.app.interective.learning.util;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import com.memmorise.app.interective.learning.LearnMap;
import com.memmorise.app.interective.learning.LearnMap.Node;

public class LearnUtil {

    private static Scanner scan = new Scanner(System.in);

    public static List<Node> checkLevelOfNow(List<Node> pack, int level, LearnMap learnMap) {
        List<Node> result = new ArrayList<>();
        for (int i = 0; i < pack.size(); i++) {
            if (pack.get(i).levelOfNow < level) {
                result.add(pack.get(i));
            }
        }
        return result;
    }

    public static void waitM(long time) {
        try {
            Thread.sleep(time);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public static void enter() {
        if (scan.nextLine() != null || scan.nextLine() == null)
            return;
    }

    public static boolean arrayContains(String[] pack, String tmp) {
        for (int i = 0; i < pack.length; i++) {
            if (tmp.equals(pack[i]))
                return true;
        }
        return false;
    }

    public static void aPrint(String line, long time) {
        try {
           char[] charLine = line.toCharArray();
           for (int i = 0; i < charLine.length; i++) {
                System.out.printf("%c", charLine[i]);
                Thread.sleep(time);
           }
           System.out.println();
        } catch (Exception e){
            throw new RuntimeException(e);
        }
    }

    public static void aPrint(char[] c, long time) {
        try {
           for (int i = 0; i < c.length; i++) {
                System.out.printf("%c", c[i]);
                Thread.sleep(time);
           }
           System.out.println();
        } catch (Exception e){
            throw new RuntimeException(e);
        }
    }
}
