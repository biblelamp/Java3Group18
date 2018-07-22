import java.util.concurrent.Semaphore;

public class Tunnel extends Stage {
    private Semaphore Semaphore;

    public Tunnel(Semaphore Semaphore) {
        this.length = 80;
        this.description = "Тоннель " + length + " метров";
        this.Semaphore = Semaphore;
    }
    @Override
    public void go(Car c) {
        try {
            try {
                System.out.println(c.getName() + " готовится к этапу(ждет): " + description);

                //если доступ не разрешен, поток вызвавший этот метод блокируется до тех пор,
                //пока семафор не разрешит доступ
                Semaphore.acquire();

                System.out.println(c.getName() + " начал этап: " + description);
                Thread.sleep(length / c.getSpeed() * 1000);

                // выезжаем с тунеля освобождаем пусть
                Semaphore.release();

            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                System.out.println(c.getName() + " закончил этап: " + description);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
