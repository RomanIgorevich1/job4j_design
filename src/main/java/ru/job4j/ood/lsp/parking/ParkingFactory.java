package ru.job4j.ood.lsp.parking;

import java.util.List;

public interface ParkingFactory {
    boolean add(Transport transport);

    void parkingCar(List<Transport> transports);

    int getPlaceForCar();

    int getParkingSize();
}
