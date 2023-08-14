package ru.job4j.ood.lsp.warehouse;

import java.time.LocalDate;

public interface Store {

    Food add(Food food);

    long use(Food food, LocalDate date);
}
