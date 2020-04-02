package bank.connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class DBConnection {
    private static java.sql.Connection connection;
    private static final String USER = "test";
    private static final String PASSWORD = "test";
    private static final String URL = "jdbc:mysql://localhost:3306/?useUnicode=yes&useSSL=false" +
            "&createIfNotExists=true";

    public static boolean createConnection() {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            connection = DriverManager.getConnection(URL, USER, PASSWORD);
            PreparedStatement stmt = connection.prepareStatement("DROP DATABASE IF EXISTS bank");
            stmt.execute();
            stmt = connection.prepareStatement("CREATE DATABASE bank");
            stmt.execute();
            stmt = connection.prepareStatement("USE bank");
            stmt.execute();
            return true;
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public static Connection getConnection() {
        return connection;
    }

    public static void closeConnection() {
        try {
            connection.close();
        } catch (SQLException e) {

        }
    }

    public static boolean createTables() {
        String queryAccount =
                "CREATE TABLE account " +
                "(id VARCHAR(50) NOT NULL, " +
                "client_id VARCHAR(50) NOT NULL, " +
                "amount Decimal NOT NULL, " +
                "acc_code VARCHAR(50) NOT NULL, " +
                "PRIMARY KEY (id))";

        String queryUser = "CREATE TABLE user " +
                "(id VARCHAR(50) NOT NULL, " +
                "login VARCHAR(50) NOT NULL, " +
                "password VARCHAR(50) NOT NULL, " +
                "address VARCHAR(50) NOT NULL, " +
                "phone VARCHAR(50) NOT NULL, " +
                "PRIMARY KEY (id))";

        String queryOperation = "CREATE TABLE operation " +
                "(id VARCHAR(50) NOT NULL, " +
                "date VARCHAR(50) NOT NULL, " +
                "currency VARCHAR(50) NOT NULL, " +
                "account_from VARCHAR(50) NOT NULL, " +
                "account_to VARCHAR(50) NOT NULL, " +
                "amount Decimal NOT NULL, " +
                "money_before Decimal NOT NULL, " +
                "money_after Decimal NOT NULL, " +
                "PRIMARY KEY (id))";

        Connection con = DBConnection.getConnection();
        try {
            PreparedStatement stmt = con.prepareStatement(queryAccount);
            stmt.execute();
            stmt = con.prepareStatement(queryUser);
            stmt.execute();
            stmt = con.prepareStatement(queryOperation);
            stmt.execute();
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }
}
