import java.io.BufferedWriter;
import java.io.FileWriter;
import java.text.SimpleDateFormat;
import java.util.Date;

class WriteFileClass {
    public static Object monitor = new Object();

    public static void writeFile() {
        new Thread(new RunnableClass()).start();
        new Thread(new RunnableClass()).start();
        new Thread(new RunnableClass()).start();
    }

    static class RunnableClass implements Runnable {
        @Override
        public void run() {
            synchronized (monitor) {
                for (int i = 0; i < 10; i++)
                    try {
                        Thread.sleep(20);
                        writeF(Thread.currentThread().getName() + " thread: " + i + "\n");
                    } catch (InterruptedException ex) {
                        ex.printStackTrace();
                    }
            }
        }

        void writeF(String str) {
            try {
                BufferedWriter bw = new BufferedWriter(new FileWriter("file.txt", true));
                Date date = new Date();
                SimpleDateFormat formatForDateNow = new SimpleDateFormat("dd.MM.yyyy hh:mm:ss.SS");
                bw.write(formatForDateNow.format(date) + " # " + str);
                bw.close();
            } catch (Exception e) {
                System.out.println("Exception: " + e.getMessage());
            }
        }
    }

}
