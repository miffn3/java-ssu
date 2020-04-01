package bank.service.impl;

import bank.entity.Operation;
import bank.repository.iface.OperationRepository;
import bank.service.iface.OperationService;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class OperationServiceImpl implements OperationService {
	private OperationRepository operationRepository;

	public OperationServiceImpl(OperationRepository operationRepository) {
		this.operationRepository = operationRepository;
	}

	@Override
	public void addOperation(String date, String currency, String accountFrom, String accountTo, BigDecimal amount,
	                           BigDecimal moneyBefore, BigDecimal moneyAfter) {
		Operation operation = new Operation(UUID.randomUUID(), date, currency, accountFrom, accountTo, amount,
				moneyBefore, moneyAfter);
		try {
			operationRepository.insertOperation(operation);
		} catch (Exception ex) {
			ex.printStackTrace();
			return;
		}
	}

	@Override
	public List<String> getHistory(String accountId) {
		List<Operation> operations;
		try {
			operations = operationRepository.getOperationByFromAccountId(accountId);
		} catch (Exception ex) {
			return null;
		}
		if (operations == null)
			return null;

		System.out.println(operations.size());
		List<String> result = new ArrayList<>();

		for (Operation operation : operations) {
			result.add(operation.toString());
		}
		return result;
	}
}
