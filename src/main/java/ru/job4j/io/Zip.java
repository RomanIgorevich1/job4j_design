package ru.job4j.io;

import java.io.*;
import java.nio.file.Path;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

public class Zip {

    public void packFiles(List<Path> sources, File target) {
        try (ZipOutputStream zip = new ZipOutputStream(new BufferedOutputStream(new FileOutputStream(target)))) {
            for (Path path : sources) {
                zip.putNextEntry(new ZipEntry(path.toString()));
                try (BufferedInputStream out = new BufferedInputStream(new FileInputStream(path.toFile()))) {
                zip.write(out.readAllBytes());
                }
            }

        } catch (IOException exception) {
            exception.printStackTrace();
        }
    }

    public void packSingleFile(File source, File target) {
        try (ZipOutputStream zip = new ZipOutputStream(new BufferedOutputStream(new FileOutputStream(target)))) {
            zip.putNextEntry(new ZipEntry(source.getPath()));
            try (BufferedInputStream out = new BufferedInputStream(new FileInputStream(source))) {
                zip.write(out.readAllBytes());
            }
        } catch (IOException exception) {
            exception.printStackTrace();
        }
    }

    private String[] validation(String[] args) {
        if (args.length < 2) {
            throw new IllegalArgumentException("Root is null. Usage ROOT_FOLDER.");
        }
        int point = 0;
        String[] array = new String[args.length];
        for (String str : args) {
            String[] newArgs = str.split("=", 2);
            array[point++] = newArgs[1];
        }
        File path = new File(array[0]);
        if (args[0].length() < 1) {
            throw new IllegalArgumentException("Parameter length must be greater than 1.");
        }
        if (!path.exists() && !path.isDirectory()) {
            throw new IllegalArgumentException("This path does not exist.");
        }
        if (!array[1].startsWith("*")) {
            throw new IllegalArgumentException("Extension must start with a dot.");
        }
        return array;
    }

    public static void main(String[] args) throws IOException {
        Zip zip = new Zip();
        String[] array = zip.validation(args);
        Path path = Path.of(array[0]);
        zip.packFiles(Search.search(path, value -> !value.toFile().getName().contains(array[1])), new File("project.zip"));
        zip.packSingleFile(
                new File("./pom.xml"),
                new File("./pom.zip")
        );
    }
}
