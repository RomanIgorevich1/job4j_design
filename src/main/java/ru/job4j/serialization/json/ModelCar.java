package ru.job4j.serialization.json;

public class ModelCar {
    private String model;

    public ModelCar(String model) {
        this.model = model;
    }

    public String getModel() {
        return model;
    }

    @Override
    public String toString() {
        return "ModelCar{"
                + "model='" + model + '\''
                + '}';
    }
}
