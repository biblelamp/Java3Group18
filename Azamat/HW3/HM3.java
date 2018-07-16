package Java3;

import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Enumeration;
import java.util.List;

/**
 * @AUTHOR Azamat
 * @ver 16/07/18
 * Честно говоря, вроде ошибку не выдает , но мне не кажется все это правильным.... надо еще раз во всем разобраться
 */

public class HM3 implements Serializable {
    static ByteArrayOutputStream out = null;
    static ByteArrayInputStream in = null;

    static File file = new File("C:\\Users\\azama\\Desktop\\lol.txt");
    public static void main(String[] args) {
         FileInputStream fileInputStream = null;
         FileOutputStream fileOutputStream = null;
         ObjectInputStream objectInputStream = null;
         ObjectOutputStream objectOutputStream = null;
  //      file_Reader();// ну, вроде работает
        lister(file,file,file,fileOutputStream, fileOutputStream,fileOutputStream,fileInputStream,fileInputStream,fileInputStream);

    }
   static void file_Reader(ObjectOutputStream objectOutputStream){
        try {
            out = new ByteArrayOutputStream();
            objectOutputStream = new ObjectOutputStream(out);
            objectOutputStream.writeObject(file);
            byte[] byteArr = out.toByteArray();
            out.close();
            objectOutputStream.close();
            in = new ByteArrayInputStream(byteArr);
//            objectInputStream = new ObjectInputStream(in);
            in.close();
           // objectInputStream.close();
            System.out.println(in.read());

        } catch (IOException e) {
            e.printStackTrace();
        }
   }
   static void lister(File file, File file1, File file2, FileOutputStream fileOutputStream, FileOutputStream fileOutputStream1, FileOutputStream fileOutputStream2, FileInputStream fileInputStream, FileInputStream fileInputStream1, FileInputStream fileInputStream2){
        file1 = new File("C:\\Users\\azama\\Desktop\\lol.txt");
        file = new File ("C:\\Users\\azama\\Desktop\\lol.txt");
        file2 = new File ("C:\\Users\\azama\\Desktop\\lol.txt");
       List<Object> list = new ArrayList<>();// это я пытался своим способом сделать
       list.add(file.toString());
       list.add(file1.toString());
       list.add(file2.toString());
       try {
           fileOutputStream = new FileOutputStream(file);
           fileOutputStream1 = new FileOutputStream(file1);
           fileOutputStream2 = new FileOutputStream(file2);
           fileOutputStream.close();
           fileOutputStream2.close();
           fileOutputStream1.close();
           ArrayList<FileInputStream> arrayList= new ArrayList<>();
           //fileInputStream = new FileInputStream();
           arrayList.add(fileInputStream);
           arrayList.add(fileInputStream1);
           arrayList.add(fileInputStream2);
           Enumeration<FileInputStream> enumeration = Collections.enumeration(arrayList);
//           fileInputStream.close();
//           fileInputStream1.close();
//           fileInputStream2.close();

           System.out.println(enumeration);


       } catch (IOException e) {
           e.printStackTrace();
       }
   }
}
