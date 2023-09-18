package com.memmorise.app.interective;

import com.memmorise.app.utils.ChecksUtils;

public class CrossRoad {




    public int mainCrossroad() {
        System.out.println("Please, write your choose:");
        System.out.println("1. Create new library.");
        System.out.println("2. Show all your libraries");
        System.out.println("3. Change existing library.");
        System.out.println("4. Learn words from library.");
        return ChecksUtils.writeInt(1, 4);
    }
}
