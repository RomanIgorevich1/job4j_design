package ru.job4j.ood.lsp.parking;

public class Truck implements Transport {

    private static final int TRUCK_SIZE = 2;
    private String name;
    private String color;

    public Truck(String name, String color) {
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
        return TRUCK_SIZE;
    }
}
