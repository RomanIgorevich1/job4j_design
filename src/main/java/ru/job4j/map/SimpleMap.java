package ru.job4j.map;

import java.util.ConcurrentModificationException;
import java.util.*;
import java.util.Iterator;
import java.util.Objects;

public class SimpleMap<K, V> implements Map<K, V> {
    private static final float LOAD_FACTOR = 0.75f;
    private int capacity = 8;
    private int count = 0;
    private int modCount = 0;
    private MapEntry<K, V>[] table = new MapEntry[capacity];

    @Override
    public boolean put(K key, V value) {
        boolean result = false;
        if (count == capacity * LOAD_FACTOR) {
            expand();
        }
        if (Objects.equals(key, null)) {
            if (table[0] == null) {
                table[0] = new MapEntry<>(null, value);
                count++;
                modCount++;
                result = true;
            }
        }
        if (!Objects.equals(key, null) && table[indexFor(hash(key.hashCode()))] == null) {
            findIndexAndAdd(key, value);
            modCount++;
            count++;
            result = true;
        }
        return result;
    }

    private int hash(int hashCode) {
        return hashCode * 31 * Objects.hash(capacity);
    }

    private int indexFor(int hash) {
        return hash % capacity;
    }

    private void expand() {
        capacity = 16;
        MapEntry<K, V>[] oldTable = table;
        table = new MapEntry[capacity];
        for (MapEntry<K, V> map : oldTable) {
            if (map == null) {
                continue;
            }
            if (map.key == null) {
                table[0] = map;
                continue;
            }
            findIndexAndAdd(map.key, map.value);
        }
    }

    private void findIndexAndAdd(K key, V value) {
        table[indexFor(hash(key.hashCode()))] = new MapEntry<>(key, value);
    }

    private boolean compare(K key) {
        return hash(key.hashCode()) == hash(table[indexFor(key.hashCode())].key.hashCode())
                && table[indexFor(key.hashCode())].key.equals(key);
    }

    @Override
    public V get(K key) {
        V value = null;
        if (key == null && table[0].key == null) {
            value = table[0].value;
        } else if (key != null && table[0] != null && table[indexFor(key.hashCode())] == table[0]) {
            value = null;
        }
        if (key != null && table[indexFor(key.hashCode())] != null && table[indexFor(key.hashCode())] != table[0]
                && compare(key)) {
            value = table[indexFor(key.hashCode())].value;
        }
        if (key != null && table[indexFor(key.hashCode())] == null) {
            value = null;
        }
        return value;
    }

    @Override
    public boolean remove(K key) {
        boolean result = false;
        if (Objects.equals(key, null) && table[0].key == null) {
            table[0] = null;
            count--;
            modCount++;
            result = true;
        }
        if (key != null && table[indexFor(key.hashCode())] != null && compare(key)) {
            table[indexFor(key.hashCode())] = null;
            count--;
            modCount++;
            result = true;
        }
        return result;
    }

    @Override
    public Iterator<K> iterator() {
        return new Iterator<>() {
            private int expectedModCount = modCount;
            int point = 0;

            @Override
            public boolean hasNext() {
                if (expectedModCount != modCount) {
                    throw new ConcurrentModificationException();
                }
                boolean result = false;
                if (point < table.length) {
                    for (int i = point; i < table.length; i++) {
                        if (table[i] != null) {
                            point = i;
                            result = true;
                            break;
                        }
                    }
                }
                return result;
            }

            @Override
            public K next() {
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }
                return table[point++].key;
            }
        };
    }

    private static class MapEntry<K, V> {
        K key;
        V value;

        public MapEntry(K key, V value) {
            this.key = key;
            this.value = value;
        }
    }
}
