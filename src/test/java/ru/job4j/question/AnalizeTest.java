package ru.job4j.question;

import org.junit.jupiter.api.Test;
import java.util.Set;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

class AnalizeTest {

    @Test
    void whenNotChanged() {
        User user1 = new User(1, "A");
        User user2 = new User(2, "B");
        User user3 = new User(3, "C");
        Set<User> previous = Set.of(user1, user2, user3);
        Set<User> current = Set.of(user1, user2, user3);
        assertThat(Analize.diff(previous, current)).isEqualTo(new Info(0, 0, 0));
    }

    @Test
    void whenOneChanged() {
        User user1 = new User(1, "A");
        User user2 = new User(2, "B");
        User user3 = new User(3, "C");
        Set<User> previous = Set.of(user1, user2, user3);
        Set<User> current = Set.of(user1, new User(2, "BB"), user3);
        assertThat(Analize.diff(previous, current)).isEqualTo(new Info(0, 1, 0));
    }

    @Test
    void whenOneDeleted() {
        User user1 = new User(1, "A");
        User user2 = new User(2, "B");
        User user3 = new User(3, "C");
        Set<User> previous = Set.of(user1, user2, user3);
        Set<User> current = Set.of(user1, user3);
        assertThat(Analize.diff(previous, current)).isEqualTo(new Info(0, 0, 1));
    }

    @Test
    void whenOneAdded() {
        User user1 = new User(1, "A");
        User user2 = new User(2, "B");
        User user3 = new User(3, "C");
        Set<User> previous = Set.of(user1, user2, user3);
        Set<User> current = Set.of(user1, user2, user3, new User(4, "D"));
        assertThat(Analize.diff(previous, current)).isEqualTo(new Info(1, 0, 0));
    }

    @Test
    void whenAllChanged() {
        User user1 = new User(1, "A");
        User user2 = new User(2, "B");
        User user3 = new User(3, "C");
        Set<User> previous = Set.of(user1, user2, user3);
        Set<User> current = Set.of(new User(1, "AA"), user2, new User(4, "D"));
        assertThat(Analize.diff(previous, current)).isEqualTo(new Info(1, 1, 1));
    }

    @Test
    void whenTwoAdded() {
        User user1 = new User(1, "A");
        User user2 = new User(2, "B");
        User user3 = new User(3, "C");
        Set<User> previous = Set.of(user1, user2, user3);
        Set<User> current = Set.of(user1, user2, user3, new User(4, "D"), new User(5, "E"));
        assertThat(Analize.diff(previous, current)).isEqualTo(new Info(2, 0, 0));
    }

    @Test
    void whenTwoAddedAndOneDeleted() {
        User user1 = new User(1, "A");
        User user2 = new User(2, "B");
        User user3 = new User(3, "C");
        Set<User> previous = Set.of(user1, user2, user3);
        Set<User> current = Set.of(user2, user3, new User(4, "D"), new User(5, "E"));
        assertThat(Analize.diff(previous, current)).isEqualTo(new Info(2, 0, 1));
    }
}