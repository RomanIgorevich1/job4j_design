package ru.job4j.ood.lsp.parking;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.*;

public class ParkingTest {

    @Test
    public void whenAddCar() {
        Parking parking1 = new Parking();
        Car car1 = new Car("Mazda", "black");
        Car car2 = new Car("Toyota", "red");
        Car car3 = new Car("Volvo", "white");
        Car car4 = new Car("Lada", "white");
        Truck truck1 = new Truck("Gruz", "blue");
        Truck truck2 = new Truck("Gruz2", "black");
        Truck truck3 = new Truck("Gruz3", "red");
        Truck truck4 = new Truck("Gruz4", "black");
        Truck truck5 = new Truck("Gruz5", "white");
        Truck truck6 = new Truck("Gruz6", "black");
        parking1.parkingCar(List.of(
                car1, car2, car3, truck1, truck2, car4,
                truck3, truck4, truck5, truck6));
        assertThat(parking1.find(car3)).isTrue();
        assertThat(parking1.getPlaceForCar()).isEqualTo(4);
    }
}