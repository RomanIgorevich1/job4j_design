package ru.job4j.collection;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class ForwardLinked<T> implements Iterable<T> {
    private Node<T> head;
    private int size;

    private static class Node<T> {
        T value;
        Node<T> next;

        public Node(T value, Node<T> next) {
            this.value = value;
            this.next = next;
        }
    }

    public void add(T value) {
        Node<T> node = new Node<>(value, null);
        if (head == null) {
            head = node;
            size++;
            return;
        }
        Node<T> tail = head;
        while (tail.next != null) {
            tail = tail.next;
        }
        tail.next = node;
        size++;
    }

    public void addFirst(T value) {
        head = new Node<>(value, head);
        size++;
    }

    public T deleteFirst() {
        if (head == null) {
            throw new NoSuchElementException();
        }
        Node<T> oldElement = head;
        Node<T> newElement = head.next;
        T element = head.value;
        oldElement.value = null;
        oldElement.next = null;
        head = newElement;
        size--;
        return element;
    }

    public boolean revert() {
        boolean result = true;
        if (size <= 1) {
            result = false;
        } else {
            Node<T> oldElement = null;
            Node<T> element = head;
            Node<T> newElement;
            while (element != null) {
                newElement = element.next;
                element.next = oldElement;
                oldElement = element;
                element = newElement;
                head = oldElement;
            }
        }
        return result;
    }

    @Override
    public Iterator<T> iterator() {
        return new Iterator<>() {
            Node<T> node = head;

            @Override
            public boolean hasNext() {
                return node != null;
            }

            @Override
            public T next() {
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }
                T element = node.value;
                node = node.next;
                return element;
            }
        };
    }
}