package ru.job4j.ood.dip.warehouse;

import java.time.LocalDate;
import java.util.List;

public interface Store {
    Food add(Food food);

    long use(Food food, LocalDate date);

    List<Food> getFoodList();
}
