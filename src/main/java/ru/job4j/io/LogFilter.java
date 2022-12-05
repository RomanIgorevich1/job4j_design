package ru.job4j.io;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
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

    public static void main(String[] args) {
        LogFilter logFilter = new LogFilter();
        List<String> list = logFilter.filter("log.txt");
        System.out.println(list);
    }
}
