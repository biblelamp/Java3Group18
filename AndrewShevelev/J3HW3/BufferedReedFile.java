import java.io.*;
import java.util.Scanner;

public class BufferedReedFile {
    public static void BufferedReedFile(String fileName) {
        File reedSize = new File(fileName);
        Scanner sc = new Scanner(System.in);
        String nextPage = "";
        int i = 0;
        int j;
        do {
            byte[] br = new byte[1800];
            try (RandomAccessFile raf = new RandomAccessFile(fileName, "r")) {
                raf.seek(i);
                raf.read(br);
                System.out.println(new String(br));

            } catch (IOException e) {
                e.printStackTrace();
            }
            System.out.println("Press key 'enter' next page. Press 'exit' to exit  ... "+ i + " from " + reedSize.length());
            nextPage = sc.nextLine();
            if (nextPage.equals("exit")) break;
            i = i + 1800;

        }while ((int)reedSize.length()- i >= 0);


    }
}
