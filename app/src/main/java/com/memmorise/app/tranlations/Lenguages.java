package com.memmorise.app.tranlations;

public enum Lenguages {
    ENGLISH ("en"),
    RUSSIAN ("ru");

    private String leng;

    Lenguages(String leng){
        this.leng = leng;
    };

    public String getLeng() {
        return leng;
    }


    public static void printAllLenguaes() {
        System.out.println("1. +" + Lenguages.ENGLISH.name());
        System.out.println("2. +" + Lenguages.RUSSIAN.name());
    }
}
