class Tunnel extends Stage {

    public Tunnel() {
        this.length = 80;
        this.description = "Tunnel " + length + " meters";
    }

    @Override
    public void go(Car car) {
        try {
            try {
                System.out.println(car.getName() + " prepares for the stage (waits): " + description);

                J3HomeWork5.TUNNEL.acquire();

                System.out.println(car.getName() + " started the stage: " + description);
                Thread.sleep(length / car.getSpeed() * 1000);

                J3HomeWork5.TUNNEL.release();

            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                System.out.println(car.getName() + " finished the stage: " + description);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
