package ru.job4j.serialization.json;

public class CreatingCar {
    private String brand;

    public CreatingCar(String brand) {
        this.brand = brand;
    }

    public String getBrand() {
        return brand;
    }

    @Override
    public String toString() {
        return "CreatingCar{"
                + "brand='" + brand + '\''
                + '}';
    }
}
