package bank.repository.iface;

import bank.entity.User;

import java.sql.SQLException;

public interface UserRepository {
    User getUserByPhone(String phone) throws SQLException;
    User getUserByLogin(String login) throws SQLException;
    User getUserById(String id) throws SQLException;
    void updateUser(User user) throws SQLException;
    void insertUser(User user) throws SQLException;
    void deleteUsers() throws SQLException;
}
