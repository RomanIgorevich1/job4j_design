package ru.job4j.iterator;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.*;
import java.util.function.Predicate;

import static org.assertj.core.api.Assertions.*;

class ListUtilsTest {
    private List<Integer> input;
    
    @BeforeEach
    void setUp() {
        input = new ArrayList<>(Arrays.asList(1, 3));
    }

    @Test
    void whenAddBefore() {
        ListUtils.addBefore(input, 1, 2);
        assertThat(input).hasSize(3).containsSequence(1, 2, 3);
    }

    @Test
    void whenAddBeforeWithInvalidIndex() {
        assertThatThrownBy(() -> ListUtils.addBefore(input, 3, 2))
                .isInstanceOf(IndexOutOfBoundsException.class);
    }

    @Test
    void whenAddAfter() {
        ListUtils.addAfter(input, 0, 2);
        assertThat(input).hasSize(3).containsSequence(1, 2, 3);
    }

    @Test
    void whenRemoveIf() {
        ListUtils.addBefore(input, 1, 2);
        ListUtils.addBefore(input, 2, 7);
        ListUtils.addAfter(input, 1, 6);
        Predicate<Integer> predicate = number -> number % 2 == 0;
        ListUtils.removeIf(input, predicate);
        assertThat(input).hasSize(3).containsSequence(1, 7, 3);
    }

    @Test
    void whenReplaceIf() {
        ListUtils.addBefore(input, 1, 4);
        ListUtils.addAfter(input, 0, 6);
        Predicate<Integer> predicate = number -> number < 4;
        ListUtils.replaceIf(input, predicate, 5);
        assertThat(input).containsSequence(5, 6, 4, 5);
    }

    @Test
    void whenRemoveAll() {
        ListUtils.addBefore(input, 1, 2);
        ListUtils.addBefore(input, 2, 7);
        ListUtils.addAfter(input, 1, 6);
        List<Integer> list = new ArrayList<>(Arrays.asList(2, 7));
        ListUtils.removeAll(input, list);
        assertThat(input).containsSequence(1, 6, 3);
    }
}