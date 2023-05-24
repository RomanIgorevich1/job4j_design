package ru.job4j.kiss;

import java.util.Comparator;
import java.util.List;

public class MaxMin {
    public <T> T max(List<T> value, Comparator<T> comparator) {
        return getResult(value, comparator);
    }

    public <T> T min(List<T> value, Comparator<T> comparator) {
        return getResult(value, comparator);
    }

    private <T> T getResult(List<T> listOfNumber, Comparator<T> comparator) {
        listOfNumber.sort(comparator);
        return listOfNumber.get(0);
    }
}
