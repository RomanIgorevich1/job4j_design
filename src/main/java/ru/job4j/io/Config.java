package ru.job4j.io;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.io.BufferedReader;
import java.io.FileReader;
import java.util.StringJoiner;

public class Config {

    private final String path;
    private final Map<String, String> value = new HashMap<>();

    public Config(final String path) {
        this.path = path;
    }

    /**
     * Метод load() - должен считать все ключи в карту values. Важно в файле могут быть
     * пустые строки и комментарии их нужно пропускать.
     */
    public void load() {
        try (BufferedReader reader = new BufferedReader(new FileReader(this.path))) {
            String text;
            while ((text = reader.readLine()) != null) {
                String[] newText = text.split("=", 2);
                if (text.contains("#")) {
                    continue;
                }
                if (!newText[0].isEmpty() && !newText[1].isEmpty()) {
                    value.put(newText[0].trim(), newText[1].trim());
                } else {
                    throw new IllegalArgumentException();
                }
            }
        } catch (IOException exception) {
            exception.printStackTrace();
        }
    }

    public String value(String key) {
        return value.getOrDefault(key, null);
    }

    @Override
    public String toString() {
        StringJoiner out = new StringJoiner(System.lineSeparator());
        try (BufferedReader read = new BufferedReader(new FileReader(this.path))) {
            read.lines().forEach(out::add);
        } catch (IOException exception) {
            exception.printStackTrace();
        }
        return out.toString();
    }

    public static void main(String[] args) {
        System.out.println(new Config("app.properties"));
    }
}
