package ru.job4j.ood.isp.menu;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.List;

import static org.assertj.core.api.Assertions.*;

public class SimpleMenuTest {
    public static final ActionDelegate STUB_ACTION = System.out::println;
    private ByteArrayOutputStream outputStream = new ByteArrayOutputStream();

    @BeforeEach
    public void setUpStreams() {
        System.setOut(new PrintStream(outputStream));
    }

    @Test
    public void whenAddThenReturnSame() {
        Menu menu = new SimpleMenu();
        menu.add(Menu.ROOT, "Сходить в магазин", STUB_ACTION);
        menu.add(Menu.ROOT, "Покормить собаку", STUB_ACTION);
        menu.add("Сходить в магазин", "Купить продукты", STUB_ACTION);
        menu.add("Купить продукты", "Купить хлеб", STUB_ACTION);
        menu.add("Купить продукты", "Купить молоко", STUB_ACTION);
        menu.add(Menu.ROOT, "Покормить кошку", STUB_ACTION);
        assertThat(new Menu.MenuItemInfo("Сходить в магазин",
                List.of("Купить продукты"), STUB_ACTION, "1."))
                .isEqualTo(menu.select("Сходить в магазин").get());
        assertThat(new Menu.MenuItemInfo(
                "Купить продукты",
                List.of("Купить хлеб", "Купить молоко"), STUB_ACTION, "1.1."))
                .isEqualTo(menu.select("Купить продукты").get());
        assertThat(new Menu.MenuItemInfo(
                "Покормить собаку", List.of(), STUB_ACTION, "2."))
                .isEqualTo(menu.select("Покормить собаку").get());
        menu.forEach(i -> System.out.println(i.getNumber() + i.getName()));
    }

    @Test
    public void whenAddThenReturnTrue() {
        Menu menu = new SimpleMenu();
        menu.add(Menu.ROOT, "Сходить в магазин", STUB_ACTION);
        menu.add(Menu.ROOT, "Покормить собаку", STUB_ACTION);
        menu.add("Сходить в магазин", "Купить продукты", STUB_ACTION);
        menu.add("Купить продукты", "Купить хлеб", STUB_ACTION);
        menu.add("Купить продукты", "Купить молоко", STUB_ACTION);
        menu.add(Menu.ROOT, "Убраться дома", STUB_ACTION);
        assertThat(new Menu.MenuItemInfo(
                "Убраться дома", List.of(), STUB_ACTION, "3."))
                .isEqualTo(menu.select("Убраться дома").get());
    }

    @Test
    public void whenAddThenPrint() {
        Menu menu = new SimpleMenu();
        MenuPrint print = new MenuPrint();
        String line = System.lineSeparator();
        menu.add(Menu.ROOT, "Пойти на работу", STUB_ACTION);
        menu.add(Menu.ROOT, "Убраться дома", STUB_ACTION);
        menu.add("Пойти на работу", "Сдать отчет", STUB_ACTION);
        menu.add("Убраться дома", "Помыть окна", STUB_ACTION);
        menu.add("Убраться дома", "Пропылесосить", STUB_ACTION);
        String result = "1.Пойти на работу" + line
                + "---- 1.1.Сдать отчет" + line
                + "2.Убраться дома" + line
                + "---- 2.1.Помыть окна" + line
                + "---- 2.2.Пропылесосить" + line;
        print.print(menu);
        assertThat(result).isEqualTo(outputStream.toString());
    }
}