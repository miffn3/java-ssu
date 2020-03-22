package bank.repository.iface;

import bank.entity.User;

import java.sql.SQLException;
import java.util.List;

public interface UserRepository {
    User getUserById(String id) throws SQLException;
    User updateUser(User user) throws SQLException;
    User insertUser(User user) throws SQLException;
    void deleteUsers() throws SQLException;
}
