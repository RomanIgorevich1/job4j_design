package ru.job4j.iterator;
import java.util.*;
import java.util.function.Predicate;

public class ListUtils {
    public static <T> void addBefore(List<T> list, int index, T value) {
        Objects.checkIndex(index, list.size());
        ListIterator<T> iterator = list.listIterator();
        while (iterator.hasNext()) {
            if (iterator.nextIndex() == index) {
                iterator.add(value);
                break;
            }
            iterator.next();
        }
    }

    public static <T> void addAfter(List<T> list, int index, T value) {
        Objects.checkIndex(index, list.size());
        ListIterator<T> iterator = list.listIterator();
        while (iterator.hasNext()) {
            if (iterator.previousIndex() == index) {
                iterator.add(value);
                break;
            }
            iterator.next();
        }
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
        while (iterator.hasNext()) {
            if (filter.test(oldValue)) {
                iterator.set(value);
            }
            oldValue = iterator.next();
            if (!iterator.hasNext() && filter.test(oldValue)) {
                iterator.set(value);
            }
        }

    }

    public static <T> void removeAll(List<T> list, List<T> elements) {
        ListIterator<T> iterator = list.listIterator();
        T listValue = iterator.next();
        while (iterator.hasNext()) {
            for (T element : elements) {
                if (listValue.equals(element)) {
                    iterator.remove();
                }
            }
            listValue = iterator.next();
        }
    }
}
