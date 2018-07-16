import java.io.*;
import java.lang.reflect.Array;
import java.util.Arrays;

/**
 * Java 3. Lesson 3.
 *
 * @author Ghermas Denis / Гермаш Денис
 * @version dated Jul 16, 2018
 * @link https://github.com/firstmessage/Java3Group18
 *
 *
 * 1. Прочитать файл (около 50 байт) в байтовый массив и вывести этот массив в консоль;
 * 2. Последовательно сшить 5 файлов в один (файлы примерно 100 байт). Может пригодиться
 *
 */



public class J3Lesson3 {

    static File file0 = new File("src\\samle0.txt");
    static File fileOut = new File("src\\samleOut.txt");

    static File file[] = new File[5] ;

    public static void main(String[] args) {
        byteReadFromFile();
        addFileOne();
    }

    public static void byteReadFromFile() {

        if (file0.exists()==true) {
            System.out.println("file exists");

            try (FileInputStream in = new FileInputStream(file0);){

              //  System.out.printf("File size: %d bytes \n", in.available());
                byte[] br = new byte[in.available()];
                in.read(br);
                System.out.println(Arrays.toString(br));
                in.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        else {System.out.println("file NOT exists");}
    }

    public static void addFileOne() {
        boolean Exists = true;

        FileOutputStream out = null;
        FileInputStream in = null;


        file[0] = new File("src\\samle1.txt");
        file[1] = new File("src\\samle2.txt");
        file[2] = new File("src\\samle3.txt");
        file[3] = new File("src\\samle4.txt");
        file[4] = new File("src\\samle5.txt");



        for (int i = 0; i < 5; i++) {
            if (file[i].exists()!=true) {
                Exists=false;
                System.out.println("File NOT exists "+ file[i].toString());
            }
        }


        if (Exists) {

            try {
                for (int i = 0; i < file.length; i++) {
                    in = new FileInputStream(file[i]);
                    byte[] fileBufer  =  new byte[in.available()];
                    in.read(fileBufer);
                    in.close();
                    out = new FileOutputStream(fileOut.getAbsoluteFile(),true);
                    out.write(fileBufer);
                    out.close();
                }

                //int count = in.read(br);
                //System.out.println("Прочитано " + count + " байт");

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


            System.out.println("files exists");

        }
        else {System.out.println("file NOT exists");}
    }
}
