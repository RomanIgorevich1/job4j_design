package ru.job4j.cache;

import java.io.File;
import java.lang.ref.SoftReference;
import java.util.Scanner;

public class Emulator {
    private String menu = """
            1. Указать кэшируемую директорию
            2. Загрузить содержимое файла в кэш
            3. Получить содержимое файла из кэша
            4. Выход""";
    private Integer directory = 1;
    private Integer download = 2;
    private Integer getFile = 3;
    private Integer exit = 4;
    private Integer choiceName = 1;
    private Integer choiceAddress = 2;

    private void showMenu() {
        boolean run = true;
        DirFileCache dirFileCache = new DirFileCache("./data");
        AbstractCache<String, String> abstractCache = new DirFileCache("./data");
        File name = new File("./data/name.txt");
        File address = new File("./data/address.txt");
        Scanner scanner = new Scanner(System.in);
        while (run) {
            System.out.println(menu);
            int choice = scanner.nextInt();
            if (choice == directory) {
                abstractCache.get();
            } else if (choice == download) {
                boolean cacheDownload = true;
                while (cacheDownload) {
                    System.out.println("""
                            Какое поле хотите заполнить:\s
                            1. Имя
                            2. Адрес""");
                    int choice2 = scanner.nextInt();
                    if (choice2 == choiceName) {
                        System.out.println("Укажите содержимое файла ");
                        String save = scanner.next();
                        SoftReference<String> softReference = new SoftReference<>(save);
                        abstractCache.put(name.getPath(), softReference.get());
                        System.out.println("Файл добавлен");
                        cacheDownload = false;
                    }
                    if (choice2 == choiceAddress) {
                        System.out.println("Укажите содержимое файла ");
                        String save = scanner.next();
                        SoftReference<String> softReference = new SoftReference<>(save);
                        abstractCache.put(address.getPath(), softReference.get());
                        System.out.println("Файл добавлен");
                        cacheDownload = false;
                    }
                }
            } else if (choice == getFile) {
                boolean cacheDownload = true;
                while (cacheDownload) {
                    System.out.println("""
                            Укажите ключ:\s
                            1. Имя
                            2. Адрес""");
                    int choice2 = scanner.nextInt();
                    if (choice2 == choiceName) {
                        System.out.println(dirFileCache.load(name.getPath()));
                        cacheDownload = false;
                    }
                    if (choice2 == choiceAddress) {
                        System.out.println(dirFileCache.load(address.getPath()));
                        cacheDownload = false;
                    }
                }
            } else if (choice == exit) {
                System.out.println("Завершение работы");
                run = false;
            } else {
                System.out.println("Не верная команда");
            }
        }
    }

    public static void main(String[] args) {
        Emulator emulator = new Emulator();
        emulator.showMenu();
    }
}
