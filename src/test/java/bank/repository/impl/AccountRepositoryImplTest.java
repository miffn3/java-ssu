package bank.repository.impl;

import bank.connection.DBConnection;
import bank.entity.AccCode;
import bank.entity.Account;
import bank.repository.iface.AccountRepository;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.List;
import java.util.UUID;

import static org.junit.Assert.assertEquals;


public class AccountRepositoryImplTest {

    private static boolean setUpIsDone = false;
    private static AccountRepository accountRepo;

    @BeforeClass()
    public static void setUp() {
        if (!setUpIsDone) {
            boolean createConnection = DBConnection.createConnection();
            boolean createTables = DBConnection.createTables();
            if(!createConnection) {
                throw new RuntimeException("Can't create connection, stop");
            }
            if(!createTables) {
                throw new RuntimeException("Can't create tables, stop");
            }
            accountRepo = new AccountRepositoryImpl();
            setUpIsDone = true;
        }
    }

    @AfterClass
    public static void close() {
        if (setUpIsDone) {
            DBConnection.closeConnection();
        }
    }

    @Before()
    public void clear() {
        try {
            accountRepo.deleteAccounts();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    @Test
    public void insertAccount() {
        AccountRepository repo = new AccountRepositoryImpl();
        String id = UUID.randomUUID().toString();
        String clientId = UUID.randomUUID().toString();
        BigDecimal amount = BigDecimal.valueOf(5000);
        String accCode = AccCode.RUB.toString();
        Account account = new Account(UUID.fromString(id), clientId, amount, accCode);
        List<Account> accounts = null;

        try {
            repo.insertAccount(account);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        try {
            accounts = repo.getAccountsByClientId(clientId);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        Account accountTmp = null;
        if (accounts != null) {
            accountTmp = accounts.get(0);
        }

        assertEquals(account, accountTmp);
    }
}