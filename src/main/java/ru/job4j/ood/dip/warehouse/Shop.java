package ru.job4j.ood.dip.warehouse;

import java.time.LocalDate;

public class Shop extends AbstractStore  {
    @Override
    public long use(Food food, LocalDate date) {
        float result = super.use(food, date);
        if (result > PERCENT_SHOP && result <  PERCENT_WAREHOUSE) {
            add(food);
        } else if (result < PERCENT_SHOP && result != PERCENT_TRASH) {
            food.setPrice(food.getPrice() / food.getDiscount());
            add(food);
        }
        return super.use(food, date);
    }
}
