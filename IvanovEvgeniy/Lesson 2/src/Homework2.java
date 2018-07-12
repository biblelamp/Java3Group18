/**
 * Java. Level 3. Homework 2.
 *
 * @author Stalker1290
 * @version dated Jul 12,2018
 * @link https://github.com/Stalker1290
 */

import java.sql.*;
import java.util.Scanner;

public class Homework2 {
    static Connection connection = null;
    final String DRIVER_NAME = "org.sqlite.JDBC";
    final String DB_NAME = "jdbc:sqlite:products.db";
    final String TABLE_NAME = "products";
    final int TABLE_SIZE = 10000;

    public static void main(String[] args) {
        new Homework2();
    }

    Homework2(){
        boolean exit = false;
        Scanner scanner = new Scanner(System.in);

        getConnection();
        createTable();
        while(!exit){
            System.out.println("Введите команду:");
            String inLine = scanner.nextLine();
            if(inLine.charAt(0) == '/'){
                String inArr[] = inLine.split(" ");
                switch (inArr[0]){
                    case "/цена":
                        printPriceByName(inArr[1]);
                        break;
                    case "/сменитьцену":
                        changePriceByName(inArr[1],Float.parseFloat(inArr[2]));
                        break;
                    case "/товарыпоцене":
                        printNameByPrice(Float.parseFloat(inArr[1]), Float.parseFloat(inArr[2]));
                        break;
                    case "/выход":
                        exit = true;
                        break;
                }
            }
        }
        closeConnection();
    }

    void printPriceByName(String name){
        try(PreparedStatement prpstmt = connection.prepareStatement("SELECT cost FROM " + TABLE_NAME + " WHERE title = ?")){
            boolean noProduct = true;
            prpstmt.setString(1,name);
            ResultSet rs = prpstmt.executeQuery();
            while(rs.next()){
                noProduct = false;
                System.out.println(rs.getFloat("cost"));
            }
            if(noProduct) System.out.println("Такого товара нет");

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    void changePriceByName(String name, float newPrice){
        try(PreparedStatement prpstmt = connection.prepareStatement("UPDATE " + TABLE_NAME + " SET cost = ? WHERE title = ?")){
            prpstmt.setFloat(1,newPrice);
            prpstmt.setString(2,name);
            prpstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    void printNameByPrice(float minPrice, float maxPrice){
        try(PreparedStatement prpstmt = connection.prepareStatement("SELECT * FROM " + TABLE_NAME + " WHERE cost>=? AND cost<=?")){

            prpstmt.setFloat(1,minPrice);
            prpstmt.setFloat(2,maxPrice);
            ResultSet rs = prpstmt.executeQuery();
            while(rs.next()){
                System.out.println(rs.getInt("id") + " " + rs.getInt("prodid") +
                        " " + rs.getString("title") + " " + rs.getFloat("cost"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    void createTable() {
        try (Statement stmt = connection.createStatement()) {
            stmt.executeUpdate(
                    "DROP TABLE IF EXISTS " + TABLE_NAME + ";" +
                            "CREATE TABLE " + TABLE_NAME +
                            "(id  INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL," +
                            " prodid INTEGER NOT NULL," +
                            " title TEXT NOT NULL," +
                            " cost REAL NOT NULL);"
            );

            PreparedStatement prpstmt = connection.prepareStatement("INSERT INTO " + TABLE_NAME +
                    " (prodid,title,cost) VALUES(?,?,?)");
            for(int i = 1; i <= TABLE_SIZE; i++){
                prpstmt.setInt(1,i);
                prpstmt.setString(2,"товар" + i);
                prpstmt.setInt(3,i*10);
                prpstmt.addBatch();
            }
            prpstmt.executeBatch();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    void getConnection() {
        if (connection == null)
            try {
                Class.forName(DRIVER_NAME);
                connection = DriverManager.getConnection(DB_NAME);
            } catch (Exception e) {
                e.printStackTrace();
            }
    }

    void closeConnection() {
        if (connection != null)
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
    }

}
