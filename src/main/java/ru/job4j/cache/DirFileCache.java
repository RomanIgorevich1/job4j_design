package ru.job4j.cache;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;

/**
 * Необходимо, чтобы можно было задать ключ получения объекта кеша, и, в случае если его нет в памяти,
 * задать поведение загрузки этого объекта в кеш.
 * Программа должна считывать текстовые файлы из системы и выдавать текст при запросе имени файла.
 * Если в кэше файла нет, кэш должен загрузить себе данные. По умолчанию в кеше нет ни одного файла.
 * Текстовые файлы должны лежать в одной директории. Пример: Names.txt, address.txt - файлы в системе.
 * При запросе по ключу Names.txt - кеш должен вернуть содержимое файла Names.txt.
 * Важно! key это относительный путь к файлу в директории
 */

public class DirFileCache extends AbstractCache<String, String> {
    private final String cachingDir;

    public DirFileCache(String cachingDir) {
        this.cachingDir = cachingDir;
    }

    @Override
    protected String load(String key) {
        String result = "";
        try (BufferedReader reader = new BufferedReader(new FileReader(key))) {
                result = reader.readLine();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return result;
    }
}
