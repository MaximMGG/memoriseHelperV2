package com.memmorise.app.tranlations;

public enum Lenguages {
    ENGLISH, RUSSIAN;


    public static void printAllLenguaes() {
        System.out.println("1. +" + Lenguages.ENGLISH.name());
        System.out.println("2. +" + Lenguages.RUSSIAN.name());
    }
}
