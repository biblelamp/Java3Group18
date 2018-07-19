import java.io.FileWriter;
import java.io.IOException;

public class WriteFileThreadsClass {

    static final int THREADS_NUM = 3;
    static final int STRINGS_NUM = 10;
    static final int WRITE_PERIOD = 20;

    public static void main(String[] args) {
        for (int i = 0; i < THREADS_NUM; i++) {
            new Thread(() -> writeString()).start();
        }
    }

    static synchronized void writeString(){
        for(int i = 0; i < STRINGS_NUM; i++){
            try(FileWriter writer = new FileWriter("output.txt",true)){
                writer.write(Thread.currentThread().getName() + ":" + i + "\r\n");
            }
            catch (IOException ex){
                ex.printStackTrace();
            }
            try{
                WriteFileThreadsClass.class.wait(WRITE_PERIOD);
            }
            catch (InterruptedException ex){
                ex.printStackTrace();
            }
        }
    }
}
