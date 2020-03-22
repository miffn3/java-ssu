package bank.repository.iface;

import bank.entity.Account;

import java.sql.SQLException;
import java.util.List;

public interface AccountRepository {
    Account getAccountById(String id) throws SQLException;
    List<Account> getAccountsByClientId(String id) throws SQLException;
    void updateAccount(Account account) throws SQLException;
    void insertAccount(Account account) throws SQLException;
    void deleteAccounts() throws SQLException;
}
