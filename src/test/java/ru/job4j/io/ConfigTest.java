package ru.job4j.io;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;

class ConfigTest {

    @Test
    void whenPairWithoutComment() {
        String path = "./data/pair_without_comment.properties";
        Config config = new Config(path);
        config.load();
        assertThat(config.value("name")).isEqualTo("Roman");
        assertThat(config.value("name2")).isEqualTo("Petr");
        assertThat(config.value("name6")).isEqualTo("Polina=");
        assertThat(config.value("name7")).isEqualTo("Viktor=1");
    }

    @Test
    void whenKeyException() {
        String path = "./data/key_exceptions.properties";
        Config config = new Config(path);
        assertThatThrownBy(config::load).isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    void whenValueException() {
        String path = "./data/value_exceptions.properties";
        Config config = new Config(path);
        assertThatThrownBy(config::load).isInstanceOf(IllegalArgumentException.class);
    }
}