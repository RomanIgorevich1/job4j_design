package ru.job4j.io;

import java.io.*;

public class Analysis {
    public void unavailable(String source, String target) {
        try (BufferedReader reader = new BufferedReader(new FileReader(source));
             PrintWriter writer = new PrintWriter(new FileOutputStream(target))) {
            String text;
            boolean result = true;
            while ((text = reader.readLine()) != null) {
                String[] newText = text.split(" ");
                for (String str : newText) {
                    if ((str.contains("400") || str.contains("500")) && result) {
                        result = false;
                        writer.printf("%s%n", newText[1]);
                    }
                    if ((str.contains("200") || str.contains("300")) && !result) {
                        result = true;
                        writer.printf("%s%n", newText[1]);
                    }
                }
            }
        } catch (IOException exception) {
            exception.printStackTrace();
        }

    }

    public static void main(String[] args) {
        Analysis analysis = new Analysis();
        analysis.unavailable("./data/server.txt", "./data/unavailable.txt");
    }
}
