package ru.job4j.io;

import java.io.*;
import java.util.*;

public class CSVReader {
    public static void handle(ArgsName argsName) {
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
                   if (argsName.get("out").equals("stdout")) {
                       if (indexSize != index.size() - 1) {
                           System.out.printf("%s", array[number] + argsName.get("delimiter"));
                           indexSize++;
                       } else {
                           System.out.printf("%s", array[number]);
                           indexSize = 0;
                       }
                       continue;
                   }
                   if (indexSize != index.size() - 1) {
                       printOut.printf("%s", array[number] + argsName.get("delimiter"));
                       indexSize++;
                   } else {
                       printOut.printf("%s", array[number]);
                       indexSize = 0;
                   }
               }
               printOut.printf("%n");
               System.out.printf("%n");
           }
       } catch (IOException exception) {
           exception.printStackTrace();
       }
    }

    private static void validation(String[] args) {
        File path = new File(args[0]);
        File out = new File(args[2]);
        if (!path.exists() && !path.isDirectory()) {
            throw new IllegalArgumentException("This path does not exist.");
        }
        if (!out.exists()) {
            throw new IllegalArgumentException("This path does not exist.");
        }
        if (!path.getName().contains(".csv")) {
            throw new IllegalArgumentException("Not valid extension.");
        }
        if (out.getName().equals("out") && !out.getName().contains(".csv")) {
            throw new IllegalArgumentException("Not valid extension.");
        }
        if (path.length() < 1) {
            throw new IllegalArgumentException("Parameter length must be greater 1.");
        }
        if (args[1].length() > 1 || (!args[1].startsWith(",") && !args[1].startsWith(";"))) {
            throw new IllegalArgumentException("Not allowed csv format.");
        }
    }

    public static void main(String[] args) {
        if (args.length != 4) {
            throw new IllegalArgumentException("Root is null. Usage ROOT_FOLDER.");
        }
        handle(ArgsName.of(args));
    }
}
