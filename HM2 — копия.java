package Java3;

import java.sql.*;
import java.util.Properties;
import java.util.Scanner;
import java.util.logging.Logger;

/**
 * @auth Azamat
 * @ver 13.07.18
 * @comment Я не уверен насчет правильности всего этого, не могу установить sqlite,
 *          и еще, по названию товара - я устанавливал title+cost, как это показывалось в условии дз,
 *          то есть имя товара это gift10, gift20... и тд
 *
 *
 */

public class HM2 {
    static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) throws SQLException {
        int id =1;
        int cost = 10;
        String title = "gift";
        Connection con = null;
        reg();//регистрация драйвера
        connection(con);// коннект, установил прдуманный url, это же не страшно для дз?
        statements(con);// создание таблицы
        add(con, id,cost, title);// добавление товаров
        takeCost(con,scanner);// работа с товарами
        con.close();
    }
    static void reg() throws SQLException {
        try {
            Class.forName("org.sqlite.JDBC");
            java.sql.DriverManager.registerDriver(new Driver() {
                @Override
                public Connection connect(String url, Properties info) throws SQLException {
                    return null;
                }

                @Override
                public boolean acceptsURL(String url) throws SQLException {
                    return false;
                }

                @Override
                public DriverPropertyInfo[] getPropertyInfo(String url, Properties info) throws SQLException {
                    return new DriverPropertyInfo[0];
                }

                @Override
                public int getMajorVersion() {
                    return 0;
                }

                @Override
                public int getMinorVersion() {
                    return 0;
                }

                @Override
                public boolean jdbcCompliant() {
                    return false;
                }

                @Override
                public Logger getParentLogger() throws SQLFeatureNotSupportedException {
                    return null;
                }
            });
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
    static void connection (Connection connection) throws SQLException {
        connection = DriverManager.getConnection("jdbs:sqlite:dbname.db");
    }
    static void statements (Connection con) throws SQLException {
        Statement statement = con.createStatement();
        statement.executeUpdate("CREATE TABLE IF NOT EXISTS Goods(" +
                "id INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL," +
                "prodid INTEGER UNIQE NOT NULL," +
                "title TEXT NOT NULL," +
                "cost INTEGER NOT NULL );" +
                "DELETE FROM Goods WHERE id>0;");

    }
    static void add(Connection con, int id, int cost, String title) throws SQLException {
        PreparedStatement ps = con.prepareStatement("INSERT INTO Goods(id, title, cost) VALUES (?, ?, ?);");
        for (int i = 0; i < 20; i++) {// не 10000, но смысл тот же самый вроде
            ps.addBatch(id+" "+title+cost+" "+ cost);// не уверен насчет правильности метода, к сожалению нет возможности воспользоваться sqlite
            id++;
            cost+= 10;
        }
    }
    static void takeCost(Connection connection, Scanner sc){
        String tc = sc.nextLine();
        if(tc.startsWith("/")) {
            if (tc.startsWith("/цена")) {
                String title = sc.nextLine();
                //int cost = sc.nextInt();
                try {
                    Statement statement = connection.createStatement();
                    ResultSet rs = statement.executeQuery("SELECT FROM Goods WHERE title = " + title);
                    System.out.println(rs);
                } catch (SQLException e) {
                    System.out.println("Товара нет в списке");
                    e.printStackTrace();
                }
            }
            else if (tc.startsWith("/сменитьцену")){
                String title = sc.nextLine();
                int cost = sc.nextInt();
                try {
                    Statement statement = connection.createStatement();
                    int upd= statement.executeUpdate("UPDATE Goods SET cost = "+cost+" WHERE title = "+ title);
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            else if (tc.startsWith("/товары по цене")){
                int cost1 = sc.nextInt();
                int cost2 = sc.nextInt();
                for (int i = cost1; i <cost2 ; i++) {
                    try {
                        Statement statement = connection.createStatement();
                        ResultSet rs = statement.executeQuery("SELECT FROM Goods WHERE cost = " + i);
                        System.out.println(rs);
                    } catch (SQLException e) {
                        System.out.println("Товара нет в списке");
                        e.printStackTrace();
                    }
                }
            }
        }
    }


}
