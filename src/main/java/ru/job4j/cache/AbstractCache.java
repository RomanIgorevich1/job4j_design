package ru.job4j.cache;

import java.io.*;
import java.lang.ref.SoftReference;
import java.util.HashMap;
import java.util.Map;

/**
 * То есть необходимо, чтобы можно было задать ключ получения объекта кеша, и, в случае если его нет в памяти,
 * задать поведение загрузки этого объекта в кеш. Создать программу, эмулирующую поведение данного кэша.
 * Программа должна считывать текстовые файлы из системы и выдавать текст при запросе имени файла.
 * Если в кэше файла нет, кэш должен загрузить себе данные. По умолчанию в кеше нет ни одного файла.
 * Текстовые файлы должны лежать в одной директории. Пример: Names.txt, Address.txt - файлы в системе.
 * При запросе по ключу Names.txt - кеш должен вернуть содержимое файла Names.txt.
 * Важно! key это относительный путь к файлу в директории
 */

public abstract class AbstractCache<K, V> {
    private final Map<K, SoftReference<V>> cache = new HashMap<>();

    public final void put(K key, V value) {
        cache.put(key, new SoftReference<>(value));
        get();
    }

    public final V get() {
        V result = null;
        for (Map.Entry<K, SoftReference<V>> map : cache.entrySet()) {
            try (BufferedWriter writer = new BufferedWriter(new FileWriter(map.getKey().toString()))) {
                writer.write(map.getValue().get().toString());
                result = map.getValue().get();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
        return result;
    }

    protected abstract V load(K key);
}
