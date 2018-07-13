import java.sql.*;
import java.util.Arrays;

class DB {
    static Connection connection = null;
    final private String DRIVER_NAME = "org.sqlite.JDBC";
    final private String DB_NAME = "jdbc:sqlite:sqlite.db";
    private String TABLE_NAME;
    private int SIZE;
    
    DB () {
        getConnection();
    }

     public String createTable(String TABLE_NAME) {
        this.TABLE_NAME = TABLE_NAME;
        try (Statement stmt = connection.createStatement()) {
            stmt.executeUpdate(
                    "DROP TABLE IF EXISTS " + TABLE_NAME + ";" +
                            "CREATE TABLE " + TABLE_NAME +
                            "(id INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL," +
                            " prodid INTEGER NOT NULL," + 
                            " title VARCHAR(15) NOT NULL," +
                            " cost INTEGER NOT NULL);"
            );
        } catch (SQLException e) {
            e.printStackTrace();
            return "Failed";
        }
        return "Done";
    }

    public String add(int prodid, String title, int cost) {
        try (Statement stmt = connection.createStatement()) {
            stmt.executeUpdate("INSERT INTO " + TABLE_NAME +
                    " (prodid, title, cost) " +
                    " VALUES (" + prodid + ", '" + title + "', " + cost + ");"
            );
        } catch (SQLException e) {
            e.printStackTrace();
            return "Failed";
        }
        return "Done";
    }

    public String add(int[] prodid, String[] title, int[] cost) {
        try (Statement stmt = connection.createStatement()) {
            if ((prodid.length == title.length) && (prodid.length == cost.length)) {
                for (int i = 0; i < prodid.length; i++) {
                    stmt.executeUpdate("INSERT INTO " + TABLE_NAME +
                    " (prodid, title, cost) " +
                    " VALUES (" + prodid[i] + ", '" + title[i] + "', " + cost[i] + ");"
                    );
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return "Failed";
        }
        return "Done";
    }

    public String delete(String title) {
        try (Statement stmt = connection.createStatement()) {
            stmt.executeUpdate("DELETE FROM " + TABLE_NAME +
                    " WHERE title='" + title + "';");
        } catch (SQLException e) {
            e.printStackTrace();
            return "Failed";
        }
        return "Done";
    }

    public String update(String title, String cost) { // using PreparedStatement
        try (PreparedStatement ps = connection.prepareStatement(
                "UPDATE " + TABLE_NAME + " SET cost=? WHERE title=?;")) {
            ps.setString(1, cost);
            ps.setString(2, title);
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            return "Failed";
        }
        return "Done";
    }

    public String list(String title) {
        String message = "No such product";
        try (Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM " + TABLE_NAME + 
            " WHERE title='" + title + "';")) {
            while (rs.next())
                message = 
                rs.getString("id") + "\t" +
                    rs.getString("prodid") + "\t" +
                    rs.getString("title") + "\t" +
                        rs.getString("cost");
        } catch (SQLException e) {
             e.printStackTrace();
             return message;
        }
        return message;
    }

    public String list(String num1, String num2) {
        String message = "";
        try (Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM " + TABLE_NAME + 
            " WHERE cost BETWEEN '" + num1 + "' AND '" + num2 + "';")) {
            while (rs.next())
                message += 
                (rs.getString("id") + "\t" +
                    rs.getString("prodid") + "\t" +
                    rs.getString("title") + "\t" +
                        rs.getString("cost") + "\n");
        } catch (SQLException e) {
             e.printStackTrace();
             return "Failed";
        }
        return message;
    }

    private Connection getConnection() {
        if (connection == null)
            try {
                Class.forName(DRIVER_NAME);
                connection = DriverManager.getConnection(DB_NAME);
            } catch (Exception e) {
                e.printStackTrace();
            }
        return connection;
    }

    public void closeConnection() {
        if (connection != null)
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
    }
}