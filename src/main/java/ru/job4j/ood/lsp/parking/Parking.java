package ru.job4j.ood.lsp.parking;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Parking implements ParkingFactory {

    private Map<Integer, List<String>> autoMap = new HashMap<>();
    private int placeForCar = 8;
    private int placeForTruck = 2;
    private int parkingSize = 10;

    @Override
    public boolean add(Transport transport) {
        parkingSize--;
        boolean result = false;
        int size = transport.getSize();
        if (autoMap.isEmpty() || !autoMap.containsKey(size)) {
            autoMap.put(size, new ArrayList<>());
        }
        for (Map.Entry<Integer, List<String>> name : autoMap.entrySet()) {
            if (name.getKey() == size) {
                name.getValue().add(transport.getName());
                result = true;
            }
        }
        return result;
    }

    @Override
    public void parkingCar(List<Transport> transports) {
        if (parkingSize != 0) {
            for (Transport car : transports) {
                if (car.getSize() == 1) {
                    if (placeForCar != 0) {
                        add(car);
                        placeForCar--;
                    } else {
                        throw new IllegalArgumentException("Нет свободных мест");
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
                    if (placeForCar == 1) {
                        throw new IllegalArgumentException("Нет свободных мест");
                    }
                }
            }
        } else {
            throw new IllegalArgumentException("Нет свободных мест");
        }
    }

    @Override
    public int getPlaceForCar() {
        return placeForCar;
    }

    @Override
    public int getParkingSize() {
        return parkingSize;
    }

    public Map<Integer, List<String>> getAutoMap() {
        return autoMap;
    }

    public String find(Transport transport) {
        String newTransport = " ";
        if (autoMap.containsKey(transport.getSize())) {
            for (Map.Entry<Integer, List<String>> name : autoMap.entrySet()) {
                if (name.getValue().contains(transport.getName())) {
                    newTransport = transport.getName();
                    break;
                }
            }
        }
        return newTransport;
    }
}
