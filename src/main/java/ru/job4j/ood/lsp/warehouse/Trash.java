package ru.job4j.ood.lsp.warehouse;

import java.time.LocalDate;

public class Trash extends AbstractStore {

    @Override
    public long use(Food food, LocalDate date) {
        if (super.use(food, date) == PERCENT_TRASH) {
            add(food);
        }
        return super.use(food, date);
    }
}
