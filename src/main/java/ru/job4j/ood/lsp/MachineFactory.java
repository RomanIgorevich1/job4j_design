package ru.job4j.ood.lsp;

public class MachineFactory {

    /**
     * Происходит нарушение lsp из-за усиления предусловия в дочернем классе
     */
    protected double engine;

   public MachineFactory(double engine) {
       this.engine = engine;
   }

   private void move(int maxSpeed) {
       if (engine < 2.5) {
           throw new IllegalArgumentException();
       }
       if (maxSpeed < 250) {
           throw new IllegalArgumentException();
       }
   }

   static class SpeedCar extends MachineFactory {

       public SpeedCar(double engine) {
           super(engine);
       }

       public void move(int maxSpeed) {
           if (engine < 2.5) {
               throw new IllegalArgumentException();
           }
           if (maxSpeed < 280) {
               throw new IllegalArgumentException();
           }
       }
   }

    public static void main(String[] args) {
        MachineFactory car = new SpeedCar(3);
        car.move(260);
    }
}
