package ru.job4j.ood.lsp.warehouse;

import java.time.LocalDate;

public class Warehouse extends AbstractStore {

    @Override
    public Food add(Food food) {
        getFoodList().add(food);
        return food;
    }

    @Override
    public long use(Food food, LocalDate date) {
        if (super.use(food, date) > getPercentWarehouse()) {
            add(food);
        }
        return super.use(food, date);
    }
}
