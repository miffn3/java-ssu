package bank.repository.impl;

import bank.connection.DBConnection;
import bank.entity.User;
import bank.repository.iface.UserRepository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.UUID;

public class UserRepositoryImpl implements UserRepository {
    @Override
    public User getUserById(String id) throws SQLException {
        String query = "select * from user where id = ?";
        Connection con = DBConnection.getConnection();
        PreparedStatement stmt = con.prepareStatement(query);
        stmt.setString(1, id);

        ResultSet rs = stmt.executeQuery();

        if(rs.next()) {
            String login = rs.getString("login");
            String password = rs.getString("password");
            String address = rs.getString("address");
            String phone = rs.getString("phone");
            return new User(UUID.fromString(id), login, password, address, phone);
        } else {
            return null;
        }
    }

    @Override
    public void updateUser(User user) throws SQLException {
        String query = "update user set login=?, password=?, address=?, phone=? where id=?";
        Connection con = DBConnection.getConnection();
        PreparedStatement stmt = con.prepareStatement(query);
        stmt.setString(1, user.getLogin());
        stmt.setString(2, user.getPassword());
        stmt.setString(3,user.getAddress());
        stmt.setString(4,user.getPhone());
        stmt.setString(5, user.getId().toString());
        stmt.executeUpdate();
        stmt.close();
    }

    @Override
    public void insertUser(User user) throws SQLException {
        String query = "insert into user values(?,?,?,?,?)";
        Connection con = DBConnection.getConnection();
        PreparedStatement stmt = con.prepareStatement(query);

        stmt.setString(1, user.getId().toString());
        stmt.setString(2, user.getLogin());
        stmt.setString(3, user.getPassword());
        stmt.setString(4,user.getAddress());
        stmt.setString(5,user.getPhone());
        boolean res = stmt.execute();
        stmt.close();
    }

    public void deleteUsers() throws SQLException {
        String query = "delete from user";
        Connection con = DBConnection.getConnection();
        PreparedStatement stmt = con.prepareStatement(query);
        stmt.executeUpdate();
        stmt.close();
    }
}
