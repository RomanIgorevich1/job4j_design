package ru.job4j.cache;

import java.io.*;
import java.lang.ref.SoftReference;
import java.util.HashMap;
import java.util.Map;

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