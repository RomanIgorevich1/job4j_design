package ru.job4j.ood.lsp.warehouse;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;

public abstract class AbstractStore implements Store {
    /**
     * Реализует общую логику хранилищ
     */
    private List<Food> foodList = new ArrayList<>();

    @Override
    public Food add(Food food) {
        foodList.add(food);
        return food;
    }

    @Override
    public long use(Food food, LocalDate date) {
        long maxTerm = ChronoUnit.DAYS.between(food.getExpiryDate(), food.getCreateDate());
        long nowDate = ChronoUnit.DAYS.between(food.getExpiryDate(), date);
        return (100 - (nowDate * 100) / maxTerm);
    }

    public boolean find(Food food) {
        return foodList.contains(food);
    }

    public int getPercentWarehouse() {
        return 75;
    }

    public int getPercentShop() {
        return 25;
    }

    public int getPercentTrash() {
        return 0;
    }

    public List<Food> getFoodList() {
        return this.foodList;
    }
 }
