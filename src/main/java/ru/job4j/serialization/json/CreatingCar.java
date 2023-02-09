package ru.job4j.serialization.json;

import java.util.Arrays;

public class CreatingCar {
    private final boolean type;
    private final int year;
    private final Car car;
    private  final String[] descriptions;

    public CreatingCar(boolean type, int year, Car car, String[] descriptions) {
        this.type = type;
        this.year = year;
        this.car = car;
        this.descriptions = descriptions;
    }

    @Override
    public String toString() {
        return "Car{"
                + "type=" + type
                + ", year=" + year
                + ", car=" + car
                + ", descriptions=" + Arrays.toString(descriptions)
                + '}';
    }
}
