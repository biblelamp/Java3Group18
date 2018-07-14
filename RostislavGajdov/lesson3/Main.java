/**
 * Java 3. Lesson 3. Homework
 *
 * @author Rostislav Gajdov / Ростислав Гайдов
 * @version dated Jul 15, 2018
 * @link https://github.com/rgajdov
 */
package ru.rgajdov.java3.lesson3;

import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Enumeration;
import java.util.List;

public class Main {

    private static void readFile() {
        FileInputStream inFile = null;
        System.out.println("Задание 1.");
        try {
            inFile = new FileInputStream("d://data.txt");
            byte[] byteArray = new byte[inFile.available()];
            int count = inFile.read(byteArray, 0, inFile.available());
            System.out.println("Считано из файла в массив: " + count + " байт");
            System.out.println("Содержимое массива:");
            for (byte array : byteArray) {
                System.out.print((char) array);
            }
            inFile.close();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (inFile != null) {
                    inFile.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private static void mergeFiles() throws IOException {
        String[] fileNames = {"d://input1.txt", "d://input2.txt", "d://input3.txt", "d://input4.txt", "d://input5.txt"};
        List<InputStream> al = new ArrayList<>();
        SequenceInputStream seq = null;
        System.out.println("\n\nЗадание 2.");

        try {
            FileInputStream[] inputFiles = new FileInputStream[fileNames.length];
            FileOutputStream outputFile = new FileOutputStream("d://output.txt");
            for (int i = 0; i < fileNames.length; i++) {
                inputFiles[i] = new FileInputStream(fileNames[i]);
                al.add(inputFiles[i]);
            }
            Enumeration<InputStream> list = Collections.enumeration(al);
            seq = new SequenceInputStream(list);
            int rb = seq.read();
            while (rb != -1) {
                outputFile.write(rb);
                rb = seq.read();
            }
            System.out.println("Файл output.txt сформирован!");
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (seq != null) {
                seq.close();
            }
        }
    }

    public static void main(String[] args) throws IOException {
	// write your code here
        readFile();
        mergeFiles();
    }
}
