package ru.job4j.collection;

import java.util.*;

public class SimpleArrayList<T> implements SimpleList<T> {
    private T[] container;
    private int size;
    private int modCount;

    public SimpleArrayList(int capacity) {
        this.container = (T[]) new Object[capacity];
    }

    @Override
    public void add(T value) {
        if (size == container.length) {
            container = grow();
        }
        container[size] = value;
        size++;
        modCount++;
    }

    @Override
    public T set(int index, T newValue) {
        T oldValue = container[index];
        Objects.checkIndex(index, container.length);
        container[index] = newValue;
        return oldValue;
    }

    @Override
    public T remove(int index) {
        T oldValue = container[index];
        Objects.checkIndex(index, container.length);
        System.arraycopy(container, index + 1, container, index, container.length - index - 1);
        container[container.length - 1] = null;
        modCount++;
        size--;
        return oldValue;
    }

    @Override
    public T get(int index) {
        if (index > container.length || size < index) {
            throw new IndexOutOfBoundsException();
        }
        Objects.checkIndex(index, container.length);
        return container[index];
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public Iterator<T> iterator() {
        return new Iterator<>() {
            int expectedModCount = modCount;
            int point = 0;

            public boolean hasNext() {
                return point < size;
            }

            @Override
            public T next() {
                if (expectedModCount != modCount) {
                    throw new ConcurrentModificationException();
                }
                if (size < container.length) {
                    throw new NoSuchElementException();
                }
                return container[point++];
            }
        };
    }

    public T[] grow() {
        return Arrays.copyOf(container, container.length * 2);
    }

}
