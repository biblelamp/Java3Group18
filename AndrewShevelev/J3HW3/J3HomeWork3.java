/**
 * Java 3 Home Work 3
 *
 * @author Andrew Shevelev
 * @version jul 15, 2018
 * <p>
 * https://github.com/ShevelevAndrew
 */


import java.io.*;
import java.util.*;

public class J3HomeWork3 {
    public static void main(String[] args) {
        inputFileBite();
        SequenceInputStream();
        BufferedReedFile.BufferedReedFile("page.txt");
    }


    private static void inputFileBite() {
        byte[] br = new byte[50];
        try (FileInputStream in = new FileInputStream("12345.bin")) {
            int count = in.read(br);
            System.out.println("Read " + count + " byte(s)");
            System.out.println(Arrays.toString(br));
        } catch (IOException ex) {
            ex.printStackTrace();
        }

    }

    private static void SequenceInputStream() {
        List<InputStream> al = new ArrayList<>();
        SequenceInputStream seq = null;
        try (FileInputStream in1 = new FileInputStream("1.txt");
             FileInputStream in2 = new FileInputStream("2.txt");
             FileInputStream in3 = new FileInputStream("3.txt");
             FileInputStream in4 = new FileInputStream("4.txt");
             FileInputStream in5 = new FileInputStream("5.txt");
             FileOutputStream out = new FileOutputStream("out.txt")) {
            al.add(in1);
            al.add(in2);
            al.add(in3);
            al.add(in4);
            al.add(in5);

            Enumeration<InputStream> list = Collections.enumeration(al);
            seq = new SequenceInputStream(list);
            int rb = seq.read();
            while (rb != -1) {
                out.write(rb);
                rb = seq.read();
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                seq.close();
            } catch (IOException e) {
            }
            ;
        }

    }

}
