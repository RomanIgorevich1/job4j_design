package ru.job4j.io;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class LogFilter {
    public List<String> filter(String filter) {
        List<String> newList = new ArrayList<>();
        try (BufferedReader input = new BufferedReader(new FileReader(filter))) {
            StringBuilder text = new StringBuilder();
            String newText;
            while ((newText = input.readLine()) != null) {
                    text.append(newText).append(System.lineSeparator());
            }
            String[] array = text.toString().split(System.lineSeparator());
            for (String str : array) {
                if (str.contains(" " + 404 + " ")) {
                    newList.add(str);
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
        System.out.println(list);
        List<String> log = logFilter.filter("log.txt");
        save(log, "404.txt");
    }
}
