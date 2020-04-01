package bank.service.iface;

import bank.entity.Operation;

import java.util.List;

public interface OperationService {
	String addOperation(Operation operation);
	List<String> getHistory(String login);
}
