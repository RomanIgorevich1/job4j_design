package ru.job4j.collection;

import java.util.*;

public class SimpleLinkedList<E> implements LinkedList<E> {
    private Node<E> last;
    private Node<E> first;
    private int size = 0;
    private int modCount;

    private static class Node<E> {
        E item;
        Node<E> next;

        Node(E item, Node<E> next) {
            this.item = item;
            this.next = next;
        }
    }

    @Override
    public void add(E value) {
        Node<E> current = last;
        Node<E> element = new Node<>(value, null);
        last = element;
        if (current == null) {
            first = element;
        } else {
            current.next = element;
        }
        size++;
        modCount++;
    }

    @Override
    public E get(int index) {
        Objects.checkIndex(index, size);
        E element = first.item;
        Node<E> newElement = first;
        for (int i = 0; i < index; i++) {
            newElement = newElement.next;
            element = newElement.item;
        }
        return element;
    }

    @Override
    public Iterator<E> iterator() {
        return new Iterator<E>() {
            private int expectedModCount = modCount;
            Node<E> newElement = first;
            @Override
            public boolean hasNext() {
                if (expectedModCount != modCount) {
                    throw new ConcurrentModificationException();
                }
                return newElement != null;
            }

            @Override
            public E next() {
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }
                E element = newElement.item;
                newElement = newElement.next;
                return element;
            }
        };
    }
}
