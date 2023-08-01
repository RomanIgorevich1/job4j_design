package ru.job4j.ood.lsp;

public class Bird {

    /**
     * Тут происходит нарушение принципа lsp, вызов метода fly() на объекте chicken не будет корректно
     * работать из-за того что курицы не умеют летать
     */
    public void fly() {
    }

    class Chicken extends Bird {

    }
}

