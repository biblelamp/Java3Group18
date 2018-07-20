/**
 * Java 3. Lesson 4.
 *
 * @author Ghermas Denis / Гермаш Денис
 * @version dated Jul 19, 2018
 * @link https://github.com/firstmessage/Java3Group18
 *
 *
 * 1. Прочитать файл (около 50 байт) в байтовый массив и вывести этот массив в консоль;
 * 2. Последовательно сшить 5 файлов в один (файлы примерно 100 байт). Может пригодиться
 *
 */


class DZ4_1_WaitNotifyClass {

    private final Object monitor = new Object();
    private volatile char currentLetter = 'A';

    public static void main(String[] args) {
        DZ4_1_WaitNotifyClass w = new DZ4_1_WaitNotifyClass();
        Thread t1 = new Thread(() -> {
            w.printA();
        });
        Thread t2 = new Thread(() -> {
            w.printB();
        });

        Thread t3 = new Thread(() -> {
            w.printC();
        });

        t1.start();
        t2.start();
        t3.start();
    }

    void printA() {
        synchronized (monitor) {
            try {
                for (int i = 0; i < 5; i++) {
                    while (currentLetter != 'A')
                        monitor.wait();
                    System.out.print("A");
                    currentLetter = 'B';
                    monitor.notifyAll();
                }
            } catch (InterruptedException ex) {
                ex.printStackTrace();
            }
        }
    }

    void printB() {
        synchronized (monitor) {
            try {
                for (int i = 0; i < 5; i++) {
                    while (currentLetter != 'B')
                        monitor.wait();
                    System.out.print("B");
                    currentLetter = 'C';
                    monitor.notifyAll();
                }
            } catch (InterruptedException ex) {
                ex.printStackTrace();
            }
        }
    }

    void printC() {
        synchronized (monitor) {
            try {
                for (int i = 0; i < 5; i++) {
                    while (currentLetter != 'C')
                        monitor.wait();
                    System.out.print("C");
                    currentLetter = 'A';
                    monitor.notifyAll();
                }
            } catch (InterruptedException ex) {
                ex.printStackTrace();
            }
        }
    }

}
