package com.memmorise.app.interective;

import com.memmorise.app.utils.ChecksUtils;

public class CrossRoad {




    public static int mainCrossroad() {
        System.out.println("Please, write your choose:");
        System.out.println("1. Create new library.");
        System.out.println("2. Show all your libraries");
        System.out.println("3. Change existing library.");
        System.out.println("4. Learn words from library.");
        System.out.println("5. Exit.");
        return ChecksUtils.writeInt(1, 5);
    }

    public static int createLibraryCrossroad() {
        System.out.println("1. Save library and back to main menu.");
        System.out.println("2. Back to main menu without saving.");
        System.out.println("3. Continue adding words.");
        System.out.println("4. Save library and exit.");
        System.out.println("5. Exit without saving.");
        return ChecksUtils.writeInt(1, 5);
    }

    public static int changeLibraryCrossroad() {
        System.out.println("1. Save library and back to main menu.");
        System.out.println("2. Back to main menu without saving.");
        System.out.println("3. Continue changing words.");
        System.out.println("4. Save library and exit.");
        System.out.println("5. Exit without saving.");
        return ChecksUtils.writeInt(1, 5);
    }
}
