/**
 *
 * @author Andrew Shevelev
 * @version Jul 23, 2018
 * @link https://github.com/ShevelevAndrew
 */

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.Semaphore;

public class J3HomeWork5 {
    public static final int CARS_COUNT = 4;
    public static final CountDownLatch GO = new CountDownLatch(CARS_COUNT + 1);
    public static final Semaphore TUNNEL = new Semaphore(CARS_COUNT / 2, true);
    public static final CountDownLatch FINISH = new CountDownLatch(CARS_COUNT);

    public static void main(String[] args) throws InterruptedException {
        System.out.println("Attention >>> Preparation...");
        Race race = new Race(new Road(60), new Tunnel(), new Road(40));
        Car[] cars = new Car[CARS_COUNT];
        for (int i = 0; i < cars.length; i++) {
            cars[i] = new Car(race, 20 + (int)(Math.random() * 10));
            new Thread(cars[i]).start();
        }

        while (GO.getCount() > 1)
            Thread.sleep(100);

        System.out.println("Attention >>> The race began!");
        GO.countDown();

        while (FINISH.getCount() > 0);
        System.out.println("Attention >>> The race is over!");
    }
}









