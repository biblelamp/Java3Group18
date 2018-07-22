package Homework_05;

import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;

public class MainClass {
    public static final int CARS_COUNT = 4;
    public static final CountDownLatch START = new CountDownLatch(CARS_COUNT+1);
    public static final Semaphore TUNNEL_SEMAPHORE = new Semaphore((int)Math.floor((double)(CARS_COUNT/2)), true);
    public static final AtomicInteger winner = new AtomicInteger(0);

    public static void main(String[] args) {
        System.out.println("ВАЖНОЕ ОБЪЯВЛЕНИЕ >>> Подготовка!!!");
        Race race = new Race(new Road(60), new Tunnel(), new Road(40));
        Car[] cars = new Car[CARS_COUNT];
        Thread racingCars[] = new Thread[CARS_COUNT];

        for (int i = 0; i < cars.length; i++) {
            cars[i] = new Car(race, 20 + (int) (Math.random() * 10));
        }
        for (int i = 0; i < cars.length; i++) {
            racingCars[i] = new Thread(cars[i]);
            racingCars[i].start();
        }
        while(START.getCount()>1)
            ;
        START.countDown();
        System.out.println("ВАЖНОЕ ОБЪЯВЛЕНИЕ >>> Гонка началась!!!");
        try{
            for (int i = 0; i < cars.length; i++) {
                racingCars[i].join();
            }
        }catch (Exception e){}
        System.out.println("ВАЖНОЕ ОБЪЯВЛЕНИЕ >>> Гонка закончилась!!!");
        System.out.println("Победил участник #"+winner+" Ура! Поздравляем!");
    }
}

