package com.memmorise.app;

import java.io.UnsupportedEncodingException;
import java.sql.SQLException;

import com.memmorise.app.interective.ClientTach;

public class MemoriseStarter {

    public static void main(String[] args) throws SQLException, UnsupportedEncodingException {
        ClientTach clientTach = ClientTach.getInstance();
        clientTach.startApp();
    }

}
