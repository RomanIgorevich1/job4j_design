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

    /**
     * Создаем три переменные предыдущий, текущий, следующий, в цикле проверяем пока элемент не будет равен null,
     * Следующему элементу мы присваиваем значение текущему следующему (newElement = element.next),
     * следующему текущему присваиваем значение предыдущего (element.next = oldElement), значению предыдущего
     * присваиваем значение текущего (oldElement = element), значению текущего присваиваем значение следующего
     * (element = newElement) и голова теперь значение предыдущего (head = oldElement)
     * @return результат
     */
    public boolean revert() {
        boolean result = size > 1;
         if (result) {
             Node<T> oldElement = null;
             Node<T> element = head;
             while (element != null) {
                 Node<T> newElement = element.next;
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