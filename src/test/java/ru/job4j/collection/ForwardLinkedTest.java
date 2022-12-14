package ru.job4j.collection;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Iterator;
import java.util.NoSuchElementException;

class ForwardLinkedTest {
    private ForwardLinked<Integer> linked;
    private ForwardLinked<Integer> forwardLinked;

    @BeforeEach
    public void init() {
        forwardLinked = new ForwardLinked<>();
        linked = new ForwardLinked<>();
        linked.add(1);
        linked.add(2);
        linked.add(3);
        linked.add(4);
    }

    @Test
    void whenDeleteFirst() {
        assertThat(linked.deleteFirst()).isEqualTo(1);
        assertThat(linked.deleteFirst()).isEqualTo(2);
        assertThat(linked.deleteFirst()).isEqualTo(3);
        assertThat(linked.deleteFirst()).isEqualTo(4);
        assertThatThrownBy(linked.iterator()::next)
                .isInstanceOf(NoSuchElementException.class);
    }

    @Test
    void whenDeleteEmptyLinked() {
        ForwardLinked<Integer> linked = new ForwardLinked<>();
        assertThatThrownBy(linked::deleteFirst)
                .isInstanceOf(NoSuchElementException.class);
    }

    @Test
    void whenMultiDelete() {
        linked.deleteFirst();
        Iterator<Integer> iterator = linked.iterator();
        assertThat(iterator.next()).isEqualTo(2);
    }

    @Test
    void whenSize0ThenReturnFalse() {
        assertThat(forwardLinked.revert()).isFalse();
    }

    @Test
    void whenSize1ThenReturnFalse() {
        forwardLinked.add(1);
        assertThat(forwardLinked.revert()).isFalse();
    }

    @Test
    void whenAddAndRevertTrue() {
        forwardLinked.add(1);
        forwardLinked.add(2);
        forwardLinked.add(3);
        forwardLinked.add(4);
        assertThat(forwardLinked).containsSequence(1, 2, 3, 4);
        assertThat(forwardLinked.revert()).isTrue();
        assertThat(forwardLinked).containsSequence(4, 3, 2, 1);
    }
}