package com.memmorise.app;

import com.memmorise.app.interective.ClientTach;

public class MemoriseStarter {

    public static void main(String[] args) {
        ClientTach clientTach = ClientTach.getInstance();
        clientTach.startApp();
    }
}
