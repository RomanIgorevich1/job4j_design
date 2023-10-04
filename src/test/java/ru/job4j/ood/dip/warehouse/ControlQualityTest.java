package ru.job4j.ood.dip.warehouse;

import org.junit.jupiter.api.Test;
import java.time.LocalDate;
import java.util.List;

import static org.assertj.core.api.Assertions.*;

public class ControlQualityTest {
    @Test
    public void whenAddProduct() {
        Food milk = new Food("milk", LocalDate.of(2023, 1, 1),
                LocalDate.of(2023, 5, 15), 600, 20);
        Food oil = new Food("oil", LocalDate.of(2023, 1, 1),
                LocalDate.of(2023, 3, 1), 600, 20);
        Food bread = new Food("bread", LocalDate.of(2023, 1, 1),
                LocalDate.of(2023, 1, 10), 600, 20);
        Food cake = new Food("cake", LocalDate.of(2023, 1, 1),
                LocalDate.of(2023, 1, 6), 600, 20);
        Food sausage = new Food("sausage", LocalDate.of(2023, 1, 1),
                LocalDate.of(2023, 1, 5), 600, 20);
        Shop shop = new Shop();
        Trash trash = new Trash();
        Warehouse warehouse = new Warehouse();
        LocalDate date = LocalDate.of(2023, 1, 5);
        List<Store> storeList = List.of(shop, trash, warehouse);
        List<Food> foodList = List.of(milk, oil, bread, cake, sausage);
        ControlQuality controlQuality = new ControlQuality(storeList);
        controlQuality.redistribution(date, foodList);
        assertThat(warehouse.foodList.size()).isEqualTo(2);
        assertThat(warehouse.find(oil)).isTrue();
        assertThat(warehouse.find(milk)).isTrue();
        assertThat(shop.find(bread)).isTrue();
        assertThat(shop.find(cake)).isTrue();
        controlQuality.resort(date);
        assertThat(trash.find(sausage)).isTrue();
        assertThat(shop.foodList.size()).isEqualTo(2);
        assertThat(warehouse.foodList.size()).isEqualTo(2);
    }
}