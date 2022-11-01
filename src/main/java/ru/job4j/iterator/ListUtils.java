package ru.job4j.iterator;
import java.util.*;
import java.util.function.Predicate;

public class ListUtils {
    public static <T> void addBefore(List<T> list, int index, T value) {
        Objects.checkIndex(index, list.size());
        ListIterator<T> iterator = list.listIterator();
        do {
            iterator.next();
        }
        while (iterator.nextIndex() != index);
        iterator.add(value);
    }

    public static <T> void addAfter(List<T> list, int index, T value) {
        Objects.checkIndex(index, list.size());
        ListIterator<T> iterator = list.listIterator();
        do {
            iterator.next();
        }
        while (iterator.previousIndex() != index);
        iterator.add(value);
    }

    public static <T> void removeIf(List<T> list, Predicate<T> filter) {
        ListIterator<T> iterator = list.listIterator();
        T value = iterator.next();
        while (iterator.hasNext()) {
            if (filter.test(value)) {
                iterator.remove();
            }
            value = iterator.next();
        }
    }

    public static <T> void replaceIf(List<T> list, Predicate<T> filter, T value) {
        ListIterator<T> iterator = list.listIterator();
        T oldValue = iterator.next();
        do {
            if (filter.test(oldValue)) {
                iterator.set(value);
            }
            oldValue = iterator.next();
        }
        while (iterator.hasNext());

    }

    public static <T> void removeAll(List<T> list, List<T> elements) {
        ListIterator<T> iterator = list.listIterator();
        T value = iterator.next();
        while (iterator.hasNext()) {
            if (elements.contains(value)) {
                iterator.remove();
            }
            value = iterator.next();
        }
    }
}