package ru.job4j.ood.lsp.warehouse;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Trash extends AbstractStore {
    private List<Food> trash = new ArrayList<>();

    @Override
    public Food find(Food food) {
        return trash.contains(food) ? food : null;
    }

    @Override
    public float use(Food food, LocalDate date) {
        if (super.use(food, date) == 0) {
            trash.add(food);
        }
        return super.use(food, date);
    }
}
