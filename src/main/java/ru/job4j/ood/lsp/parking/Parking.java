package ru.job4j.ood.lsp.parking;

import java.util.ArrayList;
import java.util.List;

public class Parking implements ParkingFactory {

    private List<Transport> transportList = new ArrayList<>();
     private int placeForCar = 10;
     private int placeForTruck = 5;
     private int parkingSize = 15;

    @Override
    public void add(Transport transport) {
        transportList.add(transport);
        parkingSize--;
    }

    @Override
    public void parkingCar(List<Transport> transports) {
        if (parkingSize != 0) {
            for (Transport car : transports) {
                if (car.getSize() == 1) {
                    if (placeForCar != 0) {
                        add(car);
                        placeForCar--;
                    }
                }
                if (car.getSize() > 1) {
                    if (placeForTruck == 0 && placeForCar > 1) {
                        add(car);
                        placeForCar -= 2;
                        parkingSize--;
                    }
                    if (placeForTruck != 0) {
                        add(car);
                        placeForTruck--;
                    }
                }
            }
        }
    }

    @Override
    public int getPlaceForCar() {
        return placeForCar;
    }

    public boolean find(Transport transport) {
        return transportList.contains(transport);
    }
}
