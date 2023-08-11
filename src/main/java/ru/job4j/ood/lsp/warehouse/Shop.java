package ru.job4j.ood.lsp.warehouse;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Shop extends AbstractStore {
    private List<Food> shop = new ArrayList<>();

    @Override
    public Food find(Food food) {
        return shop.contains(food) ? food : null;
    }

    @Override
    public float use(Food food, LocalDate date) {
        float result = super.use(food, date);
        if (result > 25 && result < 75) {
            shop.add(food);
        } else if (result < 25 && result != 0) {
            food.setPrice(food.getPrice() / food.getDiscount());
            shop.add(food);
        }
        return super.use(food, date);
    }
}
