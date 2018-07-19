
import java.io.FileNotFoundException;
import java.io.PrintWriter;

/**
 * Java. Level 3. Lesson 4
 * Simple server for chat
 *
 * @author Sergey Zesley
 * @version 0.0.1 dated July 19, 2018
 * @link https://github.com/SergeyZes/Java3Group18
 */

public class J3Lesson4 {

    public static void main(String[] args) {
        //Задание 1
        Task1();

        //Задание 2
        Task2();

        //Задание 3
        Task3();
    }

    static void Task1(){
        LetterSynch ls=new LetterSynch();
        System.out.println("Вывод букв:");

        new Thread(()->{
            for(int i=0;i<5;i++) ls.printL('A','C');
        }).start();

        new Thread(()->{
            for(int i=0;i<5;i++) ls.printL('B','A');
        }).start();

        new Thread(()->{
            for(int i=0;i<5;i++) ls.printL('C','B');
        }).start();
    }

    static void Task2(){
        try (PrintWriter printWriter=new PrintWriter("FileTask2.txt")) {

            WriteToFileSynch  ws=new WriteToFileSynch(printWriter);

            new Thread(()->{
                final int threadN=1;
                for (int i=0;i<10;i++){
                   ws.writeNSleep("Поток "+threadN+" запись #"+(i+1),threadN,3);
                }
            }).start();

            new Thread(()->{
                final int threadN=2;
                for (int i=0;i<10;i++){
                    ws.writeNSleep("Поток "+threadN+" запись #"+(i+1),threadN,1);
                }
            }).start();

            Thread t3= new Thread(()->{
                final int threadN=3;
                for (int i=0;i<10;i++){
                    ws.writeNSleep("Поток "+threadN+" запись #"+(i+1),threadN,2);
                }
            });

            t3.start();

            try {
                t3.join();
            } catch (InterruptedException e) {
                return;
            }

        } catch (FileNotFoundException e) {
            return;
        }
    }

    static void Task3(){
        System.out.println("Задание 3");
        MFU mfu=new MFU();

        new Thread(()->{
            String Name="Поток 1";
            mfu.printDocument(Name,10);
            mfu.scanDocument(Name,20);
        }).start();

        new Thread(()->{
            String Name="Поток 2";
            mfu.printDocument(Name,5);
        }).start();

        new Thread(()->{
            String Name="Поток 3";
            mfu.scanDocument(Name,15);
            mfu.printDocument(Name,6);
        }).start();
    }
}

class LetterSynch{
    private char lastLetter='C';
    public LetterSynch() {
    }
    public synchronized void printL(char current,char last){
        while (lastLetter!=last) {
            try {
                wait();
            } catch (InterruptedException e) {
               return;
            }
        }
        System.out.print(current);
        lastLetter=current;
        notifyAll();

    }
}

class WriteToFileSynch{
    private PrintWriter pw;
    private int lastThread=3;

    public WriteToFileSynch(PrintWriter pw) {
        this.pw = pw;
    }
    public void writeNSleep(String message,int current,int last){
        write(message,current,last);
        try {
            Thread.sleep(20);
        } catch (InterruptedException e) {
            return;
        }
    }
    private synchronized void write(String message,int current,int last){
        while (lastThread!=last) {
            try {
                wait();
            } catch (InterruptedException e) {
                return;
            }
        }
        pw.println(message);
        lastThread=current;
        notifyAll();
    }
}

class MFU{
    private final Object printLock=new Object();
    private final Object scanLock=new Object();

    public  void printDocument(String threadName,Integer count){

        synchronized (printLock) {

            for (int i = 1; i <= count; i++) {
                try {
                    Thread.sleep(50);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("Потоком " + threadName + " напечатано " + i + " листов.");
            }
        }

    }

    public  void scanDocument(String threadName,Integer count){

        synchronized (scanLock) {

            for (int i = 1; i <= count; i++) {
                try {
                    Thread.sleep(50);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("Потоком " + threadName + " отсканировано " + i + " листов.");
            }
        }

    }

}
