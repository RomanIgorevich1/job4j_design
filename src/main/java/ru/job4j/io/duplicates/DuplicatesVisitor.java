package ru.job4j.io.duplicates;

import java.io.IOException;
import java.nio.file.FileVisitResult;
import java.nio.file.Path;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.*;

import static java.nio.file.FileVisitResult.CONTINUE;

public class DuplicatesVisitor  extends SimpleFileVisitor<Path> {
    private Set<FileProperty> path = new HashSet<>();
    private List<Path> list = new ArrayList<>();

    @Override
    public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
        FileProperty newFile = new FileProperty(file.toFile().length(), file.getFileName().toString());
        if (!path.contains(newFile)) {
            list.add(file.toAbsolutePath());
        }
        return CONTINUE;
    }

    public void find() {
        list.forEach(name -> System.out.println(name.toAbsolutePath()));
    }
}
