public class MFDClass {
    static final int PERIOD = 50;

    static Object printMonitor = new Object();
    static Object scanMonitor = new Object();

    public static void main(String[] args) {
        new Thread(() -> doAction("Print",5),"Print Document 0").start();
        new Thread(() -> doAction("Scan",5),"Scan Document 0").start();
        new Thread(() -> doAction("Print",5),"Print Document 1").start();
        new Thread(() -> doAction("Scan",5),"Scan Document 1").start();
    }

    static void doAction(String action, int numOfPages){
        Object monitor = action.equals("Print") ? printMonitor : scanMonitor;
        synchronized(monitor) {
            for (int i = 0; i < numOfPages; i++)
            {
                System.out.println(Thread.currentThread().getName() + ":" + action + " page:" + i);
                try {
                    Thread.sleep(PERIOD);
                }
                catch (InterruptedException ex){
                    ex.printStackTrace();
                }
            }
            System.out.println();
        }
    }
}
