package bank.connection;

import java.sql.DriverManager;
import java.sql.SQLException;

public class Connection {
    private static java.sql.Connection connection;
    private static final String USER = "test";
    private static final String PASSWORD = "test";
    private static final String URL = "jdbc:mysql://localhost:3306/BANK?createDatabaseIfNotExist=true";

    public static boolean createConnection() {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            connection = DriverManager.getConnection(URL, USER, PASSWORD);
            return true;
        } catch (ClassNotFoundException | SQLException e) {
            return false;
        }
    }

    public static java.sql.Connection getConnection() {
        return connection;
    }

    public static void closeConnection() {
        try {
            connection.close();
        } catch (SQLException e) {

        }
    }
}
