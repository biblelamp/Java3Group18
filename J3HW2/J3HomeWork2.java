/**
 * Java 3 Home Work2
 *
 * @author Andrew Shevelev
 * @version jul 12, 2018
 * <p>
 * https://github.com/ShevelevAndrew
 */

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class J3HomeWork2 {
    static final String DRIVER_NAME = "org.sqlite.JDBC";
    static final String DB_NAME = "jdbc:sqlite:goods.db";
    final String TABLE_NAME = "products";
    final String COL_ID = "id";
    final String COL_TITLE = "title";
    final String COL_COST = "cost";
    final int CEL_TITLE = 10000;

    final String CREATE_TABLE =
            "DROP TABLE IF EXISTS " + TABLE_NAME + ";" +
                    "CREATE TABLE " + TABLE_NAME + "(" +
                    COL_ID + " INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL," +
                    COL_TITLE + " TEXT," + COL_COST + " REAL );";
    final String CLEAR_TABLE =
            "DELETE FROM " + TABLE_NAME + ";" +
                    "DELETE FROM sqlite_sequence WHERE name='" + TABLE_NAME + "'";
    final String INSERT_REC = "INSERT INTO " + TABLE_NAME +
            " (" + COL_TITLE + ", " + COL_COST + ") VALUES (?, ?);";
    final String SELECT_REC = "SELECT * FROM " + TABLE_NAME + " WHERE title=?;";
    final String UPDATE_REC = "UPDATE " + TABLE_NAME + " SET cost=? WHERE title=?;";
    final String LIST_IN_RANGE = "SELECT * FROM " + TABLE_NAME + " WHERE cost>=? AND cost<=?";

    Connection connect;
    Statement stmt;

    public static void main(String[] args) {
        J3HomeWork2 hw = new J3HomeWork2(DRIVER_NAME, DB_NAME);
        String inpCommand;
        System.out.println("###############");
        System.out.println("# create \n# table_init \n# getcost \n# setcost \n# goodsByPrice \n# exit");
        System.out.println("###############");

        Scanner scCommand = new Scanner(System.in);
        do {
            System.out.println("Input command:");
            System.out.print("#> ");
            inpCommand = scCommand.nextLine();

            switch (inpCommand) {
                case "create": {
                    hw.createTable();
                    break;
                }
                case "table_init": {
                    hw.initTable();
                    break;
                }
                case "getcost": {
                    String inpCost;
                    float cost;
                    System.out.println("Input name of product:");
                    System.out.print("#>");
                    inpCost = scCommand.nextLine();
                    cost = hw.getPriceByName(inpCost);
                    System.out.println((cost < 0) ? "Product not fond" : cost);
                    break;
                }
                case "setcost": {
                    System.out.println("Input name of product and cost:");
                    System.out.print("#>");
                    String[] inp = scCommand.nextLine().split(" ");
                    hw.setPriceByName(inp[0], Float.parseFloat(inp[1]));
                    break;
                }
                case "goodsByPrice": {
                    System.out.println("Input cost to cost:");
                    System.out.print("#>");
                    String[] inp = scCommand.nextLine().split(" ");
                    if (inp[1] != " ") {
                        for (String prod : hw.getListInRange(Float.parseFloat(inp[0]), Float.parseFloat(inp[1]))) {
                            System.out.println(prod);
                        }
                    }
                    break;
                }

                default: {
                    if (inpCommand.equals("exit"))
                        System.out.println("Bye! Good luke");
                    break;
                }
            }
        } while (!inpCommand.equals("exit"));

    }


    J3HomeWork2(String driverName, String dbName) {
        connect = null;
        stmt = null;
        try {
            Class.forName(driverName);
            connect = DriverManager.getConnection(dbName);
            stmt = connect.createStatement();
        } catch (ClassNotFoundException | SQLException ex) {
            ex.printStackTrace();
        }
    }

    private void createTable() {
        try {
            stmt.executeUpdate(CREATE_TABLE);
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    private void initTable() {
        try {
            int j = 1000;
            stmt.executeUpdate(CLEAR_TABLE);
            PreparedStatement pstmt = connect.prepareStatement(INSERT_REC);
            for (int i = 1; i <= CEL_TITLE; i++) {
                pstmt.setString(1, "product" + i);
                pstmt.setFloat(2, i * 10);
                pstmt.executeUpdate();
                if (j == i) {
                    System.out.println("add rec: " + i + " from " + CEL_TITLE);
                    j = j + 1000;
                }
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    private float getPriceByName(String inpCost) {

        float cost = -1;
        try {
            PreparedStatement pstmt = connect.prepareStatement(SELECT_REC);
            pstmt.setString(1, inpCost);
            ResultSet rs = pstmt.executeQuery();
            while (rs.next())
                cost = rs.getFloat(COL_COST);
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return cost;
    }

    private void setPriceByName(String prodName, float cost) {
        try {
            PreparedStatement pstmt = connect.prepareStatement(UPDATE_REC);
            pstmt.setFloat(1, cost);
            pstmt.setString(2, prodName);
            pstmt.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }

    }

    private List<String> getListInRange(float cost1, float cost2) {
        List<String> list = new ArrayList<>();
        try {
            PreparedStatement pstmt = connect.prepareStatement(LIST_IN_RANGE);
            pstmt.setFloat(1, cost1);
            pstmt.setFloat(2, cost2);
            ResultSet rs = pstmt.executeQuery();
            while (rs.next())
                list.add(
                        rs.getInt(COL_ID) + "\t" +
                                rs.getString(COL_TITLE) + "\t" +
                                rs.getFloat(COL_COST));
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return list;
    }
}
