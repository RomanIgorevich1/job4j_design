package ru.job4j.ood.isp;

public class Auto implements Transport {
    @Override
    public void drive() {
        System.out.println("Начать движение");
    }

    @Override
    public void refuel() {
        System.out.println("Заправиться");
    }

    @Override
    public void honk() {
        System.out.println("Посигналить");
    }

    class ElectricCar implements Transport {

        @Override
        public void drive() {
            System.out.println("Начать движение");
        }

        @Override
        public void refuel() {
            throw new IllegalArgumentException("Не нужно топливо");
        }

        @Override
        public void honk() {
            System.out.println("Посигналить");
        }
    }

    class RemoteControlCar implements Transport {

        @Override
        public void drive() {
            System.out.println("Начать движение");
        }

        @Override
        public void refuel() {
            throw new IllegalArgumentException("Не нужно топливо");
        }

        @Override
        public void honk() {
            throw new IllegalArgumentException("Не может сигналить");
        }
    }
}


