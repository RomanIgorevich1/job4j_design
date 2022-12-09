package ru.job4j.io.duplicates;

import java.io.IOException;
import java.nio.file.FileVisitResult;
import java.nio.file.Path;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.*;

import static java.nio.file.FileVisitResult.CONTINUE;

public class DuplicatesVisitor  extends SimpleFileVisitor<Path> {
    private List<FileProperty> path = new ArrayList<>();
    private List<FileProperty> list = new ArrayList<>();

    @Override
    public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
        FileProperty newFile = new FileProperty((int) file.toFile().length(), file.getFileName().toString());
        if (path.contains(newFile)) {
            list.add(newFile);
        } else {
            path.add(newFile);
        }
        return CONTINUE;
    }

    public void find() {
        list.forEach(name -> System.out.println(name.getName()));
    }
}
