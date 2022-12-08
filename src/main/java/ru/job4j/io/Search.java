package ru.job4j.io;

import java.io.IOException;
import java.nio.file.*;
import java.util.List;
import java.util.function.Predicate;

public class Search extends SimpleFileVisitor<Path> {
    public static void main(String[] args) throws IOException {
        Path start = Paths.get(".");
        search(start, p -> p.toFile().getName().endsWith(".properties")).forEach(System.out::println);
    }

    public static List<Path> search(Path root, Predicate<Path> condition) throws IOException {
        SearchFiles searchFiles = new SearchFiles(condition);
        Files.walkFileTree(root, searchFiles);
        return searchFiles.getPaths();
    }
}
