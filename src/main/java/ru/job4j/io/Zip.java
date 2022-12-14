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

    private void validation(Path path, File extension, String[] args) {
        if (args.length != 3) {
            throw new IllegalArgumentException("Root is null. Usage ROOT_FOLDER.");
        }
        if (path.toString().length() < 1) {
            throw new IllegalArgumentException("Parameter length must be greater than 1.");
        }
        if (!path.toFile().exists() && !path.toFile().isDirectory()) {
            throw new IllegalArgumentException("This path does not exist.");
        }
        if (!extension.getName().startsWith("*")) {
            throw new IllegalArgumentException("Extension must start with a dot.");
        }
    }

    public static void main(String[] args) throws IOException {
        Zip zip = new Zip();
        Path path = Path.of(ArgsName.of(args).get("d"));
        File extension = new File(ArgsName.of(args).get("e"));
        File archive = new File(ArgsName.of(args).get("o"));
        zip.validation(path, extension, args);
        zip.packFiles(Search.search(path, value -> !value.toFile().getName().contains(extension.getName())), new File(archive.getName()));
        zip.packSingleFile(
                new File("./pom.xml"),
                new File("./pom.zip")
        );
    }
}
