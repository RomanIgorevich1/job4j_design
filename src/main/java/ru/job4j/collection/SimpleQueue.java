package ru.job4j.collection;

import java.util.NoSuchElementException;

public class SimpleQueue<T> {
    private final SimpleStack<T> in = new SimpleStack<>();
    private final SimpleStack<T> out = new SimpleStack<>();
    private int sizeIn;
    private int sizeOut;

    public T poll() {
        if (sizeIn == 0 && sizeOut == 0) {
            throw new NoSuchElementException();
        }
        if (sizeIn > 1) {
            out.push(in.pop());
            sizeOut++;
            sizeIn--;
        }
        sizeIn--;
        return in.pop();
    }

    public void push(T value) {
        if (sizeOut > sizeIn) {
            while (sizeOut != 0) {
                in.push(out.pop());
                sizeIn++;
                sizeOut--;
            }
        }
        if (sizeIn > 1) {
            while (sizeIn != 1) {
                out.push(in.pop());
                sizeOut++;
                sizeIn--;
            }
        }
        if (sizeIn >= sizeOut) {
            in.push(value);
            sizeIn++;
        }
    }
}