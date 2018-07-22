import java.util.concurrent.CountDownLatch;

public class Car implements Runnable {
    private static int CARS_COUNT;
    static {
        CARS_COUNT = 0;
    }
    private Race race;
    private int speed;
    private String name;
    private CountDownLatch START;
    public String getName() {
        return name;
    }
    public int getSpeed() {
        return speed;
    }
    public Car(Race race, int speed, CountDownLatch START) {
        this.race = race;
        this.speed = speed;
        this.START = START;
        CARS_COUNT++;
        this.name = "Участник #" + CARS_COUNT;
    }
    @Override
    public void run() {
        try {
            System.out.println(this.name + " готовится, скорость=" +this.speed );
            Thread.sleep(500 + (int)(Math.random() * 1000));
            System.out.println(this.name + " готов");
            // уменьшение счетчика
            START.countDown();
            //ожидание старта
            START.await();
            System.out.println(this.name + " поехал");
        } catch (Exception e) {
            e.printStackTrace();
        }
        for (int i = 0; i < race.getStages().size(); i++) {
            race.getStages().get(i).go(this);
        }
        MainClass.finis(this.name);
    }
}
