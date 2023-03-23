package ru.job4j.jdbc;

import ru.job4j.io.Config;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionDemo {
    public static void main(String[] args) throws ClassNotFoundException, SQLException {
        Config config = new Config("./data/app.properties");
        config.load();
        Class.forName(config.value("driver_jdbc"));
        String url = config.value("url");
        String login = config.value("login");
        String password = config.value("password");
        /**
         * Чтобы получить подключение, нужно воспользоваться классом DriverManager
         * Для получения информации о БД и ее внутренней структуре существует класс DatabaseMetaData.
         */
        try (Connection connection = DriverManager.getConnection(url, login, password)) {
            DatabaseMetaData metaData = connection.getMetaData();
            System.out.println(metaData.getUserName());
            System.out.println(metaData.getURL());
        }
    }
}
