package ru.job4j.iterator;

import org.junit.jupiter.api.Test;
import java.util.NoSuchElementException;
import static org.assertj.core.api.Assertions.*;

class MatrixItTest {

    @Test
    void when4El() {
        int[][] in = {{1}};
        MatrixIt matrixIt = new MatrixIt(in);
        assertThat(matrixIt.next()).isEqualTo(1);
    }

    @Test
    void whenFirstEmptyThenNext() {
        int[][] in = {{}, {1}};
        MatrixIt matrixIt = new MatrixIt(in);
        assertThat(matrixIt.next()).isEqualTo(1);
    }

    @Test
    void whenFirstEmptyThenHashNext() {
        int[][] in = {{}, {1}};
        MatrixIt matrixIt = new MatrixIt(in);
        assertThat(matrixIt.hasNext()).isTrue();
    }

    @Test
    void whenRowHasDiffSize() {
        int[][] in = {
                {1},
                {2, 3},
                {},
                {},
                {4}};
        MatrixIt matrixIt = new MatrixIt(in);
        assertThat(matrixIt.next()).isEqualTo(1);
        assertThat(matrixIt.next()).isEqualTo(2);
        assertThat(matrixIt.next()).isEqualTo(3);
        assertThat(matrixIt.next()).isEqualTo(4);
        assertThat(matrixIt.hasNext()).isFalse();
    }

    @Test
    void whenFewEmpty() {
        int[][] in = {{}};
        MatrixIt matrixIt = new MatrixIt(in);
        assertThat(matrixIt.hasNext()).isFalse();
    }

    @Test
    void whenEmptyThenNext() {
        int[][] in = {{}};
        MatrixIt matrixIt = new MatrixIt(in);
        assertThatThrownBy(matrixIt::next)
                .isInstanceOf(NoSuchElementException.class);
    }

    @Test
    void whenMultiHashNext() {
        int[][] in = {{}, {1}};
        MatrixIt matrixIt = new MatrixIt(in);
        assertThat(matrixIt.hasNext()).isTrue();
        assertThat(matrixIt.hasNext()).isTrue();
    }

    @Test
    void whenNoElements() {
        int[][] in = {{}, {}, {}};
        MatrixIt matrixIt = new MatrixIt(in);
        assertThat(matrixIt.hasNext()).isFalse();
    }
}