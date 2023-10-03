package ru.job4j.ood.isp.menu;

import java.util.Scanner;

public class TodoApp {
    private static final int ONE = 1;
    private static final int TWO = 2;
    private static final int THREE = 3;
    private static final int FOUR = 4;
    private static final int FIVE = 5;
    private static final ActionDelegate DEFAULT_ACTION = () -> System.out.println("Some action");

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Menu menu = new SimpleMenu();
        boolean run = true;
        while (run) {
            System.out.format(" %d. Добавить элемент в корень меню%n %d."
                            + " Добавить элемент к родительскому элементу%n %d."
                            + " Вызвать действие%n %d. Вывести меню в консоль%n %d. Выход",
                    ONE, TWO, THREE, FOUR, FIVE);
            int number = scanner.nextInt();
            if (ONE == number) {
                System.out.println("-Введите элемент для добавления-");
                scanner.nextLine();
                String child = scanner.nextLine();
                menu.add(Menu.ROOT, child, DEFAULT_ACTION);
                System.out.println("--Элемент добавлен--");
            } else if (TWO == number) {
                System.out.println("-Введите элемент к которому хотите добавить новый-");
                scanner.nextLine();
                String parent = scanner.nextLine();
                System.out.println("-Введите новый элемент-");
                String child = scanner.nextLine();
                menu.add(parent, child, DEFAULT_ACTION);
                System.out.println("--Элемент добавлен--");
            } else if (THREE == number) {
                DEFAULT_ACTION.delegate();
            } else if (FOUR == number) {
                new MenuPrint().print(menu);
            } else if (FIVE == number) {
                run = false;
            }
        }

    }
}
