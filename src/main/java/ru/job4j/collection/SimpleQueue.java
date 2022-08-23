package ru.job4j.collection;

public class SimpleQueue<T> {
    private final SimpleStack<T> in = new SimpleStack<>();
    private final SimpleStack<T> out = new SimpleStack<>();
    private int size;
    private int secondStackSize;

    public T poll() {
        while (size != 0) {
        out.push(in.pop());
        size--;
        secondStackSize++;
        }
        secondStackSize--;
        return out.pop();
    }

    public void push(T value) {
        while (secondStackSize != 0) {
        in.push(out.pop());
        secondStackSize--;
        size++;
        }
        in.push(value);
        size++;
    }
}
