package ru.job4j.serialization.json;

public class Car {
    private final String brand;

    public Car(String brand) {
        this.brand = brand;
    }

    @Override
    public String toString() {
        return "Car{"
                + "brand='" + brand + '\''
                + '}';
    }
}
