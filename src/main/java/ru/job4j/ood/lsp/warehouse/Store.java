package ru.job4j.ood.lsp.warehouse;

import java.time.LocalDate;
import java.util.List;

public interface Store {
    List<Store> addStore();

    float use(Food food, LocalDate date);

    Food find(Food food);

}
