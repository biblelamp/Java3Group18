package Homework_04;

public class MFU {
    private final Object lockA = new Object();
    private final Object lockB = new Object();

    void myPrinting(String documentName){
        synchronized (lockA) {
            for (int i = 0; i < 5; i++) {
                System.out.print("Printing " + documentName);
                try {
                    Thread.sleep(500);
                } catch (Exception e) {
                }
            }
            System.out.println();
        }
    }
    void myScanning(String documentName){
        synchronized (lockB) {
            for (int i = 0; i < 5; i++) {
                System.out.print("Scanning " + documentName);
                try {
                    Thread.sleep(500);
                } catch (Exception e) {
                }
            }
            System.out.println();
        }
    }
}
