package bank.repository.impl;

import bank.connection.DBConnection;
import bank.entity.User;
import bank.repository.iface.UserRepository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class UserRepositoryImpl implements UserRepository {
    @Override
    public User getUserById(String id) throws SQLException {
        return null;
    }

    @Override
    public User updateUser(User user) throws SQLException {
        return null;
    }

    @Override
    public User insertUser(User user) throws SQLException {
        return null;
    }

    public void deleteUsers() throws SQLException {
        String query = "delete from user";
        Connection con = DBConnection.getConnection();
        PreparedStatement stmt = con.prepareStatement(query);
        stmt.executeUpdate();
        stmt.close();
    }
}
