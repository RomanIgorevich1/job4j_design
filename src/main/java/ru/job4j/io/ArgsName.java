package ru.job4j.io;

import java.util.HashMap;
import java.util.Map;

public class ArgsName {
    private final Map<String, String> value = new HashMap<>();

    public String get(String key) {
        if (!value.containsKey(key)) {
            throw new IllegalArgumentException("Данного ключа не существует.");
        }
        return value.get(key);
    }

    private void parse(String[] args) {
        if (args.length < 1) {
            throw new IllegalArgumentException("Список аргументов пуст.");
        }
        for (String str : args) {
            validation(str);
            String[]  newArgs = str.split("=", 2);
           for (String str2 : newArgs) {
               String[] array = str2.split("-", 2);
               value.put(array[1], newArgs[1]);
               break;
           }
        }
    }

    private void validation(String line) {
            if (!line.contains("=")) {
                throw new IllegalArgumentException("Нарушен шаблон : (-ключ=значение), отсутствует символ (=)");
            }
            String[] array = line.split("=", 2);
            for (String st : array) {
                if (array[0].length() < 2 || array[1].length() < 1) {
                    throw new IllegalArgumentException("Отсутствует ключ или значение.");
                }
                if (!st.startsWith("-")) {
                    throw new IllegalArgumentException("Нарушен шаблон : (-ключ=значение), отсутствует символ (-)");
                }
                break;
            }
    }

    public static ArgsName of(String[] args) {
        ArgsName name = new ArgsName();
        name.parse(args);
        return name;
    }

    public static void main(String[] args) {
        ArgsName jvm = ArgsName.of(new String[] {"-Xmx=512", "-encoding=UTF-8"});
        System.out.println(jvm.get("Xmx"));
        ArgsName zip = ArgsName.of(new String[] {"-out=project.zip", "-encoding=UTF-8"});
        System.out.println(zip.get("out"));
    }
}