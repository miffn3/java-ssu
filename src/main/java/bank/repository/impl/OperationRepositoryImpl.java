package bank.repository.impl;

import bank.connection.DBConnection;
import bank.entity.Operation;
import bank.repository.iface.OperationRepository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class OperationRepositoryImpl implements OperationRepository {
    @Override
    public Operation getOperationById(String id) throws SQLException {
        return null;
    }

    @Override
    public Operation insertOperation(Operation operation) throws SQLException {
        return null;
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
