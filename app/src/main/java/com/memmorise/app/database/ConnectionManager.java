package com.memmorise.app.database;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class ConnectionManager {
    
    private static final Properties properties = new Properties();
    private static final String DB_URL = "db.url";
    private static final String DB_NAME = "db.name";
    private static final String DB_PASSWORD = "db.password";


    static {
        loadProperties();
    }

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(get(DB_URL), get(DB_NAME), get(DB_PASSWORD));
    }


    private static String get(String key) {
        return properties.getProperty(key);
    }


    private static void loadProperties() {
        try (InputStream resources = ConnectionManager.class.getResourceAsStream("resources/application.properties")) {
            properties.load(resources);
        } catch (IOException e) {
            throw new RuntimeException();
        }
    }

}
