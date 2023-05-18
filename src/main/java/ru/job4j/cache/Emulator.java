package ru.job4j.cache;

import java.io.File;
import java.lang.ref.SoftReference;
import java.util.Scanner;

public class Emulator {
    private final String menu = """
            1. Указать кэшируемую директорию
            2. Загрузить содержимое файла в кэш
            3. Получить содержимое файла из кэша
            4. Выход""";
    private AbstractCache<String, String> dirFileCache;
    private File name;
    private File address;

    private void showMenu() {
        boolean run = true;
        int directory = 1;
        int download = 2;
        int getFile = 3;
        int exit = 4;
        int choiceName = 1;
        int choiceAddress = 2;
        Scanner scanner = new Scanner(System.in);
        while (run) {
            System.out.println(menu);
            int choice = scanner.nextInt();
            if (choice == directory) {
                System.out.println("Введите директорию:");
                String choiceDirectory = scanner.next();
                dirFileCache = new DirFileCache(choiceDirectory);
                name = new File(String.format("%s%s", choiceDirectory, "/name.txt"));
                address = new File(String.format("%s%s", choiceDirectory, "/address.txt"));
                System.out.println("Директория выбрана.");
            } else if (choice == download) {
                boolean cacheDownload = true;
                while (cacheDownload) {
                    System.out.println("""
                            Какое поле хотите заполнить:\s
                            1. Имя
                            2. Адрес""");
                    int choice2 = scanner.nextInt();
                    if (choice2 == choiceName) {
                        System.out.println("Укажите содержимое файла: ");
                        SoftReference<String> softReference = new SoftReference<>(scanner.next());
                        dirFileCache.put(name.getPath(), softReference.get());
                        System.out.println("Файл добавлен.");
                        cacheDownload = false;
                    }
                    if (choice2 == choiceAddress) {
                        System.out.println("Укажите содержимое файла: ");
                        SoftReference<String> softReference = new SoftReference<>(scanner.next());
                        dirFileCache.put(address.getPath(), softReference.get());
                        System.out.println("Файл добавлен.");
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
