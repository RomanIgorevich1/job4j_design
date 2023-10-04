package ru.job4j.ood.dip.warehouse;

import java.time.LocalDate;

public class Warehouse extends AbstractStore  {
    @Override
    public long use(Food food, LocalDate date) {
        if (super.use(food, date) > PERCENT_WAREHOUSE) {
            add(food);
        }
        return super.use(food, date);
    }
}