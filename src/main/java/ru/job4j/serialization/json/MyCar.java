package ru.job4j.serialization.json;

import java.util.Arrays;

public class MyCar {
    private boolean move;
    private int year;
    private ModelCar modelCar;
    private String[] descriptions;

    public MyCar(boolean move, int year, ModelCar modelCar, String[] descriptions) {
        this.move = move;
        this.year = year;
        this.modelCar = modelCar;
        this.descriptions = descriptions;
    }

    public boolean isMove() {
        return move;
    }

    public int getYear() {
        return year;
    }

    public ModelCar getModelCar() {
        return modelCar;
    }

    public String[] getDescriptions() {
        return descriptions;
    }

    @Override
    public String toString() {
        return "MyCar{"
                + "move=" + move
                + ", year=" + year
                + ", modelCar=" + modelCar
                + ", description=" + Arrays.toString(descriptions)
                + '}';
    }
}