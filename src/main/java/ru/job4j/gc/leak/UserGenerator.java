package ru.job4j.gc.leak;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class UserGenerator implements Generate {

    private String pathNames = "src/main/java/ru/job4j/gc/leak/files/names.txt";
    private String pathSurnames = "src/main/java/ru/job4j/gc/leak/files/surnames.txt";
    private String pathPatrons = "src/main/java/ru/job4j/gc/leak/files/patr.txt";
    private List<String> names;
    private List<String> surnames;
    private List<String> patrons;
    private List<User> users = new ArrayList<>();
    private Random random;

    public UserGenerator(Random random) {
        this.random = random;
        readAll();
    }

    /**
     * Генератор пользователей. При создании UserGenerator мы заполним списки с именами, фамилиями, отчествами,
     * а при вызове generate зачищаем список users и будем брать случайные значения из списков.
     * Таким образом каждый раз создаем 1000 пользователей
     */

    @Override
    public void generate() {
        users.clear();
        for (int i = 0; i < 1000; i++) {
            users.add(new User(String.format("%s %s %s", surnames.get(random.nextInt(surnames.size())),
                    names.get(random.nextInt(names.size())), patrons.get(random.nextInt(patrons.size())))
            ));
        }
    }

    private void readAll() {
        try {
            names = read(pathNames);
            surnames = read(pathSurnames);
            patrons = read(pathPatrons);
        } catch (IOException exception) {
            throw new IllegalArgumentException(exception);
        }
    }

    public User randomUser() {
        return users.get(random.nextInt(users.size()));
    }

    public List<User> getUsers() {
        return users;
    }
}
