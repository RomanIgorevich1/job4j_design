package ru.job4j.ood.lsp.parking;

public class Car implements Transport {

    private static final int CAR_SIZE = 1;
    private String name;

    private String color;

    public Car(String name, String color) {
        this.name = name;
        this.color = color;
    }

    public String getName() {
        return name;
    }

    public String getColor() {
        return color;
    }

    @Override
    public int getSize() {
        return CAR_SIZE;
    }
}
