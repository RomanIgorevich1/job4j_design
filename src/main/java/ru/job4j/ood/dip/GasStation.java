package ru.job4j.ood.dip;

public class GasStation {
    /**
     * Нарушение DIP т.к привязываем заправку к конкретному виду автомобилей, нужно реализовать интерфейс Auto
     * в котором будет метод refuel(), добавить его в поле класса, тем самым мы сможем убрать зависимость
     * и дать возможность заправке заправлять другие виды автомобилей
     */

    private GasolineCar car;

    void getFuel() {
        car.refuel();
    }

    class GasolineCar {
        void refuel() {

        }
    }
}
