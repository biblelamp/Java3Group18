import java.util.Arrays;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

public class J3HW5 {
    static final int CARS_COUNT = 4;
    static final Object monitor = new Object();
    static final CountDownLatch START = new CountDownLatch(CARS_COUNT + 3);
    static Semaphore semaphore = new Semaphore((int) CARS_COUNT/2, false); // добавим интриги
    static volatile boolean finished = false;

    public static void main(String[] args) {
        System.out.println("Attention >>> Preparation...");
        Race race = new Race(new Road(60), new Tunnel(80), new Road(40));
        Car[] cars = new Car[CARS_COUNT];
        List<Thread> threads = new ArrayList<>();
        for (int i = 0; i < cars.length; i++) {
            cars[i] = new Car(race, 20 + (int) (Math.random() * 2));// поменьше разброс в скоростях
            Thread th = new Thread(cars[i]);
            th.start();
            threads.add(th);
        }
        try {
            while (START.getCount() > 3) // проверяем: собрались ли все автомобили
                Thread.sleep(100);       // у стартовой прямой? Если нет - ждем 100ms
            Thread.sleep(1000);
            System.out.println("On your marks!");
            START.countDown(); // команда дана, уменьшаем счетчик на 1
            Thread.sleep(1000);
            System.out.println("Attention!");
            START.countDown(); // команда дана, уменьшаем счетчик на 1
            Thread.sleep(1000);
            System.out.println("Go!");
            START.countDown();//разблокируeм замок
            System.out.println("Attention >>> The race began!");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        try {
            for(Thread th: threads){
                th.join();
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("Attention >>> The race is over!");
    }
}

class Car implements Runnable {
    private static int CARS_COUNT;
    static {
        CARS_COUNT = 0;
    }
    private Race race;
    private int speed;
    private String name;

    public Car(Race race, int speed) {
        this.race = race;
        this.speed = speed;
        this.name = "Racer #" + (++CARS_COUNT);
    }

    public String getName() {
        return name;
    }

    public int getSpeed() {
        return speed;
    }

    @Override
    public void run() {
        try {
            System.out.println(name + " is getting ready");
            Thread.sleep(500 + (int)(Math.random() * 800));
            System.out.println(name + " is ready (" + speed + ")");
            J3HW5.START.countDown();
            J3HW5.START.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        for (int i = 0; i < race.getStages().size(); i++) {
            race.getStages().get(i).go(this);
        }
        synchronized (J3HW5.monitor) {
            if (!J3HW5.finished) {
                System.out.println(name + " WIN");
                J3HW5.finished = true;
            }
        }
    }
}

abstract class Stage {
    protected int length;
    protected String description;

    public String getDescription() {
        return description;
    }

    public abstract void go(Car car);
}

class Road extends Stage {

    public Road(int length) {
        this.length = length;
        this.description = "Road " + length + " meters";
    }

    @Override
    public void go(Car car) {
        try {
            System.out.println(
                car.getName() + " started the stage: " + description);
            Thread.sleep(length / car.getSpeed() * 1000);
            System.out.println(
                car.getName() + " finished the stage: " + description);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

class Tunnel extends Stage {

    public Tunnel(int length) {
        this.length = length;
        this.description = "Tunnel " + length + " meters";
    }

    @Override
    public void go(Car car) {
        try {
            try {
                System.out.println(car.getName() +
                    " prepares for the stage (waits): " + description);
                J3HW5.semaphore.acquire();
                System.out.println(car.getName() +
                    " started the stage: " + description);
                Thread.sleep(length / car.getSpeed() * 1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                System.out.println(car.getName() +
                    " finished the stage: " + description);
                J3HW5.semaphore.release();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

class Race {
    private ArrayList<Stage> stages;

    public Race(Stage... stages) {
        this.stages = new ArrayList<>(Arrays.asList(stages));
    }

    public ArrayList<Stage> getStages() {
        return stages;
    }
}