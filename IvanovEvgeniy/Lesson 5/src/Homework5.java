/**
 * Java. Level 3. Homework 5.
 *
 * @author Stalker1290
 * @version dated Jul 25,2018
 * @link https://github.com/Stalker1290
 */

import java.util.ArrayList;
import java.util.Arrays;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.Semaphore;

public class Homework5 {
    public static final int CARS_COUNT = 4;
    public static final CountDownLatch START = new CountDownLatch(CARS_COUNT + 1);
    public static final CountDownLatch END = new CountDownLatch(CARS_COUNT);
    public static final Semaphore TUNNEL_SEM = new Semaphore(CARS_COUNT/2, true);

    public static void main(String[] args) throws InterruptedException {
        System.out.println("IMPORTANT >>> Preparation!!!");
        Race race = new Race(new Road(60), new Tunnel(), new Road(40));

        for (int i = 0; i < CARS_COUNT; i++)
            new Thread(new Car(i, race, 20 + (int) (Math.random() * 10))).start();

        while(START.getCount() > 1) Thread.sleep(100);

        System.out.println("IMPORTANT >>> Race start!!!");
        START.countDown();

        END.await();
        System.out.println("IMPORTANT >>> Race end!!!");
    }
}

class Car implements Runnable {

    private Race race;
    private int speed;
    private String name;

    public String getName() {
        return name;
    }

    public int getSpeed() {
        return speed;
    }

    public Car(int carNum, Race race, int speed) {
        this.race = race;
        this.speed = speed;
        this.name = "Car #" + carNum;
    }

    @Override
    public void run() {
        try {
            System.out.println(this.name + " is being prepared");
            Thread.sleep(500 + (int)(Math.random() * 800));
            System.out.println(this.name + " ready");
            Homework5.START.countDown();
            Homework5.START.await();
        } catch (Exception e) {
            e.printStackTrace();
        }

        for (int i = 0; i < race.getStages().size(); i++) {
            race.getStages().get(i).go(this);
        }

        synchronized (Homework5.END) {
            if(Homework5.END.getCount() == Homework5.CARS_COUNT)
                System.out.println(this.name + " - WINNER");
            Homework5.END.countDown();
        }
    }
}

abstract class Stage {
    protected int length;
    protected String description;
    public String getDescription() {
        return description;
    }
    public abstract void go(Car c);
}

class Road extends Stage {
    public Road(int length) {
        this.length = length;
        this.description = "Road " + length + " meters";
    }
    @Override
    public void go(Car c) {
        try {
            System.out.println(c.getName() + " start stage: " + description);
            Thread.sleep(length / c.getSpeed() * 1000);
            System.out.println(c.getName() + " end stage: " + description);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

class Tunnel extends Stage {
    public Tunnel() {
        this.length = 80;
        this.description = "Tunnel " + length + " meters";
    }
    @Override
    public void go(Car c) {
        try {
            try {
                System.out.println(c.getName() + " is being prepared for stage (wait): " + description);

                Homework5.TUNNEL_SEM.acquire();

                System.out.println(c.getName() + " start stage: " + description);
                Thread.sleep(length / c.getSpeed() * 1000);

                Homework5.TUNNEL_SEM.release();

            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                System.out.println(c.getName() + " end stage: " + description);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

class Race {

    private ArrayList<Stage> stages;

    public ArrayList<Stage> getStages() { return stages; }

    public Race(Stage... stages) {
        this.stages = new ArrayList<>(Arrays.asList(stages));
    }
}