package ru.job4j.ood.isp.menu;

public interface ActionDelegate {
    /**
     * Action.(Действие) Ничего конкретного о действиях мы знать не можем, поэтому наши действия будут
     * представлять собой делегаты. По сути, делегат будет просто вызывать клиентскую логику
     * Что в ней будет, нас не касается
     */
    void delegate();
}
