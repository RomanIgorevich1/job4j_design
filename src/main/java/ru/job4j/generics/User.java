package ru.job4j.generics;

public class User extends Base {
    private final String surname;

    public User(String id, String surname) {
        super(id);
        this.surname = surname;
    }

    public String getSurname() {
        return surname;
    }
}
