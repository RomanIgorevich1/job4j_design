package ru.job4j.generics;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.*;

class RoleStoreTest {

    @Test
    void whenAddAndFindThenUsernameIsPetr() {
        RoleStore store = new RoleStore();
        store.add(new Role("1", "Шрек"));
        store.add(new Role("2", "Осел"));
        store.add(new Role("3", "Фиона"));
        Role result = store.findById("2");
        assertThat(result.getProtagonist()).isEqualTo("Осел");
    }

    @Test
    void whenAddAndFindThenUserIsNull() {
        RoleStore store = new RoleStore();
        store.add(new Role("1", "Шрек"));
        store.add(new Role("2", "Осел"));
        Role result = store.findById("3");
        assertThat(result).isNull();
    }

    @Test
    void whenAddDuplicateAndFindUsernameIsPetr() {
        RoleStore store = new RoleStore();
        store.add(new Role("1", "Шрек"));
        store.add(new Role("1", "Кот в сапогах"));
        Role result = store.findById("1");
        assertThat(result.getProtagonist()).isEqualTo("Шрек");
    }

    @Test
    void whenReplaceThenUsernameIsMaxim() {
        RoleStore store = new RoleStore();
        store.add(new Role("1", "Шрек"));
        store.replace("1", new Role("1", "Кот в сапогах"));
        Role result = store.findById("1");
        assertThat(result.getProtagonist()).isEqualTo("Кот в сапогах");
    }

    @Test
    void whenNoReplaceUserThenNoChangeUsername() {
        RoleStore store = new RoleStore();
        store.add(new Role("1", "Шрек"));
        store.replace("10", new Role("10", "Кот в сапогах"));
        Role result = store.findById("1");
        assertThat(result.getProtagonist()).isEqualTo("Шрек");
    }

    @Test
    void whenDeleteUserThenUserIsNull() {
        RoleStore store = new RoleStore();
        store.add(new Role("1", "Шрек"));
        store.add(new Role("2", "Осел"));
        store.add(new Role("3", "Фиона"));
        store.add(new Role("4", "Кот в сапогах"));
        store.delete("3");
        Role result = store.findById("3");
        assertThat(result).isNull();
    }

    @Test
    void whenNoDeleteUserThenUsernameIsPetr() {
        RoleStore store = new RoleStore();
        store.add(new Role("1", "Шрек"));
        store.add(new Role("2", "Осел"));
        store.delete("10");
        Role result = store.findById("1");
        assertThat(result.getProtagonist()).isEqualTo("Шрек");
    }
}