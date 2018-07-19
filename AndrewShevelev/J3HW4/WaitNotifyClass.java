/**
 * Java 3 Home Work 4
 *
 * @author Andrew Shevelev
 * @version jul 15, 2018
 * <p>
 * https://github.com/ShevelevAndrew
 */


class WaitNotifyClass {
    private final Object monitor = new Object();
    private char currentLetter = 'A';

    public static void waitNotify() {
        WaitNotifyClass w = new WaitNotifyClass();
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
                    monitor.notify();
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
                    monitor.notify();
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
                    if (i == 4) System.out.println("C");
                    else System.out.print("C");
                    currentLetter = 'A';
                    monitor.notifyAll();
                }
            } catch (InterruptedException ex) {
                ex.printStackTrace();
            }
        }
    }
}