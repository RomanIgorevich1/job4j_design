package ru.job4j.io;

import java.io.FileInputStream;
import java.io.IOException;

public class EvenNumbersFile {
    public static void main(String[] args) throws IOException {
        try (FileInputStream input = new FileInputStream("even.txt")) {
            StringBuilder text = new StringBuilder();
            int read;
            while ((read = input.read()) != -1) {
                text.append((char) read);
            }
            String[] newText = text.toString().split(System.lineSeparator());
            for (String str : newText) {
                System.out.println(Integer.parseInt(str) % 2 == 0 ? "Четное число" : "Не четное число");
            }
            System.out.println(text);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
