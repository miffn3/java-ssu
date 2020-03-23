package bank.repository.iface;

import bank.entity.Operation;

import java.sql.SQLException;
import java.util.List;

public interface OperationRepository {
    Operation getOperationById(String id) throws SQLException;
    List<Operation> getOperationByFromAccountId(String accountId) throws SQLException;
    List<Operation> getOperationByToAccountId(String accountId) throws SQLException;
    void insertOperation(Operation operation) throws SQLException;
    void deleteOperations() throws SQLException;
}
