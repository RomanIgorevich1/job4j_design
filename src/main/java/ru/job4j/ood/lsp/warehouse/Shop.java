package ru.job4j.ood.lsp.warehouse;

import java.time.LocalDate;

public class Shop extends AbstractStore {

    @Override
    public Food add(Food food) {
        getFoodList().add(food);
        return food;
    }

    @Override
    public long use(Food food, LocalDate date) {
        float result = super.use(food, date);
        if (result > getPercentShop() && result < getPercentWarehouse()) {
            add(food);
        } else if (result < getPercentShop() && result != getPercentTrash()) {
            food.setPrice(food.getPrice() / food.getDiscount());
            add(food);
        }
        return super.use(food, date);
    }
}
