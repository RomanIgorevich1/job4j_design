package ru.job4j.ood.isp;

public class Rabbit implements Animal {
    @Override
    public void eat() {
        System.out.println("Кушать");
    }

    @Override
    public void jump() {
        System.out.println("Прыгать");
    }

    @Override
    public void run() {
        System.out.println("Бегать");
    }

    class Kangaroo implements Animal {

        @Override
        public void eat() {
            System.out.println("Кушать");
        }

        @Override
        public void jump() {
            System.out.println("Прыгать");
        }

        @Override
        public void run() {
            throw new IllegalArgumentException("Я не умею бегать");
        }
    }

    class Elephant implements Animal {
        @Override
        public void eat() {
            System.out.println("Кушать");
        }

        @Override
        public void jump() {
            throw new IllegalArgumentException("Я не умею прыгать");
        }

        @Override
        public void run() {
            System.out.println("Бегать");
        }
    }
}
