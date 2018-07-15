import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Scanner;

/**
 * Java. Level 3. Lesson 3
 * Simple server for chat
 *
 * @author Sergey Zesley
 * @version 0.0.1 dated July 15, 2018
 * @link https://github.com/SergeyZes/Java3Group18
 */
public class J3Lesson3 {
    public static final int PAGE_LENGTH=1800;
    public static final long READ_TIMEOUT_MILIS=5000;

    public static void main(String[] args) {
	// write your code here

        //Задание 1
        byte[] b1=readToArray("fileForTask1.txt");
        System.out.println("Задание 1");
        System.out.println("Массив байт из файла fileForTask1.txt:");
        System.out.println(Arrays.toString(b1));

        //Задание 2
        Task2();

        //Задание 3
        Task3();


    }
    static void commandRequest(){
        System.out.println("");
        System.out.println("Введите одну из следующих комманд:");
        System.out.println("<номер страницы>");
        System.out.println("exit");
    }

    static byte[] readToArray(String fname){
        byte[] res=null;

        try {
            res= Files.readAllBytes(Paths.get(fname));

        } catch (IOException e){
            e.printStackTrace();
        }

        return res;
    }

    static void Task2(){
        ArrayList<InputStream> streams=new ArrayList<>();

        try {
            streams.add(new FileInputStream("1.txt"));
            streams.add(new FileInputStream("2.txt"));
            streams.add(new FileInputStream("3.txt"));
            streams.add(new FileInputStream("4.txt"));
            streams.add(new FileInputStream("5.txt"));

        } catch (Exception e){
            e.printStackTrace();
        }

        try (FileOutputStream os=new FileOutputStream("12345.txt");
             SequenceInputStream seq=new SequenceInputStream(Collections.enumeration(streams))){
            int rb=seq.read();
            while (rb!=-1){
                os.write(rb);
                rb=seq.read();
            }

        } catch (Exception e){
            e.printStackTrace();
        }

    }

    static void Task3(){
        Scanner sc=new Scanner(System.in);
        WorkWithFile worker=new WorkWithFile("RandomAccessFile.txt");

        for (;;){
            commandRequest();
            String inp=sc.nextLine();
            String pageText=null;
            int page=0;
            if(inp.equalsIgnoreCase("exit")) break;

            try {
                page = Integer.parseInt(inp);
            }catch (Exception E){
                System.out.println("Нарушен синтаксис.");
                continue;
            }

            pageText=worker.readFrom((page-1)*PAGE_LENGTH,READ_TIMEOUT_MILIS);

            if(pageText==null || pageText.isEmpty()) System.out.println("-ничего не прочитано-"); else{
                System.out.println("содержимое страницы: ");
                System.out.println();
                System.out.print(pageText);
                System.out.println("");
            }

        }
    }


}

class WorkWithFile{
    private String path;
    private RandomAccessFile file;
    public WorkWithFile(String path){
        this.path=path;
    }

    public String readFrom(int numberSymbol,long timeoutMilis)  {
        // открываем файл для чтения

        long startTimeMilis = System.currentTimeMillis();

        try {
            file = new RandomAccessFile(path, "r");
            StringBuilder sb=new StringBuilder("");

            // ставим указатель на нужный вам символ
            file.seek(numberSymbol);
            int b = file.read();
            int cnt=0;

            // побитово читаем и добавляем символы в строку
            while(b != -1){
                sb.append((char)b);
                b = file.read();
                if(++cnt>=J3Lesson3.PAGE_LENGTH) break;

                if (System.currentTimeMillis()-startTimeMilis>timeoutMilis) return null;
            }
            file.close();

            return sb.toString();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                file.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return null;
    }

}
