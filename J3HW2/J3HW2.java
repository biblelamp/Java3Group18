/**
 * Java 3. Home Work 2.
 *
 * @author Albert Khayrutdinov
 * @version dated Jul 13, 2018
 * @link https://github.com/bertranus
 */
 
import java.sql.*;
import java.util.Arrays;
import java.io.*;
import java.util.Scanner;

public class J3HW2 {
    final int SIZE = 10000;
    DB db = new DB ();
    final String CLIENT_PROMPT = "$ ";
    final String EXIT_COMMAND = "exit";
    final String SELECT_COMMAND = "/price";
    final String UPDATE_COMMAND = "/changeprice";
    final String SELECT_BETWEEN = "/pricebetween";
    
    public static void main(String[] args) {
        new J3HW2();
    }

    J3HW2() {
        db.createTable("product");
        db.delete("*");
        addAll();
        reader();
    }

    void addAll() {
        System.out.print("Start to filling the table...");
        int[] prodidArr = new int[SIZE];
        String[] titleArr = new String[SIZE];
        int[] costArr = new int[SIZE];
        for (int i = 0; i < SIZE; i++) {
            prodidArr[i] = i + 1;
            titleArr[i] = "product" + (i +1);
            costArr[i] = (i + 1) * 10;
        }
        System.out.println(db.add(prodidArr, titleArr, costArr));
    }

       void reader() {
        String message;
        System.out.println("Available commands: " + SELECT_COMMAND + " productN; " + SELECT_BETWEEN + " INT INT; " + UPDATE_COMMAND + " productN INT; " + EXIT_COMMAND);
        try (Scanner scanner = new Scanner(System.in)) {
            do {
                System.out.print(CLIENT_PROMPT);
                message = scanner.nextLine();
                analyzer(message);
            } while (!message.equals(EXIT_COMMAND));
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }

    void analyzer (String message) {
        if (message !=null) {
            String[] messageArr = message.split(" ");
            if (messageArr[0].equals(EXIT_COMMAND)) {
                db.closeConnection();
            } else if ((messageArr[0].equals(SELECT_COMMAND)) && 
            (messageArr.length == 2)) {
                System.out.println(db.list(messageArr[1]));
            } else if ((messageArr[0].equals(SELECT_BETWEEN)) && 
            (messageArr.length == 3) && (messageArr[1].matches("[-+]?\\d+")) && 
            (messageArr[2].matches("[-+]?\\d+"))) {
                System.out.println(db.list(messageArr[1], messageArr[2]));
            } else if ((messageArr[0].equals(UPDATE_COMMAND)) && 
            (messageArr.length == 3) && (messageArr[2].matches("[-+]?\\d+"))) {
                System.out.println(db.update(messageArr[1], messageArr[2]));
            }
        }
    }

}
