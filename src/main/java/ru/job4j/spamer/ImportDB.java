package ru.job4j.spamer;

import ru.job4j.jdbc.TableEditor;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class ImportDB {
    private Properties config;
    private String dump;

    public ImportDB(Properties config, String dump) {
        this.config = config;
        this.dump = dump;
    }

    public List<User> load() throws IOException {
        List<User> users = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(dump))) {
            reader.lines().forEach(s -> {
                        String[] result = s.split(";");
                        if (result.length != 2) {
                            throw new IllegalArgumentException("Массив должен состоять из 2 элементов");
                        }
                        if (result[0].length() < 1 || result[1].length() < 1) {
                            throw new IllegalArgumentException(
                                    "Длина имени или электронной почты не может быть меньше 1");
                        }
                        for (int i = 1; i < result.length; i++) {
                            users.add(new User(result[0], result[1]));
                        }
                    }
            );
        }
        return users;
    }

    public void save(List<User> users) throws ClassNotFoundException, SQLException {
        Class.forName(config.getProperty("jdbc.driver"));
        try (Connection connection = DriverManager.getConnection(
                config.getProperty("jdbc.url"),
                config.getProperty("jdbc.username"),
                config.getProperty("jdbc.password")
        )) {
            for (User user : users) {
                try (PreparedStatement preparedStatement = connection.prepareStatement(
                        "insert into users (name, email) values (?, ?)")) {
                    preparedStatement.setString(1, user.name);
                    preparedStatement.setString(2, user.email);
                    preparedStatement.execute();
                }
            }
        }
    }

private static class User {
    private String name;
    private String email;

    public User(String name, String email) {
        this.name = name;
        this.email = email;
    }

}

    public static void main(String[] args) throws Exception {
        Properties properties = new Properties();
        try (InputStream inputStream = ImportDB.class.getClassLoader().getResourceAsStream(
                "spammer.properties")) {
            properties.load(inputStream);
        }
        ImportDB db = new ImportDB(properties, "./data/dump.txt");
        db.save(db.load());
    }
}