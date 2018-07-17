/**
 * Java 3. Home Work 3.3.
 *
 * @author Albert Khayrutdinov
 * @version dated Jul 15, 2018
 * @link https://github.com/bertranus
 */
import java.io.IOException;
import java.io.FileReader;
import java.util.Arrays;

class J3HW33 {
    public static void main(String[] args) {
        int size = 1800;
        String fileName = "test.bin";
        readFile(fileName, size);
    }
    
    static void readFile(String fileName, int size) {
        long t1 = System.currentTimeMillis();
        char[] chars = new char[size];
        try (FileReader file = new FileReader(fileName)) {
            int j;
            while ((j = file.read()) != -1) {
                file.read(chars);
                String str = new String(chars);
                System.out.println(str);
                break;// You can remove to display the entire file;
            }
            if (j == -1) return;
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        
        long t2 = System.currentTimeMillis();
        System.out.println("Reading and printing one page took " + (t2 - t1) + " mc");
    }
}
