import com.sun.deploy.util.ArrayUtil;

import java.io.File;
import java.sql.*;
import java.util.Arrays;

/**
 * Java. Level 3. Lesson 6
 *
 * @author Sergey Zesley
 * @version 0.0.1 dated July 25, 2018
 * @link https://github.com/SergeyZes/Java3Group18
 */


public class J3Lesson6 {
    final String DRIVER_NAME="org.sqlite.JDBC";
    final String DB_NAME="jdbc:sqlite:sqlite.db";
    final String TABLE_NAME="Students";

    public J3Lesson6() {

        boolean isExists=new File("sqlite.db").exists();
        getConnection();
        if (!isExists) createTable();
    }

    Connection connection=null;
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

    int insertStudent(String surname,int rating){
        try (PreparedStatement ps=connection.prepareStatement(
                "INSERT INTO "+TABLE_NAME+" (surname, rating) values (?,?);",Statement.RETURN_GENERATED_KEYS)){

            ps.setString(1,surname);
            ps.setInt(2,rating);

            if (ps.executeUpdate()!=1) return -1;

            try(ResultSet keys=ps.getGeneratedKeys()){
                if(keys.next()){
                    return keys.getInt(1);
                } else
                {
                    throw new SQLException("No ID obtained");
                }

            }


        } catch (SQLException ex){
            ex.printStackTrace();
            return -1;
        }

    }

    String readStudent(int id){
        try (PreparedStatement ps=connection.prepareStatement(
                "SELECT surname FROM "+TABLE_NAME+" WHERE id=?;")){

            ps.setInt(1,id);
            ResultSet resultSet=ps.executeQuery();

            if (resultSet.next()){
                return resultSet.getString(1);
            }
            return null;


        } catch (SQLException ex){
            ex.printStackTrace();
            return null;
        }

    }

    boolean updateStudent(int id,String surname,int rating){
        try (PreparedStatement ps=connection.prepareStatement(
                "UPDATE "+TABLE_NAME+" SET surname=?,rating=? WHERE id=?;")){

            ps.setString(1,surname);
            ps.setInt(2,rating);
            ps.setInt(3,id);

            return ps.executeUpdate()==1;


        } catch (SQLException ex){
            ex.printStackTrace();
            return false;
        }

    }

    boolean deleteStudent(int id){
        try (PreparedStatement ps=connection.prepareStatement(
                "DELETE FROM "+TABLE_NAME+" WHERE id=?;")){

            ps.setInt(1,id);

            return ps.executeUpdate()==1;


        } catch (SQLException ex){
            ex.printStackTrace();
            return false;
        }

    }

    void createTable(){
        try(Statement stmt=connection.createStatement()){
            stmt.clearBatch();
            stmt.addBatch("DROP TABLE IF EXISTS "+TABLE_NAME+";");
            stmt.addBatch("CREATE TABLE "+TABLE_NAME+"(id INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL,"+
                    "surname CHAR(12) NOT NULL,"+
                    "rating INTEGER NOT NULL"+
                    ");");
            stmt.executeBatch();

        } catch (SQLException ex){
            ex.printStackTrace();
        }
    }

    public int[] getArrayAfter4(int[] arr){
        if(arr==null) return null;
        int n=arr.length;
        int i=0;
        if(n==0) return null;

        for (i=(n-1); i>=0; i--){
            if(arr[i]==4) return Arrays.copyOfRange(arr,i+1,n);
        }
        return null;

    }

    public boolean check1_4inArray(int[] arr){
        if(arr==null) return false;

        if (Arrays.binarySearch(arr,1)<0) return false;
        if (Arrays.binarySearch(arr,4)<0) return false;

        return true;
    }
}
