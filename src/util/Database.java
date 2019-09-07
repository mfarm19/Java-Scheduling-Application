package util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author Michael Farmer
 */

//Username, Password, and DB URL removed
public class Database {
    private static final String DB_NAME = "---";
    private static final String DB_URL = "---" + DB_NAME;
    private static final String USERNAME = "---";
    private static final String PASSWORD = "---";
    private static final String DRIVER = "com.mysql.jdbc.Driver"; 
    private static Connection conn;
    
    public Database() {}
    
    // Connects to the Database
    public static void makeConnection() {
        try {
            Class.forName(DRIVER);
            conn = DriverManager.getConnection(DB_URL, USERNAME, PASSWORD);
            System.out.println("Connected to MySQL Database");
        } catch (ClassNotFoundException e) {
            System.out.println("Class Not Found " + e.getMessage());
        } catch (SQLException e) {
            System.out.println("SQLException: " + e.getMessage()); 
            System.out.println("SQLState: " + e.getSQLState());
            System.out.println("VendorError: " + e.getErrorCode());
        }
    }
    
    // Closes the Database Connection
    public static void disconnect() {
        try {
            conn.close();
            System.out.println("Disconnected From MySQL Database");
        } catch (SQLException e) {
            System.out.println("SQLException: " + e.getMessage());
        }
    }
    
    // Returns Database Connection
    public static Connection getConnection() {
        return conn;
    }
}
