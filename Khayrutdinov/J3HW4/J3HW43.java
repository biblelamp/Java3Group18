/**
 * Java 3. Home Work 4.
 *
 * @author Albert Khayrutdinov
 * @version dated Jul 19, 2018
 * @link https://github.com/bertranus
 */
 
class J3HW43 {

    private final int TIMER = 50;

    public static void main(String[] args) {
        J3HW43 mfp1 = new J3HW43();
        J3HW43 mfp2 = new J3HW43();
        new Thread(() -> {
                mfp1.printer("Doc # 1 ", 5);
            }).start();
        System.out.println("Doc#1 sended to printer.");
        new Thread(() -> {
                mfp1.printer("Doc # 2 ", 12);
        }).start();
        System.out.println("Doc#2 sended to printer.");
         new Thread(() -> {
                mfp2.scanner("Doc # 3 ", 3);
            }).start();
        System.out.println("Doc#3 sended to scanner.");
        new Thread(() -> {
                mfp2.scanner("Doc # 4 ", 7);
            }).start();
        System.out.println("Doc#4 sended to scanner.");
    }

    synchronized void printer(String docName, int count) {
        try {
            for (int i = 1; i < count + 1; i++) {
                System.out.println(docName + " printed " + i + " pages.");
                Thread.sleep(TIMER);
            }
        } catch (InterruptedException ex) {
            ex.printStackTrace();
        }
    }

    synchronized void scanner(String docName, int count) {
        try {
            for (int i = 1; i < count + 1; i++) {
                System.out.println(docName + " scanned " + i + " pages.");
                Thread.sleep(TIMER);
            }
        } catch (InterruptedException ex) {
            ex.printStackTrace();
        }
    }
}