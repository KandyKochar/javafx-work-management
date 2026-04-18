package workManagement;

import java.sql.*;

public class DBconnection {

    public static final String DRIVER_CLASS_ORACLE = "oracle.jdbc.driver.OracleDriver";
    public static final String URL = "jdbc:oracle:thin:@calvin.humber.ca:1521:grok";
    public static final String USER_NAME = "N01743348";
    public static final String PASSWORD = "oracle";

    public static Connection getConnection() {
        Connection conn = null;

        try {
            Class.forName(DRIVER_CLASS_ORACLE);
            conn = DriverManager.getConnection(URL, USER_NAME, PASSWORD);
            System.out.println("Database connected successfully.");
        } catch (Exception e) {
            System.out.println("Connection failed.");
            e.printStackTrace();
        }

        return conn;
    }

    public static void closeConnection(Connection conn) {
        try {
            if (conn != null && !conn.isClosed()) {
                conn.close();
                System.out.println("Connection closed.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}