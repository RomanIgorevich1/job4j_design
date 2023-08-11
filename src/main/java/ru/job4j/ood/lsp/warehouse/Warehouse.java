package ru.job4j.ood.lsp.warehouse;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Warehouse extends AbstractStore {
    private List<Food> warehouse = new ArrayList<>();

    @Override
    public Food find(Food food) {
        return warehouse.contains(food) ? food : null;
    }

    @Override
    public float use(Food food, LocalDate date) {
        if (super.use(food, date) > 75) {
            warehouse.add(food);
        }
        return super.use(food, date);
    }
}
