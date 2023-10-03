package ru.job4j.ood.isp.menu;

public class MenuPrint implements MenuPrinter {
    private ActionDelegate actionDelegate;

    @Override
    public void print(Menu menu) {
        menu.forEach(i -> {
                    if (i.getNumber().length() == 2) {
                        System.out.println(i.getNumber() + i.getName());
                    }
                    if (i.getNumber().length() == 4) {
                        System.out.println("---- " + i.getNumber() + i.getName());
                    }
                    if (i.getNumber().length() > 4) {
                        System.out.println("-------- " + i.getNumber() + i.getName());
                    }
                }
        );

    }

    public static void main(String[] args) {
        Menu menu = new SimpleMenu();
        MenuPrint menuPrint = new MenuPrint();
        menu.add(Menu.ROOT, "Сходить в магазин", menuPrint.actionDelegate);
        menu.add(Menu.ROOT, "Покормить собаку", menuPrint.actionDelegate);
        menu.add("Сходить в магазин", "Купить продукты", menuPrint.actionDelegate);
        menu.add("Купить продукты", "Купить хлеб", menuPrint.actionDelegate);
        menu.add("Купить продукты", "Купить молоко", menuPrint.actionDelegate);
        menuPrint.print(menu);
    }
}
