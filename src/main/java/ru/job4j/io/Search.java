package ru.job4j.io;

import java.io.IOException;
import java.nio.file.*;
import java.util.List;
import java.util.function.Predicate;

public class Search extends SimpleFileVisitor<Path> {
    public static void main(String[] args) throws IOException {
        Search search = new Search();
        search.validation(args);
        Path start = Path.of(args[0]);
        search(start, p -> p.toFile().getName().contains(args[1])).forEach(System.out::println);
    }

    public static List<Path> search(Path root, Predicate<Path> condition) throws IOException {
        SearchFiles searchFiles = new SearchFiles(condition);
        Files.walkFileTree(root, searchFiles);
        return searchFiles.getPaths();
    }

    public void validation(String[] args) {
        if ((args.length == 0) || (args[0] == null || args[1] == null)) {
            throw new IllegalArgumentException("Root is null. Usage ROOT_FOLDER. Or args == null.");
        }
    }
}
