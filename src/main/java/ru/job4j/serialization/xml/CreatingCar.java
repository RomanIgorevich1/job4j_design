package ru.job4j.serialization.xml;

import javax.xml.bind.annotation.*;

@XmlRootElement(name = "creatingCar")
public class CreatingCar {
    @XmlAttribute
    private String brand;

    public CreatingCar() {
    }

    public CreatingCar(String brand) {
        this.brand = brand;
    }

    @Override
    public String toString() {
        return "CreatingCar{"
                + "brand='" + brand + '\''
                + '}';
    }
}
