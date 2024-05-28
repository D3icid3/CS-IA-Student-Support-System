package MVC.Utility;

import java.sql.*;

public class DbHelper {
    private Connection connection;
    private static DbHelper INSTANCE;

    public void init(){
        try {
             connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/jdbc-ia", "root", "@Heineken033"); //establishes connection to MYSQL
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static DbHelper getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new DbHelper();
            INSTANCE.init();
        }
        return INSTANCE;
    }

    public Connection getConnection() {return connection;}

    /**
     * used to close statements
     * @param stmt
     */
    public static void close(Statement stmt) {
        if (stmt != null) {
            try {
                stmt.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * used to close result sets
     * @param rs
     */
    public static void close(ResultSet rs) {
        if (rs != null) {
            try {
                rs.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
