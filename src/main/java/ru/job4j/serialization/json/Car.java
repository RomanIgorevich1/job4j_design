package ru.job4j.serialization.json;

import java.util.Arrays;
public class Car {
    private boolean type;
    private int year;
    private CreatingCar creatingCar;
    private String[] descriptions;

    public Car(boolean type, int year, CreatingCar creatingCar, String[] descriptions) {
        this.type = type;
        this.year = year;
        this.creatingCar = creatingCar;
        this.descriptions = descriptions;
    }
    /**
     *  для корректного преобразования в строку с помощью org.json необходимо добавить геттеры
     *  */

    public boolean isType() {
        return type;
    }

    public int getYear() {
        return year;
    }

    public CreatingCar getCreatingCar() {
        return creatingCar;
    }

    public String[] getDescriptions() {
        return descriptions;
    }

    @Override
    public String toString() {
        return "Car{"
                + "type=" + type
                + ", year=" + year
                + ", car=" + creatingCar
                + ", descriptions=" + Arrays.toString(descriptions)
                + '}';
    }
}