package ru.job4j.io;

import java.io.*;
import java.util.*;

public class CSVReader {
    public static void handle(ArgsName argsName) throws Exception {
       try (PrintWriter printOut = new PrintWriter(new FileOutputStream(argsName.get("out")));
            var scanner = new Scanner(new FileInputStream(argsName.get("path")))) {
           String[] arguments = {argsName.get("path"), argsName.get("delimiter"),
                   argsName.get("out"), argsName.get("filter")};
           validation(arguments);
           List<Integer> index = new ArrayList<>();
           int indexSize = 0;
           String[] filters = argsName.get("filter").split(",");
           while (scanner.hasNext()) {
               String[] array = scanner.nextLine().split(argsName.get("delimiter"));
               for (String name2 : filters) {
                   for (int i = 0; i < array.length; i++) {
                       if (name2.equals(array[i])) {
                           index.add(i);
                       }
                   }
               }
               for (Integer number : index) {
                   if (indexSize != index.size() - 1) {
                       printOut.printf("%s", array[number] + argsName.get("delimiter"));
                       indexSize++;
                   } else {
                       printOut.printf("%s", array[number]);
                       indexSize = 0;
                   }
               }
               printOut.printf("%n");
           }
       }
    }

    private static void validation(String[] args) {
        if (args.length != 4) {
            throw new IllegalArgumentException("Root is null. Usage ROOT_FOLDER.");
        }
        File path = new File(args[0]);
        if (!path.exists() && !path.isDirectory()) {
            throw new IllegalArgumentException("This path does not exist.");
        }
        if (path.length() < 1 || (args[3].length() < 1 || args[2].length() < 1)) {
            throw new IllegalArgumentException("Parameter length must be greater 1.");
        }
    }
}
