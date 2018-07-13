/**
 * Java. Level 3. Lesson 2. JDBC example
 *
 *  Note:
 *  a) Download latest version of sqlite-jdbc-(VER).jar
 *  from https://bitbucket.org/xerial/sqlite-jdbc/downloads
 *  b) Put this jar into [JDK]\jre\lib\ext
 *  or (in IDEA) <Ctrl>+<Alt>+<Shift>+s -> Libraries -> +
 *  or use Maven (pom.xml)
 *
 * @author Ghermas Denis
 * @version Jul 13, 2018
 * @adsress https://github.com/firstmessage
 */
import java.sql.*;
import java.util.Scanner;

public class SQLiteDZ {
    static Connection connection = null;
    final String DRIVER_NAME = "org.sqlite.JDBC";
    final String DB_NAME = "jdbc:sqlite:sqlite.db";
    final String TABLE_NAME = "goods";


    public static void main(String[] args) {
       new SQLiteDZ();
    /*

    не удалось по времени запустить этот код
        while (!m.equals("EXIT"))
        {
            System.out.println("Введие комманду:");
            m = scanner.nextLine();
            message = m.split(" ");

            if (message[0].charAt(0)!='/') { System.out.println("Wrong command"); }
            else if (message[0].equals("/цена")) {

                System.out.println(message[1]);
                A.search(message[1].trim());

            }
            else if (message[0].equals("/сменитьцену")) {

                A.updateGoods(message[1].trim(),message[2].trim());

            }
            else if (message[0].equals("/товарыпоцене")) {

                A.search(message[1].trim(),message[2].trim());

            }

        } ;
        */

    }

    SQLiteDZ() {
        getConnection();
        createTable();
        for (int i = 1; i <= 10000; i++) {
            add("id_goods "+String.valueOf(i),"goods"+String.valueOf(i),String.valueOf(i*10));
        }

        search("goods10") ;
        search("goods0");
        updateGoods("goods10","1000");
        search("goods10") ;
        search("10","150") ;

        closeConnection();
    }

    void createTable() {
        try (Statement stmt = connection.createStatement()) {
            stmt.executeUpdate(
                    "DROP TABLE IF EXISTS " + TABLE_NAME + ";" +
                            "CREATE TABLE " + TABLE_NAME +
                            "(" +
                            "id  integer PRIMARY KEY AUTOINCREMENT NOT NULL," +
                            "prodid TEXT NOT NULL," +
                            "title TEST NOT NULL," +
                            "cost integer NOT NULL);"
            );
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    void add(String  prodid, String title, String cost) {
        try (Statement stmt = connection.createStatement()) {
            stmt.executeUpdate("INSERT INTO " + TABLE_NAME +
                    " (prodid, title, cost) " +
                    " VALUES ('" + prodid + "', '" + title +"', '" + cost + "');"
            );
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    void search(String title) {
        int i = 0;
        try (Statement stmt = connection.createStatement() ;
             ResultSet rs = stmt.executeQuery("SELECT * FROM " + TABLE_NAME +
                     " WHERE title = '"+title+"';")
        ) {

            while (rs.next()) {
                System.out.println(rs.getString("id")
                        + "\t" + rs.getString("prodid")
                        + "\t" + rs.getString("title")
                        + "\t" + rs.getString("cost")
                );
                ++i;
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        if ( i==0) System.out.println("Такого товара нет") ;

    }

    void search(String cost1,String cost2) {

        int i = 0;
        try (Statement stmt = connection.createStatement() ;
             ResultSet rs = stmt.executeQuery("SELECT * FROM " + TABLE_NAME +
                     " WHERE "+
                     " cost >= "+cost1+
                     " AND cost <= "+cost2+
                     ";"
             )
        ) {

            while (rs.next()) {
                System.out.println(rs.getString("id")
                        + "\t" + rs.getString("prodid")
                        + "\t" + rs.getString("title")
                        + "\t" + rs.getString("cost")
                );
                ++i;
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        if ( i==0) System.out.println("Такого товара нет с такими ценами "+cost1+" < > "+cost2) ;

    }

    void delete(String login) {
        try (Statement stmt = connection.createStatement()) {
            stmt.executeUpdate("DELETE FROM " + TABLE_NAME +
                    " where LOGIN='" + login + "';"
            );
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    void updateGoods(String title, String cost) { // using PreparedStatement

        try (PreparedStatement ps = connection.prepareStatement(
                "UPDATE " + TABLE_NAME + " set cost=? where title=?;")) {
            ps.setString(1, cost);
            ps.setString(2, title);
            ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
        
    }

    void list() {
        try (Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM " + TABLE_NAME + ";")) {
            while (rs.next())
                System.out.println(rs.getString("id")
                        + "\t"+rs.getString("prodid")
                        + "\t"+rs.getString("title")
                        + "\t"+rs.getString("cost")
                );
        } catch (Exception e) {
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
            } catch (Exception e) {
                e.printStackTrace();
            }
    }
}