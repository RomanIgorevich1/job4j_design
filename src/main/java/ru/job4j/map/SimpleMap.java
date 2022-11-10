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
        if (count == (capacity * LOAD_FACTOR)) {
            expand();
        }
        if (Objects.equals(key, null)) {
            if (table[0] == null) {
                table[0] = new MapEntry<>(key, value);
                count++;
                modCount++;
                return true;
            }
        } else if (table[indexFor(hash(key.hashCode()))] == null) {
            table[indexFor(hash(key.hashCode()))] = new MapEntry<>(key, value);
            modCount++;
            count++;
            result = true;
        } else {
            return false;
        }
        return result;
    }

    private int hash(int hashCode) {
        return (hashCode == 0) ? 0 : (hashCode) ^ (hashCode >>> 16);
    }

    private int indexFor(int hash) {
        return hash % table.length;
    }

    private void expand() {
        MapEntry<K, V>[] oldTable = table;
        table = new MapEntry[capacity * 2];
        count = 0;
        for (MapEntry<K, V> map : oldTable) {
            if (map != null) {
                table[count++] = new MapEntry<>(map.key, map.value);
            }
        }
    }

    @Override
    public V get(K key) {
        V value = null;
        for (MapEntry<K, V> map : table) {
            if (key == null) {
                if (map.key == null) {
                    value = map.value;
                    break;
                } else {
                    return null;
                }
            }
            if (map != null && map.key != null) {
                if (hash(map.key.hashCode()) == hash(key.hashCode()) && map.key.equals(key)) {
                    value = map.value;
                    break;
                }
            }
        }
        return value;
    }

    @Override
    public boolean remove(K key) {
        boolean result = false;
        for (int i = 0; i < table.length; i++) {
            if ((Objects.equals(key, null))
                    || table[i] != null
                    && (hash(table[i].key.hashCode()) == hash(key.hashCode()) && table[i].key.equals(key))) {
                table[i] = null;
                result = true;
                modCount++;
                count--;
                break;
            }
        }
        return result;
    }

    @Override
    public Iterator<K> iterator() {
        return new Iterator<K>() {
            private int expectedModCount = modCount;
            int point = 0;
            int current = 0;

            @Override
            public boolean hasNext() {
                if (expectedModCount != modCount) {
                    throw new ConcurrentModificationException();
                }
                return point < count;
            }

            @Override
            public K next() {
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }
                while (table[current] == null) {
                    current++;
                }
                point++;
                return table[current++].key;
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
