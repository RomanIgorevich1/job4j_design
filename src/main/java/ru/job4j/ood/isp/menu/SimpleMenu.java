package ru.job4j.ood.isp.menu;

import java.util.*;
import java.util.stream.Collectors;

public class SimpleMenu implements Menu {

    /**
     * DFSIterator. Это итератор, основанный на поиске в глубину. Но немного модифицированный,
     * поскольку нам удобно читать пункты меню сверху-вниз и слева-направо.
     */
    private final List<MenuItem> rootElements = new ArrayList<>();

    @Override
    public boolean add(String parentName, String childName, ActionDelegate actionDelegate) {
        boolean result;
        if (rootElements.isEmpty() || Objects.equals(parentName, Menu.ROOT)) {
            rootElements.add(new SimpleMenuItem(childName, actionDelegate));
            result = true;
        } else {
            Optional<ItemInfo> item = findItem(parentName);
            item.ifPresent(itemInfo -> itemInfo.menuItem.getChildren().add(new SimpleMenuItem(childName, actionDelegate)));
            result = true;
        }
        return result;
    }

    @Override
    public Optional<MenuItemInfo> select(String itemName) {
        /**
         * Данные меню возвращаются в виде другого объекта
         */
        Optional<ItemInfo> result = findItem(itemName);
        Optional<MenuItemInfo> result2 = Optional.empty();
        if (result.isPresent()) {
            result2 = Optional.of(new MenuItemInfo(result.get().menuItem.getName(),
                    result.get().menuItem.getChildren().stream().map(MenuItem::getName).collect(Collectors.toList()),
                    result.get().menuItem.getActionDelegate(), result.get().number
            ));
        }
        return result2;
    }

    public Iterator<MenuItemInfo> iterator() {
        return new Iterator<MenuItemInfo>() {
            DFSIterator iterator = new DFSIterator();
            @Override
            public boolean hasNext() {
                return iterator.hasNext();
            }

            @Override
            public MenuItemInfo next() {
                ItemInfo info = iterator.next();
                return new MenuItemInfo(info.menuItem, info.number);
            }
        };
    }

    private Optional<ItemInfo> findItem(String name) {
        Optional<ItemInfo> newItem = Optional.empty();
        DFSIterator iterator = new DFSIterator();
        while (iterator.hasNext()) {
            ItemInfo item = iterator.next();
            if (item.menuItem.getName().equals(name)) {
                newItem = Optional.of(item);
                break;
            }
        }
        return newItem;
    }

    private static class SimpleMenuItem implements MenuItem {
        /**
         * - SimpleMenuItem. Это реализация MenuItem для SimpleMenu
         */
        private String name;
        private List<MenuItem> children = new ArrayList<>();
        private ActionDelegate actionDelegate;

        public SimpleMenuItem(String name, ActionDelegate actionDelegate) {
            this.name = name;
            this.actionDelegate = actionDelegate;
        }

        @Override
        public String getName() {
            return name;
        }

        @Override
        public List<MenuItem> getChildren() {
            return children;
        }

        @Override
        public ActionDelegate getActionDelegate() {
            return actionDelegate;
        }
    }

    private class DFSIterator implements Iterator<ItemInfo> {
        Deque<MenuItem> stack = new LinkedList<>();
        Deque<String> numbers = new LinkedList<>();

        DFSIterator() {
            int number = 1;
            for (MenuItem item : rootElements) {
                stack.addLast(item);
                numbers.addLast(String.valueOf(number++).concat("."));
            }
        }

        @Override
        public boolean hasNext() {
            return !stack.isEmpty();
        }

        @Override
        public ItemInfo next() {
            if (!hasNext()) {
                throw new NoSuchElementException();
            }
            MenuItem current = stack.removeFirst();
            String lastNumber = numbers.removeFirst();
            List<MenuItem> children = current.getChildren();
            int currentNumber = children.size();
            for (var i = children.listIterator(children.size()); i.hasPrevious();) {
                stack.addFirst(i.previous());
                numbers.addFirst((lastNumber.concat(String.valueOf(currentNumber--)).concat(".")));
            }
            return new ItemInfo(current, lastNumber);
        }
    }

    private class ItemInfo {
        /**
         * ItemInfo. Он служит для того, чтобы скомпоновать пункт меню и номер пункта (1.1., 1.1.1. и т.д.).
         */
        MenuItem menuItem;
        String number;

        public ItemInfo(MenuItem menuItem, String number) {
            this.menuItem = menuItem;
            this.number = number;
        }
    }
}
