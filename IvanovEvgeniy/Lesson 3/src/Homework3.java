/**
 * Java. Level 3. Homework 3.
 *
 * @author Stalker1290
 * @version dated Jul 16,2018
 * @link https://github.com/Stalker1290
 */

import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Enumeration;


public class Homework3 {

    public static void main(String[] args) {
        printFile("File1.txt");
        String fileNames[] = {"File1.txt", "File2.txt", "File3.txt", "File4.txt", "File5.txt"};
        sewFiles(fileNames, "output.txt");
    }

    private static void printFile(String fileName){

        File file = new File(fileName);
        byte inArr[] = new byte[(int) file.length()];

        try (InputStream in = new BufferedInputStream(new FileInputStream(fileName))) {
            in.read(inArr);
        } catch (IOException ex) {
            ex.printStackTrace();
        }

        System.out.print("Task 1. Content of the file " + fileName + ": ");
        for (byte bt : inArr) System.out.printf("0x%02X ", bt);
        System.out.println();
    }

    private static void sewFiles(String fileNames[], String outputFileName){

        ArrayList<InputStream> inStreamList = new ArrayList<>();
        for (String fileName : fileNames)
            try {
                inStreamList.add(new FileInputStream(fileName));
            } catch (IOException ex) {
                ex.printStackTrace();
            }

        Enumeration<InputStream> inStreamEnum = Collections.enumeration(inStreamList);

        try (SequenceInputStream seqInputStream = new SequenceInputStream(inStreamEnum);
             OutputStream outStream = new BufferedOutputStream(new FileOutputStream(outputFileName))) {
            int rb = seqInputStream.read();
            while (rb != -1) {
                outStream.write(rb);
                rb = seqInputStream.read();
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }

    }
}
