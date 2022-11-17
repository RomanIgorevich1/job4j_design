package ru.job4j.map;

import java.util.ConcurrentModificationException;
import java.util.*;
import java.util.Iterator;

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
        if (table[findIndexByKey(key)] == null) {
            findIndexAndAdd(key, value);
            result = true;
            modCount++;
            count++;
        }
        return result;
    }

    private int hash(int hashCode) {
        return hashCode ^ (hashCode >>> 16);
    }

    private int indexFor(int hash) {
        return hash % capacity;
    }

    private void expand() {
        capacity *= 2;
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
        table[findIndexByKey(key)] = new MapEntry<>(key, value);
    }

    private boolean compare(K key) {
        boolean result = false;
        if (key == null && table[0].key == null) {
            result = true;
        } else {
            result = (table[findIndexByKey(key)] != null  && table[findIndexByKey(key)].key == key)
                    && hash(key.hashCode()) == hash(table[findIndexByKey(key)].key.hashCode())
                    && table[findIndexByKey(key)].key.equals(key);
        }
        return  result;
    }

    private int findIndexByKey(K key) {
        return key == null ? 0 : indexFor(hash(key.hashCode()));
    }

    @Override
    public V get(K key) {
        V value = null;
        if (compare(key)) {
            value = table[findIndexByKey(key)].value;
        }
        return value;
    }

    @Override
    public boolean remove(K key) {
        boolean result = false;
        if (compare(key)) {
            table[findIndexByKey(key)] = null;
            result = true;
            modCount++;
            count--;
        }
        return result;
    }

    @Override
    public Iterator<K> iterator() {
        return new Iterator<>() {
            private int expectedModCount = modCount;
            int index = 0;

            @Override
            public boolean hasNext() {
                if (expectedModCount != modCount) {
                    throw new ConcurrentModificationException();
                }
                boolean result = false;
                if (index < table.length) {
                    for (int i = index; i < table.length; i++) {
                        if (table[i] != null) {
                            index = i;
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
                return table[index++].key;
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
