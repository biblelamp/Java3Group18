/**
 * Java 3. Home Work 4.
 *
 * @author Albert Khayrutdinov
 * @version dated Jul 19, 2018
 * @link https://github.com/bertranus
 */
 
class J3HW41 {

    private final Object monitor = new Object();
    private volatile char currentLetter = 'A';
    private final int COUNT = 5;

    public static void main(String[] args) {
        J3HW41 w = new J3HW41();
        Thread t1 = new Thread(() -> {
            w.printA();
        });
        Thread t2 = new Thread(() -> {
            w.printB();
        });
        Thread t3 = new Thread(() -> {
            w.printC();
        });
        t3.start();
        t2.start();
        t1.start();
        
    }

    void printA() {
        synchronized (monitor) {
            try {
                for (int i = 0; i < COUNT; i++) {
                    while ((currentLetter != 'A') || (currentLetter == 'B'))
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
                for (int i = 0; i < COUNT; i++) {
                    while ((currentLetter != 'B') || (currentLetter == 'C'))
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
                for (int i = 0; i < COUNT; i++) {
                    while ((currentLetter != 'C') || (currentLetter == 'B'))
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