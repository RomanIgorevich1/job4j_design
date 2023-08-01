package ru.job4j.ood.lsp;

public class Shop {

    /**
     * Нарушение lsp, т.к нарушено состояние объекта в дочернем классе (отсутствует проверка на корректные данные)
     */
    protected Passport passport;

    public Shop(Passport passport) {
        this.passport = passport;
    }

    public void validate(Passport passport) {
        if (String.valueOf(passport.getNumber()).length() != 4) {
            throw new IllegalArgumentException();
        }
        if (String.valueOf(passport.getSeries()).length() != 6) {
            throw new IllegalArgumentException();
        }
    }

    public void recruitment(Passport passport) {
        validate(passport);
        System.out.println("Вы приняты!");
    }

    static class Passport {
        int number;
        int series;

        public Passport(int number, int series) {
            this.number = number;
            this.series = series;
        }

        public int getNumber() {
            return number;
        }

        public int getSeries() {
            return series;
        }
    }

   static class PetShop extends Shop {

        public PetShop(Passport passport) {
            super(passport);
        }

       @Override
        public void recruitment(Passport passport) {
            System.out.println("Вы приняты");
        }
    }

    public static void main(String[] args) {
        Shop petShop = new PetShop(new Passport(12, 13));
        petShop.recruitment(new Passport(12, 12));
    }
}
