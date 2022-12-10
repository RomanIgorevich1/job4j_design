package ru.job4j.io.duplicates;

import java.io.IOException;
import java.nio.file.FileVisitResult;
import java.nio.file.Path;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.*;

import static java.nio.file.FileVisitResult.CONTINUE;

public class DuplicatesVisitor extends SimpleFileVisitor<Path> {
    private Map<FileProperty, List<Path>> filePropertyMap = new HashMap<>();

    @Override
    public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
        FileProperty newFile = new FileProperty(file.toFile().length(), file.getFileName().toString());
        filePropertyMap.putIfAbsent(newFile, new ArrayList<>());
        filePropertyMap.computeIfPresent(newFile, (key, value) -> {
            value.add(file.toAbsolutePath());
            return value;
        });
        return CONTINUE;
    }

    public void find() {
        for (Map.Entry<FileProperty, List<Path>> mapPath : filePropertyMap.entrySet()) {
            if (mapPath.getValue().size() > 1) {
                for (Path listPath : mapPath.getValue()) {
                    System.out.printf("%s%n", listPath);
                }
            }
        }
    }
}
