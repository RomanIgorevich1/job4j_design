package ru.job4j.ood.lsp.parking;

import org.junit.jupiter.api.Test;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.assertj.core.api.Assertions.*;

public class ParkingTest {

    @Test
    public void whenAddCar() {
        Parking parking = new Parking();
        Car car1 = new Car("Mazda", "black");
        Car car2 = new Car("Toyota", "red");
        Car car3 = new Car("Volvo", "white");
        Car car4 = new Car("Lada", "white");
        Truck truck1 = new Truck("Gruz", "blue");
        Truck truck2 = new Truck("Gruz2", "black");
        Truck truck3 = new Truck("Gruz3", "red");
        parking.parkingCar(List.of(
                car1, car2, car3, truck1, truck2, car4,
                truck3));
        assertThat(parking.getPlaceForCar()).isEqualTo(2);
    }

    @Test
    public void whenParkingFull() {
        Parking parking = new Parking();
        Car car1 = new Car("Mazda", "black");
        Car car2 = new Car("Toyota", "red");
        Car car3 = new Car("Volvo", "white");
        Car car4 = new Car("Lada", "white");
        Truck truck1 = new Truck("Gruz", "blue");
        Truck truck2 = new Truck("Gruz2", "black");
        Truck truck3 = new Truck("Gruz3", "red");
        Truck truck4 = new Truck("Gruz4", "black");
        parking.parkingCar(List.of(car1, car2, car3, car4, truck1,
                truck2, truck3, truck4));
        assertThat(parking.getParkingSize()).isEqualTo(0);
    }

    @Test
    public void whenNoPlace() {
        Parking parking = new Parking();
        List<Transport> list = new ArrayList<>();
        Car car1 = new Car("Mazda", "black");
        Car car2 = new Car("Toyota", "red");
        Car car3 = new Car("Volvo", "white");
        Car car4 = new Car("Lada", "white");
        Car car5 = new Car("Toyota", "red");
        Truck truck1 = new Truck("Gruz1", "blue");
        Truck truck2 = new Truck("Gruz2", "black");
        Truck truck3 = new Truck("Gruz3", "red");
        Truck truck4 = new Truck("Gruz4", "black");
        Collections.addAll(list, car1, car2, car3, car4, car5, truck1, truck2, truck3, truck4);
        assertThatThrownBy(() -> parking.parkingCar(list)).isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    public void whenFindCar() {
        Parking parking = new Parking();
        Car car1 = new Car("Mazda", "black");
        Car car2 = new Car("Toyota", "red");
        Car car3 = new Car("Volvo", "white");
        Car car4 = new Car("Lada", "white");
        Truck truck1 = new Truck("Gruz", "blue");
        Truck truck2 = new Truck("Gruz2", "black");
        Truck truck3 = new Truck("Gruz3", "red");
        parking.parkingCar(List.of(
                car1, car2, car3, truck1, truck2, car4,
                truck3));
        assertThat(parking.find(car3)).isEqualTo("Volvo");
    }

    @Test
    public void whenCorrectCarCount() {
        Parking parking = new Parking();
        Car car1 = new Car("Mazda", "black");
        Car car2 = new Car("Toyota", "red");
        Car car3 = new Car("Volvo", "white");
        Car car4 = new Car("Lada", "white");
        Truck truck1 = new Truck("Gruz", "blue");
        Truck truck2 = new Truck("Gruz2", "black");
        Truck truck3 = new Truck("Gruz3", "red");
        parking.parkingCar(List.of(
                car1, car2, car3, truck1, truck2, car4,
                truck3));
        assertThat(parking.getAutoMap().values().stream().mapToInt(List::size).sum()).isEqualTo(7);
    }
}