package ru.job4j.ood.dip;

public class Zoo {

    /**
     * У нас происходит нарушение DIP так как мы создаем зависимости при создании зоопарка от конкретных животных,
     * для устранения нужно в полях убрать конкретных животных и сделать одно или несколько полей Animal animal,
     * теперь при создании зоопарка можно будет добавлять любых животных
     */

    private Dog dog;
    private Elephant elephant;
    private Monkey monkey;

    public Zoo(Dog dog, Elephant elephant, Monkey monkey) {
        this.dog = dog;
        this.elephant = elephant;
        this.monkey = monkey;
    }

    class Dog extends Animal {

    }

    class Elephant extends Animal {

    }

    class Monkey extends Animal {

    }
}

