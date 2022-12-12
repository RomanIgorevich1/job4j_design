package ru.job4j.io;

import java.util.HashMap;
import java.util.Map;

public class ArgsName {
    private final Map<String, String> value = new HashMap<>();

    public String get(String key) {
        if (!value.containsKey(key)) {
            throw new IllegalArgumentException();
        }
        return value.get(key);
    }

    private void parse(String[] args) {
        validation(args);
        StringBuilder builder = new StringBuilder();
        String[] newArgs;
        for (String str : args) {
           newArgs = str.split("=", 2);
           for (String str2 : newArgs) {
               if (str2.startsWith("-")) {
                   builder.append(str2);
                   builder.deleteCharAt(0);
                   value.put(builder.toString(), newArgs[1]);
                   builder.delete(0, str2.length());
                   break;
               }
               value.put(newArgs[0], newArgs[1]);
           }
        }
    }

    private void validation(String[] args) {
        if (args.length < 1) {
            throw new IllegalArgumentException();
        }
        for (String str : args) {
            if (!str.contains("=")) {
                throw new IllegalArgumentException();
            }
            String[] array = str.split("=", 2);
            for (String st : array) {
                if (array[0].length() < 2 || array[1].length() < 1) {
                    throw new IllegalArgumentException();
                }
                if (!st.startsWith("-")) {
                    throw new IllegalArgumentException();
                }
                break;
            }
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