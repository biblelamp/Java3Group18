/**
 * Java. Level 3. Homework 3
 * 1. Прочитать файл (около 50 байт) в байтовый массив и вывести этот массив в консоль;
 * 2. Последовательно сшить 5 файлов в один (файлы примерно 100 байт).
 *
 * @author Mikhail Barinov
 * @version Jul 16, 2018
 */

package java_level_3.homework_03;
import java.io.*;
import java.util.Arrays;

public class Main {
    public static void myFileCreate(int myFileSizeInBytes, int myNumberOfFiles){
        byte [] bw = new byte[myFileSizeInBytes];
        FileOutputStream out = null ;
        for (int i=0;i<myNumberOfFiles;i++) {
            for (int j = 0; j < bw.length; j++) {
                bw[j] = (byte) (Math.random() * 256);
            }
            try {
                out = new FileOutputStream((i+1)+".txt");
                out.write(bw);
                out.close();
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                try {
                    out.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public static void main(String[] args) {
        byte [] br = new byte [50];
        FileInputStream in = null ;
        FileOutputStream out = null ;
//Я так и не понял зачем нам тут коллекция
//        ArrayList<InputStream> al = new ArrayList<>();
//        Enumeration<InputStream> en = Collections.enumeration(al);

        myFileCreate(50,5);

//      1. Прочитать файл (около 50 байт) в байтовый массив и вывести этот массив в консоль;
        try {
            in = new FileInputStream( "1.txt" );
            int count = in.read(br);
            System.out.println( "Прочитано " + count + " байт" );
            System.out.println(Arrays.toString(br));
            in.close();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                in.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

//      2. Последовательно сшить 5 файлов в один (файлы примерно 100 байт).
        try {
            out = new FileOutputStream("Frankenstein.txt");
            for(int i=0;i<5;i++) {
                in = new FileInputStream((i+1)+".txt");
                byte[] buffer = in.readAllBytes();
                out.write(buffer);
                in.close();
            }
            out.close();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                in.close();
                out.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
