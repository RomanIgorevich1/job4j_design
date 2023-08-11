package ru.job4j.ood.lsp.warehouse;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;

public abstract class AbstractStore implements Store {
    /**
     * реализует общую логику хранилищ
     */
    private List<Food> foodList = new ArrayList<>();

    public Food add(Food food) {
        foodList.add(food);
        return food;
    }

    @Override
    public List<Store> addStore() {
        List<Store> storeList = new ArrayList<>();
        storeList.add(new Warehouse());
        storeList.add(new Shop());
        storeList.add(new Trash());
        return storeList;
    }

    @Override
    public float use(Food food, LocalDate date) {
        boolean change = false;
        long maxTerm = ChronoUnit.DAYS.between(food.getExpiryDate(), food.getCreateDate());
        long nowDate = ChronoUnit.DAYS.between(food.getExpiryDate(), date);
        return ((float) (100 - (nowDate * 100) / maxTerm));
    }

    @Override
    public Food find(Food food) {
        return foodList.contains(food) ? food : null;
    }
}
