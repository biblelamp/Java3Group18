package Homework_04;

import java.io.FileOutputStream;
import java.io.IOException;

/**
 * Java. Level 3. Homework 4
 * 1. Создать три потока, каждый из которых выводит определенную букву (A, B и C) 5 раз,
 * порядок должен быть именно ABСABСABС. Используйте wait/notify/notifyAll.
 *
 * К этому заданию имеют отношение классы: myFlag, TestThreadA,B,C universalLetterPrintingThread
 *
 * 2. Написать совсем небольшой метод, в котором 3 потока построчно пишут данные в файл
 * (штук по 10 записей, с периодом в 20 мс)
 *
 * writeStuffToFile()
 *
 * 3. Написать класс МФУ, на котором возможны одновременная печать и сканирование документов,
 * при этом нельзя одновременно печатать два документа или сканировать
 * (при печати в консоль выводится сообщения "отпечатано 1, 2, 3,... страницы",
 * при сканировании тоже самое только "отсканировано...", вывод в консоль все также с периодом в 50 мс.)
 *
 * class MFU
 *
 * @author Mikhail Barinov
 * @version Jul 18, 2018
 */

public class MainHW04 {

    public static void writeStuffToFile(){
        try {
            FileOutputStream out = new FileOutputStream("MonkeyTyping.txt");
                Thread t1 =new Thread() {
                    public void run() {
                        byte[] bw = new byte[50];
                        for (int i = 0; i < 10; i++) {
                            for (int j = 0; j < bw.length; j++) {
                                bw[j] = (byte) ('A' + Math.random() * 26);
                            }
                            bw[bw.length - 1] = (byte) ('\n');
                            bw[0] = (byte) ('1');
                            try {
                                out.write(bw);
                                Thread.sleep(20);
                            } catch (Exception e) {
                                System.out.println("Exception " + e);
                            }
                        }
                    }
                };
            Thread t2 =new Thread() {
                public void run() {
                    byte[] bw = new byte[50];
                    for (int i = 0; i < 10; i++) {
                        for (int j = 0; j < bw.length; j++) {
                            bw[j] = (byte) ('A' + Math.random() * 26);
                        }
                        bw[bw.length - 1] = (byte) ('\n');
                        bw[0] = (byte) ('2');
                        try {
                            out.write(bw);
                            Thread.sleep(20);
                        } catch (Exception e) {
                            System.out.println("Exception " + e);
                        }
                    }
                }
            };
            Thread t3 =new Thread() {
                public void run() {
                    byte[] bw = new byte[50];
                    for (int i = 0; i < 10; i++) {
                        for (int j = 0; j < bw.length; j++) {
                            bw[j] = (byte) ('A' + Math.random() * 26);
                        }
                        bw[bw.length - 1] = (byte) ('\n');
                        bw[0] = (byte) ('3');
                        try {
                            out.write(bw);
                            Thread.sleep(20);
                        } catch (Exception e) {
                            System.out.println("Exception " + e);
                        }
                    }
                }
            };
                t1.start();
                t2.start();
                t3.start();
                try {
                    t1.join();
                    t2.join();
                    t3.join();
                }catch (Exception e){}
            out.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        myFlag f = new myFlag();
        // Первый вариант решения с 3мя разными классами
        System.out.println("Task 1. First solution");
        TestThreadA t1 = new TestThreadA(f);
        TestThreadB t2 = new TestThreadB(f);
        TestThreadC t3 = new TestThreadC(f);
        t1.start();
        t2.start();
        t3.start();
        try {
            t1.join();
            t2.join();
            t3.join();
        } catch (Exception e) {
        }
        // Второй вариант решения с универсальным классом
        System.out.println("Task 1. Second solution");
        universalLetterPrintingThread ut1 = new universalLetterPrintingThread(f, 'A', 5);
        universalLetterPrintingThread ut2 = new universalLetterPrintingThread(f, 'B', 5);
        universalLetterPrintingThread ut3 = new universalLetterPrintingThread(f, 'C', 5);
        // При создании дополнительных потоков необходимо изменить константу в классе myFlag
        // и отказаться от использования первого варианта решения
//        universalLetterPrintingThread ut4 = new universalLetterPrintingThread(f,'D',5);

        ut1.start();
        ut2.start();
        ut3.start();
//        ut4.start();
        try {
            ut1.join();
            ut2.join();
            ut3.join();
//            ut4.join();
        } catch (Exception e) {
        }

        writeStuffToFile();

//        Bonus Task:
        MFU myMFU = new MFU();
        new Thread(()->{myMFU.myPrinting("doc1");}).start();
        new Thread(()->{myMFU.myScanning("doc1");}).start();
        new Thread(()->{myMFU.myPrinting("doc2");}).start();
        new Thread(()->{myMFU.myScanning("doc2");}).start();
        new Thread(()->{myMFU.myPrinting("doc3");}).start();
        new Thread(()->{myMFU.myScanning("doc3");}).start();
        new Thread(()->{myMFU.myPrinting("doc4");}).start();
        new Thread(()->{myMFU.myScanning("doc4");}).start();
        new Thread(()->{myMFU.myPrinting("doc5");}).start();
        new Thread(()->{myMFU.myScanning("doc5");}).start();

    }
}