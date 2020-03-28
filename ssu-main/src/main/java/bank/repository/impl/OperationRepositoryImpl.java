package bank.repository.impl;

import bank.connection.DBConnection;
import bank.entity.Account;
import bank.entity.Operation;
import bank.repository.iface.OperationRepository;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.UUID;

public class OperationRepositoryImpl implements OperationRepository {
    @Override
    public Operation getOperationById(String id) throws SQLException {
        String query = "select * from operation where id = ?";
        Connection con = DBConnection.getConnection();
        PreparedStatement stmt = con.prepareStatement(query);
        stmt.setString(1, id);

        ResultSet rs = stmt.executeQuery();

        if(rs.next()) {
            String date = rs.getString("date");
            String currency = rs.getString("currency");
            String accountFrom = rs.getString("account_from");
            String accountTo = rs.getString("account_to");
            BigDecimal amount = rs.getBigDecimal("amount");
            BigDecimal moneyBefore = rs.getBigDecimal("money_before");
            BigDecimal moneyAfter= rs.getBigDecimal("money_after");
            return new Operation(UUID.fromString(id), date, currency, accountFrom, accountTo, amount, moneyBefore, moneyAfter);
        } else {
            return null;
        }
    }

    @Override
    public List<Operation> getOperationByFromAccountId(String accountId) throws SQLException {
        List<Operation> operations = new ArrayList<>();
        String query = "select * from operation where account_from = ?";
        Connection con = DBConnection.getConnection();
        PreparedStatement stmt = con.prepareStatement(query);
        stmt.setString(1, accountId);
        ResultSet rs = stmt.executeQuery();
        while(rs.next()){
            String id = rs.getString("id");
            String date = rs.getString("date");
            String currency = rs.getString("currency");
            String accountTo = rs.getString("account_to");
            BigDecimal amount = rs.getBigDecimal("amount");
            BigDecimal moneyBefore = rs.getBigDecimal("money_before");
            BigDecimal moneyAfter= rs.getBigDecimal("money_after");
            Operation operation = new Operation(UUID.fromString(id), date, currency, accountId,
                    accountTo, amount, moneyBefore, moneyAfter);
            operations.add(operation);
        }
        if (operations.size() == 0) {
            return Collections.emptyList();
        }
        return operations;
    }

    @Override
    public List<Operation> getOperationByToAccountId(String accountId) throws SQLException {
        List<Operation> operations = new ArrayList<>();
        String query = "select * from operation where account_to = ?";
        Connection con = DBConnection.getConnection();
        PreparedStatement stmt = con.prepareStatement(query);
        stmt.setString(1, accountId);
        ResultSet rs = stmt.executeQuery();
        while(rs.next()){
            String id = rs.getString("id");
            String date = rs.getString("date");
            String currency = rs.getString("currency");
            String accountFrom = rs.getString("account_from");
            BigDecimal amount = rs.getBigDecimal("amount");
            BigDecimal moneyBefore = rs.getBigDecimal("money_before");
            BigDecimal moneyAfter= rs.getBigDecimal("money_after");
            Operation operation = new Operation(UUID.fromString(id), date, currency, accountFrom,
                    accountId, amount, moneyBefore, moneyAfter);
            operations.add(operation);
        }
        if (operations.size() == 0) {
            return Collections.emptyList();
        }
        return operations;
    }

    @Override
    public void insertOperation(Operation operation) throws SQLException {
        String query = "insert into operation values(?,?,?,?,?,?,?,?)";
        Connection con = DBConnection.getConnection();
        PreparedStatement stmt = con.prepareStatement(query);

        stmt.setString(1, operation.getId().toString());
        stmt.setString(2, operation.getDate());
        stmt.setString(3, operation.getCurrency());
        stmt.setString(4, operation.getAccountFrom());
        stmt.setString(5, operation.getAccountTo());
        stmt.setBigDecimal(6, operation.getAmount());
        stmt.setBigDecimal(7, operation.getMoneyBefore());
        stmt.setBigDecimal(8, operation.getMoneyAfter());
        boolean res = stmt.execute();
        stmt.close();
    }

    @Override
    public void deleteOperations() throws SQLException {
        String query = "delete from operation";
        Connection con = DBConnection.getConnection();
        PreparedStatement stmt = con.prepareStatement(query);
        stmt.executeUpdate();
        stmt.close();
    }
}
