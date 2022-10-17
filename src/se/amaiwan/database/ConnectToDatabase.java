package se.amaiwan.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ConnectToDatabase {
    private static String hostAddress =  "jdbc:mysql://localhost:3306/votedb";
    private static String username = "root";
    private static String password = "";

    public ConnectToDatabase() throws ClassNotFoundException {
        Class.forName("com.mysql.jdbc.Driver");
    }
    public static Connection createConnection() {
        Connection conn = null;
        try {
            conn = DriverManager.getConnection(hostAddress, username, password);
        } catch (SQLException ex) {
            Logger.getLogger(ConnectToDatabase.class.getName()).log(Level.SEVERE, null, ex);
        }
        return conn;
    }
}