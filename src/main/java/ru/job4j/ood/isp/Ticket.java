package ru.job4j.ood.isp;

public class Ticket implements TicketOffice {
    /**
     * Происходит нарушение ISP так как в железнодорожной кассе продаются билеты только на поезд,
     * используя интерфейс мы должны использовать все методы в нашем случае они нам не нужны, необходимо разделить
     * создать интерфейсы для каждого вида билетов
     */

    @Override
    public void ticketAirplane() {
        System.out.println("Продажа билетов на самолет");
    }

    @Override
    public void ticketTrain() {
        System.out.println("Продажа билетов на поезд");
    }

    @Override
    public void ticketBus() {
        System.out.println("Продажа билетов на автобус");
    }

    class RailwayTicketOffice implements TicketOffice {
        @Override
        public void ticketAirplane() {
            throw new IllegalArgumentException("Нет билетов");
        }

        @Override
        public void ticketTrain() {
            System.out.println("Продажа билетов на поезд");
        }

        @Override
        public void ticketBus() {
            throw new IllegalArgumentException("Нет билетов");
        }
    }
}


