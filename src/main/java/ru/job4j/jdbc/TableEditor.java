package ru.job4j.jdbc;

import java.io.InputStream;
import java.sql.*;
import java.util.Properties;
import java.util.StringJoiner;

public class TableEditor implements AutoCloseable {
    private Connection connection;
    private Properties properties;

    public TableEditor(Properties properties) {
        this.properties = properties;
        initConnection();
    }

    private void initConnection() {
        connection = null;
    }

    public void createTable(String tableName) throws Exception {
        String create = String.format(
                "create table %s (id serial primary key, name text);",
                tableName
        );
        getStatement().execute(create);
    }

    public void dropTable(String tableName) throws Exception {
        String drop = String.format(
                "drop table %s;", tableName
        );
        getStatement().executeUpdate(drop);
    }

    public void addColumn(String tableName, String columnName, String type) throws Exception {
        String columnAdd = String.format(
                "alter table %s add column %s %s;",
                tableName, columnName, type

        );
        getStatement().executeUpdate(columnAdd);
    }

    public void dropColumn(String tableName, String columnName) throws Exception {
        String columnDrop = String.format(
                "alter table %s drop column %s", tableName, columnName
        );
        getStatement().executeUpdate(columnDrop);
    }

    public void renameColumn(String tableName, String columnName, String newColumnName) throws Exception {
        String rename = String.format(
                "alter table %s rename column %s to %s;", tableName, columnName, newColumnName
        );
        getStatement().executeUpdate(rename);
    }

    public String getTableScheme(String tableName) throws Exception {
        var rowSeparator = "-".repeat(30).concat(System.lineSeparator());
        var header = String.format("%-15s|%-15s%n", "Name", "Type");
        var buffer = new StringJoiner(rowSeparator, rowSeparator, rowSeparator);
        buffer.add(header);
        try (var statement = connection.createStatement()) {
            var selection = statement.executeQuery(String.format(
                    "select * from %s limit 1", tableName
            ));
            var metaData = selection.getMetaData();
            for (int i = 1; i <= metaData.getColumnCount(); i++) {
                buffer.add(String.format("%-15S|%-15s%n", metaData.getColumnName(i),
                        metaData.getColumnTypeName(i)));
            }
        }
        return buffer.toString();
    }

    private Statement getStatement() throws Exception {
        return getConnection().createStatement();
    }

    private Connection getConnection() throws Exception {
        try (InputStream inputStream = TableEditor.class.getClassLoader().getResourceAsStream("app.properties")) {
            properties.load(inputStream);
        }
        Class.forName(properties.getProperty("driver_jdbc"));
        String url = properties.getProperty("url");
        String login = properties.getProperty("login");
        String password = properties.getProperty("password");
        connection = DriverManager.getConnection(url, login, password);
        return connection;
    }

    @Override
    public void close() throws Exception {
        if (connection != null) {
            connection.close();
        }
    }

    public static void main(String[] args) throws Exception {
        try (TableEditor table = new TableEditor(new Properties())
        ) {
            table.getConnection();
            table.createTable("students");
            System.out.println(table.getTableScheme("students"));
            table.addColumn("students", "age", "text");
            System.out.println(table.getTableScheme("students"));
            table.addColumn("students", "sex", "integer");
            System.out.println(table.getTableScheme("students"));
            table.addColumn("students", "address", "varchar");
            table.dropColumn("students", "age");
            System.out.println(table.getTableScheme("students"));
            table.renameColumn("students", "name", "first_name");
            System.out.println(table.getTableScheme("students"));
            table.dropTable("students");
            System.out.println(table.getTableScheme("students"));
        }
    }
}