/**
 * Java 3. Lesson 5.
 *
 * @author Ghermas Denis / Гермаш Денис
 * @version dated Jul 22, 2018
 * @link https://github.com/firstmessage/Java3Group18
 *
 *
 * Car Racing using threads controls
 *
 */



import java.util.concurrent.CountDownLatch;
import java.util.concurrent.Semaphore;

public class MainClass {

    public static final int CARS_COUNT = 4;
    //Создаем CountDownLatch на 4+1 "условия"
    private static final CountDownLatch START = new CountDownLatch(CARS_COUNT + 1);
    //Устанавливаем флаг "справедливый", в таком случае метод
    //aсquire() будет раздавать разрешения в порядке очереди
    // за раз могут проехать  только половина участников заезда
    private static final Semaphore SEMAPHORE = new Semaphore(CARS_COUNT / 2, true);
    static String WIN = "";

    public static void main(String[] args) {
        System.out.println("ВАЖНОЕ ОБЪЯВЛЕНИЕ >>> Подготовка!!!");
        Race race = new Race(new Road(60), new Tunnel(SEMAPHORE), new Road(40));
        Car[] cars = new Car[CARS_COUNT];
        for (int i = 0; i < cars.length; i++) {
            cars[i] = new Car(race, 20 + (int) (Math.random() * 10), START);
        }

        try {

            for (int i = 0; i < cars.length; i++) {
                new Thread(cars[i]).start();
                Thread.sleep(1000);
            }

            while (START.getCount() > 1) //Проверяем, собрались ли все автомобили
            {
                try {
                    Thread.sleep(100);              //у стартовой прямой. Если нет, ждем 100m
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("ВАЖНОЕ ОБЪЯВЛЕНИЕ >>> Гонка началась!!!");
        START.countDown();//Команда дана, уменьшаем счетчик на 1

        while (Thread.activeCount() > 2) {
            if (!WIN.equals("") && !WIN.equals(" ")){
                System.out.println(WIN+" закончил победой!!!");
                WIN=" ";
            }
        }

        System.out.println("ВАЖНОЕ ОБЪЯВЛЕНИЕ >>> Гонка закончилась!!!");
    }

    synchronized public static void finis(String name) {
        synchronized (WIN) {
            if (WIN.equals("")) {
                WIN=name;
            }
        }
    }
}



