package ru.job4j.generics;

public class Role extends Base {
    private final String protagonist;

    public Role(String id, String protagonist) {
        super(id);
        this.protagonist = protagonist;
    }

    public String getProtagonist() {
        return protagonist;
    }
}
