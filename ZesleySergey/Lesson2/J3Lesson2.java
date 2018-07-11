import java.sql.*;
import java.util.Scanner;

/**
 * Java. Level 3. Lesson 2
 * Simple server for chat
 *
 * @author Sergey Zesley
 * @version 0.3.3 dated July 11, 2018
 * @link https://github.com/SergeyZes/Java3Group18
 */

public class J3Lesson2 {
    final String DRIVER_NAME="org.sqlite.JDBC";
    final String DB_NAME="jdbc:sqlite:sqlite.db";
    final String TABLE_NAME="Goods";
    final int GOODS_IN_DB=200; //ставлю 200, а не 10000, чтоб не подвисало

    Scanner sc=new Scanner(System.in);


    Connection getConnection() {
        if(connection==null)
        try {
            Class.forName(DRIVER_NAME);
            connection=DriverManager.getConnection(DB_NAME);
        } catch (Exception ex){
            ex.printStackTrace();
        }
        return connection;
    }

    void closeConnection(){
        if(connection!=null){
            try {
                if(!connection.isClosed()) connection.close();
            } catch (SQLException ex){
                ex.printStackTrace();
            }
        }
    }

    void createTable(){
        try(Statement stmt=connection.createStatement()){
            stmt.clearBatch();
            stmt.addBatch("DROP TABLE IF EXISTS "+TABLE_NAME+";");
            stmt.addBatch("CREATE TABLE "+TABLE_NAME+"(id INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL,"+
                    "prodid INTEGER UNIQUE NOT NULL,"+
                    "title CHAR(12) NOT NULL,"+
                    "cost INTEGER NOT NULL"+
                    ");");
            stmt.executeBatch();

        } catch (SQLException ex){
            ex.printStackTrace();
        }
    }


    void insert10000Goods(){
        try (PreparedStatement ps=connection.prepareStatement(
                "INSERT INTO "+TABLE_NAME+" (prodid, title, cost) values (?,?,?);")){

            for (int i=1;i<=GOODS_IN_DB;i++)
            {
              ps.setInt(1,i);
              ps.setString(2,"товар"+i);
              ps.setInt(3,i*10);
              ps.addBatch();
            }
            ps.executeBatch();
        } catch (SQLException ex){
            ex.printStackTrace();
        }

    }


    Connection connection=null;

    public static void main(String[] args) {

        new J3Lesson2();
    }
    J3Lesson2(){
        getConnection();

        System.out.println("Создаем БД...");

        //Задание 1
        createTable();

        //Задание 2
        insert10000Goods();

        //Задание 3,4,5
        for (;;){
            commandRequest();
            String inp=sc.nextLine();
            int cost=0;
            int cost2=0;
            if(inp.equalsIgnoreCase("/exit")) break;

            String[] parts=inp.split("\\s+");
            if(parts.length!=2 && parts.length!=3){
                System.out.println("Нарушен синтаксис.");
                continue;
            }

            if (parts.length==2 && parts[0].equalsIgnoreCase("/цена")){
                showGoodPrice(parts[1]);
            } else if (parts.length==3 && parts[0].equalsIgnoreCase("/сменитьцену")){
                try {
                    cost = Integer.parseInt(parts[2]);
                }catch (Exception E){
                    System.out.println("Нарушен синтаксис.");
                    continue;
                }
                changeGoodPrice(parts[1],cost);
            } else if (parts.length==3 && parts[0].equalsIgnoreCase("/товарыпоцене")){
                try {
                    cost = Integer.parseInt(parts[1]);
                    cost2 = Integer.parseInt(parts[2]);
                    showGoodsInDiapazon(cost,cost2);
                }catch (Exception E){
                    System.out.println("Нарушен синтаксис.");
                    continue;
                }

            } else
            {
                System.out.println("Нарушен синтаксис.");

            }


        }

        closeConnection();

    }

    void commandRequest(){
        System.out.println("");
        System.out.println("Введите одну из следующих комманд:");
        System.out.println("/цена <имя товара>");
        System.out.println("/сменитьцену <имя товара> <новая цена>");
        System.out.println("/товарыпоцене <нижняя цена> <верхняя цена>");
        System.out.println("/exit");
    }

    void showGoodPrice(String title){
        try (PreparedStatement ps=connection.prepareStatement(
                "SELECT cost FROM "+TABLE_NAME+" WHERE title=?;")){

            ps.setString(1,title);
            ResultSet resultSet=ps.executeQuery();

            if (resultSet.next()){
                System.out.println("Цена товара: "+resultSet.getInt(1));
                return;
            }

            System.out.println("Такого товара нет.");

        } catch (SQLException ex){
            ex.printStackTrace();
        }
    }

    void showGoodsInDiapazon(int cost1,int cost2){
        try (PreparedStatement ps=connection.prepareStatement(
                "SELECT title,cost FROM "+TABLE_NAME+" WHERE cost between ? and ? ORDER BY cost;")){

            ps.setInt(1,cost1);
            ps.setInt(2,cost2);
            ResultSet resultSet=ps.executeQuery();

            while (resultSet.next()){
                System.out.println(resultSet.getString(1)+" по цене "+resultSet.getInt(2));
            }


        } catch (SQLException ex){
            ex.printStackTrace();
        }

    }

    void changeGoodPrice(String title,int newprice){
        try (PreparedStatement ps=connection.prepareStatement(
                "UPDATE "+TABLE_NAME+" SET cost=? WHERE title=?;")){

            ps.setInt(1,newprice);
            ps.setString(2,title);

            int ra=ps.executeUpdate();

            if (ra==0) System.out.println("Такого товара нет."); else System.out.println("Цена изменена.");

        } catch (SQLException ex){
            ex.printStackTrace();
        }

    }

}
