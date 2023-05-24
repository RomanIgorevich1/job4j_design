package ru.job4j.ood.srp;

public class CarDataBase {
    /**
     * Класс в котором происходит помимо добавления машин в базу данных, также их создание и изменение, что приводит
     * к нарушению принципа SRP, необходимо отделить создание объектов от добавления
     */
    private String model;
    private int seat;
    private int speed;
    private int engine;

    private int count;

    public CarDataBase(String model, int seat, int speed) {
        this.model = model;
        this.seat = seat;
        this.speed = speed;
    }

    public int buildingRacingCar(int maxSpeed) {
        return maxSpeed * 2;
    }

    public int buildingBus(int numberSeat) {
        return numberSeat * 4;
    }

    public void createdEngine() {

    }

    public void addCar() {

    }
}
