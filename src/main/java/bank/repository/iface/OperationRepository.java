package bank.repository.iface;

import bank.entity.Operation;

import java.sql.SQLException;

public interface OperationRepository {
    Operation getOperationById(String id) throws SQLException;
    Operation insertOperation(Operation operation) throws SQLException;
    void deleteOperations() throws SQLException;
}
