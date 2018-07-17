/**
 * Java 3. Home Work 3.
 *
 * @author Albert Khayrutdinov
 * @version dated Jul 15, 2018
 * @link https://github.com/bertranus
 */
 
import java.io.IOException;
import java.io.InputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.File;
import java.io.SequenceInputStream;
import java.util.List;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Enumeration;
import java.util.Collections;

class J3HW3 {
    final static String FILE_NAME = "1.txt";
    final static int FIRST_SIZE = 50;
    final static int SECOND_SIZE = 100;
    final static int FILES_COUNT = 5;
    final static String SUMM_FILE_NAME = (FILES_COUNT + 1) + ".txt";
    
    public static void main(String[] args) {
        fCreate(FIRST_SIZE, FILE_NAME);
        System.out.println("Readed byte array: " + 
        Arrays.toString(fRead(FILE_NAME, FIRST_SIZE))); //1. Прочитать файл (около 50 байт) в байтовый массив и вывести этот массив в консоль;
        File[] files = new File[FILES_COUNT];
        for (int i = 1; i < FILES_COUNT + 1; i++) {
            String fName = i + ".txt";
            fCreate(SECOND_SIZE, fName);
            files[i-1] = new File(fName);
        }
        for (File file: files){ //Последовательно сшить 5 файлов в один (файлы примерно 100 байт);
            fWrite(fRead(file.getName(), SECOND_SIZE), SUMM_FILE_NAME, true);
        }
        System.out.println("Readed byte array: " + 
        Arrays.toString(fRead(SUMM_FILE_NAME, (SECOND_SIZE * FILES_COUNT))));
        
        otherMethod(); //Последовательно сшить 5 файлов в один (файлы примерно 100 байт). Другой способ;
    }

    static byte[] fRead(String fileName, int size) {
        byte[] readArray = new byte[size];
        try (FileInputStream in = new FileInputStream(fileName)) {
            in.read(readArray);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        return readArray;
    }

    static void fCreate(int count, String fileName) {
        byte[] writeArray = new byte[count];
        for (int i = 0; i < count; i++) {
            writeArray[i] = (byte)(Math.random()*100);
        }
        //System.out.println("Created byte array: " + Arrays.toString(writeArray));
        fWrite(writeArray, fileName, false);
    }

    static void fWrite(byte[] writeArray, String fileName, boolean append) {
        try (FileOutputStream out = new FileOutputStream(fileName, append)) {
            out.write(writeArray);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    static void otherMethod() {
        List<InputStream> al = new ArrayList<>();
        SequenceInputStream seq = null;
        try (FileInputStream in1 = new FileInputStream("1.txt");
                FileInputStream in2 = new FileInputStream("2.txt");
                FileInputStream in3 = new FileInputStream("3.txt");
                FileInputStream in4 = new FileInputStream("4.txt");
                FileInputStream in5 = new FileInputStream("5.txt");
                FileOutputStream out = new FileOutputStream("6-other.txt")) {
            al.add(in1);
            al.add(in2);
            al.add(in3);
            al.add(in4);
            al.add(in5);
            Enumeration<InputStream> list = Collections.enumeration(al);
            seq = new SequenceInputStream(list); //instead (in1, in2);
            int rb = seq.read();
            while (rb != -1) {
                out.write(rb);
                rb = seq.read();
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try { seq.close(); } catch ( IOException e ) { };
        }
    }
}