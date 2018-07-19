public class WaitNotifyClass {
    private static final int LETTERS_COUNT = 3;
    private static final int PRINT_COUNT = 5;
    private static final char START_SYMBOL = 'A';

    private final Object monitor = new Object();
    private volatile int currentIndex = 0;

    public static void main(String[] args) {
        WaitNotifyClass w = new WaitNotifyClass();
        for (char i = 0; i < LETTERS_COUNT; i++) {
            final char index = i;
            new Thread(() -> w.printChar(index)).start();
        }
    }

    void printChar(char index) {
        synchronized (monitor) {
            try {
                for (int i = 0; i < PRINT_COUNT; i++) {
                    while (currentIndex != index)
                        monitor.wait();
                    System.out.print((char)(START_SYMBOL + index));
                    currentIndex = (currentIndex + 1)%LETTERS_COUNT;
                    monitor.notifyAll();
                }
            } catch (InterruptedException ex) {
                ex.printStackTrace();
            }
        }
    }
}