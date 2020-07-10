package jm.task.core.jdbc.util;

import java.sql.Connection;
import java.sql.DriverManager;

public class Util {
    public static Connection getConnection() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver").getDeclaredConstructor().newInstance();
            return DriverManager.getConnection("jdbc:mysql://localhost:3306/PP1" +
                    "?serverTimezone=Europe/Moscow&useSSL=false",
                    "root",
                    "root");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}