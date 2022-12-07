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
                if ((newText[0].equals("400") || newText[0].contains("500")) && result) {
                    result = false;
                    writer.printf("%s%n", newText[1]);
                }
                if ((newText[0].contains("200") || newText[0].contains("300")) && !result) {
                    result = true;
                    writer.printf("%s%n", newText[1]);
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
