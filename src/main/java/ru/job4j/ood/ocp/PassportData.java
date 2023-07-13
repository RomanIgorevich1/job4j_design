package ru.job4j.ood.ocp;

public class PassportData {

    /**
     * Класс в котором происходит сохранение паспортных данных в разные места хранения, что приводит к нарушению
     * принципа OCP, необходимо создать интерфейс и через него производить сохранения
     */
    String name;
    int passportNumber;

    public PassportData(String name, int passportNumber) {
        this.name = name;
        this.passportNumber = passportNumber;
    }

    public void passportSave() {
    }

    public void passportDBSave() {

    }
}
