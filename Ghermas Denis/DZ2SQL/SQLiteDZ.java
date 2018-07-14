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
    static Statement stmt; // = null;
    static ResultSet resSet;

    final static String DRIVER_NAME = "org.sqlite.JDBC";
    final static String DB_NAME = "jdbc:sqlite:sqlite.db";
    final static String TABLE_NAME = "goods";

    public static void createTable() {
        try  {
            //stmt = connection.createStatement();
            stmt.executeUpdate(
                    "DROP TABLE IF EXISTS " + TABLE_NAME + ";" +
                            "CREATE TABLE " + TABLE_NAME +
                            "(" +
                            "id  integer PRIMARY KEY AUTOINCREMENT NOT NULL," +
                            "prodid TEXT NOT NULL," +
                            "title TEST NOT NULL," +
                            "cost integer NOT NULL);"
            );
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public static void add(String  prodid, String title, String cost) {
        try {
            stmt.executeUpdate("INSERT INTO " + TABLE_NAME +
                    " (prodid, title, cost) " +
                    " VALUES ('" + prodid + "', '" + title +"', '" + cost + "');"
            );
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void search(String title) {
        int i = 0;
        try (//Statement stmt = connection.createStatement() ;
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
            return;
        }
        if ( i==0) System.out.println("Такого товара нет") ;

    }

    public static void search(String cost1,String cost2) {

        int i = 0;
        try (// stmt = connection.createStatement() ;
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

    public static void updateGoods(String title, String cost) { // using PreparedStatement

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
        try (//Statement stmt = connection.createStatement();
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

    public static void getConnection() {
        if (connection == null)
            try {
                Class.forName(DRIVER_NAME).newInstance();
                connection = DriverManager.getConnection(DB_NAME);
                stmt = connection.createStatement();
            } catch (Exception e) {
                e.printStackTrace();
                //return null;
            }
        //return connection;
    }

    public static void closeConnection() {
        if (connection != null)
            try {
                connection.close();
             //   stmt.close();
             //   resSet.close();

            } catch (Exception e) {
                e.printStackTrace();
            }
    }
}