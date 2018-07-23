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

            J3HomeWork5.GO.countDown();
            J3HomeWork5.GO.await();

        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        for (int i = 0; i < race.getStages().size(); i++) {
            race.getStages().get(i).go(this);
        }

        if (J3HomeWork5.FINISH.getCount() ==  J3HomeWork5.CARS_COUNT)
            System.out.println(name + " - Victory");

        try {
            J3HomeWork5.FINISH.countDown();
            J3HomeWork5.FINISH.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}