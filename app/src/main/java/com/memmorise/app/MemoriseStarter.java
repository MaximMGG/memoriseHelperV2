package com.memmorise.app;

import java.sql.SQLException;

import com.memmorise.app.interective.ClientTach;

public class MemoriseStarter {

    public static void main(String[] args) throws SQLException {
        ClientTach clientTach = ClientTach.getInstance();
        clientTach.startApp();
    }
}
