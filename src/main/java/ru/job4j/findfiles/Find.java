package ru.job4j.findfiles;

import ru.job4j.io.ArgsName;

import java.io.*;
import java.nio.file.FileVisitResult;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.attribute.BasicFileAttributes;

public class Find extends SimpleFileVisitor<Path> {
    private static ArgsName argsName;

    public static void main(String[] args) throws IOException {
        if (args.length != 4) {
            throw new IllegalArgumentException("Wrong number of arguments.");
        }
        Find find = new Find();
        argsName = ArgsName.of(args);
        find.validation(argsName);
        Path start = Path.of(argsName.get("d"));
        Files.walkFileTree(start, find);
    }

    @Override
    public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) {
        search(file.toFile());
        return FileVisitResult.CONTINUE;
    }

    public void search(File file) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("log.txt", true))) {
            String type = argsName.get("t");
            String name = argsName.get("n");
            if (type.equals("mask")) {
                if (file.getName().matches(".*$" + name)) {
                writer.write(file.getName() + "\n");
                }
            }
            if (type.equals("name")) {
                if (file.getName().matches("^" + name + ".(text|txt)")) {
                    writer.write(file.getName() + "\n");
                }
            }
            if (type.equals("regex")) {
                if (file.getName().matches(name)) {
                    writer.write(file.getName() + "\n");
                }
            }
        } catch (IOException exception) {
            exception.printStackTrace();
        }

    }

    private void validation(ArgsName argsName) {
        Path path = Path.of(argsName.get("d"));
        if (argsName.get("d").length() < 1 && argsName.get("n").length() < 1
                && argsName.get("t").length() < 1 && argsName.get("o").length() < 1) {
            throw new IllegalArgumentException("Parameter length must be greater than 1.");
        }
        if (!path.toFile().exists() && !path.toFile().isDirectory()) {
            throw new IllegalArgumentException("This path does not exist.");
        }
         if ((!argsName.get("t").contains("name") || !argsName.get("t").contains("mask"))
                 || !argsName.get("t").contains("regex")) {
         throw new IllegalArgumentException("Search type should be name or mask or regex.");
         }
    }
}
