/**
 * Java 3. Home Work 4.
 *
 * @author Albert Khayrutdinov
 * @version dated Jul 19, 2018
 * @link https://github.com/bertranus
 */
 
import java.io.IOException;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;

class J3HW42 {

    private final Object monitor = new Object();
    private final String TEXT = "This text printed by ";
    private volatile char currentLetter = '1';
    private final String FILE_NAME = "1.txt";
    private final int COUNT = 10;
    private final int TIMER = 20;

    public static void main(String[] args) {
        J3HW42 w = new J3HW42();
         new Thread(() -> {
            w.printA('1', '2');
        }).start();
        new Thread(() -> {
            w.printA('2', '3');
        }).start();
        new Thread(() -> {
            w.printA('3', '1');
        }).start();
    }

    void printA(char a, char b) {
        synchronized (monitor) {
            try {
                for (int i = 0; i < COUNT; i++) {
                    while ((currentLetter != a) || (currentLetter == b)) {
                        monitor.wait();
                    }
                    try (FileWriter writer = new FileWriter(FILE_NAME, true)) {
                        writer.write(TEXT + a + " thread" + "\n");
                    } catch (IOException ex) {
                        ex.printStackTrace();
                    }
                    Thread.sleep(TIMER);
                    currentLetter = b;
                    monitor.notifyAll();
                }
            } catch (InterruptedException ex) {
                ex.printStackTrace();
            }
        }
    }

}