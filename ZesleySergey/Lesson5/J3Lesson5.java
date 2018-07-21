import java.util.ArrayList;
import java.util.Arrays;
import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.Semaphore;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Java. Level 3. Lesson 5
 * Simple server for chat
 *
 * @author Sergey Zesley
 * @version 0.0.1 dated July 21, 2018
 * @link https://github.com/SergeyZes/Java3Group18
 */
public class J3Lesson5 {
    public static final int CARS_COUNT = 4;
    public static final CyclicBarrier start_cb =new CyclicBarrier(CARS_COUNT+1); //синхронизируем одноврем. выход на старт и старт после надписи "ВАЖНОЕ ОБЪЯВЛЕНИЕ >>> Гонка началась!!!"
    public static final Semaphore tunnel_sm =new Semaphore(CARS_COUNT / 2);  //синхронизируем кол-во машин в туннеле
    public static final CountDownLatch finish_cdl =new CountDownLatch(CARS_COUNT); //главный поток ожидает финиш всех машин
    public static final ReentrantLock winner_record_lock =new ReentrantLock(); //используется для синхронизации записи победителя
    public static String winner=null;
    public static String getWinner(){
        String res;
        winner_record_lock.lock();
        res=winner;
        winner_record_lock.unlock();
        return res;
    }
    public static void setWinner(String w){
        winner_record_lock.lock();
        if (winner==null) winner=w;
        winner_record_lock.unlock();
    }

    public static void main(String[] args) {
        System.out.println("ВАЖНОЕ ОБЪЯВЛЕНИЕ >>> Подготовка!!!");
        Race race = new Race(new Road(60), new Tunnel(), new Road(40));
        Car[] cars = new Car[CARS_COUNT];
        for (int i = 0; i < cars.length; i++) {
            cars[i] = new Car(race, 20 + (int) (Math.random() * 10));
        }
        for (int i = 0; i < cars.length; i++) {
            new Thread(cars[i]).start();
        }
        try {
            start_cb.await(); //Ждем, когда все машины выдут на старт
            System.out.println("ВАЖНОЕ ОБЪЯВЛЕНИЕ >>> Гонка началась!!!");
            start_cb.await(); //Запускаем гонку

            finish_cdl.await(); //Ожидаем прибытия всех машин к финишу
            System.out.println("ВАЖНОЕ ОБЪЯВЛЕНИЕ >>> Гонка закончилась!!!");
            System.out.println("Победитель гонки: "+getWinner());
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (BrokenBarrierException e) {
            e.printStackTrace();
        }
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
    public String getName() {
        return name;
    }
    public int getSpeed() {
        return speed;
    }
    public Car(Race race, int speed) {
        this.race = race;
        this.speed = speed;
        CARS_COUNT++;
        this.name = "Участник #" + CARS_COUNT;
    }
    @Override
    public void run() {
        try {
            System.out.println(this.name + " готовится");
            Thread.sleep(500 + (int)(Math.random() * 800));
            System.out.println(this.name + " готов");
            J3Lesson5.start_cb.await(); //Ждем, когда все машины выдут на старт
            J3Lesson5.start_cb.await(); //Ждем, когда главный поток напишет - "ВАЖНОЕ ОБЪЯВЛЕНИЕ >>> Гонка началась!!!"

        } catch (Exception e) {
            e.printStackTrace();
        }
        for (int i = 0; i < race.getStages().size(); i++) {
            race.getStages().get(i).go(this);
        }
        J3Lesson5.setWinner(name);
        J3Lesson5.finish_cdl.countDown(); //Отмечаемся о прибытии
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
        this.description = "Дорога " + length + " метров";
    }
    @Override
    public void go(Car c) {
        try {
            System.out.println(c.getName() + " начал этап: " + description);
            Thread.sleep(length / c.getSpeed() * 1000);
            System.out.println(c.getName() + " закончил этап: " + description);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
class Tunnel extends Stage {
    public Tunnel() {
        this.length = 80;
        this.description = "Тоннель " + length + " метров";
    }
    @Override
    public void go(Car c) {
        try {
            try {
                System.out.println(c.getName() + " готовится к этапу(ждет): " + description);
                J3Lesson5.tunnel_sm.acquire(); //Ожидаем разрешения войти в туннель
                System.out.println(c.getName() + " начал этап: " + description);
                Thread.sleep(length / c.getSpeed() * 1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                System.out.println(c.getName() + " закончил этап: " + description);
                J3Lesson5.tunnel_sm.release(); //отмечаем о выходе из туннеля
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
