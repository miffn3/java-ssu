package bank.repository.iface;

import bank.entity.User;

import java.sql.SQLException;
import java.util.List;

public interface UserRepository {
    User getUserById(String id) throws SQLException;
    void updateUser(User user) throws SQLException;
    void insertUser(User user) throws SQLException;
    void deleteUsers() throws SQLException;
}
