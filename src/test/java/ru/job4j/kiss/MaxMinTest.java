package ru.job4j.kiss;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

import static org.assertj.core.api.Assertions.*;

class MaxMinTest {

    @Test
    void max() {
        MaxMin  min = new MaxMin();
        List<Integer> list = Arrays.asList(1, 2, 3, 4, 5, 12, 34, 56, 98, 2);
        int result = min.max(list, Comparator.reverseOrder());
        assertThat(result).isEqualTo(98);
    }

    @Test
    void min() {
        MaxMin  min = new MaxMin();
        List<Integer> list = Arrays.asList(1, 2, 3, 4, 5, 12, 34, 56, 98, 2);
        int result = min.min(list, Comparator.naturalOrder());
        assertThat(result).isEqualTo(1);
    }
}