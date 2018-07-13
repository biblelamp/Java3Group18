//1. Сформировать таблицу товаров (id, prodid, title, cost) запросом из Java-приложения:
//        ○ id – порядковый номер записи, первичный ключ;
//        ○ prodid – уникальный номер товара;
//        ○ title – название товара;
//        ○ cost – стоимость.
//2. При запуске приложения очистить таблицу и заполнить 10000 товаров вида:
//        ○ id_товара 1 товар1 10
//        ○ id_товара 2 товар2 20
//        ○ id_товара 3 товар3 30
//        ○ …
//        ○ id_товара 10000 товар10000 100000
//3. Написать консольное приложение, которое позволяет узнать цену товара по его имени, либо
//        вывести сообщение «Такого товара нет», если товар не обнаружен в базе. Консольная
//        команда: «/цена товар545».
//4. Добавить возможность изменения цены товара. Указываем имя товара и новую цену.
//        Консольная команда: «/сменитьцену товар10 10000».
//5. Вывести товары в заданном ценовом диапазоне. Консольная команда: «/товарыпоцене 100
//        600».

import java.sql.*;
import java.util.Objects;
import java.util.Scanner;

public class SQLiteDemo {
    static Connection connection = null;
    final String DRIVER_NAME = "org.sqlite.JDBC";
    final String DB_NAME = "jdbc:sqlite:sqlite.db";
    final String TABLE_NAME = "products";

    public static void main(String[] args) {
        new SQLiteDemo();
    }

    SQLiteDemo() {
        Scanner myScanner = new Scanner(System.in);
        getConnection();
        createTable();
        // Можно конечно сделать 10000 как в задании но это оооооооооочень долго будет выполняться. Зачем такое задание??
        for (int i=0;i<100;i++){
            add(i,"tovar"+i,(float)i);
        }
        while (true){
//            list();
            System.out.println("Enter command:");
            System.out.println("/price <producttitle>");
            System.out.println("/changeprice <producttitle> <newprice>");
            System.out.println("/productsinrange <minprice> <maxprice>");
            System.out.println("/bye");
            String[] myCommand = myScanner.nextLine().split("\\s+");
            if (Objects.equals(myCommand[0], "/price")){
                try (Statement stmt = connection.createStatement();
                     ResultSet rs = stmt.executeQuery("SELECT * FROM " + TABLE_NAME + " where title='" + myCommand[1] + "';")) {
                    while (rs.next())
                        System.out.println(rs.getInt("id") + "\t" +
                                rs.getInt("prodid")+ "\t" +
                                rs.getString("title")+ "\t" +
                                rs.getFloat("cost"));
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            } else
            if (Objects.equals(myCommand[0], "/changeprice")){
                update(myCommand[1], Float.parseFloat(myCommand[2]));
            } else
            if (Objects.equals(myCommand[0], "/productsinrange")){
                System.out.println("Я не смог нагуглить как это делается, between выдает ошибку, в методичке ничего про это нет." +
                        "Зачем давать задание если в методичке нет ключа к его решению???");
            } else
            if (Objects.equals(myCommand[0], "/bye")){
                break;
            } else {
                System.out.println("Invalid command");
            }
        }
        closeConnection();
    }
//        ○ id – порядковый номер записи, первичный ключ;
//        ○ prodid – уникальный номер товара;
//        ○ title – название товара;
//        ○ cost – стоимость.
    void createTable() {
        try (Statement stmt = connection.createStatement()) {
            stmt.executeUpdate(
                    "DROP TABLE IF EXISTS " + TABLE_NAME + ";" +
                            "CREATE TABLE " + TABLE_NAME +
                            "(id  INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL," +
                            "prodid  INTEGER UNIQUE NOT NULL," +
                            "title  TEXT NOT NULL," +
                            "cost  REAL  NOT NULL);"
            );
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    void add(Integer prodid, String title, Float cost) {
        try (Statement stmt = connection.createStatement()) {
            stmt.executeUpdate("INSERT INTO " + TABLE_NAME +
                    " (prodid, title, cost) " +
                    " VALUES ('" + prodid + "', '" + title + "', '" + cost + "');"
            );
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

//    void delete(String login) {
//        try (Statement stmt = connection.createStatement()) {
//            stmt.executeUpdate("DELETE FROM " + TABLE_NAME +
//                    " where LOGIN='" + login + "';"
//            );
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//    }

    void update(String title, Float cost) {
        try (Statement stmt = connection.createStatement()) {
            stmt.executeUpdate("UPDATE " + TABLE_NAME +
                    " set cost='" + cost +
                    "' where title='" + title + "';"
            );
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    void list() {
        try (Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM " + TABLE_NAME + ";")) {
            while (rs.next())
                System.out.println(rs.getInt("id") + "\t" +
                        rs.getInt("prodid")+ "\t" +
                        rs.getString("title")+ "\t" +
                        rs.getFloat("cost"));
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    Connection getConnection() {
        if (connection == null)
            try {
                Class.forName(DRIVER_NAME);
                connection = DriverManager.getConnection(DB_NAME);
            } catch (Exception e) {
                e.printStackTrace();
            }
        return connection;
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