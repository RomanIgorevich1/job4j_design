package ru.job4j.iterator;

import static org.assertj.core.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Iterator;
import java.util.NoSuchElementException;

class EvenNumbersIteratorTest {
    private Iterator<Integer> it;

    @BeforeEach
    public void setUp() {
        it = new EvenNumbersIterator(new int[] {1, 2, 3, 4, 5, 6, 7});
    }

    @Test
    void whenManyNext() {
        assertThat(it.next()).isEqualTo(2);
        assertThat(it.next()).isEqualTo(4);
        assertThat(it.next()).isEqualTo(6);
        assertThat(it.hasNext()).isFalse();
    }

    @Test
    void shouldReturnEvenNumbersSequentially() {
        assertThat(it.hasNext()).isTrue();
        assertThat(it.next()).isEqualTo(2);
        assertThat(it.hasNext()).isTrue();
        assertThat(it.next()).isEqualTo(4);
        assertThat(it.hasNext()).isTrue();
        assertThat(it.next()).isEqualTo(6);
        assertThat(it.hasNext()).isFalse();
        assertThatThrownBy(it::next)
                .isInstanceOf(NoSuchElementException.class);
    }

    @Test
    void sequentialHasNextInvocationDoesntAffectRetrievalOrder() {
        assertThat(it.hasNext()).isTrue();
        assertThat(it.hasNext()).isTrue();
        assertThat(it.next()).isEqualTo(2);
        assertThat(it.next()).isEqualTo(4);
        assertThat(it.next()).isEqualTo(6);
    }

    @Test
    void  shouldReturnFalseIfNoAnyNumbers() {
        it = new EvenNumbersIterator(new int[]{});
        assertThat(it.hasNext()).isFalse();
    }

    @Test
    void shouldReturnFalseIfNoAnyEvenNumbers() {
        it = new EvenNumbersIterator(new int[] {2, 4, 6, 8});
        assertThat(it.hasNext()).isTrue();
        assertThat(it.next()).isEqualTo(2);
        assertThat(it.hasNext()).isTrue();
        assertThat(it.next()).isEqualTo(4);
        assertThat(it.hasNext()).isTrue();
        assertThat(it.next()).isEqualTo(6);
        assertThat(it.hasNext()).isTrue();
        assertThat(it.next()).isEqualTo(8);
        assertThat(it.hasNext()).isFalse();
    }

    @Test
    void allNumbersAreOdd() {
        it = new EvenNumbersIterator(new int[] {1, 3, 5, 7});
        assertThat(it.hasNext()).isFalse();
        assertThat(it.hasNext()).isFalse();
    }
}