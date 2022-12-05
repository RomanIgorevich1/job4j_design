package ru.job4j.io;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class LogFilter {
    public List<String> filter(String filter) {
        List<String> newList = new ArrayList<>();
        try (BufferedReader input = new BufferedReader(new FileReader(filter))) {
            String newText;
            while ((newText = input.readLine()) != null) {
                String[] array = newText.split(System.lineSeparator());
                for (String str : array) {
                    if (str.contains("404 ")) {
                        newList.add(str);
                    }
                }
            }
        } catch (IOException exception) {
            exception.printStackTrace();
        }
        return newList;
    }

    public static void save(List<String> log, String file) {
        try (PrintWriter out = new PrintWriter(
                new BufferedOutputStream(
                        new FileOutputStream(file)))) {
            for (String list : log) {
                out.printf("%s%n", list);
            }
        } catch (IOException exception) {
            exception.printStackTrace();
        }
    }

    public static void main(String[] args) {
        LogFilter logFilter = new LogFilter();
        List<String> list = logFilter.filter("log.txt");
        list.forEach(System.out::println);
        List<String> log = logFilter.filter("log.txt");
        save(log, "404.txt");
    }
}
