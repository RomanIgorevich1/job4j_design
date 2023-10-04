package ru.job4j.ood.dip.warehouse;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class ControlQuality {
    private List<Store> store;

    public ControlQuality(List<Store> store) {
        this.store = store;
    }

    /**
     * Может работать с 0 и более хранилищами
     * Класс должен перераспределять еду по хранилищам в зависимости от условий.
     * 3.1. Если срок годности израсходован меньше чем на 25% направить в Warehouse только завезли в магазин
     * 3.2. Если срок годности от 25% до 75% направить в Shop;
     * 3.3. Если срок годности больше 75%, то выставить новую цену на продукт с учетом скидки и отправить в Shop,
     * срок годности подходит к концу
     * 3.4. Если срок годности вышел. Отправить продукт в Trash.
     */

    public void redistribution(LocalDate date, List<Food> food) {
        for (Food foods : food) {
            for (Store store : store) {
                store.use(foods, date);
            }
        }
    }

    public void resort(LocalDate date) {
        /**
         *  Должен извлекать все продукты и перераспределять их заново
         */
        List<Food> foods = new ArrayList<>();
        for (Store store : store) {
            foods.addAll(store.getFoodList());
            store.getFoodList().removeAll(foods);
            for (Food food : foods) {
                store.use(food, date);
            }
        }
    }
}
