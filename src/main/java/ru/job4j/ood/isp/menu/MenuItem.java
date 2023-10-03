package ru.job4j.ood.isp.menu;

import java.util.List;

public interface MenuItem {

    /**
     * MenuItem. Пункт меню имеет имя, а также дочерние элементы, последний пункт говорит, что при выборе
     * элемента меню, мы получаем действие, соответственно, пункт меню и действие связаны.
     * Но связаны не через стороннюю структуру (напр., Map), а через сам элемент меню, потому что на элементах меню
     * строится само меню.
     * @return
     */
    String getName();

    List<MenuItem> getChildren();

    ActionDelegate getActionDelegate();
}
