package ru.job4j.serialization.xml;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.StringWriter;
import java.util.Arrays;

@XmlRootElement(name = "car")
@XmlAccessorType(XmlAccessType.FIELD)
public class Car {
    @XmlAttribute
    private boolean type;
    private int year;
    private CreatingCar creatingCar;
    private String[] descriptions;

    public Car() {
    }

    public Car(boolean type, int year, CreatingCar creatingCar, String[] descriptions) {
        this.type = type;
        this.year = year;
        this.creatingCar = creatingCar;
        this.descriptions = descriptions;
    }

    @Override
    public String toString() {
        return "Car{"
                + "type=" + type
                + ", year=" + year
                + ", creatingCar=" + creatingCar
                + ", descriptions=" + Arrays.toString(descriptions)
                + '}';
    }

    public static void main(String[] args) throws JAXBException {
        final Car car = new Car(true, 2019,
                new CreatingCar("Mazda"), new String[] {"CX-5", "Black", "2.5"});
        JAXBContext context = JAXBContext.newInstance(Car.class);
        Marshaller marshaller = context.createMarshaller();
        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
        try (StringWriter writer = new StringWriter()) {
            marshaller.marshal(car, writer);
            String result = writer.getBuffer().toString();
            System.out.println(result);
        } catch (Exception exception) {
            exception.printStackTrace();
        }
    }
}