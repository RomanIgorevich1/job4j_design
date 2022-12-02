package ru.job4j.io;

import java.io.FileOutputStream;
import java.io.IOException;

public class ResultFile {
    public static void main(String[] args) {
        StringBuilder matrix = new StringBuilder();
        int[][] array = new int[4][4];
        for (int i = 0; i < array.length; i++) {
            for (int y = 0; y < array.length; y++) {
                array[i][y] = (i + 1) * (y + 1);
                matrix.append((i + 1) * (y + 1)).append(" ");
                if (y == 3) {
                    matrix.append(System.lineSeparator());
                }
            }
        }
        String newMatrix = matrix.toString();
        try (FileOutputStream out = new FileOutputStream("result.txt")) {
            out.write("Hello world!".getBytes());
            out.write(System.lineSeparator().getBytes());
            out.write(newMatrix.getBytes());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}