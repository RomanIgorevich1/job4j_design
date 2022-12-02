package ru.job4j.io;

import java.io.FileOutputStream;
import java.util.Arrays;

public class ResultFile {
    public static void main(String[] args) {
        int[][] array = new int[2][2];
        for (int i = 0; i < array.length; i++) {
            for (int y = 0; y < array.length; y++) {
                array[i][y] = (i + 1) * (y + 1);
            }
        }
        String matrix = Arrays.deepToString(array);
        try (FileOutputStream out = new FileOutputStream("result.txt")) {
            out.write("Hello world!".getBytes());
            out.write(System.lineSeparator().getBytes());
            out.write(matrix.getBytes());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}